package beans;

import java.sql.Date;

import javax.faces.bean.ManagedBean;
@ManagedBean
public class GroupeHisto {
	private int id;
	private Date date;
	private String activite;
	private int id_groupe;

	
	
	
	
	
	
	
	
	
	

	
	public GroupeHisto() {
		
	}
	public GroupeHisto (String activite , int id_groupe) {
		this.activite = activite ; 
		this.id_groupe = id_groupe ;
	}
	
public GroupeHisto(int id,Date date,String activite) {
		this.id=id;
		this.date=date;
		this.activite=activite;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getActivite() {
		return activite;
	}
	
	public int getId_groupe() {
		return id_groupe;
	}

	public void setId_groupe(int id_groupe) {
		this.id_groupe = id_groupe;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	
}
