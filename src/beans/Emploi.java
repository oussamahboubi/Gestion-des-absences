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
import javax.persistence.ManyToOne;


import javax.faces.bean.ManagedBean;
@ManagedBean
public class Emploi {
	
	private int id;
	private Groupe groupe;
	private Set<Seance> seances;
	
	public Emploi() {
		super();
		seances=new HashSet<Seance>();
	}

	public Emploi(Groupe groupe) {
		super();
		this.groupe = groupe;
		seances=new HashSet<Seance>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	@Override
	public String toString() {
		return "Emploi [id=" + id + ", groupe=" + groupe + ", seances=" + seances + "]";
	}

	/*
	public String toString() {
		return "Emploi [id=" + id + ", groupe=" + groupe + "]";
	}*/
	


}
