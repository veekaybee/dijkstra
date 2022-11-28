package com.vickiboykis;

import java.sql.SQLOutput;
import java.util.*;

//your class test shortest path
public class TestShortestPath
{

	private static Edge parseString(Map<Vertex,List<Edge>> ves, String s) {
		String[] splitString = s.split("\\s+");
		double weight = Double.parseDouble(splitString[1]);
		Edge edge = null;

		for(Vertex v : ves.keySet()) {
			if(v.name.equals(splitString[0])) {
				edge = new Edge(v,weight);
				return edge;
			}
		}

		return edge;
	}

   public static void main(String[] args)
    {

		System.out.println("Enter list of cities");
		Scanner input = new Scanner(System.in);
		String cities = input.nextLine();

		HashMap<Vertex,List<Edge>> vertexEdges = new HashMap<>();

		String[] strSplit = cities.split("\\s+");
		for(String s:strSplit){
			Vertex newVertex = new Vertex(s);
			vertexEdges.put(newVertex,new ArrayList<Edge>());
		}

		for (Map.Entry<Vertex, List<Edge>> entry : vertexEdges.entrySet()) {
			System.out.println("Add connectors to " + entry.getKey());
			System.out.println("Add connecting cities and distance in string,int pairs. When done, DONE");
			String edge;
			while (!(edge = input.nextLine()).equals("DONE")) {
				Edge newEdge = parseString(vertexEdges,edge);
				entry.getValue().add(newEdge);
			}

		}

		for(Map.Entry<Vertex,List<Edge>> e : vertexEdges.entrySet()) {
			e.getKey().adjacencies = e.getValue().toArray(new Edge[0]);
		}

		System.out.println(vertexEdges.toString());
		System.out.println(vertexEdges.keySet().iterator().next());


		Dijkstra.computePaths(vertexEdges.keySet().iterator().next());

         for (Vertex v : vertexEdges.keySet())
	      {
	          System.out.println("Distance to " + v + ": " + v.minDistance);
	          List<Vertex> path = Dijkstra.getShortestPathTo(v);
	          System.out.println("Path: " + path);
	      }
    }
}
