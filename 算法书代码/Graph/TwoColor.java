public class TwoColor{
  private boolean[] marked;
  private boolean[] color;
  private boolean isTwo=true;
  public TwoColor(Graph G){
    marked=new boolean[G.V()];
    color=new boolean[G.V()];
    for(int a=0;a<G.V();a++){
      if(!marked[a]){
        dfs(G,a);
      }
  }
}
private void dfs(Graph G,int v){
  marked[v]=true;
  for(int w:G.adj(v)){
    if(!marked[w]){
      color[w]=!color[v];
      dfs(G,w);
    }
    else if(color[w]==color[v])isTwo=false;
  }
}
public boolean isTwoColor(){return isTwo;}
public static void main(String[] args) {
      Graph G = new Graph(6);
      G.addEdge(0,2);
      G.addEdge(0,1);
      G.addEdge(0,5);
      G.addEdge(1,2);
      G.addEdge(2,3);
      G.addEdge(2,4);
      G.addEdge(3,5);
      G.addEdge(3,4);
      int s = 0;
      TwoColor cc=new TwoColor(G);

      System.out.println(cc.isTwo);
    }
}
