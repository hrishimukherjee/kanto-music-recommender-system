package edu.carleton.comp4106.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.carleton.comp4106.dao.ArtistCollection;
import edu.carleton.comp4106.dao.ClusterCollection;
import edu.carleton.comp4106.dao.SongCollection;
import edu.carleton.comp4106.dao.UserCollection;
import edu.carleton.comp4106.dao.UserQueryCollection;
import edu.carleton.comp4106.model.Artist;
import edu.carleton.comp4106.model.Cluster;
import edu.carleton.comp4106.model.Song;
import edu.carleton.comp4106.model.User;
import edu.carleton.comp4106.model.UserQuery;
import edu.carleton.comp4106.utils.ArrayUtils;
import edu.carleton.comp4106.utils.HTMLBuilder;

/**
 * The Entry Point for the web application.
 * 
 * @author Hrishi Mukherjee (100888108).
 */

@Path("/engine")
public class Recommender {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	private static final String APP_NAME = "Kanto Music Recommender";
	
	public Recommender() {
		
	}
	
	@GET
	public String printAppName() {
		return APP_NAME;
	}
	
	@GET
	@Path("/{user}/queries")
	@Produces(MediaType.TEXT_HTML)
	public Response queries(@PathParam("user") String userId) {
		System.out.println();
		System.out.println("x---------x");
		System.out.println("Request Type: GET User Queries");
		System.out.println("User ID: " + userId);
		
		Response response = null;
		String html = null;
		
		// Retrieve User's Queries
		UserQuery userQuery = 
				UserQueryCollection.getInstance().
				find(Integer.valueOf(userId));
				
		// Build HTML
		html = HTMLBuilder.buildQueriesList(userQuery);
		
		response = Response.ok(html, MediaType.TEXT_HTML).build();
		
		System.out.println("\nResponding...");
		System.out.println("x---------x");
		
		return response;
	}
	
	@GET
	@Path("/clusters")
	@Produces(MediaType.TEXT_HTML)
	public Response clusters() {
		System.out.println();
		System.out.println("x---------x");
		System.out.println("Request Type: GET Cluster Details");
		
		Response response = null;
		String html = null;
		Map<Cluster, List<User>> clusters = 
				new HashMap<Cluster, List<User>>();
		
		// Retrieve All Clusters
		List<Cluster> allClusters = 
				ClusterCollection.getInstance().findAll();
		
		// Retrieve users for each cluster
		for(Cluster cluster: allClusters) {
			int clusterId = cluster.getId();
			
			// Retrieve users
			List<User> users = UserCollection.getInstance().
					findByCluster(clusterId);
			
			clusters.put(cluster, users);
		}
		
		// Build HTML
		html = HTMLBuilder.buildClustersHTML(clusters);
		
		response = Response.ok(html, MediaType.TEXT_HTML).build();
		
		System.out.println("\nResponding...");
		System.out.println("x---------x");
		
		return response;
	}
	
	@GET
	@Path("/{user}/recommend")
	@Produces(MediaType.TEXT_HTML)
	public Response recommend(@PathParam("user") String userId) {
		System.out.println();
		System.out.println("x---------x");
		System.out.println("Request Type: GET User Recommendations");
		System.out.println("User ID: " + userId);
		
		Response response = null;
		String html = null;
		
		// ADDED
		
		System.out.println("Retrieving User...");
		
		User user = UserCollection.getInstance().
				find(Integer.valueOf(userId));
		
		System.out.println();
		System.out.println(user);
		
		int clusterNumber = user.getCluster();
		
		System.out.println();
		System.out.println("Retrieving Cluster: " + clusterNumber);
		
		Cluster cluster = ClusterCollection.getInstance().find(clusterNumber);
		
		System.out.println(cluster);
		
		List<String> genres = cluster.getAllPreferences();
		
		System.out.println();
		System.out.println("Genres: " + genres);
		
		List<Song> recommend = new ArrayList<Song>();
		
		/*
		 * FOR EACH GENRE, REPEAT THE RECOMMEND ALGORITHM
		 */
		for(int i = 0; i < genres.size(); i++) {
			// Retrieve all artists from genre
			List<Artist> artists = 
					ArtistCollection.getInstance().getAllArtistsByGenre(genres.get(i));
			
			//System.out.println();
			//ArrayUtils.printArtistList(artists);
			
			// Retrieve songs for each artist
			for(Artist artist: artists) {
				List<Song> songs = 
						SongCollection.getInstance().
						findSongsByArtist(artist.getName());
				
				// Choose 1 random song
				Random random = new Random();
				int index = random.nextInt(songs.size());
				
				Song addToList = songs.get(index);
				
				// Add to recommendations
				recommend.add(addToList);
			}
		}
		
		System.out.println("\nRecommendations generated!");
		ArrayUtils.printSongs(recommend);
		
		//System.out.println("\nShuffling recommendations...");
		//Collections.shuffle(recommend);
		//ArrayUtils.printSongs(recommend);
		
		System.out.println("\nBuilding HTML...");
		html = HTMLBuilder.buildRecommendationsHTML(recommend);
		
		response = Response.ok(html, MediaType.TEXT_HTML).build();
		
		System.out.println("\nResponding...");
		System.out.println("x---------x");
		
		return response;
	}

}
