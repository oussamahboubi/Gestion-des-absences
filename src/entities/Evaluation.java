package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Evaluation")
public class Evaluation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column
	private String name;
	@ManyToOne
	private Module module;
	@Column
	private double note;
	@ManyToOne
	private Student student;
	
	
	
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Evaluation(String name, double note) {
		super();
		this.name = name;
		this.note = note;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Module getModule() {
		return module;
	}


	public void setModule(Module module) {
		this.module = module;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public double getNote() {
		return note;
	}


	public void setNote(double note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", name=" + name + ", module=" + module + ", student=" + student + ", note="
				+ note + "]";
	}
	/*public String toString() {
		return "Evaluation [id=" + id + ", name=" + name + ", note=" + note + "]";
	}*/

}
