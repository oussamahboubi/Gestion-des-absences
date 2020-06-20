package beans;




import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.primefaces.event.RowEditEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
@ManagedBean
@SessionScoped
public class Student {
	
	private int id;
	private String firstname;
	private String lastname;
	private String login;
	private String passwd;
	private int group,age;
	private String cne;
	private List<Evaluation> evalutions;
	private List<Absence> absence;
	private boolean absent;
	private int note;
	private Groupe groupe;
	private DBpool db;
	
	
	public void loadGroupe(ComponentSystemEvent event) throws Exception{
		int id_s = (int) event.getComponent().getAttributes().get("id_s");
		Student s = db.getStudent(id_s);
		firstname = s.getFirstname();
		lastname = s.getLastname();
		cne = s.getCne();
		groupe = db.getGroupe(id_s);
		evalutions = db.getEvaluations(id_s);
		absence = db.getAbsence(id_s);
		this.id = id_s;
	}
	
	
	public void onRowEdit(RowEditEvent event) throws Exception {
    	int id_seance = ((Absence) event.getObject()).getId_seance();
    	String justif = ((Absence) event.getObject()).getJustif();
    	db.justifier(id,id_seance,justif);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 // -----------------------------------------------------------------
	public Student() throws Exception {
		super();
		evalutions=new ArrayList<Evaluation>();
		absence=new ArrayList<Absence>();
		db = DBpool.getInstance();
	}

	public Student(int id,String firstname, String lastname,int g,String cne) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		group = g;
		this.cne = cne;
	}
	
	public Groupe getGroupe() {
		return groupe;
	}

	
	public List<Absence> getAbsence() {
		return absence;
	}


	public void setAbsence(List<Absence> absence) {
		this.absence = absence;
	}


	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Student(int id,String firstname, String lastname,int g,String cne,int note) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		group = g;
		this.cne = cne;
		this.note=note;
	}
	
	public Student(int id,String firstname, String lastname,int g,String cne,boolean abs) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		group = g;
		this.cne = cne;
		absent = abs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}
	

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	
	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public List<Evaluation> getEvalutions() {
		return evalutions;
	}

	public void setEvalutions(List<Evaluation> evalutions) {
		this.evalutions = evalutions;
	}
	
	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}
	

	public boolean isAbsent() {
		return absent;
	}

	public void setAbsent(boolean absent) {
		this.absent = absent;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login
				+ ", passwd=" + passwd + ", group=" + group + ", evalutions=" + evalutions + "]";
	}

	/*public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", login=" + login
				+ ", passwd=" + passwd +  "]";
	}*/	
	
}
