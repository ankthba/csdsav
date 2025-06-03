/**
* Name: Aniketh Bandlamudi         Period: 5
* 
* Date Submitted: 2/4/2025

* Purpose of the Program: 
- The purpose of this program is to implement a binary tree and traverse through it using different methods.

* What I learned: 
- I learned how to implement a binary tree and how to traverse through it using different methods.
- I learned how to count the number of nodes, leaves, grandparents, and single child nodes in a binary tree using recursion.
- I learned how to find the height, min, and max of a binary tree using recursion.
- I learned how to display the binary tree level by level using a queue.
* 
* What I found most challegning in this lab: 
- I found it interesting, however not too challenging, to implement the insert method to insert a new node in the tree based on the node's level.
*
* How I overcame the challenge(s) and what strategies I used:
- I had a small issue where my min and max methods were based off relative ordering rather than position, but 
   I was able to fix this by comparing the values of the nodes rather than their positions.

* What I wonder: 
- I wonder how I could implement the tree class myself from scratch, rather than using the TreeNode class.

* Credits: N/A
* Students whom I helped (to what extent): N/A
*/
import java.util.*;                     //for the queue interface
public class Pd5AnikethBandlamudiTreeLab       
{
   public static void main(String[] args)
   {
      String s = "XCOMPUTERSCIENCE";
   		
      TreeNode <String>  root = new TreeNode <> ("" + s.charAt(1), null, null);
         
      for(int index = 2; index < s.length(); index++)
         insert(root, "" + s.charAt(index), index, (int)(1 + Math.log(index) / Math.log(2)));
      
      // NOTE: The following 3 lines are supposed to show you how insert works. You
      //             uncomment them and see how the tree looks like with these 3 additional nodes
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      
      // display the tree sideway; see the sample output at the end of this file
      display(root, 0);
      
      System.out.print("\nPreorder: ");
      preorderTraverse(root);

      System.out.print("\nInorder: ");
      inorderTraverse(root);

      System.out.print("\nPostorder: ");
      postorderTraverse(root);
      
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Grandparents = " + countGrandParentNodes(root));  // count the number grandparent nodes
      System.out.println("Only childs = " + countSingleChildNodes(root));   // count the number of nodes that has only 1 child
         
      //System.out.println("\nDepth = " + numOfLevels(root));                    
      System.out.println("Height = " + height(root));
   
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
      
      // level by level display of the nodes (starts from left to right for nodes on the same level)
      System.out.println("\nLevel-by-level display of the tree: ");
      displayLevelOrder(root);     
      
   } // end of main
   
   
   // insert a new node in the tree based on the node's level
   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
         if((pos & (1 << k)) == 0) 
            p = p.getLeft();      // What does this do? Answer this question first.  What does '&' do? 
         else                     // Google it!!!!  We did not learn this in APCS A!  :
            p = p.getRight();     // What does this do? Answer this question first.
            
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   } // end of insert
      
      
   /*****************************************************************************************************   
     postcondition: display the tree sideway   
   *****************************************************************************************************/   
   public static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1);
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
      display(t.getLeft(), level + 1);
   }  // end of display
        
   // post: returns the preorder traversal of the tree
   // performs a preorder traversal of a binary tree by visiting the root node, then the left subtree, and finally the right subtree.
   public static void preorderTraverse(TreeNode t)
   {

      if (t == null) {
         return;
      }

      System.out.print(t.getValue() + " ");
      preorderTraverse(t.getLeft());
      preorderTraverse(t.getRight());
   }
   
   // post: returns the inorder traversal of the tree
   // performs an inorder traversal of a binary tree by visiting the left subtree, then the root node, and finally the right subtree.
   public static void inorderTraverse(TreeNode t)
   {
      if (t == null) {
         return;
      }

      inorderTraverse(t.getLeft());
      System.out.print(t.getValue() + " ");
      inorderTraverse(t.getRight());
   }
   
   // post: returns the postorder traversal of the tree
   // performs a postorder traversal of a binary tree by visiting the left subtree, then the right subtree, and finally the root node.
   public static void postorderTraverse(TreeNode t)
   {
      if (t == null) {
         return;
      }

      postorderTraverse(t.getLeft());
      postorderTraverse(t.getRight());
      System.out.print(t.getValue() + " ");
   }
   
   // post: returns the number of nodes in the tree
   // adds 1 for the current node and recursively calls the method for the left and right children
   public static int countNodes(TreeNode t)
   {
      if (t == null) {
         return 0;
      }

      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }
     
   // post: returns the number of leaves in the tree
   // if the current node is a leaf, return 1, otherwise recursively call the method for the left and right children
   public static int countLeaves(TreeNode t)
   {
      if (t == null) {
         return 0;
      }

      if (t.getLeft() == null && t.getRight() == null) {
         return 1;
      }

      return countLeaves(t.getLeft()) + countLeaves(t.getRight());
   }
   
   // Hint: Use the height method to help you
   // post: returns the number of nodes in the tree that have at least one child
   // if the current node has a left or right child, return 1, otherwise recursively call the method for the left and right children
   public static int countGrandParentNodes(TreeNode t)
   {
      if (t == null) {
         return 0;
      }

      int count = 0;
      if (height(t) >= 2) {
         count = 1;
      }
      return count + countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
   }
   
   // post: returns the number of nodes in the tree that have only one child
   // if the current node has only one child, return 1, otherwise recursively call the method for the left and right children
   public static int countSingleChildNodes(TreeNode t)
   {
      if (t == null) {
         return 0;
      }

      int count = 0;
      if ((t.getLeft() == null && t.getRight() != null) || (t.getLeft() != null && t.getRight() == null)) {
         count = 1;
      }
      return count + countSingleChildNodes(t.getLeft()) + countSingleChildNodes(t.getRight());
   }

   // post: returns the height of the tree
   // recursively call the method for the left and right children and return the maximum height of the two children
   public static int height(TreeNode t)
   {
      if (t == null) {
         return -1;
      }
      
      return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
   }
 
   // post: returns the minimum value in the tree
   // recursively call the method for the left and right children and return the minimum value of the three nodes
   public static Object min(TreeNode t)
   {
      if (t == null) {
        return null;
      }

      Object leftMin = min(t.getLeft());
      Object rightMin = min(t.getRight());
    
      Object result = t.getValue();
    
      if (leftMin != null && ((String)leftMin).compareTo((String)result) < 0) {
         result = leftMin;
      }

      if (rightMin != null && ((String)rightMin).compareTo((String)result) < 0) {
        result = rightMin;
      }

    return result;
   }
    
   // post: returns the maximum value in the tree
   // recursively call the method for the left and right children and return the maximum value of the three nodes
   public static Object max(TreeNode t)
   {
      if (t == null) {
        return null;
      }
    
      Object leftMax = max(t.getLeft());
      Object rightMax = max(t.getRight());
    
      Object result = t.getValue();
    
      if (leftMax != null && ((String)leftMax).compareTo((String)result) > 0) {
        result = leftMax;
      }

      if (rightMax != null && ((String)rightMax).compareTo((String)result) > 0) {
        result = rightMax;
      }
        
    return result;
   }
   
   /*****************************************************************************************************
        This method is not recursive.  
        Hint: Use a local queue to store the children of the current node.
   *****************************************************************************************************/
  // post: displays the tree in level-by-level order
  // uses a queue to store the children of the current node and prints the value of the current node
   public static void displayLevelOrder(TreeNode t)
   {
      if (t == null)
         return;
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(t);
      while (!queue.isEmpty())
      {
         TreeNode current = queue.poll();
         System.out.print(current.getValue() + " ");
         if (current.getLeft() != null)
            queue.add(current.getLeft());
         if (current.getRight() != null)
            queue.add(current.getRight());
      }
   }
}  // end of TreeLab_shell



class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode initLeft, TreeNode initRight)
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
   
   public TreeNode<E> getRight() 
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


   /***************************************************
	  
      ----jGRASP exec: java Lab01
    
    			E
    		E
    			C
    	M
    			N
    		T
    			E
    C
    			I
    		U
    			C
    	O
    			S
    					C
    				B
    		P
    				A
    			R
    
    Preorder: C O P R A S B C U C I M T E N E C E 
    Inorder: R A P B C S O C U I C E T N M C E E 
    Postorder: A R C B S P C I U O E N T C E E M C 
    
    Nodes = 18
    Leaves = 8
    Grandparents = 5
    Only childs = 3
    
    Height = 5
    Min = A
    Max = U
    
    Level-by-level: 
    COMPUTERSCIENCEABC
   
    *******************************************************/

