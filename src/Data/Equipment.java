package Data;

public class Equipment {
	private String description;	
	private String ID;
	private String brand;
	private String model;
	
		
	public Equipment(String description, String iD, String brand, String model) {
		super();
		this.description = description;
		ID = iD;
		this.brand = brand;
		this.model = model;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
}
