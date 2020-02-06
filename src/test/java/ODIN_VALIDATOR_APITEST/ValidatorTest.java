package ODIN_VALIDATOR_APITEST;

import static ODIN_VALIDATOR_APITEST.ActualResults.obtainResponse_validator_v1;
import static ODIN_VALIDATOR_APITEST.Consts.FILEPATH_VALIDATOR;
import static ODIN_VALIDATOR_APITEST.Consts.LINK;
import static ODIN_VALIDATOR_APITEST.Consts.PROCESS;
import static ODIN_VALIDATOR_APITEST.Consts.*;
import static ODIN_VALIDATOR_APITEST.Consts.PATH_EXPECTED_VALIDATOR;
import static io.restassured.specification.ProxySpecification.host;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ValidatorTest {
	
	final static Logger logger = Logger.getLogger(ValidatorTest.class);
	
	@BeforeMethod

	public void startTest() {
		logger.info("=====================");
		logger.info("autentification......");
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.useRelaxedHTTPSValidation();
//		RestAssured.proxy = host("proxy.metro.ro").withPort(3128);
	}

	@Test (priority=1)
	public void test1() throws Exception {
		logger.info("TEST -- 1 --");
		logger.info("test for check response ==>http status 200<==");
		Response result = (Response)RestAssured.given().
				contentType("text/plain").				
				body(ReadBody.getBodyFromFilepath(FILEPATH_VALIDATOR, "test_1.xml")).
				put(LINK + PROCESS + VERSION_1).
				then().
				statusCode(200).contentType("application/xml").extract().response();
	}

	@Test (priority=2)
	public void test2() throws Exception {	
		logger.info("TEST -- 2 --");
		logger.info("test for  check ==>contain tag messageType<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_1.xml" , PATH_EXPECTED_VALIDATOR, "expected_1.xml","null",0);
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_1.xml");		
		String check = "<messageType>OK</messageType>";		  
		assertTrue(respond.contains(check));
	}


	@Test(priority=3)
	public void test3() throws Exception {
		logger.info("TEST -- 3 --");
		logger.info("test for check ==> contain tag status <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_1.xml" , PATH_EXPECTED_VALIDATOR, "expected_1.xml","null",0);
		String check = "<statuscode>0</statuscode>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_1.xml");		 
		assertEquals((StringUtils.countMatches(respond, check)), 2);  
	}

	
	@Test (priority=4)
	public void test4() throws Exception {
		logger.info("TEST -- 4 --");
		logger.info("test for ==>compare xml structure and content<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_1.xml" , PATH_EXPECTED_VALIDATOR, "expected_1.xml","null",0);				
	}
	
	@Test(priority=5)
	public void test5() throws Exception {
		logger.info("TEST -- 5 --");
		logger.info("test for ==>validate <elementName> <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_2.xml" , PATH_EXPECTED_VALIDATOR, "expected_2.xml","null",0);
		String check_statuscode = "<statuscode>-2</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_2.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 2);  
	}
	
	
	@Test(priority=6)
	public void test6() throws Exception {
		logger.info("TEST -- 6 --");
		logger.info("test for ==>validate the xml structure<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_3.xml" , PATH_EXPECTED_VALIDATOR, "expected_3.xml","null",0);
		String check_statuscode = "<statuscode>-2</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_3.xml");		
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2); 
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 2); 
	}
			
	@Test(priority=7)
	public void test7() throws Exception {
		logger.info("TEST for ==>validate minimum value accepted<==");
		logger.info("test -- 7 --");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_4.xml" , PATH_EXPECTED_VALIDATOR, "expected_4.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_4.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);		
	}
	
	@Test(priority=8)
	public void test8() throws Exception {
		logger.info("TEST for ==>validate maximum value accepted<==");
		logger.info("test -- 8 --");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_5.xml" , PATH_EXPECTED_VALIDATOR, "expected_5.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_5.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);		
	}
	
	@Test(priority=9)
	public void test9() throws Exception {
		logger.info("TEST for ==>validate maximum value +1 not accepted<==");
		logger.info("test -- 9 --");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_6.xml" , PATH_EXPECTED_VALIDATOR, "expected_6.xml","null",0);
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String check_brokenRule= "<brokenRule>maxValue</brokenRule>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_6.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
		assertEquals((StringUtils.countMatches(respond, check_brokenRule)), 1);
	}
	
	@Test(priority=10)
	public void test10() throws Exception {
		logger.info("TEST for ==>validate minimum value -1 not accepted<==");
		logger.info("test -- 10 --");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_7.xml" , PATH_EXPECTED_VALIDATOR, "expected_7.xml","null",0);
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String check_brokenRule= "<brokenRule>minValue</brokenRule>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_7.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
		assertEquals((StringUtils.countMatches(respond, check_brokenRule)), 1);
	}
	@Test(priority=11)
	public void test11() throws Exception {
		logger.info("TEST -- 11 --");
		logger.info("test for ==>validate variable<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_8.xml" , PATH_EXPECTED_VALIDATOR, "expected_8.xml","null",0);
		String check_statuscode = "<statuscode>-2</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_8.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 2);
	}
	
	@Test(priority=12)
	public void test12() throws Exception {
		logger.info("TEST -- 12 --");
		logger.info("test for ==>digit validation<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_9.xml" , PATH_EXPECTED_VALIDATOR, "expected_9.xml","null",0);
		String check_statuscode = "<statuscode>-2</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String check_brokenRule= "<brokenRule>digits</brokenRule>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_9.xml");	
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 2);
		assertEquals((StringUtils.countMatches(respond, check_brokenRule)), 1);
	}
	

	@Test(priority=13)
	public void test13() throws Exception {
		logger.info("TEST -- 13 --");
		logger.info("test for ==>validate min occur<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_10.xml" , PATH_EXPECTED_VALIDATOR, "expected_10.xml","null",0);
		String check_statuscode = "<statuscode>-2</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String check_brokenRule= "<brokenRule>minOccurs</brokenRule>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_10.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 2);
		assertEquals((StringUtils.countMatches(respond, check_brokenRule)), 1);
	}
	
	@Test(priority=14)
	public void test14() throws Exception {
		logger.info("TEST -- 14 --");
		logger.info("test for ==>validate max occur<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_11.xml" , PATH_EXPECTED_VALIDATOR, "expected_11.xml","null",0);
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String check_brokenRule= "<brokenRule>maxOccurs</brokenRule>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_11.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 2);
		assertEquals((StringUtils.countMatches(respond, check_brokenRule)), 2);
	}	
	
 // validate the atribute
	
	@Test(priority=15)
	public void test15() throws Exception {
		logger.info("TEST -- 15 --");
		logger.info("test for ==> digit min occur atribute <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_12.xml" , PATH_EXPECTED_VALIDATOR, "expected_12.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_12.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}	
	
	@Test(priority=16)
	public void test16() throws Exception {
		logger.info("TEST -- 16 --");
		logger.info("test for ==> digit max occur atribute <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_13.xml" , PATH_EXPECTED_VALIDATOR, "expected_13.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_13.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=17)
	public void test17() throws Exception {
		logger.info("TEST -- 17 --");
		logger.info("test for ==> digit max occur+1 atribute <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_14.xml" , PATH_EXPECTED_VALIDATOR, "expected_14.xml","null",0);
		String check_statuscode = "<statuscode>-2</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String check_brokenRule= "<brokenRule>minOccurs</brokenRule>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_14.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 2);
		assertEquals((StringUtils.countMatches(respond, check_brokenRule)), 1);
	}
	
	@Test(priority=18)
	public void test18() throws Exception {
		logger.info("TEST -- 18 --");
		logger.info("test for ==> validate  minLength atribute <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_15.xml" , PATH_EXPECTED_VALIDATOR, "expected_15.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_15.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=19)
	public void test19() throws Exception {
		logger.info("TEST -- 19 --");
		logger.info("test for ==> validate  maxLength atribute <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_16.xml" , PATH_EXPECTED_VALIDATOR, "expected_16.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_16.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=20)
	public void test20() throws Exception {
		logger.info("TEST -- 20 --");
		logger.info("test for ==> validate  minLength-1 atribute <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_17.xml" , PATH_EXPECTED_VALIDATOR, "expected_17.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_17.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=21)
	public void test21() throws Exception {
		logger.info("TEST -- 21 --");
		logger.info("test for ==> validate  maxLength+1 atribute <==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_18.xml" , PATH_EXPECTED_VALIDATOR, "expected_18.xml","null",0);
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String check_brokenRule= "<brokenRule>maxLength</brokenRule>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_18.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
		assertEquals((StringUtils.countMatches(respond, check_brokenRule)), 1);
	}
	
	@Test(priority=22)
	public void test22() throws Exception {
		logger.info("TEST -- 22 --");
		logger.info("test for ==>validate  string atribute<==");
		XmlComparator.read_xml_expected(1, 1,FILEPATH_VALIDATOR, "test_19.xml" , PATH_EXPECTED_VALIDATOR, "expected_19.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = obtainResponse_validator_v1(FILEPATH_VALIDATOR, "test_19.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	//=========boolean vaiable ============
	@Test(priority=23)
	public void test23() throws Exception {
		logger.info("TEST -- 23 --");
		logger.info("test for ==>validate  boolean  variable false <==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_20.xml" , PATH_EXPECTED_VALIDATOR, "expected_20.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_20.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=24)
	public void test24() throws Exception {
		logger.info("TEST -- 24 --");
		logger.info("test for ==>validate  boolean varialve true<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_21.xml" , PATH_EXPECTED_VALIDATOR, "expected_21.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_21.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=25)
	public void test25() throws Exception {
		logger.info("TEST -- 25 --");
		logger.info("test for ==>validate  booleam max occurance true<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_22.xml" , PATH_EXPECTED_VALIDATOR, "expected_22.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_22.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=26)
	public void test26() throws Exception {
		logger.info("TEST -- 26 --");
		logger.info("test for ==>validate  booleam max occurance false<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_23.xml" , PATH_EXPECTED_VALIDATOR, "expected_23.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_23.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=27) 
	public void test27() throws Exception {
		logger.info("TEST -- 27 --");
		logger.info("test for ==>validate  booleam max occurance true/false<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_24.xml" , PATH_EXPECTED_VALIDATOR, "expected_24.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_24.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=28)
	public void test28() throws Exception {
		logger.info("TEST -- 28 --");
		logger.info("test for ==>validate  booleam max occurance false/true<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_25.xml" , PATH_EXPECTED_VALIDATOR, "expected_25.xml","null",0);
		String check_statuscode = "<statuscode>0</statuscode>";
		String check_messageType = "<messageType>OK</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_25.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=29)
	public void test29() throws Exception {
		logger.info("TEST -- 29 --");
		logger.info("test for ==>validate  boolean max occurance + 1<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_26.xml" , PATH_EXPECTED_VALIDATOR, "expected_26.xml","null",0);
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_26.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=30) 
	public void test30() throws Exception {
		logger.info("TEST -- 30 --");
		logger.info("test for ==>validate  boolean min occurance -1<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_27.xml" , PATH_EXPECTED_VALIDATOR, "expected_27.xml","null",0);
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_27.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=31)
	public void test31() throws Exception {
		logger.info("TEST -- 31 --");
		logger.info("test for ==>validate  boolean value<==");
		XmlComparator.read_xml_expected(1, 2,FILEPATH_VALIDATOR, "test_28.xml" , PATH_EXPECTED_VALIDATOR, "expected_28.xml","null",0);
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";
		String respond = ActualResults.obtainResponse_validator_v2(FILEPATH_VALIDATOR, "test_28.xml");		 
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	//======= finish boolean variable=======
	//======== test related to date=======
	@Test(priority=32)
	public void test32() throws Exception {
		logger.info("TEST -- 32 --");
		logger.info("test for ==>date - sysdate - <==");
		XmlComparator.read_xml_expected(1, 3,FILEPATH_VALIDATOR, "test_sysdate.xml" , PATH_EXPECTED_VALIDATOR, "expected_sysdate.xml" , "today",0);
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_sysdate.xml", "today",0);		
		String check_messageType = "<messageType>WARNING</messageType>";	
		String check_statuscode = "<statuscode>1</statuscode>";        
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=33)
	public void test33() throws Exception {
		logger.info("TEST -- 33 --");
		logger.info("test for ==> date - tomorrow - <==");		
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_tomorrow.xml", "tomorrow",0);
		String expected = ExpectedResults.readExpected(PATH_EXPECTED_VALIDATOR ,"expected_tomorrow.xml","tomorrow",0);
		XmlComparator.printDifferences(XmlComparator.compareXML(respond, expected));				
		String check_messageType = "<messageType>OK</messageType>";	
		String check_statuscode = "<statuscode>0</statuscode>";        
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=34)
	public void test34() throws Exception {	
		logger.info("TEST -- 34 --");
		logger.info("test for ==>date - yesterday - <==");
		XmlComparator.read_xml_expected(1, 3,FILEPATH_VALIDATOR, "test_sysdate.xml" , PATH_EXPECTED_VALIDATOR, "expected_yesterday.xml" , "yesterday",0);
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_yesterday.xml", "yesterday",0);		
		String check_messageType = "<messageType>WARNING</messageType>";	
		String check_statuscode = "<statuscode>1</statuscode>";        
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=35)
	public void test35() throws Exception {	
		logger.info("TEST -- 35 --");
		logger.info("test for ==>min occur -1  boolean <==");
		XmlComparator.read_xml_expected(1, 3,FILEPATH_VALIDATOR, "test_35.xml" , PATH_EXPECTED_VALIDATOR, "expected_35.xml" , "yesterday", 0);
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_35.xml", "yesterday", 0);		
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";       
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=36)  
	public void test36() throws Exception {
		logger.info("TEST -- 36 --");
		logger.info("test for ==>max occur +1  boolean <==");
		XmlComparator.read_xml_expected(1, 3,FILEPATH_VALIDATOR, "test_36.xml" , PATH_EXPECTED_VALIDATOR, "expected_36.xml" , "yesterday", 0);
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_36.xml", "today", 0);		
		String check_statuscode = "<statuscode>-1</statuscode>";
		String check_messageType = "<messageType>ERROR</messageType>";       
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=37)
	public void test37() throws Exception {	
		logger.info("TEST -- 37 --");
		logger.info("test for ==>date - sysdate - (date format changed)<==");
		XmlComparator.read_xml_expected(1, 3,FILEPATH_VALIDATOR, "test_sysdate.xml" , PATH_EXPECTED_VALIDATOR, "expected_sysdate.xml" , "today",1);
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_sysdate.xml", "today",0);		
		String check_messageType = "<messageType>WARNING</messageType>";	
		String check_statuscode = "<statuscode>1</statuscode>";        
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=38)
	public void test38() throws Exception {	
		logger.info("TEST -- 38 --");
		logger.info("test for ==> date - tomorrow - (date format changed)<==");
		//XmlComparator.read_xml_expected(1, 3,FILEPATH_VALIDATOR, "test_tomorrow.xml" , PATH_EXPECTED_VALIDATOR, "expected_tomorrow.xml" , "tomorrow"); // expected..problem with data
		//expected file should be changed dinamilcally
		
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_tomorrow.xml", "tomorrow",1);
		String expected = ExpectedResults.readExpected(PATH_EXPECTED_VALIDATOR ,"expected_tomorrow.xml","tomorrow",1);
		XmlComparator.printDifferences(XmlComparator.compareXML(respond, expected));				
		String check_messageType = "<messageType>OK</messageType>";	
		String check_statuscode = "<statuscode>0</statuscode>";        
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}
	
	@Test(priority=39)
	public void test39() throws Exception {	
		logger.info("TEST -- 39 --");
		logger.info("test for ==>date - yesterday -(date format changed) <==");
		XmlComparator.read_xml_expected(1, 3,FILEPATH_VALIDATOR, "test_sysdate.xml" , PATH_EXPECTED_VALIDATOR, "expected_yesterday.xml" , "yesterday",1);
		String respond = ActualResults.obtainResponse_validator_v3(FILEPATH_VALIDATOR, "test_yesterday.xml", "yesterday",0);		
		String check_messageType = "<messageType>WARNING</messageType>";	
		String check_statuscode = "<statuscode>1</statuscode>";        
		assertEquals((StringUtils.countMatches(respond, check_statuscode)), 2);  
		assertEquals((StringUtils.countMatches(respond, check_messageType)), 1);
	}

	// ====finish tests for date ======
	// === test for complex validation ====
	
	@Test(priority=40)
	public void test40() throws Exception {	
		logger.info("TEST -- 40 --");
		logger.info("test for ==>complex validation: two variable are null  <==");
		XmlComparator.read_xml_expected(1, 5,FILEPATH_VALIDATOR, "test_37.xml" , PATH_EXPECTED_VALIDATOR, "expected_37.xml" , "today",1);
	}

	@Test(priority=41)
	public void test41() throws Exception {	
		logger.info("TEST -- 41 --");
		logger.info("test for ==>complex validation: first variable is null  <==");
		XmlComparator.read_xml_expected(1, 5,FILEPATH_VALIDATOR, "test_38.xml" , PATH_EXPECTED_VALIDATOR, "expected_38.xml" , "today",1);
	}
	
	@Test(priority=42)
	public void test42() throws Exception {	
		logger.info("TEST -- 42 --");
		logger.info("test for ==>complex validation: second variable is null  <==");
		XmlComparator.read_xml_expected(1, 5,FILEPATH_VALIDATOR, "test_39.xml" , PATH_EXPECTED_VALIDATOR, "expected_39.xml" , "today",1);
	}
	
	@Test(priority=43)
	public void test43() throws Exception {	
		logger.info("TEST -- 43 --");
		logger.info("test for ==>complex validation: positivve situation  <==");
		XmlComparator.read_xml_expected(1, 5,FILEPATH_VALIDATOR, "test_40.xml" , PATH_EXPECTED_VALIDATOR, "expected_40.xml" , "today",1);
	}
	
	@Test(priority=44)
	public void test44() throws Exception {	
		logger.info("TEST -- 44 --");
		logger.info("test for ==>complex validation: column 1 > column 4  <==");
		XmlComparator.read_xml_expected(1, 6,FILEPATH_VALIDATOR, "test_41.xml" , PATH_EXPECTED_VALIDATOR, "expected_41.xml" , "today",1);
	}
	
	@Test(priority=45)   @Ignore  //DEFECCT ODIN 1129
	public void test45() throws Exception {	
		logger.info("TEST -- 45 --DE CLARIFICAT");
		logger.info("test for ==>complex validation: column 4 : [0-9]+$  (positive situation)<== ");
		XmlComparator.read_xml_expected(1, 7,FILEPATH_VALIDATOR, "test_42.xml" , PATH_EXPECTED_VALIDATOR, "expected_42.xml" , "today",1);
	}
	
	@Test(priority=46)  @Ignore
	public void test46() throws Exception {	
		logger.info("TEST -- 46 --DE CLARIFICAT");
		logger.info("test for ==>complex validation: column 4 : [0-9]+$  (negative test)  <==");
		XmlComparator.read_xml_expected(1, 7,FILEPATH_VALIDATOR, "test_43.xml" , PATH_EXPECTED_VALIDATOR, "expected_43.xml" , "today",1);
	}
	
	
	@Test(priority=47)  @Ignore
	public void test47() throws Exception {	
		logger.info("TEST -- 47 --DE CLARIFICAT");
		logger.info("test for ==>complex validation:column 4 : [0-9]+$  (negative test)  <==");
		XmlComparator.read_xml_expected(1, 7,FILEPATH_VALIDATOR, "test_44.xml" , PATH_EXPECTED_VALIDATOR, "expected_44.xml" , "today",1);
	}
	
	@Test(priority=48)  @Ignore
	public void test48() throws Exception {	
		logger.info("TEST -- 48 --DE CLARIFICAT");
		logger.info("test for ==>complex validation:column 4 : \\d+  (positive test)  <==");
		XmlComparator.read_xml_expected(1, 8,FILEPATH_VALIDATOR, "test_45.xml" , PATH_EXPECTED_VALIDATOR, "expected_45.xml" , "today",1);
	}
	
	@Test(priority=49)  @Ignore
	public void test49() throws Exception {	
		logger.info("TEST -- 49 --DE CLARIFICAT");
		logger.info("test for ==>complex validation:column 4 : \\d+  (negative test)  <==");
		XmlComparator.read_xml_expected(1, 8,FILEPATH_VALIDATOR, "test_46.xml" , PATH_EXPECTED_VALIDATOR, "expected_46.xml" , "today",1);
	}
	
	@Test(priority=50)  @Ignore
	public void test50() throws Exception {	
		logger.info("TEST -- 50 --DE CLARIFICAT");
		logger.info("test for ==>complex validation:column 5 : isEmpty  (positive test)  <==");
		XmlComparator.read_xml_expected(1, 8,FILEPATH_VALIDATOR, "test_47.xml" , PATH_EXPECTED_VALIDATOR, "expected_46.xml" , "today",1);
	}
	
	@Test(priority=51)  @Ignore
	public void test51() throws Exception {	
		logger.info("TEST -- 51 --DE CLARIFICAT");
		logger.info("test for ==>complex validation:column 5 : isEmpty  (negative test)  <==");
		XmlComparator.read_xml_expected(1, 8,FILEPATH_VALIDATOR, "test_48.xml" , PATH_EXPECTED_VALIDATOR, "expected_46.xml" , "today",1);
	}
	
		
	@AfterMethod
	public void endTest() {
		logger.info("logout....");
		//logger.info("=====================");
		//logger.info(" ");
	}

}

//mvn clean test -DtestngFile=testng.xml
