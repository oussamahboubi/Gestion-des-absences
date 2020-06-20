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
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column
	private String intitule;
	@Column
	private String nb_Hours;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "moduleIsEnseignat")
	private Set<Enseignant> enseignant;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "moduleIsEvaluation")
	private Set<Evaluation> evaluations;
	
	public Module() {
		super();
		enseignant=new HashSet<Enseignant>();
		evaluations=new HashSet<Evaluation>();
	}

	public Module(String intitule, String nb_Hours) {
		super();
		this.intitule = intitule;
		this.nb_Hours = nb_Hours;
		enseignant=new HashSet<Enseignant>();
		evaluations=new HashSet<Evaluation>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getNb_Hours() {
		return nb_Hours;
	}

	public void setNb_Hours(String nb_Hours) {
		this.nb_Hours = nb_Hours;
	}

	public Set<Enseignant> getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Set<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}

	public Set<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(Set<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", intitule=" + intitule + ", nb_Hours=" + nb_Hours + ", enseignant=" + enseignant
				+ ", evaluations=" + evaluations + "]";
	}
	

	/*
	public String toString() {
		return "Module [id=" + id + ", intitule=" + intitule + ", nb_Hours=" + nb_Hours + "]";
	}*/
	
	
	
	
	
	

}
