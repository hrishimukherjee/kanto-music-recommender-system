package edu.carleton.comp4106.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import edu.carleton.comp4106.model.Artist;
import edu.carleton.comp4106.model.Song;
import edu.carleton.comp4106.model.User;

public class ArrayUtils {
	
	public static void printNice(int[] array) {
		System.out.print("[");
		
		for(int i = 0; i < array.length; i++) {
			if(i != (array.length - 1)) {
				System.out.print(array[i] + ", ");
			} else {
				System.out.print(array[i]);
			}
		}
		
		System.out.println("]");
	}
	
	public static void printNice(double[] array) {
		System.out.print("[");
		
		for(int i = 0; i < array.length; i++) {
			if(i != (array.length - 1)) {
				System.out.print(array[i] + ", ");
			} else {
				System.out.print(array[i]);
			}
		}
		
		System.out.println("]");
	}
	
	public static void printQueries(String[] queries) {	
		System.out.println();
		System.out.println("QUERIES:");
		System.out.println("---------------------");
		
		for(int i = 0; i < queries.length; i++) {
			System.out.println("Query " + i + ": " + queries[i]);
		}
		
		System.out.println("---------------------");
	}
	
	public static void printQueries(List<String> queries) {	
		System.out.println();
		System.out.println("QUERIES:");
		System.out.println("---------------------");
		
		for(int i = 0; i < queries.size(); i++) {
			System.out.println("Query " + i + ": " + queries.get(i));
		}
		
		System.out.println("---------------------");
	}
	
	public static void printSongs(List<Song> songs) {
		System.out.println();
		System.out.println("SONGS:");
		System.out.println("---------------------");
		
		for(int i = 0; i < songs.size(); i++) {
			System.out.println(songs.get(i));
		}
		
		System.out.println("---------------------");
	}
	
	public static void printArtists(List<Song> songs) {
		System.out.println();
		System.out.println("ARTISTS:");
		System.out.println("---------------------");
		
		for(int i = 0; i < songs.size(); i++) {
			System.out.println(songs.get(i).getArtist());
		}
		
		System.out.println("---------------------");
	}
	
	public static void printArtistList(List<Artist> artists) {
		System.out.println();
		System.out.println("ARTISTS:");
		System.out.println("---------------------");
		
		for(int i = 0; i < artists.size(); i++) {
			System.out.println(artists.get(i));
		}
		
		System.out.println("---------------------");
	}
	
	public static void printGenres(Map<String, Integer> genres) {
		System.out.println();
		System.out.println("GENRES:");
		System.out.println("---------------------");
		
		for(String genre: genres.keySet()) {
			System.out.println(genre + " -- " + 
					genres.get(genre) + " occurrences.");
		}
		
		System.out.println("---------------------");
	}
	
	public static void printUsers(List<User> users) {
		System.out.println();
		System.out.println("USERS:");
		System.out.println("---------------------");
		
		for(int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i));
		}
		
		System.out.println("---------------------");
	}
	
	public static void printMatrix(double[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(new DecimalFormat("#.###").format(matrix[i][j]) + " ");
			}
			
			System.out.println();
		}
	}
}
