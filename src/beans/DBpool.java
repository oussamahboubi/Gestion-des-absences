package beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;






public class DBpool {
	private static DBpool instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/ecole";
	DBpool db;
	
	public int VerifyEnseignant(String username, String Password) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select id from enseignant where login=? and password=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setString(1,username);
			myStmt.setString(2, Password);
			myRs = myStmt.executeQuery();
			// process result set
			if(myRs.next()) {
				int id_pm = myRs.getInt("id");
				return id_pm;
			}
			else return 0;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public int VerifyEtudiant(String username, String Password) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select id from etudiant where login=? and password=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setString(1,username);
			myStmt.setString(2, Password);
			myRs = myStmt.executeQuery();
			// process result set
			if(myRs.next()) {
				int id_pm = myRs.getInt("id");
				return id_pm;
			}
			else return 0;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	
	
	
	public List<Groupe> getGroupes(int id_ens) throws Exception {
		List<Groupe> groupes = new ArrayList<Groupe>();
		List<GroupeHisto> histo = new ArrayList<GroupeHisto>();
		List<Seance> seances = new ArrayList<Seance>();
		int n=0;
		List<Student> students = new ArrayList<Student>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from groupe g,ensemble e,enseignant ens where g.id=e.id_groupe and e.id_enseignant=ens.id and ens.id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_ens);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id_grp = myRs.getInt("g.id");
				String grpe_name = myRs.getString("g.nom");
				db = DBpool.getInstance();
				students = db.getStudents(id_grp);
				histo = db.getGroupeHisto(id_grp);
				seances = db.getSeancesByEns(id_ens,id_grp);
				Groupe g = new Groupe(id_grp,grpe_name,students,histo,seances);
				groupes.add(g);
			}
			return groupes;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Groupe> getGroupess() throws Exception {
		List<Groupe> groupes = new ArrayList<Groupe>();
		List<GroupeHisto> histo = new ArrayList<GroupeHisto>();
		List<Seance> seances = new ArrayList<Seance>();
		int n=0;
		List<Student> students = new ArrayList<Student>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from groupe";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id_grp = myRs.getInt("id");
				String grpe_name = myRs.getString("nom");
				db = DBpool.getInstance();
				students = db.getStudents(id_grp);
				histo = db.getGroupeHisto(id_grp);
				Groupe g = new Groupe(id_grp,grpe_name,students,histo);
				groupes.add(g);
			}
			return groupes;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void addGroupe(String name) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into groupe (nom) values (?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, name);
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void addHist(GroupeHisto hist) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into groupe_histo (date_h,activite,id_groupe) values (curdate(),?,?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, hist.getActivite());
			myStmt.setInt(2, hist.getId_groupe());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void addEtudiant(Etudiant etudiant) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into etudiant(cne,nom,prenom,login,password,id_groupe) values (?,?,?,?,?,?) ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, etudiant.getCne());
			myStmt.setString(2, etudiant.getLastname());
			myStmt.setString(3 , etudiant.getFirstname());
			myStmt.setString(4, etudiant.getLogin());
			myStmt.setString(5, etudiant.getPassword());
			myStmt.setInt(6 , etudiant.getId_groupe() );
			
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public Groupe getGroupe(int id_s) throws Exception {
		List<GroupeHisto> histo = new ArrayList<GroupeHisto>();
		List<Module> modules = new ArrayList<Module>();
		int n=0;
		Groupe g=null;
		List<Student> students = new ArrayList<Student>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id_groupe = getIdGroupeStudent(id_s);
		try {
			myConn = getConnection();
			String sql = "select * from groupe where id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_groupe);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id_grp = myRs.getInt("id");
				String grpe_name = myRs.getString("nom");
				db = DBpool.getInstance();
				students = db.getStudents(id_groupe);
				histo = db.getGroupeHisto(id_groupe);
				modules = db.getModules(id_grp);
				g = new Groupe(id_grp,grpe_name,students,histo,modules,0);
			}
			return g;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Seance> getSeancesByEns(int id_ens,int id_grp) throws Exception {
		List<Seance> seances = new ArrayList<Seance>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from seance s,groupe g,module m where s.id_groupe=g.id and s.id_module=m.id and id_enseignant=? and id_groupe=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_ens);
			myStmt.setInt(2,id_grp);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("s.id");
				Date date = myRs.getDate("s.date");
				String module = myRs.getString("m.nom");
				String groupe = myRs.getString("g.nom");
				int appel = myRs.getInt("s.appel");
				String heure = heuretrans(myRs.getInt("s.num_heure"));
				int id_groupe = myRs.getInt("s.id_groupe");
				boolean b;
				if (appel==0) b=false;
				else b=true;
				Seance s = new Seance (id,heure , date , module , groupe,b,id_groupe);
				seances.add(s);
			}
			return seances;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Module> getModules(int id_groupe) throws Exception {
		List<Module> modules = new ArrayList<Module>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from groupe g,ensemble e,module m,enseignant ens where ens.id=e.id_enseignant and g.id=e.id_groupe and e.id_module=m.id and g.id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_groupe);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("m.id");
				String nom = myRs.getString("m.nom");
				String nb_Hours = myRs.getString("m.vol_horaire");
				String ens = myRs.getString("ens.nom");
				String ens2 = myRs.getString("ens.prenom");
				
				Module module = new Module(id,nom,nb_Hours,ens+" "+ens2);
				modules.add(module);
			}
			return modules;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Seance> getSeancesEns(int id_ens) throws Exception {
		List<Seance> seances = new ArrayList<Seance>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from seance s,groupe g,module m where s.id_groupe=g.id and s.id_module=m.id and id_enseignant=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_ens);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("s.id");
				Date date = myRs.getDate("s.date");
				String module = myRs.getString("m.nom");
				String groupe = myRs.getString("g.nom");
				int appel = myRs.getInt("s.appel");
				String heure = heuretrans(myRs.getInt("s.num_heure"));
				int id_groupe = myRs.getInt("s.id_groupe");
				boolean b;
				if (appel==0) b=false;
				else b=true;
				Seance s = new Seance (id,heure , date , module , groupe,b,id_groupe);
				seances.add(s);
			}
			return seances;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Seance> getDistinctSeances(int id_ens) throws Exception {
		List<Seance> seances = new ArrayList<Seance>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select  s.id,s.id_groupe,s.id_module,m.nom,g.nom from seance s,groupe g,module m where s.id_groupe=g.id and s.id_module=m.id and id_enseignant=? group by s.id_module,s.id_groupe";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_ens);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id_seance = myRs.getInt("s.id");
				String module = myRs.getString("m.nom");
				String groupe = myRs.getString("g.nom");
				int id_module = myRs.getInt("s.id_module");
				int id_groupe = myRs.getInt("s.id_groupe");
				Seance s = new Seance (id_seance,id_groupe,id_module ,module , groupe);
				seances.add(s);
			}
			return seances;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public static String heuretrans(int i){
		if (i==1) return "8h-10h";
		if (i==2) return "10h-12h";
		if (i==3) return "14h-16h";
		if (i==4) return "16h-18h";
		return "";
	}
	
	public List<Student> getStudents(int id_grpe) throws Exception {
		List<Student> students = new ArrayList<Student>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from etudiant where id_groupe=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_grpe);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstname = myRs.getString("prenom");
				String lastname = myRs.getString("nom");
				int id_grp = myRs.getInt("id_groupe");
				String cne = myRs.getString("cne");
				Student s = new Student (id , firstname , lastname , id_grp,cne);
				students.add(s);
			}
			return students;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public Student getStudent(int id_etudiant) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Student s=null;
		try {
			myConn = getConnection();
			String sql = "select * from etudiant where id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_etudiant);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstname = myRs.getString("prenom");
				String lastname = myRs.getString("nom");
				int id_grp = myRs.getInt("id_groupe");
				String cne = myRs.getString("cne");
				s = new Student (id , firstname , lastname , id_grp,cne);
				
			}
			return s;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void absenter(int id_seance,int id_etudiant) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into absence(id_etudiant,id_seance) values (?,?) ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id_etudiant);
			myStmt.setInt(2, id_seance);
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void accepter(int id_etudiant,int id_seance) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update absence set accepter=1 where id_etudiant=? and id_seance=? ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id_etudiant);
			myStmt.setInt(2, id_seance);
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void refuser(int id_etudiant,int id_seance) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update absence set accepter=0 where id_etudiant=? and id_seance=? ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id_etudiant);
			myStmt.setInt(2, id_seance);
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void validerAppel(int id_seance) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update seance set appel=1 where id=? ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id_seance);
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	public void evaluer(int id_seance,int id_etudiant,int note) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();
			int id_module = getIdModule(id_seance);
			if  ( !exist(id_module,id_etudiant) ) {
				
			
			String sql = "insert into evaluation(id_module,id_etudiant,note) values (?,?,?) ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id_module);
			myStmt.setInt(2, id_etudiant);
			myStmt.setInt(3, note);
			myStmt.execute();
			}
			else {
				String sql = "update evaluation set note=? where id_module=? and id_etudiant=? ";

				myStmt = myConn.prepareStatement(sql);

				// set params
				myStmt.setInt(1, note);
				myStmt.setInt(2, id_module);
				myStmt.setInt(3, id_etudiant);
				myStmt.execute();
			}
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void justifier(int id_etudiant,int id_seance,String justif) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			
			myConn = getConnection();
				
			
			String sql = "update absence set justification=? where id_etudiant=? and id_seance=? ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, justif);
			myStmt.setInt(2, id_etudiant);
			myStmt.setInt(3, id_seance);
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	public void present(int id_seance,int id_etudiant) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from absence where id_etudiant=? and id_seance=? ";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id_etudiant);
			myStmt.setInt(2, id_seance);
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	
	
	public List<Student> getStudentsBySeance(int id_seance) throws Exception {
		List<Student> students = new ArrayList<Student>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id_groupe = 0;
		try {
			myConn = getConnection();
			id_groupe = getIdGroupe(id_seance);
			String sql = "select * from etudiant where id_groupe=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_groupe);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstname = myRs.getString("prenom");
				String lastname = myRs.getString("nom");
				int id_grp = myRs.getInt("id_groupe");
				String cne = myRs.getString("cne");
				boolean b = absent(id_seance,id);
				Student s = new Student (id , firstname , lastname , id_grp,cne,b);
				students.add(s);
			}
			return students;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Student> getStudentsBySeance2(int id_seance) throws Exception {
		List<Student> students = new ArrayList<Student>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id_groupe = 0;
		try {
			myConn = getConnection();
			id_groupe = getIdGroupe(id_seance);
			String sql = "select * from etudiant where id_groupe=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_groupe);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstname = myRs.getString("prenom");
				String lastname = myRs.getString("nom");
				int id_grp = myRs.getInt("id_groupe");
				String cne = myRs.getString("cne");
				int id_module = getIdModule(id_seance);
				int note = getNote(id,id_module);
				Student s = new Student (id , firstname , lastname , id_grp,cne,note);
				students.add(s);
			}
			return students;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	public int getIdGroupe(int id_seance) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id = 0;
		try {
			myConn = getConnection();
			String sql = "select * from groupe g,seance s where s.id_groupe=g.id and s.id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_seance);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				id = myRs.getInt("s.id_groupe");
				
			}
			return id;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public Date getDateSeance(int id_seance) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Date date = null;
		try {
			myConn = getConnection();
			String sql = "select * from seance where id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_seance);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				date = myRs.getDate("date");
				
			}
			return date;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	public String getHeureSeance(int id_seance) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String heure = null;
		try {
			myConn = getConnection();
			String sql = "select * from seance where id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_seance);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int heure1 = myRs.getInt("num_heure");
				heure = heuretrans(heure1);
				
			}
			return heure;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	public String getEns(int id_etudiant,int id_module) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String ens = null;
		try {
			myConn = getConnection();
			int id_groupe = getIdGroupe(id_etudiant);
			String sql = "select * from ensemble e,enseignant ens where e.id_enseignant=ens.id and e.id_groupe=? and e.id_module=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_groupe);
			myStmt.setInt(2,id_module);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				String ens1 = myRs.getString("ens.nom");
				String ens2 = myRs.getString("ens.prenom");
				ens = ens1 +" "+ ens2;
				
			}
			return ens;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public String getModule(int id_module) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String s=null;
		try {
			myConn = getConnection();
			String sql = "select * from module where id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_module);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				s = myRs.getString("nom");
				
			}
			return s;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public int getIdGroupeStudent(int id_etudiant) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id = 0;
		try {
			myConn = getConnection();
			String sql = "select * from etudiant where id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_etudiant);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				id = myRs.getInt("id_groupe");
				
			}
			return id;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public int getNote(int id_etudiant,int id_module) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id = 0;
		try {
			myConn = getConnection();
			String sql = "select * from evaluation where id_etudiant=? and id_module=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_etudiant);
			myStmt.setInt(2,id_module);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				id = myRs.getInt("note");
				
			}
			return id;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	public int getIdModule(int id_seance) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id = 0;
		try {
			myConn = getConnection();
			String sql = "select * from module m,seance s where s.id_module=m.id and s.id=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_seance);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				id = myRs.getInt("s.id_module");
				
			}
			return id;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public boolean absent(int id_seance,int id_student) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		boolean b=false;
		try {
			myConn = getConnection();
			String sql = "select * from absence where id_seance=? and id_etudiant=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_seance);
			myStmt.setInt(2,id_student);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				b = true;
			}
			return b;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public boolean exist(int id_module,int id_student) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		boolean b=false;
		try {
			myConn = getConnection();
			String sql = "select * from evaluation where id_module=? and id_etudiant=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_module);
			myStmt.setInt(2,id_student);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				b = true;
			}
			return b;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	public List<GroupeHisto> getGroupeHisto(int id_grpe) throws Exception {
		List<GroupeHisto> histo = new ArrayList<GroupeHisto>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from groupe_histo where id_groupe=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_grpe);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id = myRs.getInt("id");
				Date date = myRs.getDate("date_h");
				String activite = myRs.getString("activite");
				GroupeHisto h = new GroupeHisto(id,date,activite);
				histo.add(h);
			}
			return histo;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Evaluation> getEvaluations(int id_etudiant) throws Exception {
		List<Evaluation> eval = new ArrayList<Evaluation>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from evaluation where id_etudiant=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_etudiant);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id_module = myRs.getInt("id_module");
				String module = getModule(id_module);
				double note = myRs.getDouble("note");
				String ens = getEns(id_etudiant,id_module);
				Evaluation e = new Evaluation (module,note,ens);
				eval.add(e);
			}
			return eval;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Absence> getAbsence(int id_etudiant) throws Exception {
		List<Absence> abs = new ArrayList<Absence>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from absence where id_etudiant=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1,id_etudiant);
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id_seance = myRs.getInt("id_seance");
				String just = myRs.getString("justification");
				Date date = getDateSeance (id_seance);
				String num_heure = getHeureSeance(id_seance);
				Absence a = new Absence (id_etudiant,id_seance,just,date,num_heure);
				abs.add(a);
			}
			return abs;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public List<Absence> getAbs() throws Exception {
		List<Absence> abs = new ArrayList<Absence>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = getConnection();
			String sql = "select * from absence a,etudiant e,seance s where a.id_etudiant=e.id and a.id_seance=s.id";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myRs = myStmt.executeQuery();
			// process result set
			while (myRs.next()) {
				int id_seance = myRs.getInt("a.id_seance");
				int id_etudiant = myRs.getInt("a.id_etudiant");
				String just = myRs.getString("a.justification");
				Date date = myRs.getDate("s.date");
				String heure = heuretrans(myRs.getInt("s.num_heure"));
				String cne = myRs.getString("e.cne");
				Groupe g = getGroupe(id_etudiant);
				String nom_complet = myRs.getString("e.nom") + " " +myRs.getString("e.prenom");
				boolean b;
				int acc = myRs.getInt("a.accepter");
				if (acc==0) b=false;
				else b=true;
				Absence a = new Absence(id_etudiant,id_seance,just,date,heure,nom_complet,cne, b,g.getGrp_name());
				abs.add(a);
				
			}
			return abs;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//---------------------------------------------------------------------------------------------------------------
		// CONSTRUCTORS AND FREQUENT METHODS (getConnection,close..)
		public static DBpool getInstance() throws Exception {
			if (instance == null) {
				instance = new DBpool();
			}
			
			return instance;
		}
		
		private DBpool() throws Exception {		
			dataSource = getDataSource();
		}

		private DataSource getDataSource() throws NamingException {
			Context context = new InitialContext();
			
			DataSource theDataSource = (DataSource) context.lookup(jndiName);
			
			return theDataSource;
		}
		
		private Connection getConnection() throws Exception {

			Connection theConn = dataSource.getConnection();
			
			return theConn;
		}
		
		private void close(Connection theConn, Statement theStmt) {
			close(theConn, theStmt, null);
		}
		
		private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

			try {
				if (theRs != null) {
					theRs.close();
				}

				if (theStmt != null) {
					theStmt.close();
				}

				if (theConn != null) {
					theConn.close();
				}
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}	
	
}