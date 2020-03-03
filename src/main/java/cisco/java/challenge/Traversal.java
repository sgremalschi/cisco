package cisco.java.challenge;

import java.util.ArrayList;

public interface Traversal {
	public ArrayList<GNode> walkGraph(GNode node);

	public ArrayList<ArrayList<GNode>> paths(GNode node);
}
