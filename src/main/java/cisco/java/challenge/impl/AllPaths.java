package cisco.java.challenge.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import cisco.java.challenge.GNode;

public class AllPaths {
	
	private final Stack<GNode> path = new Stack<>();
	private final Set<GNode> onPath = new HashSet<>();
	private final Set<ArrayList<GNode>> paths = new HashSet<>();
	
	public AllPaths(GNode s, GNode t) {
		enumerate(s, t);
	}
	
	private void enumerate(GNode v, GNode t) {
		path.push(v);
		onPath.add(v);
		
		if (v.equals(t)) {
			paths.add(new ArrayList<>(path));
		} else {
			for (GNode n : v.getChildren()) {
				if (!onPath.contains(n)) {
					enumerate(n, t);
				}
			}
		}
		
		path.pop();
		onPath.remove(v);
	}
	
	public ArrayList<ArrayList<GNode>> getAllPaths(GNode root) {
		ArrayList<ArrayList<GNode>> result = new ArrayList<>();
		DFSTraversal dfs = new DFSTraversal();
		ArrayList<GNode> nodes = dfs.walkGraph(root);
		
		path.clear();
		paths.clear();
		onPath.clear();
		
		
		for (GNode n : nodes) {
			if (n != root) {
				enumerate(root, n);
			}
		}
		
		result = new ArrayList<>(paths);
		return result;
	}

}
