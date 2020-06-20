package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@ManyToOne
	private Student student;
	@ManyToOne
	private Seance seance;
	@Column
	private String justification;
	

	public Absence() {
		super();
	}


	public Absence(Student student, Seance seance, String justification) {
		super();
		this.student = student;
		this.seance = seance;
		this.justification = justification;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Seance getSeance() {
		return seance;
	}


	public void setSeance(Seance seance) {
		this.seance = seance;
	}


	public String getJustification() {
		return justification;
	}


	public void setJustification(String justification) {
		this.justification = justification;
	}


	@Override
	public String toString() {
		return "Absence [id=" + id + ", student=" + student + ", seance=" + seance + ", justification=" + justification
				+ "]";
	}
	
	

}
