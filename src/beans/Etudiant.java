package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Etudiant {
	private String lastname,firstname,cne,login,password;
	private int id_groupe;
	
	
	
	
	
	public Etudiant (String lastname,String firstname,String cne,String login,String password,int id_groupe) {
		this.lastname = lastname ;
		this.firstname = firstname;
		this.cne = cne;
		this.login = login;
		this.password = password ; 
		this.id_groupe = id_groupe ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getCne() {
		return cne;
	}


	public void setCne(String cne) {
		this.cne = cne;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getId_groupe() {
		return id_groupe;
	}


	public void setId_groupe(int id_groupe) {
		this.id_groupe = id_groupe;
	}


	public Etudiant() {
		
	}
	
	
	
	

}
