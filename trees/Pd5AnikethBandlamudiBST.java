/*****************************************************************************************************************
NAME: Aniketh Bandlamudi     
PERIOD: 5
DUE DATE: 2/11/25 

PURPOSE:    
The purpose of this lab is to practice with a Binary Search Tree. 
This lab uses TreeNode. The program prompts the user for an input string and builds a Binary Search Tree using Comparables. 
The program displays the tree as a sideways tree. 
The program then prompts the user for a target and searches the tree for it. 
The program displays the tree's minimum and maximum values. 
The program then prints the data in order from smallest to largest.

WHAT I LEARNED:   I learned how to implement different methods in a Binary Search Tree.

HOW I FEEL ABOUT THIS LAB: I feel like this lab helped me better understand how to implement a Binary Search Tree.
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): N/A

****************************************************************************************************************/
import java.util.Scanner;
	/****************************************************************
	Practice with a Binary Search Tree. Uses TreeNode.
    Prompt the user for an input string.  Build a Binary Search Tree 
	using Comparables.  Display it as a sideways tree (take the code 
	from the Tree Lab).  Prompt the user for a target and search the tree 
	for it.  Display the tree's minimum and maximum values.  Print 
	the data in order from smallest to largest.
	*****************************************************************/
public class Pd5AnikethBandlamudiBST
{
   public static void main(String[] args)
   {   
      BinarySearchTree <String> t = new BinarySearchTree <> ();
      
      // build the tree
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.next();               // Try these three strings: es"MAENIRAC";  "AMERICAN";   "AACEIMNR"
      for(int k = 0; k < s.length(); k++)
         t.insert ("" + s.charAt (k));
         
      // get the root of the newly created BinarySearchTree 
      TreeNode <String> root = t.getRoot();
      
      // call the display sideway method
      t.display();    
      
      // test the find method
      sc = new Scanner(System.in);
      System.out.print("Input target: ");
      String target =  sc.next();             //"I"
      
      boolean itemFound = t.find(target);
      if(itemFound)
         System.out.println("found: " + target);
      else
         System.out.println(target +" not found.");
        
      // test the min and max methods
      System.out.println("Min = " + t.min());
      System.out.println("Max = " + t.max());	
   
      // inorder traversal display the values from smallest to largest
      System.out.println("\nIn Order: ");
      t.smallToLarge();     
   }
}
class BinarySearchTree <E extends Comparable <E>>
{ 
   private TreeNode <E> root;
    
   public TreeNode <E> getRoot()
   {
      return root;
   }
   	/****************************************************************
   	Recursive algorithm to build a BST:  if the node is null, insert the 
   	new node.  Else, if the item is less, set the left node and recur to 
   	the left.  Else, if the item is greater, set the right node and recur 
   	to the right.   
   	*****************************************************************/

   public TreeNode<E> insert(E s) {
      if (root == null) {
          root = new TreeNode<E>(s);
          return root;
      }
      return insert(root, s);
  }

  // post: value is added to the binary search tree rooted at t
   private TreeNode <E> insert(TreeNode <E> t, E s) {
      if (t == null) {
          return new TreeNode<E>(s);
      }
      
      int compareResult = s.compareTo(t.getValue());
      if (compareResult < 0) {
          TreeNode<E> left = insert(t.getLeft(), s);
          t.setLeft(left);
      }
      else if (compareResult > 0) {
          TreeNode<E> right = insert(t.getRight(), s);
          t.setRight(right);
      }
      
      return t;
  }

   
   public void display() {
      display(root, 0);
   }

   // post: displays the tree with given root, indenting each line to the given level
   private void display(TreeNode<E> t, int level) {
      if (t == null)
         return;
      display(t.getRight(), level + 1);  // process right
      for (int k = 0; k < level; k++)    // indent
         System.out.print("\t");
      System.out.println(t.getValue());   // display root
      display(t.getLeft(), level + 1);    // process left
   }
   	
     /***************************************************************
      Iterative algorithm:  create a temporary pointer p at the root.  
   	While p is not null, if the p's value equals the target, return true.  
   	If the target is less than the p's value, go left, otherwise go right.   
   	If the target is not found, return false. 
      
   	Find the target. Recursive algorithm:  If the tree is empty, 
   	return false.  If the target is less than the current node 
   	value, return the left subtree.  If the target is greater, return 
   	the right subtree.  Otherwise, return true.   
   . ****************************************************************/    
   public  boolean find(E x)
   {
      return find (root, x);
   }
   
   // helper method of find
   // post: returns true if the target is found in the tree
   private boolean find(TreeNode<E> t, E x) {
      if (t == null)
         return false;
      int compareResult = x.compareTo(t.getValue());
      if (compareResult == 0)
         return true;
      if (compareResult < 0)
         return find(t.getLeft(), x);
      return find(t.getRight(), x);
   }
   
     /***************************************************************
   	starting at the root, return the min value in the BST.   
   	Use iteration.   Hint:  look at several BSTs. Where are 
   	the min values always located?  
   	***************************************************************/
   // post: returns the minimum value in the binary search tree
   public E min()              
   {
      if (root == null)
         return null;
      TreeNode<E> current = root;
      while (current.getLeft() != null)
         current = current.getLeft();
      return current.getValue();
   }
   
   /*****************************************************************
   	starting at the root, return the max value in the BST.  
   	Use recursion!
    *****************************************************************/
   public E max()              
   {
      return maxHelper(root);
   }
   
   // post: returns the maximum value in the binary search tree
   private E maxHelper(TreeNode<E> t)
   {
      if (t == null)
         return null;
      if (t.getRight() == null)
         return t.getValue();
      return maxHelper(t.getRight());
   }
   
   public void smallToLarge()  
   {
      smallToLargeHelper(root);
      System.out.println();  // add newline at end
   }

   // post: prints the values in the given tree in ascending order
   private void smallToLargeHelper(TreeNode<E> t)
   {
      if (t != null) {
         smallToLargeHelper(t.getLeft());    // process left
         System.out.print(t.getValue() + " ");  // process root
         smallToLargeHelper(t.getRight());   // process right
      }
   }
   
   
}  // BinarySearchTree

 /* TreeNode class for the AP Exams */

class TreeNode <E>
{
   private E value; 
   private TreeNode left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode <E> initLeft, TreeNode <E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode <E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode <E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode <E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode <E> theNewRight)
   { 
      right = theNewRight;
   }
}


/* OUTPUT

Input string: american
                r
                        n
        m
                        i
                e
                        c
a
Input target: x
x not found.
Min = a
Max = r

In Order: 
a c e i m n r 

************************************************************

Input string: MAENIRAC
                R
        N
M
                        I
                E
                        C
        A
Input target: I
found: I
Min = A
Max = R

In Order: 
A C E I M N R 

 */