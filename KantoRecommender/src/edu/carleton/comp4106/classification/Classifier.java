package edu.carleton.comp4106.classification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.carleton.comp4106.dao.ClusterCollection;
import edu.carleton.comp4106.dao.UserCollection;
import edu.carleton.comp4106.model.Cluster;
import edu.carleton.comp4106.model.User;
import edu.carleton.comp4106.utils.ArrayUtils;
import edu.carleton.comp4106.utils.Constants;

public class Classifier {
	
	public static void classify() {
		System.out.println("x----------------------x");
		System.out.println("CLASSIFIER INITIATING...");
		System.out.println();
		
		System.out.println("Retrieving Clusters from DB...");
		Map<Integer, List<User>> clusters = getAllClusters();
		
		System.out.println("Printing Clusters...");
		
		for(Integer clusterNum: clusters.keySet()) {
			System.out.println();
			System.out.println("CLUSTER " + clusterNum + ": " 
					+ clusters.get(clusterNum).size() + " Users");
			
			ArrayUtils.printUsers(clusters.get(clusterNum));
		}
		
		System.out.println("\nComputing Average of Clusters...");
		
		double[][] averages = new double[clusters.size()][8];
		
		for(Integer clusterNum: clusters.keySet()) {
			System.out.println();
			System.out.println("CLUSTER " + clusterNum + " FEATURES:");
			System.out.println("-------------------------------------");
			
			averages[clusterNum] = clusterAverage(clusters.get(clusterNum));
			
			System.out.println("-------------------------------------");
		}
		
		System.out.println("\nAverage Matrix:\n================");
		ArrayUtils.printMatrix(averages);
		
		System.out.println("\nComputing Feature Thresholds...");
		double[] thresholds = computeThresholds(averages);
		
		ArrayUtils.printNice(thresholds);
		
		System.out.println("\nClassifying Clusters...");
		Cluster[] classifiedClusters = new Cluster[clusters.size()];
		
		for(int i = 0; i < averages.length; i++) {
			for(int j = 0; j < averages[i].length; j++) {
				classifiedClusters[i] = 
						classifyCluster(i, averages[i], thresholds);
			}
		}
		
		System.out.println("\nPrinting Classified Clusters...");
		for(int i = 0; i < classifiedClusters.length; i++) {
			System.out.println();
			System.out.println(classifiedClusters[i]);
		}
		
		System.out.println("\nStoring Clusters in DB...");
		for(int i = 0; i < classifiedClusters.length; i++) {
			ClusterCollection.getInstance().add(classifiedClusters[i]);
		}
		
		System.out.println();
		System.out.println("CLASSIFICATION COMPLETE!");
		System.out.println("x----------------------x");
	}
	
	private static Map<Integer, List<User>> getAllClusters() {
		Map<Integer, List<User>> clusters = new HashMap<Integer, List<User>>();
		
		System.out.println("Retrieving all clusters...");
		
		for(int i = 0; i < Constants.NUM_CLUSTERS; i++) {
			List<User> cluster = 
					UserCollection.getInstance().findByCluster(i);
			
			clusters.put(i, cluster);
		}
		
		System.out.println("All clusters retrieved!");
		
		return clusters;
	}
	
	private static double[] clusterAverage(List<User> cluster) {
		double edm = 0.0, hiphop = 0.0, rnb = 0.0, 
				pop = 0.0, rock = 0.0, reggae = 0.0, 
				metal = 0.0, country = 0.0;
		
		// Sum up
		for(User user: cluster) {
			edm += user.getEDM();
			hiphop += user.getHipHop();
			rnb += user.getRNB();
			pop += user.getPop();
			rock += user.getRock();
			reggae += user.getReggae();
			metal += user.getMetal();
			country += user.getCountry();
		}
		
		int size = cluster.size();
		
		// Compute Average
		edm /= size;
		hiphop /= size;
		rnb /= size;
		pop /= size;
		rock /= size;
		reggae /= size;
		metal /= size;
		country /= size;
		
		// Print
		System.out.println("EDM = " + edm);
		System.out.println("HIP HOP = " + hiphop);
		System.out.println("R&B = " + rnb);
		System.out.println("POP = " + pop);
		System.out.println("ROCK = " + rock);
		System.out.println("REGGAE = " + reggae);
		System.out.println("METAL = " + metal);
		System.out.println("COUNTRY = " + country);
		
		// Store
		double[] features = new double[8];
		
		features[0] = edm;
		features[1] = hiphop;
		features[2] = rnb;
		features[3] = pop;
		features[4] = rock;
		features[5] = reggae;
		features[6] = metal;
		features[7] = country;
		
		return features;
	}
	
	public static double[] computeThresholds(double[][] averages) {
		// Threshold for each feature in the corresponding index
		double[] thresholds = new double[averages[0].length];
		
		// Average of each index (column wise iteration)
		// Sum up first
		for(int j = 0; j < averages[0].length; j++) {
			for(int i = 0; i < averages.length; i++) {
				thresholds[j] += averages[i][j];
			}
		}
		
		// Divide by total
		for(int i = 0; i < thresholds.length; i++) {
			thresholds[i] = thresholds[i]/averages.length;
		}
		
		return thresholds;
	}
	
	public static Cluster classifyCluster(int clusterNum, double[] averages, double[] thresholds) {
		Cluster cluster = new Cluster();
		
		cluster.setId(clusterNum);
		
		// If average is more than threshold, add genre as preference in cluster
		// Genres are mapped to specific indices in array
		
		if(averages[0] >= thresholds[0]) {
			cluster.setEDM(1);
		} else {
			cluster.setEDM(0);
		}
		
		if(averages[1] >= thresholds[1]) {
			cluster.setHipHop(1);
		} else {
			cluster.setHipHop(0);
		}
		
		if(averages[2] >= thresholds[2]) {
			cluster.setRNB(1);
		} else {
			cluster.setRNB(0);
		}
		
		if(averages[3] >= thresholds[3]) {
			cluster.setPop(1);
		} else {
			cluster.setPop(0);
		}
		
		if(averages[4] >= thresholds[4]) {
			cluster.setRock(1);
		} else {
			cluster.setRock(0);
		}
		
		if(averages[5] >= thresholds[5]) {
			cluster.setReggae(1);
		} else {
			cluster.setReggae(0);
		}
		
		if(averages[6] >= thresholds[6]) {
			cluster.setMetal(1);
		} else {
			cluster.setMetal(0);
		}
		
		if(averages[7] >= thresholds[7]) {
			cluster.setCountry(1);
		} else {
			cluster.setCountry(0);
		}
		
		return cluster;
	}
	
	public static void main(String[] args) {
		classify();
	}

}
