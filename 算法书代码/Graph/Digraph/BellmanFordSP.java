import java.util.*;
public class BellmanFordSP{
  //一般加权有向图的最短路径：基于队列的BellmanFord算法
  //算法描述为：给定起点s，从s无法到达任何负权重环，将distTo【s】初始化为0
  //其他的distTo[]为无穷，以任意顺序放松所有边，重复V轮。
  //其中可以含有负权重，但就是不能有负权重环（环中所有权重加一起为负）
  //时间EV。空间为V
  private Diedge[] edgeTo;
  private double[] distTo;
  private boolean[] onQ;
  private LinkedList<Integer> queue;
  private int cost;
  private Iterable<Diedge> cycle;
  public BellmanFordSP(EdgeWeightedDigraph G,int s){
    edgeTo=new Diedge[G.V()];
    distTo=new double[G.V()];
    onQ=new boolean[G.V()];
    queue=new LinkedList<>();
    for(int v=0;v<G.V();v++){
      distTo[v]=Double.POSITIVE_INFINITY;
      distTo[s]=0.0;
    }
    queue.addLast(s);
    onQ[s]=true;
    while(queue.size()!=0&&!hasNegativeCycle()){
      int v=queue.remove();
      onQ[v]=false;
      relax(G,v);
    }
  }
  public void relax(EdgeWeightedDigraph G,int v){
    for(Diedge e:G.adj(v)){
      int w=e.to();
      if(distTo[w]>distTo[v]+e.weight()){
        distTo[w]=distTo[v]+e.weight();
        edgeTo[w]=e;
        if(!onQ[w]){
          queue.addLast(w);
          onQ[w]=true;
        }
      }
      //要是每轮过后队列刚好空，则说明没有从起点可达的负有向环
      //要是存在，则会无限循环，为了退出循环，此处应寻找有向环
      if(cost++%G.V()==0){
        findNegativeCycle();
      }
    }
  }
  private void findNegativeCycle(){
    int V=edgeTo.length;
    EdgeWeightedDigraph spt=new EdgeWeightedDigraph(V);
    for(int v=0;v<V;v++){
      if(edgeTo[v]!=null)
      spt.addEdge(edgeTo[v]);
    }
    EdgeWeightedCycleFinder cf=new EdgeWeightedCycleFinder(spt);
    cycle=cf.cycle();
  }
  public boolean hasNegativeCycle(){
    return cycle!=null;
  }
  public Iterable<Diedge> negativeCycle(){
    return cycle;
  }
}
class EdgeWeightedCycleFinder{
  private boolean[] marked;
  private boolean[] onStack;
  private Diedge[] edgeTo;
  private LinkedList<Diedge> cycle;

  public EdgeWeightedCycleFinder(EdgeWeightedDigraph G){
    marked=new boolean[G.V()];
    onStack=new boolean[G.V()];
    edgeTo=new Diedge[G.V()];
    for(int v=0;v<G.V();v++){
      if(!marked[v]){
        dfs(G,v);
      }
    }
  }

  public void dfs(EdgeWeightedDigraph G,int v){
    marked[v]=true;
    onStack[v]=true;
    for(Diedge e:G.adj(v)){
      int w=e.to();
      if(cycle!=null) return ;
      else if(!marked[w]){
        edgeTo[w]=e;
        dfs(G,w);
      }
      else if(onStack[w]){
        cycle=new LinkedList<>();
        while(e.from()!=w){
          cycle.push(e);
          e=edgeTo[e.from()];
        }
        cycle.push(e);
        return ;
      }
    }
    onStack[v]=false;
  }
  public boolean hasCycle() {
        return cycle != null;
    }
    public Iterable<Diedge> cycle() {
        return cycle;
    }
}
