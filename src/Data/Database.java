package Data;
import java.util.ArrayList;
import java.util.Date;


public class Database {
	private ArrayList<Member> members = new ArrayList<Member>();
	private ArrayList<Practitioner> practitioners = new ArrayList<Practitioner>();	
	private ArrayList<Equipment> equipmentOwned = new ArrayList<Equipment>(); 
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Staff> staffMembers = new ArrayList<Staff>();	
	private ArrayList<Treatment> treatmentTemplates = new ArrayList<Treatment>();

	public ArrayList<Member> getMembers() {
		return members;
	}
	public ArrayList<Practitioner> getDoctors() {
		return practitioners;
	}
	public ArrayList<Equipment> getEquipmentOwned() {
		return equipmentOwned;
	}
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	public ArrayList<Staff> getStaffMembers() {
		return staffMembers;
	}
	public ArrayList<Treatment> getTreatmentTemplates() {
		return treatmentTemplates;
	}
	
	
	// Prototype sample database population
	public void initialize() {
		Date prDate = new Date(1980,10,10);
		String prPass = "123456";
		String prAddress = "22 Jump Street, Adelaide, SA 5000 ";
		String prEmail = "drDolittle@gmail.com";
		String prPhone = "0455566677";
		String prEduc = "Masters of physiotherapy";
		String prSkills = "Massageing, needling, healing";
		String prLic = "ID001";
		
		
		//add doctors
		Practitioner doc1 = new Practitioner("a.lewis", prPass, "Albert Lewis", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc1);
		members.add(doc1);
		
		Practitioner doc2 = new Practitioner("a.potoni", prPass, "Angela Potoni", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc2);
		members.add(doc2);
		
		Practitioner doc3 = new Practitioner("j.clarke", prPass, "Jason Clarke", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc3);
		members.add(doc3);
		
		Practitioner doc4 = new Practitioner("f.holmes", prPass, "Farhan Holmes", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc4);
		members.add(doc4);
		
		Practitioner doc5 = new Practitioner("moh.lee", prPass, "Mohammed Lee", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc5);
		members.add(doc5);

		
		Practitioner doc6 = new Practitioner("c.williams", prPass, "Charlies Williams", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc6);
		members.add(doc6);

		
		Practitioner doc7 = new Practitioner("e.jones", prPass, "Emily jones", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc7);
		members.add(doc7);

		
		Practitioner doc8 = new Practitioner("d.young", prPass, "Doctor Young", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc8);
		members.add(doc8);

		
		Practitioner doc9 = new Practitioner("d.prince", prPass, "Diana Prince", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc9);
		members.add(doc9);

		
		Practitioner doc10 = new Practitioner("d.smith", prPass, "Dan Smith", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc10);
		members.add(doc10);

		
		Practitioner doc11 = new Practitioner("l.frost", prPass, "Lionel Frost", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc11);
		members.add(doc11);

		
		Practitioner doc12 = new Practitioner("b.morton", prPass, "Benjamin Morton", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc12);
		members.add(doc12);

		
		Practitioner doc13 = new Practitioner("c.white", prPass, "Christine White", 
				prAddress, prEmail, prPhone, prDate, prEduc, prSkills, prLic);		
		practitioners.add(doc13);
		members.add(doc13);
		
		
		//add patients
		
		Patient p1 = new Patient("bogdb001", prPass, "Darin Bogdanov", 
				prAddress, prEmail, prPhone, prDate, "01356781234");
		members.add(p1);
		patients.add(p1);

		Patient p2 = new Patient("wanjy139", prPass, "Jingqin Wang", 
				prAddress, prEmail, prPhone, prDate, "01356781234");
		members.add(p2);
		patients.add(p2);

		Patient p3 = new Patient("fuyty006", prPass, "Tianyi Fu", 
				prAddress, prEmail, prPhone, prDate, "01356781234");
		members.add(p3);
		patients.add(p3);

		Patient p4 = new Patient("jiajy027", prPass, "Jialin Jia", 
				prAddress, prEmail, prPhone, prDate, "01356781234");
		members.add(p4);
		patients.add(p4);

		Patient p5 = new Patient("mohsy063", prPass, "Sushant Mohite", 
				prAddress, prEmail, prPhone, prDate, "01356781234");
		members.add(p5);
		patients.add(p5);

		Patient p6 = new Patient("zengy004", prPass, "Guandi Zeng", 
				prAddress, prEmail, prPhone, prDate, "01356781234");
		members.add(p6);
		patients.add(p6);
		
		
		for(Practitioner doctor: practitioners) {
			for(Patient patient : patients) {
				doctor.getPatients().add(patient);

			}
		}
	}
}
