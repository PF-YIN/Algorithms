import java.util.*;
public class Graph{
  private final int V;
  private int E;
  private LinkedList<Integer>[] adj;
  public Graph(int V){
    this.V=V;
    this.E=0;
    adj=(LinkedList<Integer>[])new LinkedList[V];
    for (int v=0;v<V ;v++ ){
      adj[v]=new LinkedList<Integer>();
    }
  }
  public Graph(Scanner in){
    this(in.nextInt());
    int E=in.nextInt();
    for (int i=0;i<E ;i++ ) {
      int v=in.nextInt();
      int w=in.nextInt();
      addEdge(v,w);
    }
  }
  public int V(){return V;}
  public int E(){return E;}
  public void addEdge(int v,int w){
  adj[v].add(w);
  adj[w].add(v);
  E++;
  }
  public Iterable<Integer> adj(int v){
    return adj[v];
  }
  public static int degree(Graph G,int v){
    int degree=0;
    for(int w:G.adj(v))degree++;
    return degree;
  }
  public static int maxDegree(Graph G){
    int max=0;
    for(int v=0;v<G.V();v++){
      if(degree(G,v)>max)max=degree(G,v);
    }
    return max;
  }
  public static int numberOfSelfLoops(Graph G){
    int count=0;
    for(int v=0;v<G.V();v++){
      for(int w:G.adj[v]){
        if(v==w)count++;
      }
    }
    return count/2;
  }
  public String toString(){
    String s=V+" vertics, "+E +" edge\n";
    for(int v=0;v<V;v++){
        s+= v+": ";
        for (int w:this.adj(v)) {
          s+=w+" ";
        }
        s+="\n";
    }
    return s;
  }
  public static void main(String[] args) {
        Graph G = new Graph(6);
        G.addEdge(0,1);
        G.addEdge(0,5);
        G.addEdge(0,2);
        G.addEdge(1,2);
        G.addEdge(3,2);
        G.addEdge(3,4);
        G.addEdge(3,5);
        G.addEdge(4,2);
        System.out.println(G);
      }
}
