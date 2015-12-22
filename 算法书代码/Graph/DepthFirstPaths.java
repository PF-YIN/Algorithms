import java.util.*;
public class DepthFirstPaths{
  private int[] edgeTo;
  private boolean[] marked;
  private final int s;
  public DepthFirstPaths(Graph G,int s){
    marked=new boolean[G.V()];
    edgeTo=new int[G.V()];
    this.s=s;
    dfs(G,s);
  }
  private void dfs(Graph G,int v){
    marked[v]=true;
    for(int w:G.adj(v)){
      if(!marked[w]){
      edgeTo[w]=v;
      dfs(G,w);
    }
    }
  }
  public boolean hasPathTo(int v){
    return marked[v];
  }
  public Iterable<Integer> pathTo(int v){
    if(!hasPathTo(v)) return null;
    Deque<Integer> path=new ArrayDeque<>();
    for (int x=v;x!=s ;x=edgeTo[x] ) {
      path.addFirst(x);
    }
    path.addFirst(s);
    return path;
  }
  public static void main(String[] args) {
        Graph G = new Graph(6);
        G.addEdge(0,2);
        G.addEdge(0,1);
        G.addEdge(0,5);
        G.addEdge(1,2);
        G.addEdge(3,2);
        G.addEdge(4,2);
        G.addEdge(3,4);
        G.addEdge(3,5);

        int s = 0;
          String ss=G.V()+" vertics, "+G.E() +" edge\n";
            System.out.println(ss);
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);
        boolean a=dfs.Cycle(G);
        System.out.print(a);
      /*  for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                  System.out.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s)   System.out.print(x);
                    else          System.out.print("-" + x);
                }
                  System.out.println();
            }

            else {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }

        }*/
    }
}
