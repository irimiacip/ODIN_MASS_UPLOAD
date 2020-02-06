package ODIN_VALIDATOR_APITEST;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GetHour {

	public static void main(String[] args) {


		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);
		int second = now.get(Calendar.SECOND);
		int millis = now.get(Calendar.MILLISECOND);

		//System.out.printf("%d-%02d-%02d %02d:%02d:%02d.%03d", year, month, day, hour, minute, second, millis);
		
		
		
		Date now1 = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
		String format1 = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss.SSS", Locale.ENGLISH).format(now1);
		String format2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH).format(now1);
		String format3 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(now1);
		
		/*System.out.println(format1);
		System.out.println(format2);
		System.out.println(format3);*/
		
		ZoneId test = ZoneId.of("America/New_York");
		LocalDate today = LocalDate.now(test);
			
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z");
		//String formattedString = today.format(formatter);
		
		
		
		//System.out.println("Today's Local date : " + formattedString);
		
		
		Instant now55 = Instant.now();
		ZonedDateTime chicago = now55.atZone(ZoneId.of("Europe/Paris"));
		String xxx = (String)chicago.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL));
		System.out.println("Chicago formated: " + xxx);
	    
		System.out.println(xxx.substring(0,1));
		
	
	}
	
	

}
