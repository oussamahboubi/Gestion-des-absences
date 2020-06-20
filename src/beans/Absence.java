package beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.sql.Date;

import javax.faces.bean.ManagedBean;
@ManagedBean
public class Absence {
	
	private int id;
	private int id_student;
	private int id_seance;
	private String justif;
	private Date date;
	private String num_heure;
	private String nom_complet,cne;
	private boolean accepter;
	private String groupe;
	

	public Absence() {
		super();
	}

	public Absence(int student, int seance, String justif,Date date,String num_heure) {
		super();
		this.id_student = student;
		this.id_seance = seance;
		this.justif = justif;
		this.date = date;
		this.num_heure = num_heure;
	}
	
	public Absence(int student, int seance, String justif,Date date,String num_heure,String nom_complet,String cne,boolean b,String groupe) {
		super();
		this.groupe = groupe ;
		this.id_student = student;
		this.id_seance = seance;
		this.justif = justif;
		this.date = date;
		this.num_heure = num_heure;
		this.nom_complet = nom_complet ;
		this.cne = cne ;
		accepter = b;
	}

	public int getId_student() {
		return id_student;
	}
	
	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}

	public int getId_seance() {
		return id_seance;
	}

	public void setId_seance(int id_seance) {
		this.id_seance = id_seance;
	}

	public String getJustif() {
		return justif;
	}

	public void setJustif(String justif) {
		this.justif = justif;
	}

	public Date getDate() {
		return date;
	}
	
	
	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}

	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}


	public boolean isAccepter() {
		return accepter;
	}

	public void setAccepter(boolean accepter) {
		this.accepter = accepter;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNum_heure() {
		return num_heure;
	}

	public void setNum_heure(String num_heure) {
		this.num_heure = num_heure;
	}
	
	
	



	
	

}
