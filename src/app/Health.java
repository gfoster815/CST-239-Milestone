package app;

public class Health extends SalableProduct {
	
	private int healthGained;
	
	public Health(String name, String description, double price, int healthGained) {
		super(name, description, price);
		this.healthGained = healthGained;
	}

}
