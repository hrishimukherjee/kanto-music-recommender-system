package edu.carleton.comp4106.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class Artist implements DBObject {
	String name;
	String genre;
	
	private static final String NAME = "name";
	private static final String GENRE = "genre";
	
	public Artist() {
	}
	
	public Artist setName(String name) {
		this.name = name;
		return this;
	}
	
	public Artist setGenre(String genre) {
		this.genre = genre;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("Name: " + this.getName() + "; ");
		result.append("Genre: " + this.getGenre() + "; ");
		
		return result.toString();
	}

	@Override
	public boolean containsField(String keys) {
		if(keys.equals(NAME) || keys.equals(GENRE)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean containsKey(String keys) {
		if(keys.equals(NAME) || keys.equals(GENRE)) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object get(String keys) {
		if(keys.equals(NAME)) {
			return getName();
		} else if(keys.equals(GENRE)) {
			return getGenre();
		}
		
		return null;
	}

	@Override
	public Set<String> keySet() {
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(NAME);
		keySet.add(GENRE);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		if(key.equals(NAME)) {
			setName((String) value);
		} else if(key.equals(GENRE)) {
			setGenre((String) value);
		} 
		
		return null;
	}

	@Override
	public void putAll(BSONObject arg0) {

	}

	@Override
	public void putAll(Map arg0) {
	}

	@Override
	public Object removeField(String key) {
		if(key.equals(NAME)) {
			setName(null);
		} else if(key.equals(GENRE)) {
			setGenre(null);
		} 
		
		return null;
	}

	@Override
	public Map toMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPartialObject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markAsPartialObject() {
		// TODO Auto-generated method stub
		
	}

}

