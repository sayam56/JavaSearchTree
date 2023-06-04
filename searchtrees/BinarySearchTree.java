package searchtrees;

import java.util.Random;

//BinarySearchTree class
//
//CONSTRUCTION: with no initializer
//
//******************PUBLIC OPERATIONS*********************
//void insert( x )       --> Insert x
//void remove( x )       --> Remove x
//boolean contains( x )  --> Return true if x is present
//Comparable findMin( )  --> Return smallest item
//Comparable findMax( )  --> Return largest item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//void printTree( )      --> Print tree in sorted order
//******************ERRORS********************************
//Throws UnderflowException as appropriate

/**
* Implements an unbalanced binary search tree.
* Note that all "matching" is based on the compareTo method.
* @author Mark Allen Weiss
*/
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
 /**
  * Construct the tree.
  */
 public BinarySearchTree( )
 {
     root = null;
 }

 /**
  * Insert into the tree; duplicates are ignored.
  * @param x the item to insert.
  */
 public void insert( AnyType x )
 {
     root = insert( x, root );
 }

 /**
  * Remove from the tree. Nothing is done if x is not found.
  * @param x the item to remove.
  */
 public void remove( AnyType x )
 {
     root = remove( x, root );
 }

 /**
  * Find the smallest item in the tree.
  * @return smallest item or null if empty.
  */
 public AnyType findMin( )
 {
     if( isEmpty( ) )
         return null;
     return findMin( root ).element;
 }

 /**
  * Find the largest item in the tree.
  * @return the largest item of null if empty.
  */
 public AnyType findMax( )
 {
     if( isEmpty( ) )
         return null;
     return findMax( root ).element;
 }

 /**
  * Find an item in the tree.
  * @param x the item to search for.
  * @return true if not found.
  */
 public boolean contains( AnyType x )
 {
     return contains( x, root );
 }

 /**
  * Make the tree logically empty.
  */
 public void makeEmpty( )
 {
     root = null;
 }

 /**
  * Test if the tree is logically empty.
  * @return true if empty, false otherwise.
  */
 public boolean isEmpty( )
 {
     return root == null;
 }

 /**
  * Print the tree contents in sorted order.
  */
 public void printTree( )
 {
     if( isEmpty( ) )
         System.out.println( "Empty tree" );
     else
         printTree( root );
 }

 /**
  * Internal method to insert into a subtree.
  * @param x the item to insert.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
*/
 private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return new BinaryNode<>( x, null, null );
     
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = insert( x, t.left );
     else if( compareResult > 0 )
         t.right = insert( x, t.right );
     else
         ;  // Duplicate; do nothing
     return t;
 }

 /**
  * Non recursive method, created by LR - 29-092014
  
 private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return new BinaryNode<>( x, null, null );
     
     while (t != null) {     
    	 int compareResult = x.compareTo( t.element );
         
    	 if( compareResult < 0 )
    		 t = t.left;
    	 else if( compareResult > 0 )
    		 t = t.right;
    	 else
    		 ;  // Duplicate; do nothing
     }
    	 return t;
 }*/
 
 /**
  * Internal method to remove from a subtree.
  * @param x the item to remove.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
  */
 private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return t;   // Item not found; do nothing
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = remove( x, t.left );
     else if( compareResult > 0 )
         t.right = remove( x, t.right );
     else if( t.left != null && t.right != null ) // Two children
     {
         t.element = findMin( t.right ).element;
         t.right = remove( t.element, t.right );
     }
     else
         t = ( t.left != null ) ? t.left : t.right;
     return t;
 }

 /**
  * Internal method to find the smallest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the smallest item.
  */
 private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
 {
     if( t == null )
         return null;
     else if( t.left == null )
         return t;
     return findMin( t.left );
 }

 /**
  * Internal method to find the largest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the largest item.
  */
 private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
 {
     if( t != null )
         while( t.right != null )
             t = t.right;

     return t;
 }

 /**
  * Internal method to find an item in a subtree.
  * @param x is item to search for.
  * @param t the node that roots the subtree.
  * @return node containing the matched item.
  */
 private boolean contains( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return false;
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         return contains( x, t.left );
     else if( compareResult > 0 )
         return contains( x, t.right );
     else
         return true;    // Match
 }

 /**
  * Internal method to print a subtree in sorted order.
  * @param t the node that roots the subtree.
  */
 private void printTree( BinaryNode<AnyType> t )
 {
     if( t != null )
     {
         printTree( t.left );
         System.out.println( t.element );
         printTree( t.right );
     }
 }

 /**
  * Internal method to compute height of a subtree.
  * @param t the node that roots the subtree.
  */
 private int height( BinaryNode<AnyType> t )
 {
     if( t == null )
         return -1;
     else
         return 1 + Math.max( height( t.left ), height( t.right ) );    
 }
 
 // Basic node stored in unbalanced binary search trees
 private static class BinaryNode<AnyType>
 {
         // Constructors
     BinaryNode( AnyType theElement )
     {
         this( theElement, null, null );
     }

     BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
     {
         element  = theElement;
         left     = lt;
         right    = rt;
     }

     AnyType element;            // The data in the node
     BinaryNode<AnyType> left;   // Left child
     BinaryNode<AnyType> right;  // Right child
 }


   /** The tree root. */
 private BinaryNode<AnyType> root;
 
 
 public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}


     // Test program
 public static void main( String [ ] args )
 {
     BinarySearchTree<Integer> t = new BinarySearchTree<>( );
     final double NUMS = 100000;  // must be even
     long start, end;

     System.out.println( "Create the tree..." );

     int rd=0;
     
// loop for insertion
     start = System.nanoTime( );
     for( int i = 1; i <= NUMS; i++)
     {
    	 int number = randInt(1, 100000);
//    	 System.out.println( number );
         t.insert( number );
     }
     end = System.nanoTime( ); 
     System.out.println( "Total time used for insertion in nanos: " + ((end-start)) );
     System.out.println( "Average time used for each insertion in nanos: " + ((end-start)/NUMS) );
     
//     System.out.println( "Tree after insterions:" );
//     t.printTree( );
     
  // loop for Search
     int found=0;
     start = System.nanoTime( );
     for( int i = 1; i <= NUMS; i++)
     {
    	 int number = randInt(1, 100000);
    	 if (t.contains( number )) {
    		 found++;
    		 System.out.println("key " + number + "found" );
    	 }
    		 

     }
     end = System.nanoTime( ); 
     System.out.println( "Total time used for search in nanos: " + ((end-start)) );
     System.out.println( "Average time used for each search in nanos: " + ((end-start)/NUMS) );
     System.out.println( "Total items found: " + found);
     
  // loop for Deletion
     start = System.nanoTime( );
     for( int i = 1; i <= NUMS; i++ )
     {
    	 int number = randInt(1, 100000);
    	 if (t.contains(number))
    		 t.remove( number );
     }
     end = System.nanoTime( ); 
     System.out.println( "Total time used for deletion in nanos: " + ((end-start)) );
     System.out.println( "Average time used for each deletion in nanos: " + ((end-start)/NUMS) );
//     t.printTree( );
 }
}
