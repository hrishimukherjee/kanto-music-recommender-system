package edu.carleton.comp4106.scripts;

import java.util.Arrays;
import java.util.List;

import edu.carleton.comp4106.dao.UserQueryCollection;
import edu.carleton.comp4106.model.UserQuery;
import edu.carleton.comp4106.utils.ArrayUtils;

public class GenerateUserData {
	
	private static final int NUM_USERS = 1000;
	
	public static void main(String[] args) {
		System.out.println();
		System.out.println("******************************");
		System.out.println("Script: GENERATE USER DATA");
		System.out.println("Initializing...");
		
		// Create Queries for each user and save them
		for(int user = 1; user <= NUM_USERS; user++) {
			System.out.println();
			System.out.println("Creating Queries for user: " + user);
			
			String[] queries = QueryGenerator.generateQueries();
			List<String> queryList = Arrays.asList(queries);
			
			ArrayUtils.printQueries(queryList);
			
			System.out.println("Saving Queries...");
			
			UserQuery userQuery = new UserQuery();
			userQuery.setId(user);
			userQuery.setQueries(queryList);
			
			UserQueryCollection.getInstance().add(userQuery);
			
			System.out.println("Done.");
		}
		
		
		System.out.println();
		System.out.println("Done.");
		System.out.println("******************************");
	}

}
