package cisco.java.challenge.util;

import static org.junit.Assert.*;

import org.junit.Test;

import cisco.java.challenge.impl.DFSTraversal;
import cisco.java.challenge.impl.Node;

public class GraphGeneratorTest {

	@Test
	public void testSmallRandomGraph() {
		int nodes = 1000;
		
		Node[] vertices = new Node[nodes];
		for (int i = 0; i < nodes; i++) {
			Node node = new Node("" + i);
			vertices[i] = node;
			
			for (int j = 0; j < nodes; j++) {
				if (i != j) {
					if (null == vertices[j]) {
						vertices[j] = new Node("" + j);
					}
					node.addChild(vertices[j]);
				}
			}
		}
		
		DFSTraversal dfs = new DFSTraversal();
		assertTrue(dfs.walkGraph(vertices[0]).size() == nodes);
	}

}
