/***********************************************************************************************************************************************
 * Name: Aniketh Bandlamudi
 * Period: 5
 * Name of the Lab: 
 * Purpose of the Program: 
 * Due Date: 9/27/24
 * Date Submitted: 9/26/24
 * What I learned: 
 * 1. 
 * 2. 
 * How I feel about this lab: 
 * 
 * What I wonder: 
 *
 * Student(s) who helped me (to what extent): 
 * Student(s) whom I helped (to what extent): 
 *************************************************************************************************************************************************/

   
import java.util.Scanner;
import java.io.*;
public class AreaFill_shell
{
   public static char[][] grid = null;
   
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Filename: ");
      String filename = sc.next();
      grid = read(filename + ".txt");
      display(grid);
      
      while (true) {
         System.out.print("\nEnter ROW COL to fill from (or -1 -1 to quit): ");
         int row = sc.nextInt();
         int col = sc.nextInt();
         
         if (row == -1 && col == -1) {
            break;
         }
         
         char fillChar;
         System.out.print("Enter fill character: ");
         fillChar = sc.next().charAt(0);
         
         fill(grid, row, col, grid[row][col], fillChar);
         display(grid);
      }
      
      sc.close();
   }
   
   public static char[][] read(String filename)throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(filename));
      char[][] board = new char[sc.nextInt()][sc.nextInt()];
      for(int i = 0; i < board.length; i++) {
         String row = sc.next();
         board[i] = row.toCharArray();
      }
      return board;
   }
   
   public static void display(char[][] g)
   {              
      for(int i = 0; i < g.length; i++) {
         for(int j = 0; j < g[i].length; j++) {
            System.out.print(g[i][j]);
         }
         System.out.println();
      }
   }
   
   /**
    * pre: method called in main method
    * post: modifies char[][] g and replaces characters of the 
    * index indicated on the grid with a *
    * @param g
    * @param r
    * @param c
    * @param ch
    */
   
    
   /**
    * Fills the area of the grid starting from the given row and column with the specified fill character.
    * This method uses recursion to explore all adjacent cells that match the original character.
    * 
    * @param g The 2D character array representing the grid.
    * @param r The row index of the starting position.
    * @param c The column index of the starting position.
    
    * @param ch The original character to be replaced.
    * @param fillChar The character to fill the area with.
    */
   public static void fill(char[][] g, int r, int c, char ch, char fillChar)
   {       
      // Base case: If the current position is out of bounds or the character at the current position does not match the original character, return.
      if (r < 0 || r >= g.length || c < 0 || c >= g[0].length || g[r][c] != ch) {
         return;
      }
      
      // Replace the character at the current position with the fill character.
      g[r][c] = fillChar;

      // Recursively explore and fill all adjacent cells.
      fill(g, r + 1, c, ch, fillChar); // Down
      fill(g, r - 1, c, ch, fillChar); // Up
      fill(g, r, c + 1, ch, fillChar); // Right
      fill(g, r, c - 1, ch, fillChar); // Left
   }// fill
}

/********* PROGRAM OUTPUTS ******
 The program outputs a grid of characters read from a specified text file, allowing the user to fill a specified area of the grid with a chosen character.
 
 1. Prompts for Filename: Asks the user to input the filename (without the .txt extension).
 2. Displays Initial Grid: Shows the grid read from the file.
 3. User Input for Filling: Prompts the user to enter the row and column to start filling. Input of -1 -1 exits the program.
 4. Prompts for Fill Character: Asks for a fill character for the specified area.
 5. Displays Updated Grid: Shows the grid after filling the specified area.
 6. Repeat or Exit: The process continues until the user decides to quit.
 
 Example Outputs:
 
area:
Filename: area
xxxx............................
...xx...........................
..xxxxxxxxxxxx..................
..x.........xxxxxxx.............
..x...........0000xxxx..........
..xxxxxxxxxxxx0..000............
..xxxxxxxxx...0...00.....0000000
..........xx.......0000000000000
.....xxxxxxxxx........0.........
....xx.................00000....
....xx.....................00...
.....xxxxxxxxxxxxxxxxxx....00...
......................xx...00...
.......................xxxx00000
............................0000

Enter ROW COL to fill from (or -1 -1 to quit): 9 9
Enter fill character: !
xxxx............................
...xx...........................
..xxxxxxxxxxxx..................
..x.........xxxxxxx.............
..x...........0000xxxx..........
..xxxxxxxxxxxx0!!000............
..xxxxxxxxx!!!0!!!00.....0000000
..........xx!!!!!!!0000000000000
.....xxxxxxxxx!!!!!!!!0.........
....xx!!!!!!!!!!!!!!!!!00000....
....xx!!!!!!!!!!!!!!!!!!!!!00...
.....xxxxxxxxxxxxxxxxxx!!!!00...
......................xx!!!00...
.......................xxxx00000
............................0000
Enter ROW COL to fill from (or -1 -1 to quit): 


**area2:**

Filename: area2
..........00
...0....0000
...000000000
0000.....000
............
..#########.
..#...#####.
......#####.
...00000....

Enter ROW COL to fill from (or -1 -1 to quit): 4 4
Enter fill character: #
..........00
...0....0000
...000000000
0000#####000
############
############
############
############
###00000####

 Enter ROW COL to fill from (or -1 -1 to quit): 


 **area3**

Filename: area3
+++
@+@
@+@
@@@

Enter ROW COL to fill from (or -1 -1 to quit): 1 1
Enter fill character: %
%%%
@%@
@%@
@@@

Enter ROW COL to fill from (or -1 -1 to quit): 


**********************************/