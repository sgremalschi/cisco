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
	public void testDFSAllPaths() {
		GNode a = GraphUtils.loadGraph("src/test/resources/graph1.txt", "A");
		DFSTraversal traversal = new DFSTraversal();
		ArrayList<ArrayList<GNode>> paths = traversal.paths(a);

		assertNotNull(paths);
		GraphUtils.printPaths(paths);
		
		a = GraphUtils.loadGraph("src/test/resources/clique_4.txt", "A");
		assertTrue(GraphUtils.hasCycle(a, new HashSet<GNode>()));
		paths = traversal.paths(a, GraphUtils.get(a, "D"));
		
		assertNotNull(paths);
		GraphUtils.printPaths(paths);
	}

}
