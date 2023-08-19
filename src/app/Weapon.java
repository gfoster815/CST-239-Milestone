package app;

public class Weapon extends SalableProduct {
	private String damageType;
	public Weapon(String name, String description, double price, String damageType) {
		super(name, description, price);
		this.damageType = damageType;
		
	}
}
