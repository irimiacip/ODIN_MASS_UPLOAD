package ODIN_VALIDATOR_APITEST;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class App2 {
	
	final static Logger logger = Logger.getLogger(App2.class);

	@Test(priority=1)
		public static void test1() {
			System.out.println("test 1");			
		}
	@Test(priority=2)
	public static void test2() {
		System.out.println("test 2");
	}
	
}

