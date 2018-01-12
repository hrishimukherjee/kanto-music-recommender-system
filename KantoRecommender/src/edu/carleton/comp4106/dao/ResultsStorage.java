package edu.carleton.comp4106.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import edu.carleton.comp4106.utils.Constants;

public class ResultsStorage {
	
	private static ResultsStorage instance;
	
	private static final String DB_PATH = Constants.RESULTS_DIRECTORY;
	private static final boolean APPEND = true;
	
	private ResultsStorage() {
		
	}
	
	public static ResultsStorage getInstance() {
		if(instance == null) {
			instance = new ResultsStorage();
		}
		
		return instance;
	}
	
	/**
	 * Saves the results related to each query
	 * in a file with name "userId".txt
	 * 
	 * @param searchResults key - query, value - search results
	 */
	public void saveResults(int userId, String[] queries, String[][] results) {
		final String filePath = DB_PATH + "/" + userId + ".txt";
		
		try {
			PrintWriter writer = new PrintWriter(
					new FileWriter(filePath, APPEND));
			
			for(int i = 0; i < queries.length; i++) {
				writer.println(queries[i]);
				
				for(int j = 0; j < results[i].length; j++) {
					writer.println(results[i][j]);
				}
				
				writer.println();
			}
			
			writer.flush();
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
