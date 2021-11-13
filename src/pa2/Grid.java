// Grid.java, for pa3
// originally by Scott Madin
package pa2;

import java.util.*;

/**
 * Class to demonstrate greedy algorithms. Skeleton. 
 */
public class Grid {
	private boolean[][] grid = null;
	private ArrayList<Set<Spot> > allGroups; // All groups
	/**
	 * Very simple constructor
	 * 
	 * @param ingrid
	 *            a two-dimensional array of boolean to be used as the grid to
	 *            search
	 */
	public Grid(boolean[][] ingrid) {
		grid = ingrid;
        	allGroups = new ArrayList<Set<Spot> >();
	}

	/**
	 * Main method, creates a Grid, then asks it for the size of the group
	 * containing the given point.
	 */
	public static void main(String[] args) {
		// Check arguments here.
		
		// Usage: java Grid 3 7 to search from (3, 7), top occupied square
		// of L-shaped group. 
		// -all to print all sets.

		boolean[][] gridData = {
				{ false, false, false, false, false, false, false, false,
						false, true },
				{ false, false, false, true, true, false, false, false, false,
						true },
				{ false, false, false, false, false, false, false, false,
						false, false },
				{ false, false, false, false, true, false, false, true, false,
						false },
				{ false, false, false, true, false, false, false, true, false,
						false },
				{ false, false, false, false, false, false, false, true, true,
						false },
				{ false, false, false, false, true, true, false, false, false,
						false },
				{ false, false, false, false, false, false, false, false,
						false, false },
				{ false, false, false, false, false, false, false, false,
						false, false },
				{ false, false, false, false, false, false, false, false,
						false, false } };
		// Other stuff here. 

	}

	public void printAllGroups()
	{
           for(Set<Spot> g:allGroups) {
            	for(Spot s:g)
                	System.out.println(s);
	    	System.out.println("");
             }
	}
    // The best way IMO to calculate the number of groups is to set up a matrix of integers and
    // for each non-0 entry calculate the group it's in.
    public ArrayList<Set<Spot>> calcAllGroups() {
     	// Write code here. 
	return allGroups;

    }
	/**
	 * Prints out a usage message
	 */
	private static void printUsage() {
		System.out.println("Usage: java Grid <i> <j>");
		System.out.println("Find the size of the cluster of spots including position i,j");
		System.out.println("Usage: java Grid -all");
		System.out.println("Print all spots.");
	}

	/**
	 * This calls the recursive method to find the group size
	 * 
	 * @param i
	 *            the first index into grid (i.e. the row number)
	 * @param j
	 *            the second index into grid (i.e. the col number)
	 * @return the size of the group
	 */
	public int groupSize(int i, int j) {
		if (j > this.grid.length && i > this.grid.length){
			return 0;
		}
		if(this.grid[i][j] == true){
			this.grid[i][j]=false;
		}
		int right = groupSize(i,j+1);
		int bottom = groupSize(i+1,j);
		int left = groupSize(i,j-1);
		int top = groupSize(i-1,j);

		return 1 + right + bottom + left + top;
	}

	
	/**
	 * Nested class to represent a filled spot in the grid
	 */
	public static class Spot {
		int i;
		int j;
		int group;
		/**
		 * Constructor for a Spot
		 * 
		 * @param i
		 *            the i-coordinate of this Spot
		 * @param j
		 *            the j-coordinate of this Spot
		 */
		public Spot(int i, int j) {
			this.i = i;
			this.j = j;
			this.group = 0; // Default. Will be set later.
		}

		/**
		 * Tests whether this Spot is equal (i.e. has the same coordinates) to
		 * another
		 * 
		 * @param o
		 *            an Object
		 * @return true if o is a Spot with the same coordinates
		 */
		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (o.getClass() != getClass())
				return false;
			Spot other = (Spot) o;
			return (other.i == i) && (other.j == j);

		}

		/**
		 * Returns an int based on Spot's contents
		 * another way: (new Integer(i)).hashCode()^(new Integer(j)).hashCode();
		 */
		public int hashCode() {
			return i << 16 + j; // combine i and j two halves of int
		}

		public void setGroup(int g) {group = g;}

		public int getI() {return i;}
		public int getJ() {return j;}	
		public int getGroup() {return group;}	
		/**
		 * Returns a String representing this Spot, just the coordinates. You can add group if you want.
		 */
		public String toString() {
			return "(" + i + " , " + j + ")";
		}
	}
}
