package app;

import java.util.ArrayList;

public class ShoppingCart {
	private ArrayList<SalableProduct> cart;

	public ShoppingCart() {
		cart = new ArrayList<SalableProduct>();

	}

	public void addItemToCart(SalableProduct product) {
		cart.add(product);

	}

	public void removeItemFromCart(String name) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getName().equals(name)) {
				cart.remove(i);
				break;
			}
		}
		// TODO: add item back into inventory

	}

	public void showCart() {
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getName() + " $" + cart.get(i).getPrice());
		}
	}

	public void itemsPurchased() {
		for (int i = 0; i < cart.size(); i++) {
				cart.get(i).decrementQuantity();

		}

	}

	public void clearCart() {
		cart.clear();
	}

}
