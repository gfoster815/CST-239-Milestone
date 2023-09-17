package app;

import java.util.Comparator;

public class SortByNameComparator implements Comparator<SalableProduct> {

	@Override
	public int compare(SalableProduct firstProduct, SalableProduct secondProduct) {
		int value = firstProduct.getName().compareToIgnoreCase(secondProduct.getName());

		return value;
	}

}
