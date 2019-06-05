package Data;

import java.util.ArrayList;
import java.util.Date;


public class Patient extends Member {
	private String medicare;
	private ArrayList<Appointment> history = new ArrayList<Appointment>();
	private ArrayList<Treatment> tPlan = new ArrayList<Treatment>();
	
	public Patient(String _username, String _password, String _name, String _address, 
			String _email, String _phone, Date _dob,String _medicare) {
		
		username = _username;
		password = _password;
		name = _name;
		address = _address;
		email = _email;
		phone = _phone;
		dob = _dob;	
		medicare = _medicare;
	}
	
	public String getMedicare() {
		return medicare;
	}

	public void setMedicare(String medicare) {
		this.medicare = medicare;
	}
	public ArrayList<Treatment> gettPlan() {
		return tPlan;
	}

	public ArrayList<Appointment> getHistory() {
		return history;
	}
}
