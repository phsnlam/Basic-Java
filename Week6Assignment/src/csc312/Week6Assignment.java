package csc312;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/* You must complete the AuthorshipInformation Annotation to have 2 attributes: author and course
*
 * The class Week6Assignment must have the annotation  AuthorshipInformation, with author as "John Smith" and 
 * for the course "CSC312"
 * 
 * If you do not do it correctly, the test testAnnotationIsImplementedCorrectly will fail
 * 
 */

public class Week6Assignment {
	
	// you must seperate the input stream in String
	// \n is the seperator, and it must not be included in the extracted String
	//
	// you must use stream for your implementation
	//
	public String[] tokenizeUsingStream( char  charArray[] ) {
		try {
			
		String strs[] = new String[1];
		
		byte byteStream[] = new String(charArray).getBytes();
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteStream);
		
		byteArrayInputStream.read();
		
		String str = new String(byteStream);
		strs = str.split("\n");
		
		byteArrayInputStream.close();
		
		return strs;
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
			return null;
		}
	}

	
	public String getLastWord( byte [] bytes ) {
		
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
			ObjectInputStream objIn = new ObjectInputStream(byteIn);
			String str[] = (String[]) objIn.readObject();
			objIn.close();

			return str[str.length-1];
			
			
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
				return null;
			}
		}
	
	//
	// for NY: New York
	// for VT: Vermont
	//
	// you must implement it, by adding additional information to the Enum State
	//
	public String getStateName( State state ) {
		String str = "";
		for (State someState: State.values())
		{
			str = someState.getName();
			return str;
		}
		return null;
		
	}

}
