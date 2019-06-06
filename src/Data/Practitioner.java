package Data;

import java.util.ArrayList;
import java.util.Date;

public class Practitioner extends Member {
	
	private String education;
	private String skills;	
	private String licenseID;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	public Specs specialise; 	
	public enum Specs {GENERAL, ONCOLOGY, WOMENS_HEALTH, CARDIO, ASSISSTANT}
	

	public Practitioner(String _username, String _password, String _name, String _address, 
			String _email, String _phone, Date _dob, String _education, 
			String _skills, String _licenseID) {
		
		username = _username;
		password = _password;
		name = _name;
		address = _address;
		email = _email;
		phone = _phone;
		dob = _dob;
		education = _education;
		skills = _skills;
		licenseID = _licenseID;
		
	}
	
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getLicenseID() {
		return licenseID;
	}
	public void setLicenseID(String licenseID) {
		this.licenseID = licenseID;
	}
	public ArrayList<Patient> getPatients() {
		return patients;
	}
}
