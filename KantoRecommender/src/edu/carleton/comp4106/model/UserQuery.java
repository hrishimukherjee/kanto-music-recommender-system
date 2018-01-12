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

public class UserQuery implements DBObject {
	Integer id;
	List<String> queries;
	
	private static final String ID = "id";
	private static final String QUERIES = "queries";
	
	public UserQuery() {
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setQueries(List<String> queries) {
		this.queries = queries;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public List<String> getQueries() {
		return this.queries;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("ID: " + this.getId() + "; ");
		result.append("Queries: " + this.getQueries() + "; ");
		
		return result.toString();
	}

	@Override
	public boolean containsField(String keys) {
		if(keys.equals(ID) || keys.equals(QUERIES)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean containsKey(String keys) {
		if(keys.equals(ID) || keys.equals(QUERIES)) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object get(String keys) {
		if(keys.equals(ID)) {
			return getId();
		} else if(keys.equals(QUERIES)) {
			return getQueries();
		}
		
		return null;
	}

	@Override
	public Set<String> keySet() {
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(ID);
		keySet.add(QUERIES);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		if(key.equals(ID)) {
			setId((Integer) value);
		} else if(key.equals(QUERIES)) {
			setQueries((List<String>) value);
		} 
		
		return null;
	}

	@Override
	public void putAll(BSONObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAll(Map arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object removeField(String key) {
		if(key.equals(ID)) {
			setId(null);
		} else if(key.equals(QUERIES)) {
			setQueries(null);
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

