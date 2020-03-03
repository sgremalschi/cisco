package cisco.java.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cisco.java.challenge.impl.Node;

public class NodeTest {

	@Test(expected = IllegalArgumentException.class)
	public void whenExceptionThrown_nullOrEmpty() {
		String name = null;
		new Node(name);

		name = "";
		new Node(name);
	}
	
	@Test(expected = NullPointerException.class)
	public void whenExceptionThrown_null_node() {
		Node node = new Node("A");
		node.addChild(null);
	}

	@Test
	public void testGetChildren() {
		Node a = new Node("A");
		assertNotNull(a.getChildren());
		assertEquals(0, a.getChildren().length);
		
		assertTrue("A".equals(a.getName()));
		assertTrue("A".equals(a.toString()));
		
		Node b = new Node("B");
		a.addChild(b);
		assertTrue(1 == a.getChildren().length);
	}

}
