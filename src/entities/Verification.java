package entities;

public class Verification {
	
	private int id;
	private boolean valid;
	
	
	public Verification(int id, boolean valid) {
		super();
		this.id = id;
		this.valid = valid;
	}

	public int getId() {
		return id;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " valid ? "+this.valid+" id "+this.id;
	}
	

}
