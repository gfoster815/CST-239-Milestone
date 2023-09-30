package test;


import org.junit.Assert;
import org.junit.Test;
import app.Armor;


public class ArmorTests {

	@Test
	public void testBasicGetters() {

		Armor testProduct = new Armor("TestProduct", "Test", 9.99, 5);

		Assert.assertEquals("TestProduct", testProduct.getName());
		Assert.assertEquals("Test", testProduct.getDescription());
		Assert.assertEquals(9.99, testProduct.getPrice(), 0);
		Assert.assertEquals(5, testProduct.getQuantity());

	}
	
	@Test
	public void testBasicSetters() {

		Armor testProduct = new Armor("TestProduct", "Test", 9.99, 5);

		testProduct.setName("TestChange");
		Assert.assertEquals("TestChange", testProduct.getName());

		testProduct.setDescription("descriptionChange");
		Assert.assertEquals("descriptionChange", testProduct.getDescription());

		testProduct.setPrice(14.99);
		Assert.assertEquals(14.99, testProduct.getPrice(), 0);

		testProduct.setQuantity(10);
		Assert.assertEquals(10, testProduct.getQuantity());

	}

	@Test
	public void testCustomQuantityChanges() {

		Armor testProduct1 = new Armor("TestProduct", "Test", 9.99, 5);

		testProduct1.incrementQuantity();
		Assert.assertEquals(6, testProduct1.getQuantity());

		Armor testProduct2 = new Armor("TestProduct", "Test", 9.99, 5);

		testProduct2.decrementQuantity();
		Assert.assertEquals(4, testProduct2.getQuantity());

		Armor testProduct3 = new Armor("TestProduct", "Test", 9.99, 5);

		testProduct3.incrementQuantity(5);
		Assert.assertEquals(10, testProduct3.getQuantity());

	}

}
