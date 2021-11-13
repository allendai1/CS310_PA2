package pa2;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;

public class DegreesOfSeparationBFS {
    private SymbolGraph sg;
    private BreadthFirstPaths bfs;
    private ArrayList<String> movies_list;

    public DegreesOfSeparationBFS(String filename, String delimiter, String source) {
        In in = new In(filename);
        this.movies_list = new ArrayList<String>();
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            movies_list.add(a[0]);

        }
        this.sg = new SymbolGraph(filename, delimiter);
//        ArrayList<Integer> sources = new ArrayList<Integer>();
        this.bfs = new BreadthFirstPaths(this.sg.graph(), this.sg.indexOf(source));

    }

    // in the format "Lastname, Firstname", calculates bacon number
    public int baconNumber(String sink) {
        int baconNumber = this.bfs.distTo(this.sg.indexOf(sink));
        if (this.sg.contains(sink) == false || !this.bfs.hasPathTo(this.sg.indexOf(sink))) {
            return -1;
        }
        int total = 0;
        for (int i : this.bfs.pathTo(this.sg.indexOf(sink))) {
            if (this.movies_list.contains(this.sg.nameOf(i))) {
                total += 1;
            }

        }
        return total;

    }

    Stack<Integer> graphPath(String sink) {
        Stack<Integer> stack = new Stack<Integer>();
//        for (int i : this.bfs.pathTo(this.sg.indexOf(sink))) {
//            if (!this.movies_list.contains(this.sg.nameOf(i))) { // a movie vertex
//                stack.push(i);
//            }
//        }
        stack.push(1);
        return stack;
    }

    public SymbolGraph getSymbolGraph() {
        return this.sg;
    }

    public BreadthFirstPaths getBreadthFirstPaths() {
        return this.bfs;
    }

    public static void main(String[] args) {

        String filename = args[0];
        String delimiter = args[1];
        String source = args[2];
        String[] tests = new String[args.length - 3];

        for (int i = 3; i < args.length; i++) {
            tests[i - 3] = args[i];
        }
        DegreesOfSeparationBFS deg = new DegreesOfSeparationBFS(filename, delimiter, source);
        for (int i = 0; i < tests.length; i++) {
            System.out.printf("%s has a Bacon number of %d\n", tests[i], deg.baconNumber(tests[i]));
            // System.out.println(deg.graphPath(tests[i]).pop());

        }

    }

}
