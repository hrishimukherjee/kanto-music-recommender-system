package edu.carleton.comp4106.utils;

import java.util.List;
import java.util.Map;

import edu.carleton.comp4106.model.Cluster;
import edu.carleton.comp4106.model.Song;
import edu.carleton.comp4106.model.User;
import edu.carleton.comp4106.model.UserQuery;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class HTMLBuilder {
	
	public static final String TITLE = "RECOMMENDED SONGS";
	
	public static String buildRecommendationsHTML(List<Song> songs) {
		StringBuilder htmlDoc = new StringBuilder();
		
		// BUILD HEADER
		htmlDoc.append("<html>");
		htmlDoc.append("<head><title>" + TITLE + "</title></head>");
		
		// BUILD BODY
		htmlDoc.append("<body>");
		
		htmlDoc.append("<b><font color=\"royalblue\" size=\"6\" face=\"verdana\">" + "<center>" +
				TITLE + "</center>" + "</font></b>");
		htmlDoc.append("<br/>");
		htmlDoc.append("<br/>");
		
		htmlDoc.append("<table style=\"width:100%\" border=\"1\">");
		
		// Insert Table Headers
		htmlDoc.append("<tr>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"TITLE" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"ARTIST" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"LYRICS" + "</font></b>"  + "</th>");
		htmlDoc.append("</tr>");
		
		// Insert Table Rows
		for(Song song: songs) {
			htmlDoc.append("<tr>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(song.getName());
			htmlDoc.append("</td>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(song.getArtist());
			htmlDoc.append("</td>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(song.getLyrics());
			htmlDoc.append("</td>");
			
			htmlDoc.append("</tr>");
		} 
		
		htmlDoc.append("</table>");
		htmlDoc.append("</body>");
		
		return htmlDoc.toString();
	}
	
	public static String buildClustersHTML(Map<Cluster, List<User>> clusters) {
		StringBuilder htmlDoc = new StringBuilder();
		
		// BUILD HEADER
		htmlDoc.append("<html>");
		htmlDoc.append("<head><title>" + "CLUSTERS" + "</title></head>");
		
		// BUILD BODY
		htmlDoc.append("<body>");
		
		htmlDoc.append("<b><font color=\"royalblue\" size=\"6\" face=\"verdana\">" + "<center>" +
				"CLUSTERS" + "</center>" + "</font></b>");
		htmlDoc.append("<br/>");
		htmlDoc.append("<br/>");
		
		htmlDoc.append("<table style=\"width:100%\" border=\"1\">");
		
		// Insert Table Headers
		htmlDoc.append("<tr>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"CLUSTER NUMBER" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"PREFERENCES" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"USERS" + "</font></b>"  + "</th>");
		htmlDoc.append("</tr>");
		
		// Insert Table Rows
		for(Cluster cluster: clusters.keySet()) {
			htmlDoc.append("<tr>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(cluster.getId());
			htmlDoc.append("</td>");
			
			// Build Preferences String
			List<String> prefs = cluster.getAllPreferences();
			StringBuilder prefString = new StringBuilder();
			
			for(int i = 0; i < prefs.size(); i++) {
				if(i < (prefs.size() - 1)) {
					prefString.append(prefs.get(i) + ", ");
				} else {
					prefString.append(prefs.get(i));
				}
			}
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(prefString.toString());
			htmlDoc.append("</td>");
			
			// Build Users String
			List<User> users = clusters.get(cluster);
			StringBuilder usersString = new StringBuilder();
			
			for(int i = 0; i < users.size(); i++) {
				if(i < (users.size() - 1)) {
					usersString.append(users.get(i).getId() + ", ");
				} else {
					usersString.append(users.get(i).getId());
				}
			}
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(usersString.toString());
			htmlDoc.append("</td>");
			
			htmlDoc.append("</tr>");
		} 
		
		htmlDoc.append("</table>");
		htmlDoc.append("</body>");
		
		return htmlDoc.toString();
	}
	
	public static String buildQueriesList(UserQuery userQueries) {
		StringBuilder response = new StringBuilder();
		response.append("<html>");
		response.append("<head><title>ADVERTISEMENTS</title></head>");
		response.append("<body>");
		response.append("<b><font color=\"red\" size=\"6\" face=\"verdana\">" +
				"User " + userQueries.getId() + "'s Queries" + "</font></b>");
		
		List<String> queries = userQueries.getQueries();
		

		response.append("<ul>");
			
		for(String query: queries) {
			response.append("<li>");
			response.append(query);
			response.append("</li>");
		}
			
		response.append("</ul>");

		
		response.append("</body>");
		
		return response.toString();
	}

}
