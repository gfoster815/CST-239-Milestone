package app;
/**
 * Class Health that extends SalableProduct
 */
public class Health extends SalableProduct {
	/**
	 * int healthGained variable specific to Health class
	 */
	private int healthGained;
	
	public Health(String name, String description, double price, int healthGained) {
		super(name, description, price);
		this.healthGained = healthGained;
	}

}
