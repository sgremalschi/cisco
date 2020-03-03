package cisco.java.challenge.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import cisco.java.challenge.GNode;
import cisco.java.challenge.Traversal;


/**
 * @author stefan
 *
 */
public class DFSTraversal implements Traversal {
	
	private final Set<GNode> visited;
	private final Stack<GNode> path;
	private final ArrayList<ArrayList<GNode>> paths;
	
	public DFSTraversal() {
		this.visited = new LinkedHashSet<>();
		this.path = new Stack<>();
		this.paths = new ArrayList<>();
	}

	/**
	 * Returns a list of nodes discovered using Depth First Search iterative traversal
	 * Note that since a stack is used, the children will be discovered starting with the
	 * right most.
	 *
	 * @param	node	start node (root for trees)
	 * @return			list of nodes in order of discovery 
	 */
	public ArrayList<GNode> walkGraph(GNode node) {
		visited.clear();
		if (null == node) {
			return new ArrayList<>(visited);
		}
		
		visited.add(node);

		Stack<GNode> stack = new Stack<>();
		for (GNode child : node.getChildren()) {
			stack.push(child);
		}

		while (!stack.isEmpty()) {
			GNode v = stack.pop();
			visited.add(v);

			for (GNode child : v.getChildren()) {
				if (!visited.contains(child)) {
					stack.push(child);
				}
			}
		}

		return new ArrayList<GNode>(visited);
	}

	/**
	 * Finds all existing paths in a tree from root node to the leafs
	 * 
	 * @param	node	root node
	 * @return			list of all existing paths from root to leafs in a tree
	 */
	public ArrayList<ArrayList<GNode>> paths(GNode node) {
		visited.clear();
		path.clear();
		paths.clear();
		
		findAllPaths(node);
		return paths;
	}
	
	/**
	 * Finds all paths from root to leaves in a tree
	 * For large graphs more memory will be required
	 * 
	 * @param node root note of a tree
	 */
	private void findAllPaths(GNode node) {
		visited.add(node);
		path.push(node);

		if (node.getChildren().length == 0) {
			paths.add(new ArrayList<GNode>(path));
		} else {
			for (GNode child : node.getChildren()) {
				if (!visited.contains(child)) {
					findAllPaths(child);
				}
			}
		}
		path.pop();
	}
	
	public ArrayList<ArrayList<GNode>> paths(GNode s, GNode t) {
		visited.clear();
		path.clear();
		paths.clear();
		
		enumerate(s, t);
		return paths;
	}
	
	private void enumerate(GNode v, GNode t) {
		path.push(v);
		visited.add(v);
		
		if (v.equals(t)) {
			paths.add(new ArrayList<>(path));
		} else {
			for (GNode n : v.getChildren()) {
				if (!visited.contains(n)) {
					enumerate(n, t);
				}
			}
		}
		
		path.pop();
		visited.remove(v);
	}
	
}