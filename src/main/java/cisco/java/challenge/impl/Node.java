package cisco.java.challenge.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import cisco.java.challenge.GNode;

import com.google.common.base.Strings;

public class Node implements GNode {

	private final String name;
	private final Set<GNode> children;

	public Node(String name) throws IllegalArgumentException {
		if (Strings.isNullOrEmpty(name)) {
			throw new IllegalArgumentException("Node name cannot be null or empty");
		}
		this.name = name;
		this.children = new LinkedHashSet<GNode>();
	}

	public void addChild(GNode node) throws NullPointerException {
		if (null == node) {
			throw new NullPointerException("Child node cannot be null");
		}
		this.children.add(node);
	}

	public String getName() {
		return this.name;
	}

	public GNode[] getChildren() {
		return this.children.toArray(new GNode[children.size()]);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
