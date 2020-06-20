package entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Enseignant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name="firstname", nullable = false, length = 100)
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column
	private String login;
	@Column
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "enseignantIsModules")
	private Set<Module> modules;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "enseignantIsGroups")
	private Set<Groupe> groupes;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "enseignantIsSeance")
	private Set<Seance> seances;
	
	
	
	public Enseignant() {
		super();
		modules= new HashSet<Module>();
		groupes=new HashSet<Groupe>();
		seances=new HashSet<Seance>();
	}

	public Enseignant(String firstname, String lastname,String login,String pwd ) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login=login;
		this.password=pwd;
		modules= new HashSet<Module>();
		groupes=new HashSet<Groupe>();
		seances=new HashSet<Seance>();
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


	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Set<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
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
