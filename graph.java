package minimumCut;
import java.io.FileNotFoundException;
import java.util.*;
public class graph {
	ArrayList<ArrayList<Integer>> adj;
	ArrayList<Integer> mergedVertices; // it tracks the merged vertices
	public graph() throws FileNotFoundException {
		adj = new ArrayList<ArrayList<Integer>>();
		mergedVertices = new ArrayList<Integer>();
		//System.out.println("Initializing a graph object ...");
		load();
		//print();
	}
	
	public void addVertex(int n) { // initialzie the adjacent list
		ArrayList<Integer> empty = new ArrayList<Integer>();
		while(adj.size()< n) {
			adj.add(empty);
		}
	}
	
	public void addEdge(int u, int v) { // vertices u and v
		adj.get(u).add(v);
		adj.get(v).add(u);
	}
	
	public void load() throws FileNotFoundException {
		loadText ld = new loadText("data/kargerMinCut.txt");
		//loadText ld = new loadText("data/test2.txt");
		adj = ld.get();
	}
	
	public void contract(int u, int v) { // contract two vertices on an edge
		ArrayList<Integer> vertexA = adj.get(u - 1);
		ArrayList<Integer> vertexB = adj.get(v - 1);
		ArrayList<Integer> vertexMerge = new ArrayList<Integer>();
		for(int i : vertexA) {
			if(i != v) { // remove v in the new vertex after merge
				vertexMerge.add(i);
			}
		}
		for(int i : vertexB) {
			if(i != v && i != u) { // remove v in the new vertex after merge
				vertexMerge.add(i);
			}
		}
		adj.set(u - 1, vertexMerge);
		replace(u,v);
		mergedVertices.add(v); // this line will not be merged again
	}
	
	private void replace(int u, int v) {
		// replace the v in the edges to u after merging uv
		for(int i = 0; i < adj.size(); i++) {
			if(i == u - 1 || i == v - 1) {
				continue;
			}
			else {
				ArrayList<Integer> line = adj.get(i);
				for(int k = 1; k < line.size(); k++) {
					if(line.get(k) == v) {
						line.set(k, u);
					}
				}
			}
		}
	}
	
	public int remainingSize() { // tracks how many vertices remain
		return adj.size() - mergedVertices.size();
	}
	
	public int[] randomEdge() {
		ArrayList<int[]> edges = getEdges();
		Random rand = new Random();
		int index = rand.nextInt(edges.size());
		//for(int i = 0; i < edges.size(); i++) {
		//	System.out.print(edges.get(i)[0] + ",");
		//	System.out.println(edges.get(i)[1]);
			
		//}
		return edges.get(index);
	}
	
	private ArrayList<int[]> getEdges(){
		ArrayList<int[]> edges = new ArrayList<int[]>();
		for(int i = 0; i < adj.size(); i++) {
			if(mergedVertices.contains(i + 1)) {
				continue; // merged vertices
			}
			else {
				ArrayList<Integer> points = adj.get(i); // all linked vertices
				for(int k = 1; k < points.size(); k ++) {
					int[] edge = new int[2];
					edge[0] = i + 1;
					edge[1] = points.get(k);
					//System.out.print(edge[0] + "," + edge[1]);
					edges.add(edge);
				}
			}
		}
		return edges;
	}
	
	public int minCut() {
		int count = 0;
		for(int i = 1; i <= adj.size(); i ++) {
			if(mergedVertices.contains(i)) {
				continue;
			}
			else {
				ArrayList<Integer> superVertex = adj.get(i - 1);
				System.out.print(" ");
				for(int edge : superVertex) {
					System.out.print(" ");
					System.out.print(edge);
				}
				System.out.println(" ");
				return superVertex.size() - 1;
			}
		}
		return count;
	}
	
	public void print() {
		System.out.println(" ");
		for(ArrayList<Integer> line: adj) {
			for(int i : line) {
				System.out.print(i);
				System.out.print(",");
			}
			System.out.println(" ");
		}
	}
}
