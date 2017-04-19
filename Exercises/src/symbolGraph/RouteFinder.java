package symbolGraph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class RouteFinder {

	public static void main(String[] args) {
		
		String filename  = "src/symbolGraph/routes.txt";
        String delimiter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
//                BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
                System.out.println("The following destinations can be reached from " + source + ":");
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.nameOf(v));
                }
            }
            else {
                StdOut.println(source + " cannot be found. Please enter another Destination.");
            }
        }
	}
}