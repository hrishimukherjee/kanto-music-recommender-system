package edu.carleton.comp4106.scripts;

import java.util.ArrayList;
import java.util.Random;

import edu.carleton.comp4106.dao.SongCollection;
import edu.carleton.comp4106.model.Song;
import edu.carleton.comp4106.utils.ArrayUtils;

public class QueryGenerator {
	
	public static void main(String[] args) {
		System.out.println("Generating queries...");
		
		String[] queries = generateQueries();
		
		ArrayUtils.printQueries(queries);
	}
	
	public static String[] generateQueries() {		
		SongCollection songs = SongCollection.getInstance();
		Random random = new Random();
		int numSongs = songs.getCount();
		int numQueries = 50;
		
		String[] queries = new String[numQueries];
		
		// Create Queries
		for(int i = 0; i < numQueries; i++) {
			// Grab random song
			int id = random.nextInt(numSongs);
			Song song = songs.find(id);
			
			// Generate Query
			String query = generateQuery(song);
			
			queries[i] = query;
		}
		
		return queries;
	}
	
	public static String generateQuery(Song song) {
		//System.out.println();
		//System.out.println("Song to Parse:\n" + song);
		
		String query = null;
		String lyrics = null;
		int[] blanks = null;
		int queryStart = -1;
		int queryEnd = -1;
		
		// Compute all blank spaces
		lyrics = song.getLyrics();
		blanks = blankSpaces(lyrics);
		
		// If blank spaces are less than 10
		// i.e less than 10 words, then the full
		// song is the query
		if(blanks.length < 15) {
			query = song.getLyrics();
			
			// Clean Up
			query = query.toLowerCase();
			query = query.replaceAll("[\\p{P}\\p{S}]", ""); // Removes punctuation/symbols
			query = query.replaceAll("[\\[\\]]", ""); // Removes [ and ]
			query = query.trim(); // Remove leading spaces
			
			return query;
		}
		
		//System.out.print("Blanks: ");
		//ArrayUtils.printNice(blanks);
		
		// Choose start and end indices
		Random random = new Random();
		
		// Between 2 - 10 words
		int queryLength = random.nextInt(9) + 2;
		
		//System.out.println(blanks.length - queryLength);
		
		int begin = random.nextInt(blanks.length - queryLength);
		int end = begin + queryLength;
		
		//System.out.println("Query Length = " + queryLength);
		//System.out.println("[" + begin + ", " + end + "]");
		
		queryStart = blanks[begin];
		queryEnd = blanks[end];
		
		//System.out.println("[" + queryStart + ", " + queryEnd + "]");
		
		// Get Substring
		query = lyrics.substring(queryStart, queryEnd);
		
		// Clean Up
		query = query.toLowerCase();
		query = query.replaceAll("[\\p{P}\\p{S}]", ""); // Removes punctuation/symbols
		query = query.replaceAll("[\\[\\]]", ""); // Removes [ and ]
		query = query.trim(); // Remove leading spaces
		
		return query;
	}
	
	public static int[] blankSpaces(String lyrics) {
		char[] characters = lyrics.toCharArray();
		int numBlanks = 0;
		int[] blankSpaces = null;
		
		// Count number of blank spaces
		for(int i = 0; i < characters.length; i++) {
			if(characters[i] == ' ') {
				numBlanks++;
			}
		}
		
		//System.out.println("Num Blanks = " + numBlanks);
		
		blankSpaces = new int[numBlanks];
		int blankNum = 0;
		
		// Store indices of blank spaces
		for(int i = 0; i < characters.length; i++) {
			if(characters[i] == ' ') {
				blankSpaces[blankNum] = i;
				blankNum++;
			}
		}
		
		return blankSpaces;
	}
}
