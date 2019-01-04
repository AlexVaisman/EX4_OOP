package Algo;

import Geom.Point3D;

public class Segment {

	private Point3D a;
	private Point3D b;
	private boolean isVertical;
	
	public Segment (Point3D GpsA,Point3D GpsB) {
		this.a = GpsA;
		this.b = GpsB;
		if(GpsA.y()==GpsB.y()) {
			this.isVertical = true;
		}
		else this.isVertical = false;
	}
	
		public String toString() {
		return "Segment [a=" + a + ", b=" + b + ", isVertical=" + isVertical + "]";
	}
 /* Getters */
	
	
	public Point3D getA() {
		return a;
	}

	public Point3D getB() {
		return b;
	}

	public boolean isVertical() {
		return isVertical;
	}


	

}
