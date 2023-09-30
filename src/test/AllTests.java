package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArmorTests.class, HealthTests.class, InventoryManagerTests.class, SalableProductTests.class,
		WeaponTests.class })
public class AllTests {

}
