
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Digraph{
  //有向图数据结构
  private int E;
  private final int V;
  private LinkedList<Integer>[] adj;
  public Digraph(int V){
    this.V=V;
    this.E=0;
    adj=(LinkedList<Integer>[])new LinkedList[V];
    for(int v=0;v<V;v++){
      adj[v]=new LinkedList<Integer>();
    }
  }
  public int V(){return V;}
  public int E(){return E;}
  public void addEdge(int v,int w){
  adj[v].add(w);
  E++;
  }
  public Iterable<Integer> adj(int v){
    return adj[v];
  }
  public Digraph reverse(){
    Digraph R=new Digraph(V);
    for(int v=0;v<V;v++){
      for(int w:adj(v)){
        R.addEdge(w,v);
      }
    }
    return R;
  }
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
         System.out.println(ss);
       }
     }
}
