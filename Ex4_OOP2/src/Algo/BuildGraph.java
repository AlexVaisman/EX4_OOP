package Algo;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import GIS.Corner;
import GIS.Fruit;
import GIS.Player;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;

public class BuildGraph {
	private Player player;
	private ArrayList<Corner> corners;
	private Fruit fruit;
	private Graph G;
	private Node ans;

	public BuildGraph(Graph G, Player player, ArrayList<Corner> corners, Fruit fruit) {
		G.clear_meta_data();
		this.corners = corners;
		this.player = player;
		this.fruit = fruit;
		this.G = G;
	

		InitGraph();

	}

	private void InitGraph() {
		String source = "player";
		String target = "fruit";

		Node pStart = new Node(source);
		G.add(pStart); // adding player

		// adding all corners to the graph with their id
		Iterator<Corner> cornIt = this.corners.iterator();
		while (cornIt.hasNext()) {
			String id = "" + cornIt.next().getMyId();
			Node d = new Node(id);
			G.add(d);
		}
		Node tFruit = new Node(target);
		G.add(tFruit); // adding fruit
		
		AddEdges();
		Graph_Algo.dijkstra(G, source);

		Node b = G.getNodeByName(target);
		this.ans = b;

	}

	private void AddEdges() {
		MyCoords convert = new MyCoords();
		
		for(int i = 0 ; i < G.size(); i++) {
			G.getNodeByIndex(i).get_ni().clear();
		}
		
		/* added edges that the player sees */
		for (int i = 0; i < this.player.getWhatISee().size(); i++) {
			Corner corn = this.player.getWhatISee().get(i);
			G.addEdge("player", "" + corn.getMyId(), convert.distance3d(player.getGps(), corn.getGps()));
		}

		Iterator<Corner> cornerIt = this.corners.iterator();
		while (cornerIt.hasNext()) {
			Corner corn = cornerIt.next();

			for (int i = 0; i < corn.getWhatISee().size(); i++) {
				double distance = convert.distance3d(corn.getGps(), corn.getWhatISee().get(i).getGps());
				G.addEdge("" + corn.getMyId(), "" + corn.getWhatISee().get(i).getMyId(), distance);
			}
			for (int i = 0; i < corn.getWhatFruitIsee().size(); i++) {
				Fruit seeFruit = corn.getWhatFruitIsee().get(i);

				if (seeFruit.getGps().x() == this.fruit.getGps().x()
						&& seeFruit.getGps().y() == this.fruit.getGps().y()) {
					double distance = convert.distance3d(corn.getGps(), this.fruit.getGps());
					G.addEdge("" + corn.getMyId(), "fruit", distance);
				}
			}
		}
	}

	/* Getters */
	public Node getAns() {
		return ans;
	}
}