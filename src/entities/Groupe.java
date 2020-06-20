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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Groupe")
public class Groupe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grp")
	private int id_grp;
	@Column(name = "grp_name")
	private String grp_name;
	@Column
	private int nb_student;
	@ManyToOne
	private Enseignant enseignant_respo;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "groupIsStudent")
	private Set<Student> students;
	
	//that is needed for the framework
	public Groupe() {
		super();
		students=new HashSet<Student>();
	}
	//that is for me 
	public Groupe(String grp_name,int nb_student) {
		super();
		this.grp_name = grp_name;
		this.nb_student=nb_student;
		students=new HashSet<Student>();
	}
	public int getId_grp() {
		return id_grp;
	}
	public void setId_grp(int id_grp) {
		this.id_grp = id_grp;
	}
	public String getGrp_name() {
		return grp_name;
	}
	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public int getNb_student() {
		return nb_student;
	}
	public void setNb_student(int nb_student) {
		this.nb_student = nb_student;
	}
	public Enseignant getEnseignant_respo() {
		return enseignant_respo;
	}
	public void setEnseignant_respo(Enseignant enseignant_respo) {
		this.enseignant_respo = enseignant_respo;
	}
	@Override
	public String toString() {
		return "Groupe [id_grp=" + id_grp + ", grp_name=" + grp_name + ", nb_student=" + nb_student
				+ ", enseignant_respo=" + enseignant_respo + ", students=" + students + "]";
	}
	
	
	//this toString impl is for the student when he need to know his group(because he does not want all the the student in it , know just about the group itself)
	/*public String toString() {
		return "Groupe [id_grp=" + id_grp + ", grp_name=" + grp_name + ", nb_student=" + nb_student+ "]";
	}*/
	
	
}
