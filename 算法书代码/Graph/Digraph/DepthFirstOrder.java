import java.util.*;
public class DepthFirstOrder{
  //基于dfs的顶点排序
  private boolean[] marked;
  private LinkedList<Integer> pre;//所有顶点的前序排列，队列
  private LinkedList<Integer> post;//所有顶点的后序排列，队列
  private LinkedList<Integer> reversePost;//左右顶点的逆后序列，栈
  public DepthFirstOrder(Digraph G){
    marked=new boolean[G.V()];
    pre=   new LinkedList<>();
    post=  new LinkedList<>();
    reversePost=new LinkedList<>();
    for(int v=0;v<G.V();v++){
      if(!marked[v])
      dfs(G,v);
    }
  }
  public void dfs(Digraph G,int v){
    pre.addLast(v);
    marked[v]=true;
    for(int w:G.adj(v)){
      if(!marked[w])dfs(G,w);
    }
    post.addLast(v);
    reversePost.push(v);
  }
  public Iterable<Integer> pre(){return pre;}
  public Iterable<Integer> post(){return post;}
  public Iterable<Integer> reversePost(){return reversePost;}
}
