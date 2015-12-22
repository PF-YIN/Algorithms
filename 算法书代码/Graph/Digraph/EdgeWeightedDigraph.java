
import java.util.*;

public class EdgeWeightedDigraph{
  private final int V;
  private int E;
  private LinkedList<Diedge>[] adj;
  public EdgeWeightedDigraph(int V){
    this.V=V;
    this.E=0;
    adj=(LinkedList<Diedge>[])new LinkedList[V];
    for(int v=0;v<V;v++){
      adj[v]=new LinkedList<Diedge>();
    }
  }
  public int V(){return V;}
  public int E(){return E;}
  public void addEdge(Diedge e){
    adj[e.from()].add(e);
    E++;
  }
  public Iterable<Diedge> adj(int v){
    return adj[v];
  }
  public Iterable<Diedge> edges(){
    LinkedList<Diedge> b=new LinkedList<>();
    for(int v=0;v<V;v++){
      for(Diedge e:adj[v]){
        b.add(e);//忽略自环
      }
    }
    return b;
  }
  public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Diedge e : adj[v]) {
                s.append(e + "  ");
            }
          //  s.append(NEWLINE);
        }
        return s.toString();
    }
}
