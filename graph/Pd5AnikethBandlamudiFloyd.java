/**************************************************** 
Name (Full Name): Aniketh Bandlamudi   Period:   5
Name of the Lab/Assignment: U11: Floyd's algo

Purpose:
Implement Floyd's algorithm to find shortest paths between all pairs
of vertices in a weighted graph represented as cities.

Mistakes made:
Challenges with the nested loop structure and correctly updating
shorter paths through intermediate vertices.


How I feel about this programming experience:
Challenging but rewarding as I gained deeper understanding of graph algorithms.


What I Learned:
- Floyd's algorithm implementation
- Working with adjacency matrices for weighted graphs
- Dynamic programming applications in graph problems


The credits: who and/or what website(s) helped you and TO WHAT EXTENT: N/A

Students (names) you helped and to what extent: N/A
****************************************************/


// Graphs 2,  uses AdjMat

import java.util.*;
import java.io.*;

public class Pd5AnikethBandlamudiFloyd {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(System.in);
        System.out.print("Floyd's Algorithm! Enter file of names: ");
        String fileNames = kb.next() + ".txt";

        Scanner sc = new Scanner(new File(fileNames));
        int size = sc.nextInt();
        AdjMat g = new AdjMat(size);
        g.readNames(fileNames);

        System.out.print("Enter file of the matrix: ");
        String fileGrid = kb.next() + ".txt";
        g.readGrid(fileGrid);

        System.out.println("\nAdjacency Matrix");
        System.out.println(g.toString());

        System.out.println("\nNumber of Edges: " + g.edgeCount());

        g.allPairsWeighted(); // Floyd's Algorithm

        System.out.println();
        g.displayVertices();

        System.out.println("Cost Matrix");
        System.out.println(g.toString());

        System.out.println("\nNumber of Edges: " + g.edgeCount());

        while (true) {
            System.out.print("\nWhat is the cost?  Enter start city (-1 to exit): ");
            String from = kb.next();
            if (from.equals("-1"))
                break;
            System.out.print("                Enter end city: ");
            String to = kb.next();
            System.out.println(g.getCost(from, to));
        }
    }
}

interface Floyd {
    void allPairsWeighted();
}

class AdjMat implements Floyd {
    private int[][] grid;
    private ArrayList<String> names;

    public AdjMat(int size) {
        grid = new int[size][size];
        names = new ArrayList<>(Collections.nCopies(size, ""));
        for (int i = 0; i < size; i++) {
            Arrays.fill(grid[i], 9999);
            grid[i][i] = 0;
        }
    }

    // pre: 0 <= from, to < grid.length
    // post: grid[from][to] = 1
    public void allPairsWeighted() {
      for (int k = 0; k < grid.length; k++) {
          for (int i = 0; i < grid.length; i++) {
              for (int j = 0; j < grid.length; j++) {
                  if (grid[i][k] + grid[k][j] < grid[i][j]) {
                      grid[i][j] = grid[i][k] + grid[k][j];
                  }
              }
          }
      }
  }  

   // pre: filename is a valid file
   // post: names are read from the file
    public void readNames(String filename) throws FileNotFoundException {
        Scanner infile = new Scanner(new File(filename));
        int size = infile.nextInt();
        infile.nextLine(); // skip the rest of the line
        for (int i = 0; i < size; i++) {
            names.set(i, infile.nextLine().trim());
        }
    }

    // pre: filename is a valid file
   // post: grid is read from the file
    public void readGrid(String filename) throws FileNotFoundException {
        Scanner infile = new Scanner(new File(filename));
        int size = grid.length;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (infile.hasNextInt()) {
                    grid[r][c] = infile.nextInt();
                }
            }
        }
    }

    // pre: 0 <= from, to < grid.length
   // post: returns true if there is an edge from from to to
    public boolean isEdge(int from, int to) {
        return grid[from][to] != 0 && grid[from][to] != 9999;
    }

    public int edgeCount() {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (isEdge(r, c)) {
                    count++;
                }
            }
        }
        return count;
    }

    // pre: 0 <= from, to < grid.length
    // post: prints the vertices in the graph
    public void displayVertices() {
        for (int i = 0; i < names.size(); i++) {
            System.out.println(i + "-" + names.get(i));
        }
    }

    // pre: 0 <= from, to < grid.length
    // post: returns the string representation of the grid
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int val : row) {
                sb.append(String.format("%5d", val));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // pre: name is a valid vertex
   // post: returns the index of the vertex in the names list
    public int indexOf(String name) {
        return names.indexOf(name);
    }

    // pre: from and to are valid vertex names
   // post: returns the cost from from to to
    public int getCost(String from, String to) {
        int start = indexOf(from);
        int end = indexOf(to);
        if (start == -1 || end == -1) return 9999;
        return grid[start][end];
    }
}



/*******************************      
Floyd's Algorithm! Enter file of names: cities
Enter file of the matrix: citymatrixweighted

Adjacency Matrix
 0  9999  9999  9999  9999  9999  9999  8 
 9999  0  9999  5  9999  9999  9999  9999 
 9999  9999  0  9999  9999  5  9999  3 
 9999  9999  9999  0  9999  10  9999  3 
 2  9999  9999  9999  0  9999  9999  9999 
 9999  4  9999  10  9999  0  9999  9999 
 9999  9999  9999  9999  9999  2  0  9999 
 8  9999  9999  9999  3  9999  9999  0 

Number of Edges: 12

0-Pendleton
1-Pensacola
2-Peoria
3-Phoenix
4-Pierre
5-Pittsburgh
6-Princeton
7-Pueblo

Cost Matrix
 0  9999  9999  9999  11  9999  9999  8 
 13  0  9999  5  11  15  9999  8 
 8  9  0  14  6  5  9999  3 
 8  14  9999  0  6  10  9999  3 
 2  9999  9999  9999  0  9999  9999  10 
 17  4  9999  9  15  0  9999  12 
 19  6  9999  11  17  2  0  14 
 5  9999  9999  9999  3  9999  9999  0 

Number of Edges: 33

What is the cost?  Enter start city (-1 to exit): Pittsburgh
                Enter end city: Phoenix
9

What is the cost?  Enter start city (-1 to exit): Pendleton
                Enter end city: Phoenix
9999

What is the cost?  Enter start city (-1 to exit): -1

*************************************************************/     


// END OF PROGRAM