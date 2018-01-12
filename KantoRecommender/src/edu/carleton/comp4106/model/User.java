package edu.carleton.comp4106.model;

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

public class User implements DBObject {
	Integer id;
	Integer cluster;
	Double edm;
	Double hiphop;
	Double rnb;
	Double pop;
	Double rock;
	Double reggae;
	Double metal;
	Double country;
	
	private static final String ID      = "id";
	private static final String CLUSTER = "cluster";
	private static final String EDM     = Constants.EDM;
	private static final String HIP_HOP = Constants.HIP_HOP;
	private static final String RNB     = Constants.RNB;
	private static final String POP     = Constants.POP;
	private static final String ROCK    = Constants.ROCK;
	private static final String REGGAE  = Constants.REGGAE;
	private static final String METAL   = Constants.METAL;
	private static final String COUNTRY = Constants.COUNTRY;
	
	public User() {
	}
	
	public void setId(Integer id) { this.id = id; }
	public void setCluster(Integer cluster) { this.cluster = cluster; }
	public void setEDM(Double value) { this.edm = value; }
	public void setHipHop(Double value) { this.hiphop = value; }
	public void setRNB(Double value) { this.rnb = value; }
	public void setPop(Double value) { this.pop = value; }
	public void setRock(Double value) { this.rock = value; }
	public void setReggae(Double value) { this.reggae = value; }
	public void setMetal(Double value) { this.metal = value; }
	public void setCountry(Double value) { this.country = value; }
	
	public Integer getId() { return this.id; }
	public Integer getCluster() { return this.cluster; }
	public Double getEDM() { return this.edm; }
	public Double getHipHop() { return this.hiphop; }
	public Double getRNB() { return this.rnb; }
	public Double getPop() { return this.pop; }
	public Double getRock() { return this.rock; }
	public Double getReggae() { return this.reggae; }
	public Double getMetal() { return this.metal; }
	public Double getCountry() { return this.country; }
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("\nID: " + this.getId() + "; ");
		result.append("Cluster: " + this.getCluster() + ";");
		
		result.append("\n-------------------------");
		result.append("\nPREFERENCES (0-100):");
		result.append("\n====================");
		
		result.append("\nEDM: " + this.getEDM() + "; ");
		result.append("\nHip Hop: " + this.getHipHop() + "; ");
		result.append("\nR&B: " + this.getRNB() + "; ");
		result.append("\nPop: " + this.getPop() + "; ");
		result.append("\nRock: " + this.getRock() + "; ");
		result.append("\nReggae: " + this.getReggae() + "; ");
		result.append("\nMetal: " + this.getMetal() + "; ");
		result.append("\nCountry: " + this.getCountry() + "; ");
		
		result.append("\n-------------------------");
		
		return result.toString();
	}

	@Override
	public boolean containsField(String keys) {
		if(keys.equals(ID) || keys.equals(CLUSTER)
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
		if(keys.equals(ID) || keys.equals(CLUSTER)
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
		} else if(keys.equals(CLUSTER)) {
			return getCluster();
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
		keySet.add(CLUSTER);
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
		} else if(key.equals(CLUSTER)) {
			setCluster((Integer) value);
		} else if(key.equals(EDM)) {
			setEDM((Double) value);
		} else if(key.equals(HIP_HOP)) {
			setHipHop((Double) value);
		} else if(key.equals(RNB)) {
			setRNB((Double) value);
		} else if(key.equals(POP)) {
			setPop((Double) value);
		} else if(key.equals(ROCK)) {
			setRock((Double) value);
		} else if(key.equals(REGGAE)) {
			setReggae((Double) value);
		} else if(key.equals(METAL)) {
			setMetal((Double) value);
		} else if(key.equals(COUNTRY)) {
			setCountry((Double) value);
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
		} else if(key.equals(CLUSTER)) {
			setCluster(null);
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

