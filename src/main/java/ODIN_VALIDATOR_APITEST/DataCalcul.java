package ODIN_VALIDATOR_APITEST;
import static ODIN_VALIDATOR_APITEST.Consts.FILEPATH_VALIDATOR;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.omg.Messaging.SyncScopeHelper;

public class DataCalcul {

	public static String returnDate(String date_required, Integer i) throws Exception {
		String aaa="";
		Calendar time = Calendar.getInstance();//time.getTime()
		
		if(date_required.contains("today")) {
			Date today = time.getTime();
			time.add(Calendar.DAY_OF_YEAR, 1);
			time.add(Calendar.DATE, 1);  
			String TIMESTAMP_FORMAT = "dd.MM.yyyy HH:mm:ss.SSSSSS";
			String[] DATE_FORMAT = new String[] {"dd.MM.yyyy", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss'Z'" , 
			            "MM.dd.yyyy", "MM.d.yyyy", "dd.M.yyyy", "M.d.yyyy", "d.M.yyyy", "d.MM.yyyy", "M.dd.yyyy"};
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT[i]);
	        SimpleDateFormat timestampFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
	        aaa= dateFormat.format(today);
		}else if(date_required.contains("tomorrow")) {
			Date today = time.getTime();
			Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
			time.add(Calendar.DATE, 1);  
			String TIMESTAMP_FORMAT = "dd.MM.yyyy HH:mm:ss.SSSSSS";
			String[] DATE_FORMAT = new String[] {"dd.MM.yyyy", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss'Z'" , 
			            "MM.dd.yyyy", "MM.d.yyyy", "dd.M.yyyy", "M.d.yyyy", "d.M.yyyy", "d.MM.yyyy", "M.dd.yyyy"};
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT[i]);
	        SimpleDateFormat timestampFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
	        aaa= dateFormat.format(tomorrow);
		}else {
			Date today = time.getTime();
			Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));
			time.add(Calendar.DATE, 1);  
			String TIMESTAMP_FORMAT = "dd.MM.yyyy HH:mm:ss.SSSSSS";
			String[] DATE_FORMAT = new String[] {"dd.MM.yyyy", "yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ss'Z'" , 
			            "MM.dd.yyyy", "MM.d.yyyy", "dd.M.yyyy", "M.d.yyyy", "d.M.yyyy", "d.MM.yyyy", "M.dd.yyyy" ,"dd-MM-YY"};
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT[i]);
	        SimpleDateFormat timestampFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
	        aaa= dateFormat.format(yesterday);
		}

        return aaa;
	}
	public static String fomatDay(String  actualDate){
		String formated_date=null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MMM-yy", Locale.ENGLISH);
		LocalDate ld = LocalDate.parse(actualDate, dtf);
		String date = dtf2.format(ld);
		formated_date = date.toUpperCase();
		return formated_date;
	}
	
	public static String getDayPlus1() {	
		String zzz = null;
		Date date = new Date();
		long ltime=date.getTime()+1*24*60*60*1000;
		Date today1=new Date(ltime);			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		zzz = formatter.format(today1).toString().toUpperCase();
		return zzz;			
	}

	public static String getDay() {	
		String zzz = null;
		Date date = new Date();
		long ltime=date.getTime();
		Date today1=new Date(ltime);			
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		zzz = formatter.format(today1).toString().toUpperCase();
		return zzz;			
	}
	
	public static String data_special() throws Exception {
		String data_special=null;
		Date date = new Date();   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
	//	int a = calendar.get(Calendar.HOUR);        // gets hour in 12h format
		//int b = calendar.get(Calendar.MINUTE);
	//	String hour = String.valueOf(a-1); 		
		Instant now = Instant.now();
		ZonedDateTime chicago = now.atZone(ZoneId.of("Europe/Paris"));
		String hour = (String)chicago.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL));
		System.out.println(hour);
		hour = hour.substring(0,1);
		System.out.println(hour);
		
	//	String minutes = String.valueOf(b);
		System.out.println(hour);
		//System.out.println(minutes);
		String day = DataCalcul.fomatDay(getDay());
		System.out.println(day);
		
		data_special = day+" "+hour;//+"."+minutes;
		System.out.println("data ce va fi inserata : "+ data_special);
		if(hour.contains("10")||hour.contains("11")||hour.contains("12")) {
			data_special = day+" "+hour;//+"."+minutes;
			System.out.println("data ce va fi inserata : "+ data_special);
		}else {
			data_special = day+" "+"0"+hour;//+"."+minutes;
			System.out.println("data ce va fi inserata : "+ data_special);
		}
		
		return data_special;		
	}
	

}
