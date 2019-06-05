package Data;

public class Appointment {
	private String reason;
	private String findings;
	private String procedures;
	private String outcome;
		
	
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
}
