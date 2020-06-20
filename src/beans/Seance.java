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
public class Seance {
	
	private int id;
	private String num_heure;
	private Date date;//format 01/01/2018
	private String module;
	private String groupe;
	private boolean appel;
	private Seance seance;
	private int id_groupe;
	private int id_module;
	
	
	public Seance() {
		super();
	}
	
	public Seance(int id_seance,int id_groupe,int id_module,String module,String groupe) {
		id = id_seance ;
		this.id_groupe = id_groupe;
		this.id_module = id_module;
		this.module = module;
		this.groupe = groupe;
	}

	public Seance(int id,String heure,Date date,String module,String groupe,boolean appel,int id_groupe) {
		super();
		this.id=id;
		this.num_heure=heure;
		this.date=date;
		this.module=module;
		this.groupe=groupe;
		this.appel = appel;
		this.id_groupe = id_groupe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum_heure() {
		return num_heure;
	}

	public void setNum_heure(String num_heure) {
		this.num_heure = num_heure;
	}

	
	public int getId_groupe() {
		return id_groupe;
	}

	public void setId_groupe(int id_groupe) {
		this.id_groupe = id_groupe;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	public boolean getAppel() {
		return appel;
	}

	public void setAppel(boolean appel) {
		this.appel = appel;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	





	/*
	public String toString() {
		return "Seance [id=" + id + ", num_semaine=" + num_semaine + ", nom_jour=" + nom_jour + ", num_seance="
				+ num_seance + ", semestre=" + semestre + ", annee=" + annee  + "]";
	}*/

	
	
	
	
	
	
}
