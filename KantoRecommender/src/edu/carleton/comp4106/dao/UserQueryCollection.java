package edu.carleton.comp4106.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.carleton.comp4106.model.Song;
import edu.carleton.comp4106.model.User;
import edu.carleton.comp4106.model.UserQuery;
import edu.carleton.comp4106.utils.Constants;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class UserQueryCollection {

	private static UserQueryCollection instance;
	
	public static void setInstance(UserQueryCollection instance) {
		UserQueryCollection.instance = instance;
	}
	
	public static UserQueryCollection getInstance() {
		if(instance == null)
			instance = new UserQueryCollection();
		return instance;
	}
	
	// MongoDB
	private static MongoClient mongoClient;
	private static DB database;
	private static DBCollection userQueryColl;
	
	private UserQueryCollection() {
		mongoClient = new MongoClient(Constants.LOCALHOST);
		
		database = mongoClient.getDB(Constants.DB_NAME);

		userQueryColl = database.getCollection(Constants.DB_COLL_USER_QUERY);
		userQueryColl.setObjectClass(UserQuery.class);
	}
	
	public void add(UserQuery userQuery) {		
		userQueryColl.insert(userQuery);
	}
	
	public List<UserQuery> getAll() {
		System.out.println("Retrieving All User Queries...");
		
		List<UserQuery> results = new ArrayList<UserQuery>();
		
		DBCursor cursor = userQueryColl.find();
		
		System.out.println("Retrieved! Adding User Queries to list...");
		
		while(cursor.hasNext()) {
			UserQuery userQuery = (UserQuery) cursor.next();
			results.add(userQuery);
		}
		
		System.out.println("Returning User Query List...");
		
		return results;
	}
	
	public UserQuery find(int id) {
		UserQuery user = null;
		
		DBObject query = 
				new BasicDBObject(Constants.FIELD_ID, id);
		
		DBCursor cursor = userQueryColl.find(query);
		
		if(cursor.hasNext()) {
			user = (UserQuery) cursor.next();
		}
		
		return user;
	}
	
}

