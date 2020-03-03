package cisco.java.challenge.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import cisco.java.challenge.GNode;
import cisco.java.challenge.impl.DFSTraversal;
import cisco.java.challenge.impl.Node;

import com.google.common.base.Strings;

public class GraphUtils {

	private GraphUtils() {
	}

	public static HashMap<String, Node> loadVertices(String fileUrl) {
		HashMap<String, Node> vertices = new HashMap<>();

		File f = new File(fileUrl);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(f));
			String line = null;
			while (null != (line = in.readLine())) {
				String[] nodeList = line.split(" ");

				if (nodeList.length > 0) {
					Node parent;

					parent = (vertices.containsKey(nodeList[0])) ? vertices
							.get(nodeList[0]) : new Node(nodeList[0]);

					for (int i = 1; i < nodeList.length; i++) {
						Node child = (vertices.containsKey(nodeList[i])) ? vertices
								.get(nodeList[i]) : new Node(nodeList[i]);
						parent.addChild(child);
						vertices.put(nodeList[i], child);
					}

					vertices.put(parent.getName(), parent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
					in = null;
				}
			}
		}

		return vertices;
	}

	public static GNode loadGraph(String fileUrl, String root) {
		if (Strings.isNullOrEmpty(fileUrl) || Strings.isNullOrEmpty(root)) {
			throw new NullPointerException("Input file URL and root cannot be null");
		}

		HashMap<String, Node> vertices = loadVertices(fileUrl);
		return vertices.get(root);
	}

	public static boolean hasCycle(GNode node, Set<GNode> visited) {
		if (!visited.add(node))
			return true;

		boolean flag = false;
		for (GNode child : node.getChildren()) {
			flag = hasCycle(child, visited);
		}
		return flag;
	}

	public static GNode get(GNode root, String name) {
		if ((null == root) || Strings.isNullOrEmpty(name)) {
			return null;
		}

		if (root.getName().equals(name)) {
			return root;
		} else {
			DFSTraversal dfs = new DFSTraversal();
			for (GNode n : dfs.walkGraph(root)) {
				if (n.getName().equals(name)) {
					return n;
				}
			}
		}

		return null;
	}

	public static void print(ArrayList<GNode> list) {
		if (null == list) {
			return;
		}

		for (GNode node : list) {
			System.out.print(node.getName() + " ");
		}
		System.out.println();
	}

	public static void printPaths(ArrayList<ArrayList<GNode>> paths) {
		if (null == paths) {
			return;
		}

		for (ArrayList<GNode> path : paths) {
			print(path);
		}
	}
}
