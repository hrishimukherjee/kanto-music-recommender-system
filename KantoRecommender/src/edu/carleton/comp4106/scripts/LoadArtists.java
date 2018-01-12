package edu.carleton.comp4106.scripts;

import java.util.ArrayList;
import java.util.List;

import edu.carleton.comp4106.dao.ArtistCollection;
import edu.carleton.comp4106.model.Artist;
import edu.carleton.comp4106.utils.Constants;

/**
 * Extract Music from Mongo DB
 * and migrate it to the Lucene Index.
 * 
 * @author Hrishi Mukherjee (100888108).
 */
public class LoadArtists {
	
	public static void main(String[] args) {
		execute();
	}
	
	public static void execute() {
		System.out.println();
		System.out.println("******************************");
		System.out.println("Script: LOAD ARTISTS");
		System.out.println("Initializing...");
		
		ArtistCollection database = ArtistCollection.getInstance();
		List<Artist> toLoad = new ArrayList<Artist>();
		
		toLoad.add(new Artist().setName("Daft Punk").setGenre(Constants.EDM));
		toLoad.add(new Artist().setName("Calvin Harris").setGenre(Constants.EDM));
		toLoad.add(new Artist().setName("Afrojack").setGenre(Constants.EDM));
		toLoad.add(new Artist().setName("Drake").setGenre(Constants.HIP_HOP));
		toLoad.add(new Artist().setName("Selena Gomez").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("Frank Ocean").setGenre(Constants.RNB));
		toLoad.add(new Artist().setName("Red Hot Chili Peppers").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Linkin Park").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Adele").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("The Weeknd").setGenre(Constants.RNB));
		toLoad.add(new Artist().setName("The Beatles").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Taylor Swift").setGenre(Constants.COUNTRY));
		toLoad.add(new Artist().setName("Childish Gambino").setGenre(Constants.HIP_HOP));
		toLoad.add(new Artist().setName("ABBA").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("Lady Gaga").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("Enrique Iglesias").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("U2").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Kendrick Lamar").setGenre(Constants.HIP_HOP));
		toLoad.add(new Artist().setName("Timbaland").setGenre(Constants.HIP_HOP));
		toLoad.add(new Artist().setName("Maroon 5").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("Bob Marley").setGenre(Constants.REGGAE));
		toLoad.add(new Artist().setName("Coldplay").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Eminem").setGenre(Constants.HIP_HOP));
		toLoad.add(new Artist().setName("Madonna").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("Beyonce").setGenre(Constants.RNB));
		toLoad.add(new Artist().setName("Elvis Presley").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Rolling Stones").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Led Zeppelin").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Paul McCartney").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Pink Floyd").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Rihanna").setGenre(Constants.RNB));
		toLoad.add(new Artist().setName("Nirvana").setGenre(Constants.ROCK));
		toLoad.add(new Artist().setName("Metallica").setGenre(Constants.METAL));
		toLoad.add(new Artist().setName("Jay Z").setGenre(Constants.HIP_HOP));
		toLoad.add(new Artist().setName("Elton John").setGenre(Constants.ROCK)); 
		toLoad.add(new Artist().setName("Michael Jackson").setGenre(Constants.POP));
		toLoad.add(new Artist().setName("Bruno Mars").setGenre(Constants.RNB)); 
		toLoad.add(new Artist().setName("Justin Timberlake").setGenre(Constants.POP));
		
		System.out.println("Loading Artists to Database...");
		System.out.println();
		
		for(Artist artist: toLoad) {
			System.out.println("Adding --> " + artist);
			database.add(artist);
		}
		
		System.out.println();
		System.out.println("Done.");
		System.out.println("******************************");
	}

}
