/*****************************************************************************************************************
NAME: Aniketh Bandlamudi
PERIOD: 5
DUE DATE: 2/18/25

PURPOSE:
The purpose of this lab is to create a binary expression tree that can build itself from a postorder expression, 
evaluate and print itself, and print an inorder string and a preorder string.

WHAT I LEARNED:
I learned how to create a binary expression tree that can build itself from a postorder expression, evaluate and 
print itself, and print an inorder string and a preorder string.
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITES, ETC.): N/A

****************************************************************************************************************/
import java.util.*;

 	/***********************************
	Represents a binary expression tree.
	The BXT can build itself from a postorder expression.  It can
	evaluate and print itself. It also prints an inorder string and a preorder string.  
	************************************/
   	
	/*******************
	Driver for a binary expression tree class.
	Input: a postfix string, each token separated by a space.
	**********************/
public class Pd5AnikethBandlamudiBXT
{
   public static void main(String[] args)
   {
      BXT tree = new BXT();
      Scanner sc = new Scanner(System.in);
      System.out.print("Input postfix string: ");
         // Try these 3 postfix expressions:
         // 14 -5 / 
         // 3 4 5 + *
         // 2 3 + 5 / 4 5 - *
   
      String s =  sc.nextLine();   
      tree.buildTree(s);
      tree.display();
      System.out.print("Infix order:  ");
      tree.inorderTraverse();
      System.out.print("\nPrefix order:  ");
      tree.preorderTraverse();
      System.out.print("\nEvaluates to " + tree.evaluateTree());
   }
}  // BXT_shell
   
   
class BXT <E extends Comparable <E>>
{

   private TreeNode <E> root;
   
   public BXT()
   {

      root = null;
   }
   public BXT(E obj)
   {

      root = new TreeNode <>(obj, null, null);
   }
      /***********************
   	Builds a BXT from a postfix expression.  
      Uses a helper stack of TreeNodes.
   	****************************/

   // pre: str contains a postfix expression
   // post: root points to the root of a binary expression tree that represents the postfix expression in str
   public void buildTree(String str) {
      Stack<TreeNode<E>> stack = new Stack<>();
      StringTokenizer tokenizer = new StringTokenizer(str);

      while (tokenizer.hasMoreTokens()) {
         String token = tokenizer.nextToken();
         if (isOperator(token)) {
            TreeNode<E> right = stack.pop();
            TreeNode<E> left = stack.pop();
            TreeNode<E> node = new TreeNode<>((E) token, left, right);
            stack.push(node);
         } else {
            TreeNode<E> node = new TreeNode<>((E) token);
            stack.push(node);
         }
      }
      root = stack.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   // pre: root points to the root of a binary expression tree
   // post: returns the value of the expression tree
   private double evaluateNode(TreeNode <E> root)  //recursive
   {
    if (root == null) {
        return 0.0;
    }
    if (!isOperator((String) root.getValue())) {
        return Double.parseDouble((String) root.getValue());
    }
    double left = evaluateNode(root.getLeft());
    double right = evaluateNode(root.getRight());
    return computeTerm((String) root.getValue(), left, right);
   }

   // pre: s is one of "+", "-", "*", or "/"
   // post: returns the result of applying the operator to a and b
   private double computeTerm(String s, double a, double b) {
    switch (s) {
        case "+":
            return a + b;
        case "-":
            return a - b;
        case "*":
            return a * b;
        case "/":
            return a / b;
        default:
            throw new IllegalArgumentException("Invalid operator: " + s);
    }
}
   // pre: s is a string
   // post: returns true if s is "+" or "-", false otherwise
   private boolean isOperator(String s)
   {
      return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
   }
   public void display()
   {
      display(root, 0);
   }
   
   // pre: root points to the root of a binary expression tree; level >= 0
   // post: prints the tree with root at the left-most position, and each level indented 4 spaces
   private void display(TreeNode <E> root, int level)
   {
      if(root == null)
         return;
      display(root.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(root.getValue());
      display(root.getLeft(), level + 1); //recurse left
   }

   //display() from TreeLab01   	 
   // inorder traverse
   public void inorderTraverse()
   {
    inorderTraverse(root);
   }
   
   // pre: root points to the root of a binary expression tree
   // post: prints the tree in infix form
   private void inorderTraverse(TreeNode<E> root)
   {
    if(root == null)
       return;
    inorderTraverse(root.getLeft());    // recurse left
    System.out.print(root.getValue() + " ");  // process root
    inorderTraverse(root.getRight());   // recurse right
   }
     // preorder traverse
     public void preorderTraverse()
     {
        preorderTraverse(root);
     }
     
     private void preorderTraverse(TreeNode<E> root)
     {
        if(root == null)
           return;
        System.out.print(root.getValue() + " ");  // process root
        preorderTraverse(root.getLeft());    // recurse left
        preorderTraverse(root.getRight());   // recurse right
     }
}  // BXT

class TreeNode <E>
{
   private E value; 
   private TreeNode <E> left, right;
   
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


	
/****OUTPUT****




**************/