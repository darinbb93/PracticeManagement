package Data;

import java.util.ArrayList;
import java.util.Date;

/**	
 * This is the Patient Data class derived from Abstract Member
 * this class defines Patients recorded in the system and stores 
 * their appointments history, equipment borrowed by them and
 * their treatment plan. 
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class Patient extends Member {
	private String medicare;
	private ArrayList<Equipment> borrowed = new ArrayList<Equipment>();
	private ArrayList<Appointment> history = new ArrayList<Appointment>();
	private ArrayList<Treatment> tPlan = new ArrayList<Treatment>();
	
	public Patient(String _username, String _password, String _name, String _address, 
			String _email, String _phone, Date _dob, String _medicare) {
		
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
	public ArrayList<Equipment> getBorrowed() {
		return borrowed;
	}

	public ArrayList<Treatment> gettPlan() {
		return tPlan;
	}

	public ArrayList<Appointment> getHistory() {
		return history;
	}
}
