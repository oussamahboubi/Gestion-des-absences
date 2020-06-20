package beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Table;

import javax.faces.bean.ManagedBean;
@ManagedBean
public class Groupe {
	
	private int id_grp;
	private String grp_name;
	private Enseignant ens_respo;
	private List<Student> students;
	private List<GroupeHisto> histo;
	private List<Seance> seances;
	private List<Module> modules;
	//that is needed for the framework
	public Groupe() {
		super();
		students=new ArrayList<Student>();
		histo=new ArrayList<GroupeHisto>();
		modules=new ArrayList<Module>();
	}
	//that is for me 
	public Groupe(int id,String grp_name, List<Student> students,List<GroupeHisto> histo,List<Seance> seances) {
		super();
		id_grp = id;
		this.grp_name = grp_name;
		this.students = students;
		this.histo=histo;
		this.seances= seances;
	}
	public Groupe(int id,String grp_name, List<Student> students,List<GroupeHisto> histo) {
		super();
		id_grp = id;
		this.grp_name = grp_name;
		this.students = students;
		this.histo=histo;
	}
	public Groupe(int id,String grp_name, List<Student> students,List<GroupeHisto> histo,List<Module> modules,int n) {
		super();
		id_grp = id;
		this.grp_name = grp_name;
		this.students = students;
		this.histo=histo;
		this.modules = modules;
	}
	public int getId_grp() {
		return id_grp;
	}
	
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public void setId_grp(int id_grp) {
		this.id_grp = id_grp;
	}
	public String getGrp_name() {
		return grp_name;
	}
	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<GroupeHisto> getHisto() {
		return histo;
	}
	public void setHisto(List<GroupeHisto> histo) {
		this.histo = histo;
	}
	public List<Seance> getSeances() {
		return seances;
	}
	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}
	
	
	
	
	
	//this toString impl is for the student when he need to know his group(because he does not want all the the student in it , know just about the group itself)
	/*public String toString() {
		return "Groupe [id_grp=" + id_grp + ", grp_name=" + grp_name + ", nb_student=" + nb_student+ "]";
	}*/
	
	
}
