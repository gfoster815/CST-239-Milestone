package app;
/**
 * Class Weapon that extends SalableProduct
 */
public class Weapon extends SalableProduct {
	/**
	 * New variable damageType specific to Weapon
	 */
	private String damageType;
	public Weapon(String name, String description, double price, String damageType) {
		super(name, description, price);
		this.damageType = damageType;
		
	}
}
