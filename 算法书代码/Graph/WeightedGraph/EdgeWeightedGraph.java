import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class EdgeWeightedGraph{
  private final int V;
  private int E;
  private LinkedList<Edge>[] adj;
  public EdgeWeightedGraph(int V){
    this.V=V;
    this.E=0;
    adj=(LinkedList<Edge>[])new LinkedList[V];
    for (int v=0;v<V;v++){
      adj[v]=new LinkedList<Edge>();
    }
  }
  public int V(){return V;}
  public int E(){return E;}
  public void addEdge(Edge e){
    int v=e.either();
    int w=e.other(v);
  adj[v].add(e);
  adj[w].add(e);
  E++;
  }
  public Iterable<Edge> adj(int v){
    return adj[v];
  }
  public Iterable<Edge> edges(){
    LinkedList<Edge> b=new LinkedList<>();
    for(int v=0;v<V;v++){
      for(Edge e:adj[v]){
        if(e.other(v)>v)b.add(e);//忽略自环
      }
    }
    return b;
  }
  public String toString(){
    StringBuilder s=new StringBuilder();
    s.append(V+" "+E);s.append("\n");
    for(int v=0;v<V;v++){
      s.append(v+": ");
      for(Edge e:adj(v)){
        s.append(e+" ");
      }
      s.append("\n");
    }

    return s.toString();
  }
  
  public static void main(String[] args) throws IOException{

      String filename="C:\\Users\\Administrator\\Algorithms\\算法书代码\\Graph\\WeightedGraph\\tinyEWD.txt";
      try(BufferedReader in=Files.newBufferedReader(Paths.get(filename))){
         EdgeWeightedGraph G = new EdgeWeightedGraph(8);
         String ss=null;
         while((ss=in.readLine())!=null){
           String[] a=ss.split(" ");
           int v=Integer.parseInt(a[0]);
           int w=Integer.parseInt(a[1]);
           double weight=Double.parseDouble(a[2]);
           Edge e=new Edge(v,w,weight);
           G.addEdge(e);
         }

         //String s=G.V()+" vertics, "+G.E() +" edge\n";
           System.out.println(G);
         }
       }

      //  System.out.println(G.edges());
}
