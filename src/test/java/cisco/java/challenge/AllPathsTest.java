package cisco.java.challenge;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import cisco.java.challenge.impl.AllPaths;
import cisco.java.challenge.util.GraphUtils;

public class AllPathsTest {

	@Test
	public void testDFSAllPaths() {
		GNode a = GraphUtils.loadGraph("src/test/resources/graph1.txt", "A");
		AllPaths allPaths = new AllPaths(a, a);
		ArrayList<ArrayList<GNode>> paths = allPaths.getAllPaths(a);
		
		assertNotNull(paths);
		GraphUtils.printPaths(paths);
		
		/*
		a = GraphUtils.loadGraph("src/test/resources/clique_4.txt", "A");
		paths = allPaths.getAllPaths(a);
		
		assertNotNull(paths);
		GraphUtils.printPaths(paths);
		*/
	}

}
