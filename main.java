package minimumCut;

import java.io.FileNotFoundException;

public class main {
	public static void main(String args[]) throws FileNotFoundException {
		int min = 999;
		for(int i = 0; i <1000; i ++) {
			graph g = new graph();
			while(g.remainingSize() > 2) {
				//g.print();
				int[] edge = g.randomEdge();
				int u = edge[0]; // vertex 1
				int v = edge[1]; // vertex 2
				//System.out.print("random:" + u);
				//System.out.println("," + v);
				g.contract(u, v); // contract two vertices
				
			}
			
			int result = g.minCut();
			if(result < min) {
				min = result;
			}
		}
		System.out.println("Minimum cut:" + min);
	}
}
