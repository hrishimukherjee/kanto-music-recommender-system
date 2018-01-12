package edu.carleton.comp4106.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import edu.carleton.comp4106.utils.Constants;

public class QueryStorage {
	
	private static QueryStorage instance;
	
	private static final String DB_PATH = Constants.QUERY_DIRECTORY;
	private static final boolean APPEND = true;
	
	private QueryStorage() {
		
	}
	
	public static QueryStorage getInstance() {
		if(instance == null) {
			instance = new QueryStorage();
		}
		
		return instance;
	}
	
	/**
	 * Writes queries to a file
	 * with name "userId".txt
	 * in the queries directory.
	 * 
	 * @param userId ID of user who made queries
	 * @param queries the queries made by the user
	 */
	public void saveQueries(int userId, String[] queries) {
		final String filePath = DB_PATH + "/" + userId + ".txt";
		
		try {
			PrintWriter writer = new PrintWriter(
					new FileWriter(filePath, APPEND));
			
			for(int i = 0; i < queries.length; i++) {
				writer.println(queries[i]);
			}
			
			writer.flush();
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Extracts all the queries of a user.
	 * 
	 * @param userId ID of the user
	 * @return queries made by user
	 */
	public List<String> getQueries(int userId) {
		List<String> queries = null;
		final String filePath = DB_PATH + "/" + userId + ".txt";
		Path path = Paths.get(filePath);
		
		System.out.println(path);
		
		try {
			queries = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return queries;
	}

}
