package Algo;

import java.util.ArrayList;
import java.util.Iterator;

import graph.Node;

public class Path {

	private Node data;
	private ArrayList<Integer> theWay;
	private  double distance;

	public Path(Node data) {
		this.data = data;
		this.distance = data.getDist();
		ConvertToInt();
	}


	private void ConvertToInt() {
		ArrayList<String> theStringWay = data.getPath();
		Iterator<String> stringIt = theStringWay.iterator();

		if(stringIt.hasNext()) {
			stringIt.next();
		}
		while(stringIt.hasNext()) {
			int num = Integer.parseInt(stringIt.next());
			this.theWay.add(num);
		}
	}

        /* Getters */
	public Node getData() {
		return data;
	}


	public ArrayList<Integer> getTheWay() {
		return theWay;
	}


	public double getDistance() {
		return distance;
	}
}
