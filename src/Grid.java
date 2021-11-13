package pa2;
import java.util.*;
public class Grid {
    private boolean[][] grid = null;
    private ArrayList<Set<Grid.Spot> > allGroups; // All groups
    public Grid(boolean[][] ingrid) {
        grid = ingrid;
        allGroups = new ArrayList<Set<Grid.Spot> >();
    }
    public static void main(String[] args){
    }
    public void printAllGroups()
    {
        for(Set<Grid.Spot> g:allGroups) {
            for(Grid.Spot s:g)
                System.out.println(s);
            System.out.println("");
        }
    }
    public ArrayList<Set<Grid.Spot>> calcAllGroups() {
        return allGroups;
    }
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
    public static class Spot {
        int i;
        int j;
        int group;
        public Spot(int i, int j) {
            this.i = i;
            this.j = j;
            this.group = 0; // Default. Will be set later.
        }
        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (o.getClass() != getClass())
                return false;
            Spot other = (Spot) o;
            return (other.i == i) && (other.j == j);

        }
        public int hashCode() {
            return i << 16 + j; // combine i and j two halves of int
        }
        public void setGroup(int g) {group = g;}
        public int getI() {return i;}
        public int getJ() {return j;}
        public int getGroup() {return group;}
        public String toString() {
            return "(" + i + " , " + j + ")";
        }
    }
}
