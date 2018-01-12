package edu.carleton.comp4106.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.DBObject;

import edu.carleton.comp4106.utils.Constants;

/**
 * @author Hrishi Mukherjee (100888108).
 */

public class Cluster implements DBObject {
	Integer id;
	Integer edm;
	Integer hiphop;
	Integer rnb;
	Integer pop;
	Integer rock;
	Integer reggae;
	Integer metal;
	Integer country;
	
	private static final String ID      = "id";
	private static final String EDM     = Constants.EDM;
	private static final String HIP_HOP = Constants.HIP_HOP;
	private static final String RNB     = Constants.RNB;
	private static final String POP     = Constants.POP;
	private static final String ROCK    = Constants.ROCK;
	private static final String REGGAE  = Constants.REGGAE;
	private static final String METAL   = Constants.METAL;
	private static final String COUNTRY = Constants.COUNTRY;
	
	public Cluster() {
	}
	
	public void setId(Integer id) { this.id = id; }
	public void setEDM(Integer value) { this.edm = value; }
	public void setHipHop(Integer value) { this.hiphop = value; }
	public void setRNB(Integer value) { this.rnb = value; }
	public void setPop(Integer value) { this.pop = value; }
	public void setRock(Integer value) { this.rock = value; }
	public void setReggae(Integer value) { this.reggae = value; }
	public void setMetal(Integer value) { this.metal = value; }
	public void setCountry(Integer value) { this.country = value; }
	
	public Integer getId() { return this.id; }
	public Integer getEDM() { return this.edm; }
	public Integer getHipHop() { return this.hiphop; }
	public Integer getRNB() { return this.rnb; }
	public Integer getPop() { return this.pop; }
	public Integer getRock() { return this.rock; }
	public Integer getReggae() { return this.reggae; }
	public Integer getMetal() { return this.metal; }
	public Integer getCountry() { return this.country; }
	
	public List<String> getAllPreferences() {
		List<String> genres = new ArrayList<String>();
		
		if(this.getEDM() == 1)
			genres.add(Constants.EDM);
		
		if(this.getHipHop() == 1)
			genres.add(Constants.HIP_HOP);
		
		if(this.getRNB() == 1)
			genres.add(Constants.RNB);
		
		if(this.getPop() == 1)
			genres.add(Constants.POP);

		if(this.getRock() == 1)
			genres.add(Constants.ROCK);
		
		if(this.getReggae() == 1)
			genres.add(Constants.REGGAE);
		
		if(this.getMetal() == 1)
			genres.add(Constants.METAL);
		
		if(this.getCountry() == 1)
			genres.add(Constants.COUNTRY);
		
		return genres;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("\nCLUSTER ID: " + this.getId() + "; ");
		
		result.append("\n-------------------------");
		result.append("\nGENRE PREFERENCES:");
		result.append("\n==================");
		
		if(this.getEDM() == 1)
			result.append("\n" + EDM);
		
		if(this.getHipHop() == 1)
			result.append("\n" + HIP_HOP);
		
		if(this.getRNB() == 1)
			result.append("\n" + RNB);
		
		if(this.getPop() == 1)
			result.append("\n" + POP);

		if(this.getRock() == 1)
			result.append("\n" + ROCK);
		
		if(this.getReggae() == 1)
			result.append("\n" + REGGAE);
		
		if(this.getMetal() == 1)
			result.append("\n" + METAL);
		
		if(this.getCountry() == 1)
			result.append("\n" + COUNTRY);
		
		result.append("\n-------------------------");
		
		return result.toString();
	}

	@Override
	public boolean containsField(String keys) {
		if(keys.equals(ID)
				|| keys.equals(EDM) || keys.equals(HIP_HOP)
				|| keys.equals(RNB) || keys.equals(POP)
				|| keys.equals(ROCK) || keys.equals(REGGAE)
				|| keys.equals(METAL) || keys.equals(COUNTRY)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean containsKey(String keys) {
		if(keys.equals(ID)
				|| keys.equals(EDM) || keys.equals(HIP_HOP)
				|| keys.equals(RNB) || keys.equals(POP)
				|| keys.equals(ROCK) || keys.equals(REGGAE)
				|| keys.equals(METAL) || keys.equals(COUNTRY)) {
			return true;
		}
		
		return false;
	}

	@Override
	public Object get(String keys) {
		if(keys.equals(ID)) {
			return getId();
		} else if(keys.equals(EDM)) {
			return getEDM();
		} else if(keys.equals(HIP_HOP)) {
			return getHipHop();
		} else if(keys.equals(RNB)) {
			return getRNB();
		} else if(keys.equals(POP)) {
			return getPop();
		} else if(keys.equals(ROCK)) {
			return getRock();
		} else if(keys.equals(REGGAE)) {
			return getReggae();
		} else if(keys.equals(METAL)) {
			return getMetal();
		} else if(keys.equals(COUNTRY)) {
			return getCountry();
		}
		
		return null;
	}

	@Override
	public Set<String> keySet() {
		Set<String> keySet = new HashSet<String>();
		
		keySet.add(ID);
		keySet.add(EDM);
		keySet.add(HIP_HOP);
		keySet.add(RNB);
		keySet.add(POP);
		keySet.add(ROCK);
		keySet.add(REGGAE);
		keySet.add(METAL);
		keySet.add(COUNTRY);
		
		return keySet;
	}

	@Override
	public Object put(String key, Object value) {
		if(key.equals(ID)) {
			setId((Integer) value);
		} else if(key.equals(EDM)) {
			setEDM((Integer) value);
		} else if(key.equals(HIP_HOP)) {
			setHipHop((Integer) value);
		} else if(key.equals(RNB)) {
			setRNB((Integer) value);
		} else if(key.equals(POP)) {
			setPop((Integer) value);
		} else if(key.equals(ROCK)) {
			setRock((Integer) value);
		} else if(key.equals(REGGAE)) {
			setReggae((Integer) value);
		} else if(key.equals(METAL)) {
			setMetal((Integer) value);
		} else if(key.equals(COUNTRY)) {
			setCountry((Integer) value);
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
		if(key.equals(ID)) {
			setId(null);
		} else if(key.equals(EDM)) {
			setEDM(null);
		} else if(key.equals(HIP_HOP)) {
			setHipHop(null);
		} else if(key.equals(RNB)) {
			setRNB(null);
		} else if(key.equals(POP)) {
			setPop(null);
		} else if(key.equals(ROCK)) {
			setRock(null);
		} else if(key.equals(REGGAE)) {
			setReggae(null);
		} else if(key.equals(METAL)) {
			setMetal(null);
		} else if(key.equals(COUNTRY)) {
			setCountry(null);
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

