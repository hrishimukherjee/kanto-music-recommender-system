package edu.carleton.comp4106.scripts;

import java.util.List;

import edu.carleton.comp4106.dao.SongCollection;
import edu.carleton.comp4106.lucene.Lucene;
import edu.carleton.comp4106.model.Song;

/**
 * Extract Music from Mongo DB
 * and migrate it to the Lucene Index.
 * 
 * @author Hrishi Mukherjee (100888108).
 */
public class IndexMusic {
	
	public static void main(String[] args) {
		System.out.println();
		System.out.println("******************************");
		System.out.println("Script: Index Music");
		System.out.println("Initiating...");
		
		// Extract all songs from Mongo DB
		System.out.println("Extracting all songs from DB...");
		List<Song> allSongs = SongCollection.getInstance().getAllSongs();
		
		System.out.println();
		System.out.println("Number of songs retrieved: " + allSongs.size());
		System.out.println();
		
		// Indexing songs into Lucene
		System.out.println("Indexing...");
		boolean success = Lucene.getInstance().indexSongs(allSongs);
		
		// Status Update
		if(success) {
			System.out.println();
			System.out.println("INDEXING PROCESS COMPLETE.");
			System.out.println("******************************");
		} else {
			System.out.println();
			System.out.println("INDEXING PROCESS RAN INTO AN ERROR! NOT COMPLETE.");
			System.out.println("******************************");
		} 
	}

}
