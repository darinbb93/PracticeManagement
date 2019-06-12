package Data;

import java.util.Date;
/**	
 * This is the Treatment Class representing what data is contained 
 * by each Treatment block a treatment plan is simply a series of
 * treatment blocks
 * 
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class Treatment {
	private Practitioner doctor;
	private String instructions;
	private Date date;
	private Types type;
	public enum Types { APPOINTMENT, EXCERCISE, ACTIVITY}
	
	
	
	public Treatment(Practitioner doctor, String instructions, Date date, Types type) {
		super();
		this.doctor = doctor;
		this.instructions = instructions;
		this.date = date;
		this.type = type;
	}

	
	public Practitioner getDoctor() {
		return doctor;
	}
	public void setDoctor(Practitioner doctor) {
		this.doctor = doctor;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	
	//overriding to string to display in listview
	@Override
	public String toString() {
		String theString = "";
		switch (type) {
		case APPOINTMENT:
			theString = "Appointment with: " + doctor.getName();
			break;
		case EXCERCISE:
			theString = "Excercise: " + instructions.substring(0, 10);
			break;
		case ACTIVITY:
			theString = "Activity: " + instructions.substring(0, 10);
			break;

		default:
			break;
		} 
		return theString;
	}
}
