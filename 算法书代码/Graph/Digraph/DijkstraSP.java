import java.util.*;
public class DijkstraSP{
  //最短路径的Dijkstra算法 时间复杂度                   ElogV
  private Diedge[] edgeTo;//其中保存的值为节点到他的父节点的边，也就是s到v的最小路径树中的最后一条边
  private double[] distTo;//为从s到v中的已知路径的最短长度，约定edgeTo[s]为null
                          //起点到不可达的点的路径长度为无穷，所以可以不要以前的标记
                          //数组marked，以为可以直接判断其中的值是否为无穷就可以知道节点数否被访问过
  private IndexMinPQ1<Double> pq;
  public DijkstraSP(EdgeWeightedDigraph G,int s){
    edgeTo=new Diedge[G.V()];
    distTo=new Double[G.V()];
    pq=new IndexMinPQ1<>();
    for(int v=0;v<G.V();v++){
      distTo[v]=Double.POSITIVE_INFINITY;
    }
    distTo[s]=0.0;
    pq.insert(s,0.0);
    while(!pq.isEmpty){
      relax(G,pq.delMin());
    }
  }
  //边的松弛算法，
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
