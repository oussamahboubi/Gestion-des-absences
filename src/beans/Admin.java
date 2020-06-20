package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;


@ManagedBean
@SessionScoped
public class Admin {
	private List<Groupe> groupes;
	private DBpool db;
	private List<Absence> absences;

	public Admin() throws Exception {
		groupes = new ArrayList<>();
		absences = new ArrayList<>();
		db = DBpool.getInstance();
	}
	
	public void loadGroupes(ComponentSystemEvent event) throws Exception{
		groupes = db.getGroupess();
	}
	
	public void loadAbsences(ComponentSystemEvent event) throws Exception{
		absences = db.getAbs();
	}
	
	public void rowedit(boolean b,int id_etudiant,int id_seance) throws Exception {
		if (b) {
			db.accepter (id_etudiant,id_seance);
		}
		else {
			db.refuser (id_etudiant,id_seance);
		}
	}
	
	public String addGroupe(Groupe groupe) throws Exception{
		db.addGroupe(groupe.getGrp_name());
		groupes = db.getGroupess();
		return "admin-groupes.xhtml?faces-redirect=true";
	}
	
	public String addEtudiant(Etudiant etudiant) throws Exception{
		db.addEtudiant(etudiant);
		groupes = db.getGroupess();
		return "admin-groupes.xhtml?faces-redirect=true";
	}
	
	public String addEvenement(GroupeHisto hist) throws Exception{
		db.addHist(hist);
		groupes = db.getGroupess();
		return "admin-groupes.xhtml?faces-redirect=true";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	// --------------------------------------
	public List<Groupe> getGroupes() {
		return groupes;
	}
	
	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

}
