package edu.carleton.comp4106.clustering;

import java.util.List;

import edu.carleton.comp4106.dao.UserCollection;
import edu.carleton.comp4106.model.User;
import edu.carleton.comp4106.utils.ArrayUtils;

public class Clustering {
	
	public static void main(String[] args) {
		execute();
	}
	
	public static void execute() {
		System.out.println("x----------------------x");
		System.out.println("USER CLUSTERING INITIATING...");
		System.out.println();
		
		System.out.println("Retrieving All Users...");
		List<User> allUsers = UserCollection.getInstance().findAll();
		
		System.out.println("Displaying All Users BEFORE Clustering...");
		ArrayUtils.printUsers(allUsers);
		
		KMeans clustering = new KMeans(4, 8, allUsers);
		List<User> clusteredUsers = clustering.execute();
		
		System.out.println("Clustered Users: " + clusteredUsers.size());
		System.out.println("Displaying All Users AFTER Clustering...");
		
		ArrayUtils.printUsers(clusteredUsers); 
		
		System.out.println("Adding to DB...");
		UserCollection collection = UserCollection.getInstance();
		
		for(User user: clusteredUsers) {
			collection.update(user.getId(), user);
		}
		
		System.out.println();
		System.out.println("USER CLUSTERING COMPLETE!");
		System.out.println("x----------------------x"); 
	}

}
