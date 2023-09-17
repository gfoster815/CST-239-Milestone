package app;

/**
 * Creates SalableProduct class
 */
public class SalableProduct implements Comparable<SalableProduct> {
	/**
	 * String name that identifies the name of a product
	 */
	private String name;
	/**
	 * String description that gives a description of the product
	 */
	private String description;
	/**
	 * double that gives the price the product
	 */
	private double price;
	/**
	 * int gives the quantity of the product
	 */
	private int quantity;

	public SalableProduct() {

	}

	/**
	 * creates object SalabaleProduct
	 * 
	 * @param Name        initialized
	 * @param Description initialized
	 * @param Price       initialized
	 */
	public SalableProduct(String Name, String Description, double Price, int quantity) {
		this.name = Name;
		this.description = Description;
		this.price = Price;
		this.quantity = quantity;

	}

	/**
	 * @return name of product
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name sets name of product
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return description of product
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description is set for product
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return price of product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price is set for product
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return quantity of product
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity of product is set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * reduces quantity by 1
	 */
	public void decrementQuantity() {
		this.quantity--;
	}

	/**
	 * increases quantity by 1
	 */
	public void incrementQuantity() {
		this.quantity++;
	}

	@Override
	public int compareTo(SalableProduct o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
