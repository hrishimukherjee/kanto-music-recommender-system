package edu.carleton.comp4106.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.carleton.comp4106.dao.ArtistCollection;
import edu.carleton.comp4106.dao.UserCollection;
import edu.carleton.comp4106.dao.UserQueryCollection;
import edu.carleton.comp4106.lucene.Lucene;
import edu.carleton.comp4106.model.Song;
import edu.carleton.comp4106.model.User;
import edu.carleton.comp4106.model.UserQuery;
import edu.carleton.comp4106.utils.ArrayUtils;
import edu.carleton.comp4106.utils.Constants;

public class FeatureComputer {
	
	public static void main(String[] args) {
		List<UserQuery> userQueries = UserQueryCollection.getInstance().getAll();
		
		for(UserQuery userQuery: userQueries) {
			System.out.println();
			System.out.println("Current User: " + userQuery);
			System.out.println("**************************");
			
			User userToAdd = computeUserFeatures(userQuery);
			
			System.out.println();
			System.out.println("User to add:\n" + userToAdd);
			
			System.out.println("\nAdding user to DB...");
			UserCollection.getInstance().add(userToAdd);
			System.out.println("User added.");
			System.out.println("**************************");
		}
	}
	
	// SHOULD RETURN A USER WITH FEATURES
	public static User computeUserFeatures(UserQuery userQuery) {
		List<Song> allSearchResults = new ArrayList<Song>();
		List<String> queries = userQuery.getQueries();
		
		Lucene database = Lucene.getInstance();
		
		// Perform each query on index and amalgamate all results
		for(String query: queries) {
			List<Song> searchResults = 
					database.query(Constants.FIELD_LYRICS, query);
			
			// Retain only top 3 results
			List<Song> top3 = new ArrayList<Song>();
			
			for(int i = 0; i < 3 && i < searchResults.size(); i++) {
				top3.add(searchResults.get(i));
			}
			
			allSearchResults.addAll(top3);
		}
		
		System.out.println("Search Results: " + allSearchResults.size());
		// ArrayUtils.printArtists(allSearchResults);
		
		// Transform results to genre map (Genre --> Num Occurrences)
		Map<String, Integer> genres = new HashMap<String, Integer>();
		ArtistCollection artistDB = ArtistCollection.getInstance();
		
		for(Song result: allSearchResults) {
			String artist = result.getArtist();
			
			// Retrieve genre for song's artist
			String genre = artistDB.getGenre(artist);
			
			if(genre == null) {
				System.out.println(artist);
			}
			
			// Update Genre Value
			if(genres.containsKey(genre)) {
				Integer numOccurrences = genres.get(genre);
				numOccurrences++;
				
				genres.put(genre, numOccurrences);
			} else {
				genres.put(genre, 1);
			}
		}
		
		//System.out.println();
		//ArrayUtils.printGenres(genres);
		
		// Compute Average Occurrence of Genre
		double edm = average(Constants.EDM, genres);		
		double hiphop = average(Constants.HIP_HOP, genres);		
		double rnb = average(Constants.RNB, genres);		
		double pop = average(Constants.POP, genres);		
		double rock = average(Constants.ROCK, genres);		
		double reggae = average(Constants.REGGAE, genres);		
		double metal = average(Constants.METAL, genres);		
		double country = average(Constants.COUNTRY, genres);
		
		// Create new user with features
		User user = new User();
		user.setId(userQuery.getId());
		user.setCluster(null);
		user.setEDM(edm);
		user.setHipHop(hiphop);
		user.setRNB(rnb);
		user.setPop(pop);
		user.setRock(rock);
		user.setReggae(reggae);
		user.setMetal(metal);
		user.setCountry(country);
		
		return user;
	}
	
	public static double average(String genre, Map<String, Integer> occurrences) {
		double average = 0.0;
		double totalCount = 0.0;
		double genreCount = 0.0;
		
		for(String key: occurrences.keySet()) {
			totalCount += occurrences.get(key);
			
			if(key.equals(genre)) {
				genreCount = occurrences.get(key);
			}
		}
		
		//System.out.println(genre + " Count = " + genreCount);
		//System.out.println("Total Count = " + totalCount);

		average = (genreCount/totalCount) * 100.0;
		
		return average;
	}

}
