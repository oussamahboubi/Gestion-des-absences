package entities;




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

@Entity
@Table(name = "Student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name="firstname", nullable = false, length = 100)
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column(name="login")
	private String login;
	@Column(name="password")
	private String passwd;
	@ManyToOne
	private Groupe group;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "studentIsEvaluation")
	private List<Evaluation> evalutions;
	
	public Student() {
		super();
		evalutions=new ArrayList<Evaluation>();
	}

	public Student(String firstname, String lastname, String login, String passwd) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.passwd = passwd;
		evalutions=new ArrayList<Evaluation>();
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

	public Groupe getGroup() {
		return group;
	}

	public void setGroup(Groupe groups) {
		this.group = groups;
	}

	public List<Evaluation> getEvalutions() {
		return evalutions;
	}

	public void setEvalutions(List<Evaluation> evalutions) {
		this.evalutions = evalutions;
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
