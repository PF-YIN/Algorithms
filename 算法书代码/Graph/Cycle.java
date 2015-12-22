public class Cycle{
  //判断是无环图么,暂时有问题
  private boolean[] marked;
  private boolean hasCycle;
  public Cycle(Graph G){
    marked=new boolean[G.V()];
    for(int a=0;a<G.V();a++){
      if(!marked[a]){
        dfs(G,a,a);
      }
    }
  }
  private void dfs(Graph G,int v,int u){
    marked[v]=true;
    for(int w:G.adj(v)){
      if(!marked[w]){dfs(G,w,v);}

      else if(w!=u){hasCycle=true;}
      }
  }
  public boolean hasCycle(){return hasCycle;}
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
        Cycle cc=new Cycle(G);

        System.out.println(cc.hasCycle());
      }
}
