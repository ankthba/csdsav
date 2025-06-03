/**************************************************** 
Name (Full Name): Aniketh Bandlamudi   Period:   5
Name of the Lab/Assignment:  U11: Warshall's program

Purpose: To implement Warshall's algorithm for finding the transitive closure
         of a directed graph represented as an adjacency matrix. This program
         determines whether there is a path between any two vertices in the graph.


Mistakes made: Initially struggled with properly implementing the reachability
               algorithm and understanding how to represent vertex names with
               their corresponding indices in the adjacency matrix.



How I feel about this programming experience: This assignment helped me gain a
                                             deeper understanding of graphs and
                                             algorithms for path finding. Working with
                                             adjacency matrices was challenging but rewarding.



What I Learned: I learned how to implement Warshall's algorithm efficiently, work with
                adjacency matrices to represent graphs, and convert between vertex names
                and their corresponding indices. I also gained experience with Java's Map
                interface for maintaining vertex information.



The credits: who and/or what website(s) helped you and TO WHAT EXTENT: N/A

Students (names) you helped and to what extent: N/A
****************************************************/



import java.util.*;
import java.io.*;

interface AdjacencyMatrix {
   // pre: source and target are valid vertex indices
   // post: edge from source to target is added to matrix
   void addEdge(int source, int target);
   
   // pre: source and target are valid vertex indices
   // post: edge from source to target is removed from matrix
   void removeEdge(int source, int target);
   
   // pre: from and to are valid vertex indices
   // post: returns true if edge exists, false otherwise
   boolean isEdge(int from, int to);
   
   // pre: none
   // post: returns string representation of adjacency matrix
   String toString();
   
   // pre: none
   // post: returns count of edges in graph
   int edgeCount();
   
   // pre: source is a valid vertex index
   // post: returns list of neighboring vertex indices
   List<Integer> getNeighbors(int source);
}

interface Warshall {
   // pre: from and to are valid vertex names
   // post: returns true if path exists from from to to, false otherwise
   boolean isEdge(String from, String to);
   
   // pre: none
   // post: returns map of vertex names to indices
   Map<String, Integer> getVertices();
   
   // pre: fileName exists and contains valid vertex data
   // post: vertices loaded from file into internal structure
   void readNames(String fileName) throws FileNotFoundException;
   
   // pre: fileName exists and contains valid adjacency matrix data
   // post: graph structure loaded from file
   void readGrid(String fileName) throws FileNotFoundException;
   
   // pre: vertices have been initialized
   // post: vertex information displayed to console
   void displayVertices();
   
   // pre: adjacency matrix has been initialized
   // post: matrix updated to show all reachable pairs
   void allPairsReachability();
   
   // pre: from is a valid vertex name
   // post: returns list of all vertices reachable from given vertex
   List<String> getReachables(String from);
}

class AdjMat implements AdjacencyMatrix, Warshall {
   private int[][] grid;
   private Map<String, Integer> vertices = new HashMap<>();
   private ArrayList<String> nameList = new ArrayList<>();

   // pre: size is non-negative
   // post: creates empty adjacency matrix of given size
   public AdjMat(int size) {
      grid = new int[size][size];
   }

   // pre: source and target are valid indices in grid
   // post: edge from source to target is added to matrix
   public void addEdge(int source, int target) {
      grid[source][target] = 1;
   }

   // pre: source and target are valid indices in grid
   // post: edge from source to target is removed from matrix
   public void removeEdge(int source, int target) {
      grid[source][target] = 0;
   }

   // pre: from and to are valid indices in grid
   // post: returns true if edge exists, false otherwise
   public boolean isEdge(int from, int to) {
      return grid[from][to] == 1;
   }

