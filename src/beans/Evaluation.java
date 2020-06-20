package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.faces.bean.ManagedBean;
@ManagedBean
public class Evaluation {
	
	private String module;
	private double note;
	private String ens;
	
	
	
	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Evaluation(String module,double note,String ens) {
		super();
		this.ens = ens;
		this.module = module;
		this.note = note;
	}


	public String getModule() {
		return module;
	}


	public void setModule(String module) {
		this.module = module;
	}


	public double getNote() {
		return note;
	}

	

	public String getEns() {
		return ens;
	}


	public void setEns(String ens) {
		this.ens = ens;
	}


	public void setNote(double note) {
		this.note = note;
	}
	
	

}
