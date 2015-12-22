import java.util.*;
public class BreadthFirstPaths{
  private boolean[] marked;
  private int[] edgeTo;
  private final int s;
  public BreadthFirstPaths(Graph G,int s){
    this.s=s;
    marked=new boolean[G.V()];
    edgeTo=new int[G.V()];
    bfs(G,s);
  }
  private void bfs(Graph G,int s){
    LinkedList<Integer> queue=new LinkedList<>();
    marked[s]=true;
    queue.addLast(s);
    while(queue.size()>0){
      int v=queue.poll();
      //System.out.print(v);
      for(int w:G.adj(v)){
        if(!marked[w]){//if didn't add {} check a long time.
        edgeTo[w]=v;
        marked[w]=true;
        queue.addLast(w);
      }
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
        G.addEdge(2,3);
        G.addEdge(2,4);
        G.addEdge(3,5);
        G.addEdge(3,4);

        int s = 0;//返回顶点0到其他顶点的最短路径
          String ss=G.V()+" vertics, "+G.E() +" edge\n";
            System.out.println(ss);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                  System.out.printf("%d to %d:  ", s, v);
                for (int x : bfs.pathTo(v)) {
                    if (x == s)   System.out.print(x);
                    else          System.out.print("-" + x);
                }
                  System.out.println();
            }

            else {
                System.out.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}
