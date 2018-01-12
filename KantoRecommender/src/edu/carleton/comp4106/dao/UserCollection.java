package edu.carleton.comp4106.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.carleton.comp4106.model.User;
import edu.carleton.comp4106.utils.Constants;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class UserCollection {

	private static UserCollection instance;
	
	public static void setInstance(UserCollection instance) {
		UserCollection.instance = instance;
	}
	
	public static UserCollection getInstance() {
		if(instance == null)
			instance = new UserCollection();
		return instance;
	}
	
	// MongoDB
	private static MongoClient mongoClient;
	private static DB database;
	private static DBCollection collection;
	
	private UserCollection() {
		mongoClient = new MongoClient(Constants.LOCALHOST);
		
		database = mongoClient.getDB(Constants.DB_NAME);

		collection = database.getCollection(Constants.DB_COLL_USER);
		collection.setObjectClass(User.class);
	}
	
	public void add(User user) {
		collection.insert(user);
	}
	
	public User find(int id) {
		User user = null;
		
		DBObject query = 
				new BasicDBObject(Constants.FIELD_ID, id);
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			user = (User) cursor.next();
		}
		
		return user;
	}
	
	public boolean update(Integer id, User user) {
		DBObject query = new BasicDBObject(Constants.FIELD_ID, id);
		
		System.out.println("Updating User: " + id);
		DBObject result = (User) collection.findAndModify(query, user);
		
		if(result == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<User> findAll() {
		List<User> allUsers = new ArrayList<User>();
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			allUsers.add((User) cursor.next());
		}
		
		return allUsers;
	}
	
	public List<User> findByCluster(int clusterNumber) {
		List<User> usersInCluster = new ArrayList<User>();
		
		BasicDBObject query = 
				new BasicDBObject(Constants.FIELD_CLUSTER, clusterNumber);
		
		DBCursor cursor = collection.find(query);
		
		while(cursor.hasNext()) {
			usersInCluster.add((User) cursor.next());
		}
		
		return usersInCluster;
	}
	
}

