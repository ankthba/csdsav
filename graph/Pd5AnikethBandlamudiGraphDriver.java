// /**
//  * Name: Aniketh Bandlamudi                          
//  * Period: 5
//  * Name of the Lab: U11: Graph 0 (AdjMat) short program
//  * Purpose of the Program: To implement and test a graph using adjacency matrix representation
//  * Due Date: 4/28/25
//  * Date Submitted: 4/27/25
//  * What I learned: How to represent a graph using adjacency matrix and implement basic operations
//  * like adding/removing edges and finding neighbors
//  * How I feel about this lab: It was interesting to see how graphs can be represented using 2D arrays
//  * What I wonder: How this implementation compares to an adjacency list representation in terms of
//  * time and space complexity
//  *
//  * Most Difficult Method: getNeighbors() making sure to efficiently collect all connected vertices
//  * Credits: N/A
//  */



// import java.util.*;
// import java.io.*;
// //import javax.swing.JFileChooser;

// class GraphAdjMat
// {
//    private int[][] grid = null;   //adjacency matrix representation
 

//    public GraphAdjMat(int size)
//    {
//       grid = new int[size][size];
//    }    
   
//    // pre: source and target are valid vertices
//    // post: add an edge from source to target
//    // Note: addEdge only changes grid, not the vertex list
//    public void addEdge(int source, int target)
//    {
//       grid[source][target] = 1;
//    } 

//    // pre: source and target are valid vertices
//    // post: remove an edge from source to target
//    // Note: removeEdge only changes grid, not the vertex list
//    public void removeEdge(int source, int target)
//    {
//       grid[source][target] = 0;
//    }

//    // pre: source and target are valid vertices
//    // post: return true if there is an edge from source to target
//    private boolean isEdge(int from, int to)
//    {
//       return grid[from][to] == 1;
//    }

//    // pre: source and target are valid vertices
//    // display contents of ajacency matrix
//    public void displayGrid()
//    {
//       for(int i = 0; i < grid.length; i++) {
//          for(int j = 0; j < grid[i].length; j++) {
//             System.out.print("  " + grid[i][j]);
//          }
//          System.out.println(" ");
//       }
//    }  //displayGrid
   
//    // pre: source and target are valid vertices
//    // count how many edges are in the graph
//    public int edgeCount()
//    {
//       int count = 0;
//       for(int i = 0; i < grid.length; i++) {
//          for(int j = 0; j < grid[i].length; j++) {
//             if(grid[i][j] == 1)
//                count++;
//          }
//       }
//       return count;
//    }  // edgeCount
   
    
//    // return neighbors of "source" in a List structure
//    // the neighbors of a source vertex are all the vertices reachable by traveling along one (1) edge.
//    public List<Integer> getNeighbors(int sourceVertex)
//    {
//       List<Integer> neighbors = new ArrayList<>();
//       for(int col = 0; col < grid.length; col++) {
//          if(grid[sourceVertex][col] == 1) {
//             neighbors.add(col);
//          }
//       }
//       return neighbors; 
//    }  // getNeighbors

//    //Note: There are other methods that we don't include here for a graph
//    // addVertex, removeVertex
   
// }  // GraphAdjMat_shell

// public class Pd5AnikethBandlamudiGraphDriver
// {
//    public static void main(String[] args)throws FileNotFoundException
//    {
//     /*  JFileChooser ourChooser = new JFileChooser(".");
//       int retval = ourChooser.showOpenDialog(null);
//       if (retval == JFileChooser.APPROVE_OPTION) 
//       {
//          File file = ourChooser.getSelectedFile();
//       }     
//    */
//       Scanner kb = new Scanner(System.in);
//       System.out.print("Enter size of adjacency matrix: "); 
//       int size = kb.nextInt();
//       GraphAdjMat g = new GraphAdjMat(size);
//       System.out.println("Adjacency Matrix");
//       g.displayGrid();
//       System.out.println("Add edges, source<space>target<enter>.  Enter -1 to end.");
//       while(true)
//       {
//          int source = kb.nextInt();
//          if( source == -1 ) 
//             break;
//          g.addEdge(source,kb.nextInt());
//          g.displayGrid();
//       }
      
//       g.displayGrid();
//       System.out.print("Remove an edge? ");
//       if( kb.next().equalsIgnoreCase("Y"))
//       {
//          System.out.print("Remove which edge?  ");
//          g.removeEdge( kb.nextInt(), kb.nextInt() );
//          g.displayGrid();
//       }
   			
//       System.out.println("Number of edges: " + g.edgeCount());
//       System.out.println("The neighbors of each vertex: ");
//       for(int i=0; i<size; i++)
//       {
//          System.out.println(i + ": " +g.getNeighbors(i));
//       }
//    }  // main
// }  // Graph0Driver

// /*
//  Enter size of adjacency matrix: 4
//  Adjacency Matrix
//   0  0  0  0 
//   0  0  0  0 
//   0  0  0  0 
//   0  0  0  0 
//  Add edges, source<space>target<enter>.  Enter -1 to end.
//  0 3
//   0  0  0  1 
//   0  0  0  0 
//   0  0  0  0 
//   0  0  0  0 
//  1 3
//   0  0  0  1 
//   0  0  0  1 
//   0  0  0  0 
//   0  0  0  0 
//  3 2
//   0  0  0  1 
//   0  0  0  1 
//   0  0  0  0 
//   0  0  1  0 
//  3 0
//   0  0  0  1 
//   0  0  0  1 
//   0  0  0  0 
//   1  0  1  0 
//  2 2
//   0  0  0  1 
//   0  0  0  1 
//   0  0  1  0 
//   1  0  1  0 
//  -1
//   0  0  0  1 
//   0  0  0  1 
//   0  0  1  0 
//   1  0  1  0 
//  Remove an edge? Y/Nn
//  Number of edges: 5
//  The neighbors of each vertex: 
//  0: [3]
//  1: [3]
//  2: [2]
//  3: [0, 2]

// */