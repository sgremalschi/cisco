package cisco.java.challenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import cisco.java.challenge.impl.DFSTraversal;
import cisco.java.challenge.impl.Node;
import cisco.java.challenge.util.GraphUtils;

public class DFSTraversalTest {
	
	@Test
	public void testDFSTraversalInitialization() {
		DFSTraversal traversal = new DFSTraversal();
		assertNotNull(traversal);
		
		ArrayList<GNode> path = new ArrayList<>();
		ArrayList<GNode> result = traversal.walkGraph(null);
		assertTrue(path.size() == result.size());
	}
	
	@Test
	public void testDFSNoLoop() {
		DFSTraversal traversal = new DFSTraversal();
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");

		a.addChild(b);
		a.addChild(c);
		a.addChild(d);
		
		Set<GNode> expected = new HashSet<>();
		expected.add(a);
		expected.add(b);
		expected.add(c);
		expected.add(d);
		
		ArrayList<GNode> result = traversal.walkGraph(a);
		assertTrue(expected.size() == result.size());
		for (GNode n : result) {
			assertTrue(expected.contains(n));
		}
	}
	
	@Test
	public void testDFSWithLoop() {
		DFSTraversal traversal = new DFSTraversal();
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		
		a.addChild(b);
		b.addChild(a);
		b.addChild(c);
		
		Set<GNode> expected = new HashSet<>();
		expected.add(a);
		expected.add(b);
		expected.add(c);
		
		ArrayList<GNode> result = traversal.walkGraph(a);
		
		assertTrue(expected.size() == result.size());
		for (GNode n: result) {
			assertTrue(expected.contains(n));
		}
	}
	
	@Test
	public void testAllPaths() {
		// Input is a tree
		GNode a = GraphUtils.loadGraph("src/test/resources/tree.txt", "A");
		DFSTraversal dfs = new DFSTraversal();
		ArrayList<ArrayList<GNode>> paths = dfs.paths(a);
		GraphUtils.printPaths(paths);
		
		// Input is a clique
		a = GraphUtils.loadGraph("src/test/resources/clique.txt", "A");
		paths = dfs.paths(a);
		GraphUtils.printPaths(paths);
		
		// Input is a diamond
		a = GraphUtils.loadGraph("src/test/resources/diamond.txt", "A");
		paths = dfs.paths(a);
		GraphUtils.printPaths(paths);
		
		// Input is a linked list
		a = GraphUtils.loadGraph("src/test/resources/list.txt", "1");
		paths = dfs.paths(a);
		GraphUtils.printPaths(paths);
		
		// Input is a double linked list
		a = GraphUtils.loadGraph("src/test/resources/double-linked-list.txt", "1");
		paths = dfs.paths(a);
		GraphUtils.printPaths(paths);
	}
	
	@Test
	public void testDFSAllPaths() {
		GNode a = GraphUtils.loadGraph("src/test/resources/tree.txt", "A");
		DFSTraversal traversal = new DFSTraversal();
		ArrayList<ArrayList<GNode>> paths = traversal.paths(a);

		assertNotNull(paths);
		GraphUtils.printPaths(paths);
	}

}
