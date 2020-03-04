# cisco

Repository includes solutions to the following challenges:

  1. DFSTraversal.java

  ArrayList<GNode> walkGraph(GNode node)
    - returns an ArrayList containing every GNode in the graph. 
      Each node appears in the ArrayList exactly once (i.e. no duplicates).
  
  ArrayList<ArrayList<GNode>> paths(GNode node)
    - returns a ArrayList of ArrayLists, representing all possible longest paths through the graph starting at 'node'.
      The ArrayList returned can be thought of as a ArrayList of paths, where each path is represented
      as an ArrayList of GNodes. Intermediate paths are not included in the output.
      
  2. SimpleWordCounter.java
    - new SimpleWordCounter(fileUrl) - produces a list of word occurences sorted DESC.
    
    Example
      14 the
      9 in
      9 of
      6 a
      ...
