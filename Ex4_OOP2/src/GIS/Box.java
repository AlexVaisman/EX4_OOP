package GIS;

import java.awt.Color;
import java.text.ParseException;

import Geom.Point3D;

public class Box {
	private Point3D botLeft;
	private Point3D botRight;
	private Point3D topRight;
	private Point3D topLeft;
	private BoxHitBox Vbox ;
	private WallBox wallBox;
	private int id;

	public Box(String[] line) throws ParseException {
		double lat = 0, lon = 0, alt = 0;
		lat = Double.parseDouble(line[2]);
		lon = Double.parseDouble(line[3]);
		alt = Double.parseDouble(line[4]);

		botLeft = new Point3D(lat, lon, alt);

		lat = Double.parseDouble(line[5]);
		lon = Double.parseDouble(line[6]);
		alt = Double.parseDouble(line[7]);

		topRight = new Point3D(lat, lon, alt);
		
		topLeft = new Point3D(topRight.x(), botLeft.y());
		botRight = new Point3D(botLeft.x(), topRight.y());
        this.Vbox = new BoxHitBox(this);
        this.wallBox = new WallBox(this);
		id = Integer.parseInt(line[1]);
		
	}
	
	public boolean isIn(Point3D gps) {

		if (botLeft.x() <= gps.x() && gps.x() <= topRight.x()) {
			if (botLeft.y() <= gps.y() && gps.y() <= topRight.y())
				return true;
		}
		return false;
	}
	
	

	public String toString() {
		return "Box: botLeft=" + botLeft + ", topRight=" + topRight + ", id=" + id;
	}
	
	/* Getters */
	public int getId() {
		return id;
	}

	public Point3D getBotLeft() {
		return botLeft;
	}

	public Point3D getTopRight() {
		return topRight;
	}

	public Point3D getTopLeft() {
		return topLeft;
	}

	public Point3D getBotRight() {
		return botRight;
	}

	public BoxHitBox getVBox() {
		return Vbox;
	}

	public WallBox getWallBox() {
		return wallBox;
	}

}
