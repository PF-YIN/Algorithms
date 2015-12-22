public class AcyclicSP{
  //无环加权有向图的最短路径算法，使用了顶点的拓扑排序作为顶点放松的顺序。时间复杂度E+V
  //该算法处理时间为线性级别E+V，在没有环的情况下，为最优算法，而且可以处理负权重的边
  //WeightedTopologic为之前无环有向图的拓扑排序的有权重版本
  private Diedge[] edgeTo;
  private double[] distTo;

  public AcyclicSP(EdgeWeightedDigraph G,int s){
    edgeTo=new Digraph[G.V()];
    distTo=new Double[G.V()];
    for(int v=0;v<G.V();v++){
      distTo[v]=Double.POSITIVE_INFINITY;
    }
    distTo[s]=0.0;
    Topologic top=new Topologic(G);
    for(int v:top.order()){
      relax(G,v);
    }
  }
  private void relax(EdgeWeightedDigraph G,int v){
    for(Diedge e:G.adj(v)){
      int w=e.to();
      if(distTo[w]>distTo[v]+e.weight()){
        distTo[w]=distTo[v]+e.weight();
        edgeTo[w]=e;
        if(pq.contain(w))pq.change(w,distTo[w]);
        else             pq.insert(w,distTo[w]);
      }
    }
  }
  public double distTo(int v){return distTo[v];}
  public boolean hasPathTo(int v){return distTo[v]<Double.POSITIVE_INFINITY;}
  public Iterable<Integer> pathTo(int v){
    if(!hasPathTo(v)) return null;
    Deque<Integer> path=new ArrayDeque<>();
    for (Diedge e=edgeTo[v];e!=null ;e=edgeTo[e.from()] ) {
      path.addFirst(e);
    }
    return path;
  }
}
