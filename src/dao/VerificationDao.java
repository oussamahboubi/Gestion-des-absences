package dao;


import entities.Verification;

public class VerificationDao {
	
	public Verification isValid(String login, String password,String type){
	    
		Verification verif=null;
		boolean created=false;
        if (type.equals("etudiant")) {
        	
        	if(true) {
        		verif=new Verification(0, true);
        		created=true;
        	}
        }
        else if (type.equals("enseignant")) {
        	
        	if(true) {
        		verif=new Verification(0, true);
        		created=true;
        	}
        }
        else if (type.equals("admin")) {
        	
        	if(true) {
        		verif=new Verification(0, true);
        		created=true;
        	}
        }
        if(created==false) verif=new Verification(0, false);
        return verif;
    }
	

}