   // pre: none
   // post: returns formatted string representation of grid
   public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int[] row : grid) {
         for (int val : row) {
            sb.append(String.format("%2d ", val));
         }
         sb.append("\n");
      }
      return sb.toString();
   }

   // pre: none
   // post: returns total number of edges in graph
   public int edgeCount() {
      int count = 0;
      for (int[] row : grid) {
         for (int val : row) {
            if (val == 1) count++;
         }
      }
      return count;
   }

   // pre: source is a valid index in grid
   // post: returns list of vertices that have edges from source
   public List<Integer> getNeighbors(int source) {
      List<Integer> neighbors = new ArrayList<>();
      for (int i = 0; i < grid[source].length; i++) {
         if (grid[source][i] == 1)
            neighbors.add(i);
      }
      return neighbors;
   }

   // pre: filename exists and contains valid vertex data
   // post: vertices loaded from file with assigned indices
   public void readNames(String fileName) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(fileName));
      int size = sc.nextInt();
      sc.nextLine(); // skip to names
      for (int i = 0; i < size; i++) {
         String name = sc.nextLine().trim();
         vertices.put(name, i);
         nameList.add(name);
      }
   }

   // pre: filename exists and contains valid adjacency data
   // post: adjacency matrix loaded from file
   public void readGrid(String fileName) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(fileName));
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (sc.hasNextInt()) {
               grid[i][j] = sc.nextInt();
            }
         }
      }
   }

   // pre: vertices have been initialized
   // post: vertex mapping displayed to console
   public void displayVertices() {
      for (int i = 0; i < nameList.size(); i++) {
         System.out.println(i + "-" + nameList.get(i));
      }
   }

   // pre: from and to are vertex names
   // post: returns true if path exists from from to to, false otherwise
   public boolean isEdge(String from, String to) {
      Integer fromIndex = vertices.get(from);
      Integer toIndex = vertices.get(to);
      if (fromIndex == null || toIndex == null) return false;
      return isEdge(fromIndex, toIndex);
   }

   // pre: none
   // post: returns map of vertex names to indices
   public Map<String, Integer> getVertices() {
      return vertices;
   }

   // Implements Warshall's algorithm to compute all pairs reachability.
   // Transforms the adjacency matrix into a reachability matrix.
   public void allPairsReachability() {
      int n = grid.length;
      
      // Warshall's algorithm
      for (int k = 0; k < n; k++) {
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               // If vertex i can reach k AND k can reach j, then i can reach j
               if (grid[i][k] == 1 && grid[k][j] == 1) {
                  grid[i][j] = 1;
               }
            }
         }
      }
   }

   // pre: from is a valid vertex name
   // post: returns list of vertices reachable from given vertex
   public List<String> getReachables(String from) {
      List<String> reachables = new ArrayList<>();
      Integer fromIndex = vertices.get(from);
      if (fromIndex == null) return reachables;

      for (int i = 0; i < grid.length; i++) {
         if (grid[fromIndex][i] == 1 || fromIndex == i) {
            reachables.add(nameList.get(i));
         }
      }
      return reachables;
   }

   
}


public class Pd5AnikethBandlamudiWarshall {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner kb = new Scanner(System.in);
      System.out.print("Warshall's Algorithm! Enter file of names: "); 
      String fileNames = kb.next() + ".txt";
      Scanner nameScanner = new Scanner(new File(fileNames));
      int size = nameScanner.nextInt();
      nameScanner.close();
      
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      
      System.out.print("Enter file of the matrix: ");
      String fileGrid = kb.next() + ".txt";
      g.readGrid(fileGrid);
      

      System.out.println("Adjacency Matrix");
      System.out.println(g);
      System.out.println("Number of Edges: " + g.edgeCount());
      System.out.println();

      g.allPairsReachability();    
      g.displayVertices();
      System.out.println("Reachability Matrix");
      System.out.println(g);
      System.out.println("Number of Edges: " + g.edgeCount());

      while (true) {
         System.out.print("\nIs it reachable? Enter name of start city (-1 to exit): ");
         String from = kb.next().trim();
         if (from.equals("-1")) break;
         System.out.print("                Enter name of end city: ");
         String to = kb.next().trim();  
         System.out.println(g.isEdge(from, to));
      }

