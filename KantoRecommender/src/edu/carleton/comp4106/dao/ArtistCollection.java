package edu.carleton.comp4106.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.carleton.comp4106.model.Artist;
import edu.carleton.comp4106.utils.Constants;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class ArtistCollection {

	private static ArtistCollection instance;
	
	public static void setInstance(ArtistCollection instance) {
		ArtistCollection.instance = instance;
	}
	
	public static ArtistCollection getInstance() {
		if(instance == null)
			instance = new ArtistCollection();
		return instance;
	}
	
	// MongoDB
	private static MongoClient mongoClient;
	private static DB database;
	private static DBCollection collection;
	
	private ArtistCollection() {
		mongoClient = new MongoClient(Constants.LOCALHOST);
		
		database = mongoClient.getDB(Constants.DB_NAME);

		collection = database.getCollection(Constants.DB_COLL_ARTIST);
		collection.setObjectClass(Artist.class);
	}
	
	public void add(Artist artist) {
		collection.insert(artist);
	}
	
	public String getGenre(String artist) {
		String genre = null;
		
		BasicDBObject query = new BasicDBObject("name", artist);
		DBCursor cursor = collection.find(query);
		
		if(cursor.hasNext()) {
			Artist result = (Artist) cursor.next();
			genre = result.getGenre();
		}
		
		return genre;
	}
	
	public List<Artist> getAllArtistsByGenre(String genre) {
		List<Artist> artists = new ArrayList<Artist>();
		
		DBObject query = new BasicDBObject(Constants.FIELD_GENRE, genre);
		DBCursor cursor = collection.find(query);
		
		while(cursor.hasNext()) {
			artists.add((Artist) cursor.next());
		}
		
		return artists;
	}
}

