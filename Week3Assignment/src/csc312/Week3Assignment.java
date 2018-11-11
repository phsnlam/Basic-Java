package csc312;

import java.net.*;
import java.io.*;
public class Week3Assignment {

	//you must implement the function to retrieve the content of a specific URL at https://wordfinder-001.appspot.com/wordfinder
	//
	//be aware that at random  the  ResponseCode may be SC_INTERNAL_SERVER_ERROR  or SC_INTERNAL_SERVER_ERROR instead of SC_OK
	//
	
	public Character getURL( String url) {
		String input;
		URL someurl;
		int resCode = 0;
		
		try {
			someurl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) someurl.openConnection();
			resCode = connection.getResponseCode();
			switch(resCode) {
			
			case 200:
				BufferedReader in = new BufferedReader(new InputStreamReader(someurl.openStream()));
				while ((input = in.readLine()) != null) {
				return input.charAt(0);
			}
			in.close();
			default:
				break;
				}
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		System.out.print(resCode);
		return null;
	}
}
