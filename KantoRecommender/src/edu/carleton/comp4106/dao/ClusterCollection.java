package edu.carleton.comp4106.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.carleton.comp4106.model.Cluster;
import edu.carleton.comp4106.utils.Constants;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class ClusterCollection {

	private static ClusterCollection instance;
	
	public static void setInstance(ClusterCollection instance) {
		ClusterCollection.instance = instance;
	}
	
	public static ClusterCollection getInstance() {
		if(instance == null)
			instance = new ClusterCollection();
		return instance;
	}
	
	// MongoDB
	private static MongoClient mongoClient;
	private static DB database;
	private static DBCollection collection;
	
	private ClusterCollection() {
		mongoClient = new MongoClient(Constants.LOCALHOST);
		
		database = mongoClient.getDB(Constants.DB_NAME);

		collection = database.getCollection(Constants.DB_COLL_CLUSTER);
		collection.setObjectClass(Cluster.class);
	}
	
	public void add(Cluster cluster) {
		collection.insert(cluster);
	}
	
	public Cluster find(int clusterNumber) {
		Cluster cluster = null;
		
		DBObject query = 
				new BasicDBObject(Constants.FIELD_ID, clusterNumber);
		
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			cluster = (Cluster) cursor.next();
		}
		
		return cluster;
	}
	
	public List<Cluster> findAll() {
		List<Cluster> clusters = new ArrayList<Cluster>();
		
		DBCursor cursor = collection.find();
		
		while(cursor.hasNext()) {
			clusters.add((Cluster) cursor.next());
		}
		
		return clusters;
	}
}

