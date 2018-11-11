package csc312;

import java.util.ArrayList;
import java.util.Collections;

public class Week2Assignment {

	public String reverseTheCharacter( String str) {
		String reverse = "";
		
		for (int i = str.length()-1; i>=0; i--) {
			reverse += str.charAt(i);
		}
			 
		return reverse;
	}
	
	public Integer findIntegerInACollection( int values[], int valueToFind ) {
		for (int x : values) {
			if (x == valueToFind) {return x;}
		}
		return null;
	}
	
	public ArrayList<Integer> sortAndReturnAnArrayList( int values[]) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (int x : values) { newList.add(x); }
		Collections.sort(newList);
		return newList;
	}
	
	public ArrayList<Staff> testSortAndReturnAnArrayListOfStaffByBannerId( Staff staffs[]) {
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		for (Staff st : staffs) {staffList.add(st);}
		Collections.sort(staffList, new Staff());
		return staffList;
	}
}
