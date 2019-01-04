package GIS;

import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;

import Coords.MyCoords;
import Geom.Point3D;

public class Player {
	private int id;
	private double radius;
	private double speed;
	private Point3D gps;
	private double orientation;
	private Color color;
	private int picSize;
	private ArrayList<Corner> whatISee;

	public Player(String[] line) throws ParseException {
		id = Integer.parseInt(line[1]);
		
		double lat = Double.parseDouble(line[2]);
		double lon = Double.parseDouble(line[3]);
		double alt = Double.parseDouble(line[4]);
		this.gps = new Point3D(lat, lon, alt);
		
		this.speed = Double.parseDouble(line[5]);
		this.radius = Double.parseDouble(line[6]);
		this.orientation = 0;
		this.whatISee = new ArrayList<Corner>();
		
		/* Player GUI representation settings */
		this.color = Color.PINK;
		this.picSize = 50;
	}

	public void findOrientation(Point3D gps) {
		MyCoords coords = new MyCoords();
		double arr[] = new double[3];
		arr = coords.azimuth_elevation_dist(this.gps, gps);
		orientation = arr[0];
	}
	
	/* Getters */
	public Point3D getGps() {
		return gps;
	}

	public double getOrientation() {
		return orientation;
	}

	public Color getColor() {
		return color;
	}

	public int getPicSize() {
		return picSize;
	}

	public int getId() {
		return id;
	}

	public double getRadius() {
		return radius;
	}

	public double getSpeed() {
		return speed;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}

	public ArrayList<Corner> getWhatISee() {
		return whatISee;
	}

}
