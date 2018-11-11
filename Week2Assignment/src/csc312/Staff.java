package csc312;

import java.util.*;

public class Staff implements Comparator<Staff>, Comparable<Staff> {
	
	private String name;
	private Integer bannerid;
	Staff(){}
	
	public Staff( int bannerid,  String name) {
		this.bannerid = new Integer( bannerid );
		this.name 	  = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBannerid() {
		return bannerid;
	}
	public void setBannerid(Integer bannerid) {
		this.bannerid = bannerid;
	}

	
	public int compareTo(Staff st) {
		return (this.name).compareTo(st.name);
	}

	
	public int compare(Staff st, Staff st1) {
		return st.bannerid - st1.bannerid;
	}
	
	

}
