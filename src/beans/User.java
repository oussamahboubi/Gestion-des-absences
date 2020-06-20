package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class User {
	private String username,password;
	private int id_enseignant;
	private int id_ens;
	private int id_s;
	private String type;
	private int id_student;
	private DBpool db;

	
	
	public String verify() throws Exception {
		db = DBpool.getInstance();
		
		if (username.equals("admin") & password.equals("admin"))  return "admin-accueil?faces-redirect=true";
		if (type.equals("enseignant")) {
			int id = db.VerifyEnseignant(username,password);
			if (id==0) {
				type = null;
				return "login?faces-redirect=true";
			}
			else {
				id_ens = id;
				return "prof-accueil?faces-redirect=true";
			}
		}
		
		if (type.equals("etudiant")) {
			int id = db.VerifyEtudiant(username,password);
			if (id==0) {
				type = null;
				return "login?faces-redirect=true";
			}
			else {
				id_s = id;
				return "etudiant_accueil?faces-redirect=true";
			}
		}
		
		
		
		type = null;
		return "login?faces-redirect=true";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//------------------------------------------------------------
	public User() {
		
	}
public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	
	public int getId_s() {
		return id_s;
	}

	public void setId_s(int id_s) {
		this.id_s = id_s;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId_enseignant() {
		return id_enseignant;
	}

	public void setId_enseignant(int id_enseignant) {
		this.id_enseignant = id_enseignant;
	}

	public int getId_ens() {
		return id_ens;
	}

	public void setId_ens(int id_ens) {
		this.id_ens = id_ens;
	}

	public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}
	
}
