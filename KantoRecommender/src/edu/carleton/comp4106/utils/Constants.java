package edu.carleton.comp4106.utils;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class Constants {
	
	/*
	 * APPLICATION
	 */
	public static final String BASE_DIRECTORY    = ""; // ADD PATH TO WHERE THE BASE APP DIRECTORY SHOULD BE
	public static final String QUERY_DIRECTORY   = BASE_DIRECTORY + "/queries";
	public static final String RESULTS_DIRECTORY = BASE_DIRECTORY + "/results";
	
	/*
	 * DATABASE (MONGODB)
	 */
	public static final String DB_NAME              = "kanto";
	public static final String LOCALHOST            = "localhost";
	
	public static final String DB_COLL_SONGS        = "songs";
	public static final String DB_COLL_USER_QUERY   = "user_query";
	public static final String DB_COLL_ARTIST       = "artist";
	public static final String DB_COLL_USER         = "user";
	public static final String DB_COLL_CLUSTER      = "cluster";
	
	/*
	 * DATABASE (LUCENE)
	 */
	public static final String INDEX_PATH = BASE_DIRECTORY + "/lucene-index";
	
	/*
	 * FIELDS
	 */
	public static final String FIELD_ID			   = "id";
	public static final String FIELD_CLUSTER       = "cluster";
	public static final String FIELD_NAME          = "name";
	public static final String FIELD_ARTIST        = "artist";
	public static final String FIELD_LYRICS        = "lyrics";
	public static final String FIELD_GENRE         = "genre";
	
	/*
	 * GENRES
	 */
	public static final String EDM     = "Electronic Dance Music";
	public static final String HIP_HOP = "Hip Hop";
	public static final String RNB     = "R&B";
	public static final String POP     = "Pop";
	public static final String ROCK    = "Rock";
	public static final String REGGAE  = "Reggae";
	public static final String METAL   = "Metal";
	public static final String COUNTRY = "Country";
	
	/*
	 * EXTRA
	 */
	public static final int NUM_CLUSTERS = 4;
	
	private Constants() {
		throw new RuntimeException();
	}

}
