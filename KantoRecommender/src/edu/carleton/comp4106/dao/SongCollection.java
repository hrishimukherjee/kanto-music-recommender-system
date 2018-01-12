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
import edu.carleton.comp4106.utils.Constants;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class SongCollection {

	private static SongCollection instance;
	
	public static void setInstance(SongCollection instance) {
		SongCollection.instance = instance;
	}
	
	public static SongCollection getInstance() {
		if(instance == null)
			instance = new SongCollection();
		return instance;
	}
	
	// MongoDB
	private static MongoClient mongoClient;
	private static DB database;
	private static DBCollection songColl;
	
	private SongCollection() {
		mongoClient = new MongoClient(Constants.LOCALHOST);
		
		database = mongoClient.getDB(Constants.DB_NAME);

		songColl = database.getCollection(Constants.DB_COLL_SONGS);
		songColl.setObjectClass(Song.class);
	}
	
	public void add(Song song) {
		System.out.println("Adding Song to Database | Id: " 
				+ song.getId() + " Name: " 
				+ song.getName());
		
		songColl.insert(song);
	}
	
	public Song find(Integer id) {
		Song song = null;
		
		BasicDBObject query = new BasicDBObject("id", id);
		DBCursor cursor = songColl.find(query);
		
		if(cursor.hasNext()) {
			song = (Song) cursor.next();
		}
		
		return song;
		
	}
	
	public List<Song> findSongsByArtist(String artist) {
		List<Song> songs = new ArrayList<Song>();
		
		DBObject query = 
				new BasicDBObject(Constants.FIELD_ARTIST, artist);
		
		DBCursor cursor = songColl.find(query);
		
		while(cursor.hasNext()) {
			songs.add((Song) cursor.next());
		}
		
		return songs;
	}
	
	public List<Song> getAllSongs() {
		System.out.println("Retrieving All Songs...");
		
		List<Song> songList = new ArrayList<Song>();
		Song song = null;
		
		DBCursor cursor = songColl.find();
		
		System.out.println("Retrieved! Adding songs to list...");
		
		while(cursor.hasNext()) {
			song = (Song) cursor.next();
			songList.add(song);
		}
		
		System.out.println("Returning Song List...");
		
		return songList;
	}
	
	public int getCount() {
		System.out.println("Retrieving number of songs in collection...");
		
		long count = -1;
		
		count = songColl.getCount();
		
		return (int) count;
	}
	
}

