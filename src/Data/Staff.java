package Data;

public class Staff extends Member{
	/**	
	 * This is the class acts as an expansion in case any other
	 * staff members require access to the system 
	 * 
	 * @author Darin Bogdanov  - bogdb001
	 *	
	 */
	public types job; 
	
	public enum types {RECEPTION, MAINTENANCE, CLEANER}

}
