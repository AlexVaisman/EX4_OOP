package GIS;

import java.util.ArrayList;

/**
 * This class represents a Path.
 * A path is an ArrayList of GIS_elements.
 * A path will be arranged to represent the path a pacman must take to eat all the fruits.
 * 
 * @author Alex vaisman, Shay naor.
 *
 */
public class Path {



	private ArrayList<GIS_element> path;
	private double time;
	private double distance;
	private double score;
	private double fruitsEaten;
	
	public Path() {
		this.path = new ArrayList<GIS_element>();
		this.time = 0;
		this.distance = 0;
		this.score = 0;
		this.fruitsEaten = 0;
	}

	
	public void addDistance(double x) {
		this.distance = this.distance + x;
	}
	
	public double finalTime() {
		return this.distance/((Pacman)(path.get(0))).getMetaData().getSpeedWeight();
	}
	
	
	
	//=======Geters======
	public ArrayList<GIS_element> getPath() {
		return path;
	}

	public double getTime() {
		return time;
	}

	public double getDistance() {
		return distance;
	}

	public double getScore() {
		return score;
	}

	public double getFruitsEaten() {
		return fruitsEaten;
	}
	
	

}
