package GIS;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class represents a Solution.
 * Solution is an ArrayList of Paths.
 * a Solution will be created to represent all the paths all the pacmans will make in a given game.
 * @author Alex vaisman, Shay naor.
 */
public class Solution {

	
	private ArrayList<Path> solution;
	private double time;
	private double distance;
	private double score;
	
	
	public Solution() {
		this.solution = new  ArrayList<Path>();
		this.time = 0;
		this.distance = 0 ;
		this.score = 0;
	}


	/* Getters */
	public ArrayList<Path> getSolution() {
		return solution;
	}

/**
 * returns the time of the longest path
 * @return the time of the longest path
 */
	public double getTime() {
		double time = 0;
		
		Iterator<Path> pathIt = this.solution.iterator();
		while(pathIt.hasNext()) {
			Path path = pathIt.next();
			time = path.finalTime();
			if(time > this.time) {
				this.time = time;
			}
		}
		return this.time;
	}


	public double getDistance() {
		
		Iterator<Path> pathIt = this.solution.iterator();
		while(pathIt.hasNext()) {
			Path path = pathIt.next();
			this.distance += path.getDistance();
		}
		return distance;
	}


	public double getScore() {
		return score;
	}
}
