import java.util.*;
public class DirectedCycle{
  //判断有向图中是否含有环
  private boolean[] marked;
  private boolean[] onStack;//用于标记递归调用栈上的所有顶点
  private int[] edgeTo;
  private LinkedList<Integer> cycle;//cycle 作用是栈，保存有向环中所有顶点
  public DirectedCycle(Digraph G){
    marked=new boolean[G.V()];
    onStack=new boolean[G.V()];
    edgeTo=new int[G.V()];
    for(int v=0;v<G.V();v++){
      if(!marked[v])
      dfs(G,v);
    }
  }
  public void dfs(Digraph G,int v){
    marked[v]=true;
    onStack[v]=true;
    for(int w:G.adj(v)){
      if(this.hasCycle())return ;
      else if(!marked[w]){
        edgeTo[w]=v;
        dfs(G,w);
      }
      else if(onStack[w]){
        cycle=new LinkedList<Integer>();
        for (int x=v;x!=w;x=edgeTo[x]) {
          cycle.push(x);
        }
        cycle.push(w);
        cycle.push(v);
      }
    }
    onStack[v]=false;//开始设为true，结束设为false
  }
  public boolean hasCycle(){
    return cycle!=null;
  }
  public Iterable<Integer> cycle(){
    return cycle;
  }
}
