import java.util.*;
public class Topologic{
  //所有顶点的拓扑排序，一副有向无环图的拓扑排序即为所有顶点的逆后序排列
  //可以解决图顶点优先级调度的问题
  private Iterable<Integer> order;
  public Topologic(Digraph G){
    DirectedCycle cyclefinder=new DirectedCycle(G);
    if(!cyclefinder.hasCycle()){
      DepthFirstOrder dfs=new DepthFirstOrder(G);
      order=dfs.reversePost();
    }
  }
  public Topologic(EdgeWeightedDigraph G){
    EdgeWeightedCycleFinder finder=new EdgeWeightedCycleFinder(G);
    if(!finder.hasCycle()){
      DepthFirstOrder dfs=new DepthFirstOrder();
      order=dfs.reversePost();
    }
  }
  public Iterable<Integer> order(){
    return order;
  }
}
