package cisco.java.challenge.impl;

import java.util.ArrayList;
import java.util.HashSet;
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
	
	public DFSTraversal() {
		this.visited = new LinkedHashSet<>();
	}

	/**
	 * Assumptions: 
	 *  1. input is a directed graph, i.e. nodes have outgoing edges to their children
	 *  2. walkGraph method will only return all reachable vertices from a given vertex.
	 *  	Not all vertices will be returned if the graph has unreachable or disconnected vertices
	 *  	from given (start) vertex.
	 *  	
	 *  Example:
	 *    Given
	 *       A
	 *      /\
	 *     B--C
	 *  		
	 *    walkGraph(A) returns
	 *      [A, C, B]
	 *  
	 *    Given
	 *       A
	 *      /
	 *     B  C
	 *  	
	 *    walkGraph(A) returns
	 *      [A, B]
	 *  
	 * Method returns a list of nodes discovered using Depth First Search iterative traversal
	 * Note that since a stack is used, children will be discovered starting with the
	 * right most.
	 *
	 * @param	node	start node
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
	 * Assumptions:
	 * 	1. input is a directed graph
	 *  2. sub-paths are not returned 
	 * 
	 * Example
	 * 	
	 * Given
	 *    A
	 *    |
	 * 	  B
	 *   /\
	 *  C  D
	 *   \/
	 *    E
	 *    |
	 * 	  F
	 * 
	 *  paths(A) returns
	 *    [[A,B,D,E,F],[A,B,C,E,F]]
	 * 		
	 * Finds all unique longest paths from start vertex
	 * 
	 * @param	node	start node
	 * @return			list of all unique longest paths from start node
	 */
	public ArrayList<ArrayList<GNode>> paths(GNode node) {
		Set<ArrayList<GNode>> result = new HashSet<>();
		if (null == node) {
			return new ArrayList<ArrayList<GNode>>(result);
		}
		
		Stack<Stack<GNode>> paths = new Stack<>();
		Stack<GNode> path = new Stack<GNode>();
		path.push(node);
		paths.push(path);
		
		while (!paths.isEmpty()) {
			boolean extendedPath = false;
			Stack<GNode> current = paths.pop();
			GNode last = current.peek();
			
			for (GNode child : last.getChildren()) {
				if (!current.contains(child)) {
					Stack<GNode> newPath = new Stack<>();
					newPath.addAll(current);
					newPath.push(child);
					paths.push(newPath);
					extendedPath = true;
				}
			}
			
			if (!extendedPath) {
				result.add(new ArrayList<GNode>(current));
			}
		}
		
		return new ArrayList<ArrayList<GNode>>(result);
	}
}