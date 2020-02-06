package ODIN_VALIDATOR_APITEST;
import static ODIN_VALIDATOR_APITEST.Consts.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.custommonkey.xmlunit.XMLUnit;
public class ExpectedResults {

	
	public static String  readExpected(String path_expected ,String expectedXml, String datasystem, Integer i) throws Exception {
		
		String data_system = DataCalcul.returnDate(datasystem,i);

		
		FileInputStream fis1 = new FileInputStream(path_expected + expectedXml);		 
		 BufferedReader expected = new BufferedReader(new InputStreamReader(fis1));

		 String strExpected = org.apache.commons.io.IOUtils.toString(expected);
		
		 strExpected = strExpected.replace("DATE", data_system);
		 
		 XMLUnit.setIgnoreWhitespace(true); 	
		 		 
		return strExpected;
	}
	

	
}
