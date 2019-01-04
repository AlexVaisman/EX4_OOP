package GIS;

import java.awt.Color;
import java.text.ParseException;

import Geom.Point3D;

public class Ghost {
	private Point3D gps;
	private Mdata_game metaData;
	private Color color;
	private int picSize;
	

	public Ghost(String[] line) throws ParseException {
		double lat = 0, lon = 0, alt = 0;
		lat = Double.parseDouble(line[2]);
		lon = Double.parseDouble(line[3]);
		alt = Double.parseDouble(line[4]);
		this.gps = new Point3D(lat, lon, alt);

		this.metaData = new Mdata_game(line);
		
		/* Ghost GUI representation settings */
		this.color = Color.RED;
		this.picSize = 35;
	}
	
	public String toString() {
		return "Ghost:gps=" + gps + ", metaData:" + metaData;
	}

	/* Getters */
	public Point3D getGps() {
		return gps;
	}

	public Mdata_game getMetaData() {
		return metaData;
	}

	public Color getColor() {
		return color;
	}

	public int getPicSize() {
		return picSize;
	}
	
}
