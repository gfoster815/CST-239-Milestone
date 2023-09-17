package app;

import java.util.Comparator;

public class SortByPriceComparator implements Comparator<SalableProduct> {

	@Override
	public int compare(SalableProduct firstProduct, SalableProduct secondProduct) {

		if (firstProduct.getPrice() > secondProduct.getPrice()) {
			return 1;
		}

		if (firstProduct.getPrice() < secondProduct.getPrice()) {

			return -1;
		}
		
		return 0;

	}
}
