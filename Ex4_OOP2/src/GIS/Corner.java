package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Algo.Segment;
import Geom.Point3D;

public class Corner {

	private Point3D gps;
	private ArrayList<Corner> whatISee;
	private static int NextId = 0;
	private final int myId;

	public Corner (Point3D corner) {
		this.gps = corner;
		this.myId = NextId++;
		this.whatISee = new ArrayList<Corner>();
	}


	public String toString() {
		return "Corner [gps=" + gps + ", whatISee=" + whatISee + ", myId=" + myId + "]";
	}
	/* Getters */

	public Point3D getGps() {
		return gps;
	}

	public ArrayList<Corner> getWhatISee() {
		return whatISee;
	}

	public int getMyId() {
		return myId;
	}


}
