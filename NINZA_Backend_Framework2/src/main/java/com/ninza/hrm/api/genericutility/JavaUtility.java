package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
/**
 * get the random number, in the range of 0-5000
 * @return
 */
	public int getRandomNumber() {
		Random random = new Random();
		int ranInt = random.nextInt(1000);
		return ranInt;
	}
	

	public String date() {
		Date d = new Date();
		String system_date = d.toString().replace(" ", "").replace(":", "_");
		return system_date;

	}
	/**
	 * get the system date based on YYYY-DD-MM format
	 * @return
	 */
	public String getSystemDateYYYYMMDD() {
		Date d = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String date = sdf.format(d);
		return date;
	}
	/**
	 * get the required date based on YYYY-DD-MM format
	 * @return
	 */

	public String getRequiredDateYYYYMMDD(int days) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sdf.format(cal.getTime());
		return reqDate;
	}

}
