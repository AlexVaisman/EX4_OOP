package Algo;

import java.util.ArrayList;
import java.util.Iterator;

import GIS.Corner;
import GIS.Fruit;
import GIS.Player;
import graph.Graph;
import graph.Node;
import graph.Point3D;

public class BuildGraph {
     private Player player;
     private ArrayList<Corner> corners;
     private Fruit fruit;
     private Graph G ;
	
	
	public BuildGraph(Player player,ArrayList<Corner> corners,Fruit fruit ) {
		this.corners = corners;
		this.player = player;
		this.fruit = fruit;
		this.G = new Graph();
		
		InitGraph();
	}


	private void InitGraph() {
		String source = "player";
		String target = "fruit"; 
		int size = this.corners.size();
		
		G.add(new Node(source)); // adding player
        
		// adding all corners to the graph with their id
		Iterator<Corner> cornIt = this.corners.iterator();
		while(cornIt.hasNext()) {
			String id =""+ cornIt.next().getMyId();
			Node d = new Node(id);
			G.add(d);
		}
		G.add(new Node(target)); // adding fruit
		
	}
}
