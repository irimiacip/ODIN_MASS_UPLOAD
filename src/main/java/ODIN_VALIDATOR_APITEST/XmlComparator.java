package ODIN_VALIDATOR_APITEST;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;
import static ODIN_VALIDATOR_APITEST.Consts.*;

public class XmlComparator {
	final static Logger logger = Logger.getLogger(XmlComparator.class);


    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void read_xml_expected(int microservice, int version, String path, String actualXml , String path_expected, String expectedXml , String day , Integer date_format_value) throws SAXException, IOException, Exception {

		FileInputStream fis1 = new FileInputStream(path_expected+ expectedXml);		 
		 BufferedReader expected = new BufferedReader(new InputStreamReader(fis1));
		 String strExpected = org.apache.commons.io.IOUtils.toString(expected);
		 XMLUnit.setIgnoreWhitespace(true); 		 
		 
		// List differences_validator = compareXML(ActualResults.obtainResponse(path, actualXml), strExpected); 
		// List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml), strExpected); 
		 
		 switch (microservice) {
		 case 1:
			    if(version == 1) {
					 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v1(path, actualXml), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }else if( version == 2) {
					 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v2(path, actualXml), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }else if (version == 3){
			    	 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v3(path, actualXml, day, date_format_value), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }else if (version == 5){
			    	 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v5(path, actualXml, day, date_format_value), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }else if (version == 6){
			    	 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v6(path, actualXml, day, date_format_value), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }else if (version == 7){
			    	 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v7(path, actualXml, day, date_format_value), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }else if (version == 8){
			    	 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v8(path, actualXml, day, date_format_value), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }else if (version == 9){
			    	 List differences_validator = compareXML(ActualResults.obtainResponse_validator_v9(path, actualXml, day, date_format_value), strExpected); 
		             // System.out.println("differences_validator");
		              printDifferences(differences_validator);
		              break;
			    }
		 case 2:
			 
			 
			 if(version == 1) {
				 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_1), strExpected);  
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if( version == 2) {
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_2), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 3){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_3), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 4){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_4), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 5){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_5), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 6){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_6), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 7){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_7), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 8){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_8), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 9){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_9), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 10){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_10), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 11){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_11), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 12){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_12), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }else if (version == 13){
		    	 List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_13), strExpected); 
	             // System.out.println("differences_validator");
	              printDifferences(differences_request_procesor);
	              break;
		    }
			 
			/* List differences_request_procesor = compareXML(ActualResults.obtainResponse_request_processor(path, actualXml,VERSION_PROCESSOR_1), strExpected); 
             //System.out.println("differences_request_procesor");
             printDifferences(differences_request_procesor);
             break;*/
		 case 3:
             System.out.println("differences_response");
           //  printDifferences(differences_response);
		 }
	
    }
    
    @SuppressWarnings("rawtypes")
	public static List compareXML(String string, String string2) throws SAXException, IOException { 
		 Diff xmlDiff = new Diff(string, string2); 		 		 
		 DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff); 		 
		 return detailXmlDiff.getAllDifferences(); 		 
		 } 
		 
		 public static void printDifferences(List<String> differences)  { 
			  
	     int totalDifferences = differences.size(); 
	     System.out.println("--------------------------------"); 
		 System.out.println("Total differences : " + totalDifferences);
		 System.out.println("---------------------------------"); 
			//logger.info("Total differences : " + totalDifferences);

		 		 		 
		 for(String difference : differences){
			 
			 System.out.println(difference); } 
		 
		 }
	 
}
