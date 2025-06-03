// Name: Aniketh Bandlamudi   
// Date: 05/19/25

/*
Purpose: To implement a graph data structure using adjacency lists and demonstrate basic graph operations.

Mistakes made: Initially struggled with edge addition logic and proper vertex retrieval.

How I feel about this programming experience: Challenging but rewarding. Helped me understand graph concepts better.

What I Learned: How adjacency lists represent graphs efficiently and the importance of proper data structure selection.

The credits: who and/or what website(s) helped you and TO WHAT EXTENT: N/A

Students (names) you helped and to what extent: N/A
*/

import java.util.*;
import java.io.*;

/* Interfaces for use with Graphs 3–5 */
interface VertexInterface {
   String toString();
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addAdjacent(Vertex v);
} 

interface AdjListInterface { 
   List<Vertex> getVertices();
   Vertex getVertex(int i);
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();
}

/************************************/
/********** VERTEX CLASS ************/
class Vertex implements VertexInterface {
   private final String name;
   private ArrayList<Vertex> adjacencies;

   public Vertex(String name) {
      this.name = name;
      this.adjacencies = new ArrayList<>();
   }

   public String getName() {
      return name;
   }

   public ArrayList<Vertex> getAdjacencies() {
      return adjacencies;
   }

   public void addAdjacent(Vertex v) {
      adjacencies.add(v);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(name).append(" [");
      for (int i = 0; i < adjacencies.size(); i++) {
         sb.append(adjacencies.get(i).getName());
         if (i < adjacencies.size() - 1)
            sb.append(" ");
      }
      sb.append("]");
      return sb.toString();
   }
}

/******************************************/
/********** ADJACENCY LIST CLASS **********/
class AdjList implements AdjListInterface {
   private ArrayList<Vertex> vertices = new ArrayList<>();
   private Map<String, Integer> nameToIndex = new TreeMap<>();

   // pre: none
   // post: returns the list of all vertices in the graph
   public List<Vertex> getVertices() {
      return vertices;
   }

   // pre: 0 <= i < vertices.size()
   // post: returns the vertex at index i
   public Vertex getVertex(int i) {
      return vertices.get(i);
   }

   // pre: vertexName is not null
   // post: returns the vertex with the given name or null if not found
   public Vertex getVertex(String vertexName) {
      Integer index = nameToIndex.get(vertexName);
      if (index != null) {
         return vertices.get(index);
      }
      return null;
   }

   // pre: none
   // post: returns the map from vertex names to their indices
   public Map<String, Integer> getVertexMap() {
      return nameToIndex;
   }

   // pre: v is not null
   // post: adds vertex v to the graph if it doesn't already exist
   public void addVertex(String v) {
      if (!nameToIndex.containsKey(v)) {
         Vertex vertex = new Vertex(v);
         vertices.add(vertex);
         nameToIndex.put(v, vertices.size() - 1);
      }
   }

   // pre: source and target are not null
   // post: adds a directed edge from source to target if both vertices exist
   public void addEdge(String source, String target) {
      if (!nameToIndex.containsKey(source) || !nameToIndex.containsKey(target))
         return;
      Vertex from = getVertex(source);
      Vertex to = getVertex(target);
      from.addAdjacent(to);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      for (Vertex v : vertices) {
         sb.append(v.toString()).append("\n");
      }
      return sb.toString().trim();
   }
}

/**********************************************/
/********** DRIVER CLASS WITH MAIN ************/

public class Pd5AnikethBandlamudiAdjList
{  
   public static void main(String[] args)throws FileNotFoundException
   {
      System.out.println("Edge List Representation! ");
      System.out.println("Test the Vertex class");
      Vertex a = new Vertex("alpha");
      Vertex b = new Vertex("beta");
      a.addAdjacent(b);
      b.addAdjacent(a);
      System.out.println("get the names:\n  " + a.getName() + "\n  " + b.getName() );
      System.out.println("get the list of adjacencies: \n  " + a.getAdjacencies() +"\n  " + b.getAdjacencies());
      System.out.println("toString() shows the names plus the name of the neighbor(s): \n  " + a.toString() +"\n  " + b.toString());
     
      System.out.println("\nTest the AdjList class");
      AdjList g = new AdjList();
      g.addVertex("A");      
      g.addVertex("B");
      System.out.println("list of vertices in the graph:  " + g.getVertices());
      System.out.println("  map string to index:  " + g.getVertexMap());  
      System.out.println("  get vertex by index 1:  " + g.getVertex(1).toString());  
      System.out.println("  get vertex by name \"B\":  " + g.getVertex("B").toString());
      System.out.println("the whole graph:\n" + g.toString());
      
      g.addEdge("A", "C"); 
      g.addVertex("C");
      g.addVertex("D");
      g.addEdge("B", "A");
      g.addEdge("C", "C");
      g.addEdge("C", "D");
      g.addEdge("D", "C");
      g.addEdge("D", "A");
      System.out.println("\nlist of vertices in the graph:  " + g.getVertices());
      System.out.println("  map string to index:  " + g.getVertexMap());  
      System.out.println("  get vertex by index:  " + g.getVertex(1));  
      System.out.println("  get vertex by name:  " + g.getVertex("B").toString());  
      System.out.println("the whole graph:\n"+g.toString());       
   }
}

/*output
Edge List Representation! 
Test the Vertex class
get the names:
  alpha
  beta
get the list of adjacencies: 
  [beta [alpha]]
  [alpha [beta]]
toString() shows the names plus the name of the neighbor(s): 
  alpha [beta]
  beta [alpha]

Test the AdjList class
list of vertices in the graph:  [A [], B []]
  map string to index:  {A=0, B=1}
  get vertex by index 1:  B []
  get vertex by name "B":  B []
the whole graph:
A []
B []

list of vertices in the graph:  [A [], B [A], C [C D], D [C A]]
  map string to index:  {A=0, B=1, C=2, D=3}
  get vertex by index:  B [A]
  get vertex by name:  B [A]
the whole graph:
A []
B [A]
C [C D]
D [C A]

*/

// end of program