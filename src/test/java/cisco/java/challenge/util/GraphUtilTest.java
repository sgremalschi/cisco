package cisco.java.challenge.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import cisco.java.challenge.GNode;
import cisco.java.challenge.impl.DFSTraversal;
import cisco.java.challenge.impl.Node;

public class GraphUtilTest {
	
	@Test(expected = NullPointerException.class)
	public void testGraphUtilsInputs() {
		GraphUtils.loadGraph(null, null);
		GraphUtils.loadGraph(null, "");
		GraphUtils.loadGraph("", null);
		GraphUtils.loadGraph("", "");
	}
	
	@Test
	public void testGraphUtilInitialization() {
		GNode root = GraphUtils.loadGraph("src/test/resources/tree.txt", "A");
		assertNotNull(root);
	}
	
	@Test
	public void hasGraphUtilsHasCycle() {
		Node a = new Node("A");
		Node b = new Node("B");
		a.addChild(b);
		b.addChild(a);
		assertTrue(GraphUtils.hasCycle(a, new HashSet<GNode>()));
	}
	
	@Test
	public void testGraphUtilsPrintPaths() {
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		
		a.addChild(b);
		b.addChild(c);
		b.addChild(d);
		
		ArrayList<GNode> path1 = new ArrayList<>();
		path1.add(a);
		path1.add(b);
		path1.add(c);
		
		ArrayList<GNode> path2 = new ArrayList<>();
		path2.add(a);
		path2.add(b);
		path2.add(d);
		
		ArrayList<ArrayList<GNode>> paths = new ArrayList<>();
		paths.add(path1);
		paths.add(path2);
		
		GraphUtils.print(path1);
		GraphUtils.printPaths(paths);
		
		GraphUtils.print(null);
		GraphUtils.printPaths(null);
	}
	
	@Test
	public void testGetNode() {
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		
		a.addChild(b);
		b.addChild(c);
		b.addChild(d);
		d.addChild(a);
		
		assertTrue(null == GraphUtils.get(null, null));
		assertTrue(null == GraphUtils.get(null, ""));
		assertTrue(null == GraphUtils.get(a, "E"));
		
		assertTrue(a == GraphUtils.get(a, "A"));
		assertTrue(d == GraphUtils.get(a, "D"));
	}
	
	@Test
	public void testListGraph() {
		GNode root = GraphUtils.loadGraph("src/test/resources/clique.txt", "1");
		DFSTraversal dfs = new DFSTraversal();
		ArrayList<GNode> path = dfs.walkGraph(root);
		assertNotNull(path);
		GraphUtils.print(path);
		
		ArrayList<ArrayList<GNode>> paths = dfs.paths(root);
		assertNotNull(paths);
		GraphUtils.printPaths(paths);
	}

}
