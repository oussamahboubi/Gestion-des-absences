package beans;


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

import javax.faces.bean.ManagedBean;
@ManagedBean
public class Module {
	
	private int id;
	private String nom;
	private String nb_Hours;
	private String enseignant;
	
	public Module() {
		super();
	}

	public Module(int id,String intitule, String nb_Hours,String ens) {
		super();
		this.id=id;
		this.nom = intitule;
		this.nb_Hours = nb_Hours;
		enseignant = ens;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return nom;
	}

	public void setIntitule(String intitule) {
		this.nom = intitule;
	}

	public String getNb_Hours() {
		return nb_Hours;
	}

	public void setNb_Hours(String nb_Hours) {
		this.nb_Hours = nb_Hours;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(String enseignant) {
		this.enseignant = enseignant;
	}

	
	
	

	/*
	public String toString() {
		return "Module [id=" + id + ", intitule=" + intitule + ", nb_Hours=" + nb_Hours + "]";
	}*/
	
	
	
	
	
	

}
