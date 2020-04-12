package csc313;

//Test cases for Project 1

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

//These tests were taken from Week 8 Assignment, I used JUnit 5 by default when I created the test case. 
public class Project1Test {

	@Test
	public void testValidURL() {
		Project1 project1 = new Project1();
        assertEquals( new Character('E'), project1.AccessURL("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=b5"));
    }
	
    @Test
    public void testServerError() {
    	Project1 project1 = new Project1();
        assertEquals( null, project1.AccessURL("https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z99"));
    }

    @Test
    public void testServerForbidden() {
    	Project1 project1 = new Project1();
        assertEquals( null, project1.AccessURL("https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z88"));
    }

    @Test
    public void testInvalidDomain() {
    	Project1 project1 = new Project1();
    	assertEquals( null, project1.AccessURL("https://aninvaliddomainname.com"));
    }
	

}
