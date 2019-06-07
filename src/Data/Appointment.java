package Data;

import java.util.Date;
/**	
 * This is the Appointment Data class representing and holding 
 * data for each appointment made
 * 
 * @author Darin Bogdanov  - bogdb001
 *	
 */
public class Appointment {
	private String reason;
	private String findings;
	private String procedures;
	private String outcome;
	private boolean future;
	private Date date;
	

	public Appointment(String reason, String findings, String procedures, String outcome) {		
		this.reason = reason;
		this.findings = findings;
		this.procedures = procedures;
		this.outcome = outcome;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getFindings() {
		return findings;
	}
	public void setFindings(String findings) {
		this.findings = findings;
	}
	public String getProcedures() {
		return procedures;
	}
	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}	
	
	public boolean isFuture() {
		return future;
	}

	public void setFuture(boolean future) {
		this.future = future;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
