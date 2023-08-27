package app;
/**
 * Class armor that extends SalableProduct
 */
public class Armor extends SalableProduct {
	/**
	 * String areaOfProtection variable specific to Armor class
	 */
	private String areaOfProtection;
	
	public Armor(String name, String description, double price, String areaOfProtection) {
		super(name, description, price);
		this.areaOfProtection = areaOfProtection;
		
	}
}
