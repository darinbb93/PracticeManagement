package Data;

import java.util.Date;

public class Treatment {
	private Practitioner doctor;
	private String instructions;
	private Date date;
	public Types type;
	public enum Types { APPOINTMENT, EXCERCISE, ACTIVITY}

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
}
