package beans;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
@ManagedBean
@SessionScoped
public class Enseignant {
	
	private int id;
	private String firstname;
	private String lastname;
	private String login;
	private String password;
	private List<Module> modules;
	private List<Groupe> groupes;
	private List<Seance> seances;
	private List<Student> students;
	private List<Student> students2;
	private DBpool db;
	private Groupe selectedGroupe;
	private int selectedSeance;
	private List<Seance> seances2;
	private int selectedSeance2;
	
	public void loadGroupes(ComponentSystemEvent event) throws Exception{
		int id_ens = (int) event.getComponent().getAttributes().get("id_ens");
		groupes = db.getGroupes(id_ens);
		seances = db.getSeancesEns(id_ens);
		seances2 = db.getDistinctSeances(id_ens);
		this.id = id_ens;
	}
	public void clear(ComponentSystemEvent event) throws Exception{
		groupes.clear();
		seances.clear();
		students2.clear();
	}
	
	public String appel() throws Exception {
		students = db.getStudentsBySeance(selectedSeance);
		return "prof-appel?faces-redirect=true";
	}
	public void rowedit(boolean b,int id_seance,int id_etudiant) throws Exception {
		if (b) {
			db.absenter (id_seance,id_etudiant);
		}
		else {
			db.present (id_seance,id_etudiant);
		}
	}
	
	public String noter() throws Exception {
		students2 = db.getStudentsBySeance2(selectedSeance2);
		return "prof-notes?faces-redirect=true";
	}
	
	public void onRowEdit(RowEditEvent event) throws Exception {
    	int id_etudiant = ((Student) event.getObject()).getId();
    	int note = ((Student) event.getObject()).getNote();
    	db.evaluer(selectedSeance2,id_etudiant,note) ;
    }
	
	
	
	public void validerAppel() throws Exception {
		db.validerAppel(selectedSeance);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//  ---------------------------------------------------------------------
	public Enseignant() throws Exception {
		super();
		db = DBpool.getInstance();
		modules= new ArrayList<>();
		groupes=new ArrayList<>();
		seances=new ArrayList<>();
		students=new ArrayList<>();
		seances2=new ArrayList<>();
		students2=new ArrayList<>();
	}
	

	public List<Student> getStudents2() {
		return students2;
	}

	public void setStudents2(List<Student> students2) {
		this.students2 = students2;
	}

	public Enseignant(String firstname, String lastname,String login,String pwd ) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login=login;
		this.password=pwd;
	}

	
	public List<Seance> getSeances2() {
		return seances2;
	}

	public void setSeances2(List<Seance> seances2) {
		this.seances2 = seances2;
	}

	public int getSelectedSeance2() {
		return selectedSeance2;
	}

	public void setSelectedSeance2(int selectedSeance2) {
		this.selectedSeance2 = selectedSeance2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public List<Module> getModules() {
		return modules;
	}

	
	public int getSelectedSeance() {
		return selectedSeance;
	}



	public void setSelectedSeance(int selectedSeance) {
		this.selectedSeance = selectedSeance;
	}


	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public List<Seance> getSeances() {
		return seances;
	}

	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Groupe getSelectedGroupe() {
		return selectedGroupe;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}



	public void setSelectedGroupe(Groupe selectedGroupe) {
		this.selectedGroupe = selectedGroupe;
	}

	
	@Override
	public String toString() {
		return "Enseignant [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login
				+ ", password=" + password + ", modules=" + modules + ", groupes=" + groupes + ", seances=" + seances
				+ "]";
	}

	

	/*public String toString() {
		return "Enseignant [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login
				+ ", password=" + password + "]";
	}*/

	

}
