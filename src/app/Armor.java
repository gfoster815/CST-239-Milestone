package app;

public class Armor extends SalableProduct {
	
	private String areaofProtection;
	
	public Armor(String name, String description, double price, String areaOfProtection) {
		super(name, description, price);
		this.areaofProtection = areaOfProtection;
		
	}
}
