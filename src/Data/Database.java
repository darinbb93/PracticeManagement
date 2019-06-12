package Data;

import java.util.ArrayList;
import java.util.Date;

/**
 * This is the Database Date class acting as a local storage for all data the
 * practice needs stored and accessed
 * 
 * @author Darin Bogdanov - bogdb001
 * 
 */
public class Database {
	private ArrayList<Member> members = new ArrayList<Member>();
	private ArrayList<Equipment> equipmentOwned = new ArrayList<Equipment>();
	private ArrayList<Treatment> treatmentTemplates = new ArrayList<Treatment>();

	public ArrayList<Member> getMembers() {
		return members;
	}

	public ArrayList<Equipment> getEquipmentOwned() {
		return equipmentOwned;
	}

	public ArrayList<Treatment> getTreatmentTemplates() {
		return treatmentTemplates;
	}

	// Prototype sample database population
	public void initialize() {
		Date prDate = new Date(1980, 10, 10);
		String prPass = "123456";
		String prAddress = "22 Jump Street, Adelaide, SA 5000 ";
		String prEmail = "drDolittle@gmail.com";
		String prPhone = "0455566677";
		String prEduc = "Masters of physiotherapy";
		String prSkills = "Massaging, needling, healing";
		String prLic = "ID001";

		// add doctors
		Practitioner doc1 = new Practitioner("a.lewis", prPass, "Albert Lewis", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Male");
		members.add(doc1);

		Practitioner doc2 = new Practitioner("a.potoni", prPass, "Angela Potoni", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Female");
		members.add(doc2);

		Practitioner doc3 = new Practitioner("j.clarke", prPass, "Jason Clarke", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Male");
		members.add(doc3);

		Practitioner doc4 = new Practitioner("f.holmes", prPass, "Farhan Holmes", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Male");
		members.add(doc4);

		Practitioner doc5 = new Practitioner("moh.lee", prPass, "Mohammed Lee", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Male");
		members.add(doc5);

		Practitioner doc6 = new Practitioner("c.williams", prPass, "Charlies Williams", prAddress, prEmail, prPhone,
				prDate, prEduc, prSkills, prLic, "Male");
		members.add(doc6);

		Practitioner doc7 = new Practitioner("e.jones", prPass, "Emily jones", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Female");
		members.add(doc7);

		Practitioner doc8 = new Practitioner("d.young", prPass, "Doctor Young", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Male");
		members.add(doc8);

		Practitioner doc9 = new Practitioner("d.prince", prPass, "Diana Prince", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Female");
		members.add(doc9);

		Practitioner doc10 = new Practitioner("d.smith", prPass, "Dan Smith", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Male");
		members.add(doc10);

		Practitioner doc11 = new Practitioner("l.frost", prPass, "Lionel Frost", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Male");
		members.add(doc11);

		Practitioner doc12 = new Practitioner("b.morton", prPass, "Benjamin Morton", prAddress, prEmail, prPhone,
				prDate, prEduc, prSkills, prLic, "Male");
		members.add(doc12);

		Practitioner doc13 = new Practitioner("c.white", prPass, "Christine White", prAddress, prEmail, prPhone, prDate,
				prEduc, prSkills, prLic, "Female");
		members.add(doc13);

		// add patients

		Patient p1 = new Patient("bogdb001", prPass, "Darin Bogdanov", prAddress, prEmail, prPhone, prDate,
				"01356781234", "male");
		members.add(p1);

		Patient p2 = new Patient("wanjy139", prPass, "Jingqin Wang", prAddress, prEmail, prPhone, prDate, "01356781234",
				"male");
		members.add(p2);

		Patient p3 = new Patient("fuyty006", prPass, "Tianyi Fu", prAddress, prEmail, prPhone, prDate, "01356781234",
				"male");
		members.add(p3);

		Patient p4 = new Patient("jiajy027", prPass, "Jialin Jia", prAddress, prEmail, prPhone, prDate, "01356781234",
				"male");
		members.add(p4);

		Patient p5 = new Patient("mohsy063", prPass, "Sushant Mohite", prAddress, prEmail, prPhone, prDate,
				"01356781234", "male");
		members.add(p5);

		
		members.add(new Patient("zengy004", prPass, "Guandi Zeng", prAddress, prEmail, prPhone, prDate, "01356781234",
				"male"));

		p1.gettPlan().add(new Treatment(doc13, "You will have to take an appointment with our specialist Dr. Young. Please check your timetable for more details about the appointment.", new Date(2020, 1, 1), Treatment.Types.APPOINTMENT  ));
		p1.gettPlan().add(new Treatment(doc12, "You will have to spend 10 minuteson daily basis conditioning your bicep by lifting 20KG at 10reps and do 3 sessions with 3 minute breaks, starting on the stated date", new Date(2019,12,12), Treatment.Types.EXCERCISE));
		p1.gettPlan().add(new Treatment(doc10, "Use the provided COMBO STIM+ Tens MACHINE to stimulate and relief pain in the affected area. For correct use please follow instruction in the video from the link: https://www.youtube.com/watch?v=Wo8igqC6-oY", new Date(2019,12,12), Treatment.Types.ACTIVITY));
		// debugging to code fill each doctor with all patients
		for (Member doctorM : members) {
			try {
				Practitioner doctor = (Practitioner) doctorM;
				for (Member patientM : members) {
					try {
						Patient patient = (Patient) patientM;

						doctor.getPatients().add(patient);
					} catch (Exception notPatient) {
					}
				}
			} catch (Exception notDocor) {
			}
		}

		// add equipment
		Equipment e1 = new Equipment("Excercise/fitness ball", "ID0001", "Cortex", "C3000");
		equipmentOwned.add(e1);

		Equipment e2 = new Equipment("Heart Monitor", "ID0002", "Sony", "S-HM300");
		equipmentOwned.add(e2);

		Equipment e3 = new Equipment("Professional Cupping Therapy Equipment Set", "ID0003", "Hansol", "BU-HANG");
		equipmentOwned.add(e3);

		Equipment e4 = new Equipment("3 in one TENS, EMS and MASSAGE Combo Unit ", "ID0004", "Caremax", "RC3");
		equipmentOwned.add(e4);

		Equipment e5 = new Equipment("COMBO STIM+ Tens MACHINE 2 in 1unit", "ID0005", "PHYSIO", "EV-806P");
		equipmentOwned.add(e5);

		Equipment e6 = new Equipment("Medical Easy On PC Based Spirometer", "ID0006", "NDD", "AG");
		equipmentOwned.add(e6);

		Equipment e7 = new Equipment("Automatic External Defibrillator (AED)", "ID0007", "CardiacScience", "G3Plus");
		equipmentOwned.add(e7);

		Equipment e8 = new Equipment("Short Moon Boot Cam-Walker ", "ID0008", "AlcareOrtho", "AOSM-3");
		equipmentOwned.add(e8);

		Equipment e9 = new Equipment("Seat Walker Territory 130kg", "ID0009", "CareQuip", "c1a187");
		equipmentOwned.add(e9);

	}
}
