package csc312;

import java.util.Calendar;
import java.util.function.BiFunction;
import java.util.Random;
import java.util.Date;

public class Week4Assignment {
	
	/* must produce a random number between 0 and 4 */
	public int produceRandomNumber() {
		Random ran = new Random();
		int n = ran.nextInt(4);
		return n;
	}
	
	public BiFunction<String, String, Boolean>  searchFunction() {
		BiFunction<String, String, Boolean> mySearchFunction = (String s1, String s2)-> {
			Boolean bool=s2.contains(s1);
			return bool; };
		
		return mySearchFunction;
	}

	/* 
	 *  You should expect 5 parameters, all Integer and you must print them with with no space in between them, but 
	 *  with up to 6 positions (with leading 0s if required) using a Formatter
	 * 
	 */
	public String formatAMessage( Integer... arguments ) {
		String s1 = new String();
		String s2 = new String();
			for (Integer i : arguments) {
				 s1 = String.format("%06d", i);
				 s2 += s1;
			}
		return s2;
	}


	/* 
	 *  For the calendar in input, return its year : https://docs.oracle.com/javase/7/docs/api/java/util/GregorianCalendar.html
	 */
	public int datetoYear( Calendar cal ) {
		Date date = new Date();
		cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}


	/* 
	 *  str : it is a 5 letters word
	 *  
	 *  c1 is the 1st character of a 3 characters long string
	 *  c3 is the 3rd character of a 3 characters long string
	 *  
	 *  You must check in str, if it is possible that the word beginning with c1 and ending with c3 can be in str
	 *  
	 *  it is case sensitive and the word is always left to right
	 */
	public boolean canItBeInThisString( String str, char c1, char c3) {
		for (int i=0; i < str.length(); i++){
			int index1 = str.indexOf(c1);
			int index2 = str.indexOf(c3);
			if (index2 - index1 == 2) {return true;}
		}
		
		return false;
	}

}
