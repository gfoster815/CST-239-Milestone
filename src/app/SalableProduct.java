package app;

public class SalableProduct {
	private String name;
	private String description;
	private double price;
	private int quantity;

	public SalableProduct(String Name, String Description, double Price) {
		this.name = Name;
		this.description = Description;
		this.price = Price;
		quantity = 3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString(SalableProduct product) {
		return name + " $" + price;
	}

	public void decrementQuantity() {
		this.quantity--;
	}

	public void incrementQuantity() {
		this.quantity++;
	}

}
