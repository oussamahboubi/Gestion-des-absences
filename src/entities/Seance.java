package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Seance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column
	private int  num_semaine;
	@Column
	private String nom_jour;
	@Column
	private String seance;//format 08:30-10:30
	@Column
	private int semestre;
	@Column
	private String annee;
	@Column
	private String dateComplet;//format 01/01/2018
	@ManyToOne
	private Module module;
	
	
	public Seance() {
		super();
	}

	public Seance(int num_semaine, String nom_jour, String num_seance, int semestre, String annee) {
		super();
		this.num_semaine = num_semaine;
		this.nom_jour = nom_jour;
		this.seance = num_seance;
		this.semestre = semestre;
		this.annee = annee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum_semaine() {
		return num_semaine;
	}

	public void setNum_semaine(int num_semaine) {
		this.num_semaine = num_semaine;
	}

	public String getNom_jour() {
		return nom_jour;
	}

	public void setNom_jour(String nom_jour) {
		this.nom_jour = nom_jour;
	}

	public String getNum_seance() {
		return seance;
	}

	public void setNum_seance(String num_seance) {
		this.seance = num_seance;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "Seance [id=" + id + ", num_semaine=" + num_semaine + ", nom_jour=" + nom_jour + ", num_seance="
				+ seance + ", semestre=" + semestre + ", annee=" + annee + ", module=" + module + "]";
	}

	/*
	public String toString() {
		return "Seance [id=" + id + ", num_semaine=" + num_semaine + ", nom_jour=" + nom_jour + ", num_seance="
				+ num_seance + ", semestre=" + semestre + ", annee=" + annee  + "]";
	}*/

	
	
	
	
	
	
}
