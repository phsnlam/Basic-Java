package csc313;

/* Project 1 - Word Search Game - by <Jake Pham>
 * 
 * with the help of W3Schools, TutorialsPoint, Oracle docs and javatpoint.
 * Reading PDF lectures on 2D Arrays also helped a lot;
 * code and comments were written from scratch 100% by me;
 * connection methods and tests were taken from Week 8 Assignment.
 * 
 * The general idea of the program is:
 * 1 - Connect to the word list URL to get the list of words in the form of
 * an ArrayList.
 * 2 - Set up a 5x5 grid as a 2D array.
 * 3 - Fill up each cell with the letter corresponding to its coordinates.
 * 4 - Loop through each row and column, put together words as we go and compare
 * them to those in the ArrayList of words.
 * 5 - Rinse and repeat for each game.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Project1 {
	//Method to connect to the word list
	public static ArrayList<String> getWordList(String wordListUrl) {
		ArrayList<String> wordList = new ArrayList<String>();
		int attempts = 5;
		boolean running = true;
		
		/* Each time there is an error, attempts will reduce by 1. The program will
		 * stop trying to connect when count reaches 0.
		 */
		while(running) {
			try
			{
				URL wordLink = new URL(wordListUrl);
				HttpURLConnection wordListConn = (HttpURLConnection) wordLink.openConnection();
				
				int responseCode = wordListConn.getResponseCode();
				
				String retrieved;
				
				//If response code is 200, then it's a success. If anything else, then retry.
				switch(responseCode) {
					case(200): {
						BufferedReader reader = new BufferedReader(new InputStreamReader(wordListConn.getInputStream()));
						while((retrieved = reader.readLine()) != null) {
							wordList.add(retrieved);
						}
						reader.close();
						running = false;
						break;
					}
					case(403): 
						System.out.println("SC_FORBIDDEN");
						attempts--;
						break;
					case(500): 
						System.out.println("SC_INTERNAL_SERVER_ERROR");
						attempts--;
						break;
					default:
						break;
				}
			}
			catch(Exception someException) {
				someException.printStackTrace();
				attempts--;
			}
			if(attempts == 0) {
				running = false;
				System.out.println("Error! No retry attempts left!");
			}
		}

		return wordList;
	}
	
	//Method to retrieve character from each URL for the game
	public static Character AccessURL(String letterUrl) {
		Character content = null;
		int attempts = 5;
		boolean running = true;
		
		//Retries up to 5 time in case there's a connection error
		while(running) {
			try
			{
				URL gameLink = new URL(letterUrl);
				HttpURLConnection gameConnection = (HttpURLConnection) gameLink.openConnection();
				
				int responseCode = gameConnection.getResponseCode();
			
				String retrieved;
				
				//If response code is 200, then it's a success. If anything else, then retry.
				switch(responseCode) {
					case(200):{
						BufferedReader reader = new BufferedReader(new InputStreamReader(gameConnection.getInputStream()));
						while((retrieved = reader.readLine()) != null) {
							content = retrieved.charAt(0);
						}
						reader.close();
						running = false;
						break;
					}
					case(403): 
						System.out.println("SC_FORBIDDEN");
						attempts--;
						break;
					case(500): 
						System.out.println("SC_INTERNAL_SERVER_ERROR");
						attempts--;
						break;
					default:
						break;
				}
			}
			catch(Exception someException) {
				someException.printStackTrace();
				attempts--;
			}
			if(attempts == 0) {
				running = false;
				System.out.println("Error! No retry attempts left!");
			}
		}

		return content;
	}
	
	//Return the URL that links to each cell in the grid
	public static String GameMaker(int gameNumber, int row, int column) {
		String letterURL;
		
		/* This HashMap links the "number" of the columns with the corresponding letter.
		 * This way, we can use the reference to puzzle to get the column letter instead.
		 * Specifically: A-E will replace 1-5 when we use the get method.
		 */
        HashMap<Integer, Character> puzzle = new HashMap<>();
        puzzle.put(1, 'A');
        puzzle.put(2, 'B');
        puzzle.put(3, 'C');
        puzzle.put(4, 'D');
        puzzle.put(5, 'E');
        
        //Put together the URL for each letter
        letterURL = ("https://wordfinder-001.appspot.com/wordfinder?game=" + gameNumber + "&pos=" + puzzle.get(column) + row);
        
		return (letterURL);
	}
	
	//Return the grid with the words inside of it.
	public static char[][] GridCreator(int gameNumber) {
		char[][] grid = new char[5][5];
		String cellURL;
		char letter;

		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				cellURL = GameMaker(gameNumber, (row+1), (col+1));
				letter = AccessURL(cellURL);
				grid[row][col] = letter;
			}
		}
		return grid;
	}
	
	//The complete method.
	private static void playGame(int gameNumber) {
		//Get the list of word from the link
		ArrayList<String> wordList = getWordList("https://wordfinder-001.appspot.com/word.txt");
		
		//Create the grid for the game using a 2D Array
		char[][] gameGrid = GridCreator(gameNumber);
		
		//StringBuilder is mutable so I prefer it over String
		StringBuilder wordH = new StringBuilder();
		StringBuilder wordV = new StringBuilder();
		
		/* This HashMap serves the same purpose as previously used, but for 0-4 instead
		 * of 1-5 so that the loops would be less confusing to deal with.
		 */
		HashMap<Integer, Character> puzzle = new HashMap<>();
        puzzle.put(0, 'A');
        puzzle.put(1, 'B');
        puzzle.put(2, 'C');
        puzzle.put(3, 'D');
        puzzle.put(4, 'E');
        
        /* This is a rather brute force method, but it works well. Loop though each
         * row in the grid, then put a nested loop for each column. Then, set up
         * conditions based on the current position.
         *  
         * The idea is using append to add characters that are <1 and 2> cells <to the right> 
         * and <below> whichever cell we are at, then check the list of words to see if
         * the combinations of the 3 characters are in the list, then empty the StringBuilder
         * object so that we can do it again for the next cell. The 4 bottom right cells are
         * skipped entirely because we cannot form any words from there.
         * 
         * Time complexity should be O(n^3) for 3 layers of loops.
         */
        for(int row = 0; row < gameGrid.length; row++) {
        	for(int col = 0; col < gameGrid[row].length; col++) {
        		/* [x][x][x][ ][ ]
        		 * [x][x][x][ ][ ]
        		 * [x][x][x][ ][ ]
        		 * [ ][ ][ ][ ][ ]
        		 * [ ][ ][ ][ ][ ]
        		 */
        		if(row < 3 && col < 3) {
        			for(int i = col; i < col +3; i++) {
        				wordH.append(gameGrid[row][i]);
        			}
            		for(int j = row; j < row +3; j++) {
        				wordV.append(gameGrid[j][col]);
        			}
        		}
        		/* [ ][ ][ ][x][x]
        		 * [ ][ ][ ][x][x]
        		 * [ ][ ][ ][x][x]
        		 * [ ][ ][ ][ ][ ]
        		 * [ ][ ][ ][ ][ ]
        		 */
        		else if(row < 3 && col >= 3) {
        			wordH.setLength(0);
            		for(int j = row; j < row +3; j++) {
        				wordV.append(gameGrid[j][col]);
        			}
        		}
        		/* [ ][ ][ ][ ][ ]
        		 * [ ][ ][ ][ ][ ]
        		 * [ ][ ][ ][ ][ ]
        		 * [x][x][x][ ][ ]
        		 * [x][x][x][ ][ ]
        		 */
        		else if(row >= 3 && col < 3) {
        			for(int i = col; i < col +3; i++) {
        				wordH.append(gameGrid[row][i]);
        			}
        			wordV.setLength(0);
        		}
        		/* [ ][ ][ ][ ][ ]
        		 * [ ][ ][ ][ ][ ]
        		 * [ ][ ][ ][ ][ ]
        		 * [ ][ ][ ][x][x]
        		 * [ ][ ][ ][x][x]
        		 */
        		else if(row >= 3 && col >= 3) {
        			wordH.setLength(0);
        			wordV.setLength(0);
        		}

        		while(wordH.length() >= 3) {
        			if(wordList.contains(wordH.toString())) {
        				System.out.println("Game " + gameNumber + " word: " + wordH + "\n" +
        									"Location: " + (puzzle.get(col)) + (row+1) + ":" + 
        									puzzle.get(col+2) + (row+1));
        			}
        			wordH.setLength(0);
        		}
        		while(wordV.length() >= 3) {
        			if(wordList.contains(wordV.toString())) {
        				System.out.println("Game " + gameNumber + " word: " + wordV + "\n" +
											"Location: " + puzzle.get(col) + (row+1) + ":" +
											puzzle.get(col) + (row+3));
        			}
        			wordV.setLength(0);
        		}        		
        	}
        }

	}
	
	//A simple main to run everything.
	public static void main(String[] args) {
		for(int g = 1; g < 4; g++) {
			playGame(g);
		}
	}
}
