import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class SCC{
  //计算有向图中的强联通分量的Kosaraju算法
  //1 给定一个图，计算他的反向图的逆后序排列；
  //2 在G中进行标准dfs，但是按照1中计算的顺序来访问未被标记节点
  //3 在构造函数中所有在一个递归dfs调用中被访问的节点都属于同一个联通分量
  private boolean[] marked;
  private int count;
  private int[] id;
  public SCC(Digraph G){
    marked=new boolean[G.V()];
    id=new int[G.V()];
    DepthFirstOrder order=new DepthFirstOrder(G.reverse());
    for(int v:order.reversePost()){
      if(!marked[v]){
        dfs(G,v);
        count++;
      }
    }
  }
  public void dfs(Digraph G,int v){
    marked[v]=true;
    id[v]=count;
    for(int w:G.adj(v)){
      if(!marked[w]){
        dfs(G,w);
      }
    }
  }
  public boolean stronglyConnected(int v,int w){return id[v]==id[w];}
  public int id(int v){return id[v];}
  public int count(){return count;}
  public static void main(String[] args) throws IOException {
    String filename="C:\\Users\\Administrator\\Algorithms\\算法书代码\\Graph\\Digraph\\tinyDG.txt";
    try(BufferedReader in=Files.newBufferedReader(Paths.get(filename))){
       Digraph G=new Digraph(13);
       String ss=null;
       while((ss=in.readLine())!=null){
         String[] a=ss.split(" ");
         int v=Integer.parseInt(a[0]);
         int w=Integer.parseInt(a[1]);
           G.addEdge(v, w);
       }

       String s=G.V()+" vertics, "+G.E() +" edge\n";
         System.out.println(s);
        SCC scc = new SCC(G);
        int M=scc.count();
        //System.out.print(M);
        LinkedList<Integer>[] components = (LinkedList<Integer>[]) new LinkedList[M];
          for (int i = 0; i < M; i++) {
              components[i] = new LinkedList<Integer>();
          }
          for (int v = 0; v < G.V(); v++) {
              components[scc.id(v)].addLast(v);
          }

          // print results
          for (int i = 0; i < M; i++) {
              for (int v : components[i]) {
                  System.out.print(v + " ");
              }
              System.out.println();
          }

      }
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