      System.out.println("\n================== EXTENSION =================="); 
      System.out.println("List of every city's reachable cities: ");
      for (String city : g.getVertices().keySet()) {
         System.out.println(city + "--> " + g.getReachables(city));
      }

      while (true) {
         System.out.print("\nList the reachable cities from: ");
         String from = kb.next();
         if (from.equals("-1")) break;
         System.out.println(g.getReachables(from));
      }
   }
}


/* SAMPLE OUTPUT */

/*

Warshall's Algorithm! Enter file of names:
cities
Enter file of the matrix: citymatrix
Adjacency Matrix
0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0
0 0 0 0 0 1 0 1
0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0
0 1 0 1 0 0 0 0
0 0 0 0 0 1 1 0
1 0 0 0 1 0 0 0
Number of Edges: 13
0-Pendleton
1-Pensacola
2-Peoria
3-Phoenix
4-Pierre
5-Pittsburgh
6-Princeton
7-Pueblo
Reachability Matrix
1 0 0 0 1 0 0 1
1 1 0 1 1 1 0 1
1 1 0 1 1 1 0 1
1 1 0 1 1 1 0 1
1 0 0 0 1 0 0 1
1 1 0 1 1 1 0 1
1 1 0 1 1 1 1 1
1 0 0 0 1 0 0 1
Number of Edges: 40

Is it reachable? Enter name of start city (-1 to
exit): Peoria
Enter name of end city: Pittsburgh
true
Is it reachable? Enter start city (-1 to exit): -1
================== EXTENSION ==================
List of every city's reachable cities:
Pendleton--> [Pendleton, Pierre, Pueblo]
Pensacola--> [Pendleton, Pensacola, Phoenix,
Pierre, Pittsburgh, Pueblo]
Peoria--> [Pendleton, Pensacola, Phoenix, Pierre,
Pittsburgh, Pueblo]
Phoenix--> [Pendleton, Pensacola, Phoenix, Pierre,
Pittsburgh, Pueblo]
Pierre--> [Pendleton, Pierre, Pueblo]
Pittsburgh--> [Pendleton, Pensacola, Phoenix,
Pierre, Pittsburgh, Pueblo]
Princeton--> [Pendleton, Pensacola, Phoenix,
Pierre, Pittsburgh, Princeton, Pueblo]
Pueblo--> [Pendleton, Pierre, Pueblo]
List the reachable cities from: Pittsburgh
[Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh,
Pueblo]
List the reachable cities from: -1



 */

/*
// pre: none
   // post: runs the program with user interaction
   public static void main(String[] args) throws FileNotFoundException {
      Scanner kb = new Scanner(System.in);
      System.out.print("Warshall's Algorithm! Enter file of names: "); 
      String fileNames = kb.next() + ".txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);

      System.out.print("Enter file of the matrix: ");
      String fileGrid = kb.next() + ".txt";
      g.readGrid(fileGrid);

      System.out.println("Adjacency Matrix");
      System.out.println(g);
      System.out.println("Number of Edges: " + g.edgeCount());
      System.out.println();

      g.allPairsReachability();    
      g.displayVertices();
      System.out.println("Reachability Matrix");
      System.out.println(g);
      System.out.println("Number of Edges: " + g.edgeCount());

      while (true) {
         System.out.print("\nIs it reachable? Enter name of start city (-1 to exit): ");
         String from = kb.next().trim();
         if (from.equals("-1")) break;
         System.out.print("                Enter name of end city: ");
         String to = kb.next().trim();  
         System.out.println(g.isEdge(from, to));
      }

      System.out.println("\n================== EXTENSION =================="); 
      System.out.println("List of every city's reachable cities: ");
      for (String city : g.getVertices().keySet()) {
         System.out.println(city + "--> " + g.getReachables(city));
      }

      while (true) {
         System.out.print("\nList the reachable cities from: ");
         String from = kb.next();
         if (from.equals("-1")) break;
         System.out.println(g.getReachables(from));
      }
   }
 */


// END OF PROGRAM