import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.math.BigDecimal;
public class LazyPrimMST{
  //延迟Prim计算最小生成树算法，即优先队列中会保留失效的边
  private LinkedList<Edge> mst;
  private boolean[] marked;
  private PriorityQueue<Edge> pq;
  public LazyPrimMST(EdgeWeightedGraph G){
    marked=new boolean[G.V()];
    mst=new LinkedList<Edge>();
    pq=new PriorityQueue<Edge>();
    visit(G,0);//假设G联通
    while(pq.size()!=0){
      Edge e=pq.poll();
      int v=e.either();
      int w=e.other(v);
      if(marked[v]&&marked[w])continue;//跳过失效的边
      mst.addLast(e);
      if(!marked[v])visit(G,v);
      if(!marked[w])visit(G,w);
    }
  }
  private void visit(EdgeWeightedGraph G,int v){
    //标记v并将所有连接v并且没有被标记顶点的边加入pq
    marked[v]=true;
    for(Edge e:G.adj(v)){
      if(!marked[e.other(v)])pq.add(e);
    }
  }
  public Iterable<Edge> edges(){
    return mst;
  }
  public double weight(){
    double w=0D;
    for(Edge e:mst){
      w=add(w,e.weight());
    }
    return w;
  }
  //java中实现浮点数的精确计算，添加add方法，转为BigDecimal再计算否则会出现0.01+0.05=
  //0.060000000005的情况
  public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
  public static void main(String[] args) throws IOException{
//tiny8个点 medi250个
      //String filename="C:\\Users\\Administrator\\Algorithms\\算法书代码\\Graph\\WeightedGraph\\tinyEWG.txt";
      String filename="C:\\Users\\Administrator\\Algorithms\\算法书代码\\Graph\\WeightedGraph\\mediumEWG.txt";
      try(BufferedReader in=Files.newBufferedReader(Paths.get(filename))){
         EdgeWeightedGraph G = new EdgeWeightedGraph(250);
         String ss=null;
         while((ss=in.readLine())!=null){
           String[] a=ss.split(" ");
           int v=Integer.parseInt(a[0]);
           int w=Integer.parseInt(a[1]);
           double weight=Double.parseDouble(a[2]);
           Edge e=new Edge(v,w,weight);
           G.addEdge(e);
         }
         LazyPrimMST m=new LazyPrimMST(G);
         for(Edge e:m.edges()){
          System.out.println(e+" ");
         }
         System.out.println(m.weight());
       }
     }

}
