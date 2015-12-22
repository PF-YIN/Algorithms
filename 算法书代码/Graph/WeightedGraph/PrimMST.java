import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.math.BigDecimal;
public class PrimMST{
  //及时Prim计算最小生成树算法
    private Edge[] edgeTo;        // 距离树最近的边
    private double[] distTo;      // edgeTo边的权重
    private boolean[] marked;     // v在树中为true
    private IndexMinPQ1<Double> pq;//有效的横切边
  public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ1<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[0]=0.0;
        pq.insert(0,0.0);
        while(!pq.isEmpty()){
          visit(G,pq.delMin());
        }
  }
  private void visit(EdgeWeightedGraph G,int v){
    //标记v并将所有连接v并且没有被标记顶点的边加入pq
    marked[v]=true;
    for(Edge e:G.adj(v)){
      int w=e.other(v);
      if(marked[w])continue;
      if(e.weight()<distTo[w]){
        //连接w和树的最佳边变为e
        edgeTo[w]=e;
        distTo[w]=e.weight();
        if(pq.contains(w))pq.change(w,distTo[w]);
        else              pq.insert(w,distTo[w]);
      }
    }
  }
  public Iterable<Edge> edges() {
          LinkedList<Edge> mst = new LinkedList<Edge>();
          for (int v = 0; v < edgeTo.length; v++) {
              Edge e = edgeTo[v];
              if (e != null) {
                  mst.add(e);
              }
          }
          return mst;
      }
  public double weight(){
    double w=0D;
    for(Edge e:edges()){
      w+=e.weight();
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
      String filename="C:\\Users\\Administrator\\Algorithms\\算法书代码\\Graph\\WeightedGraph\\tinyEWG.txt";
      //String filename="C:\\Users\\Administrator\\Algorithms\\算法书代码\\Graph\\WeightedGraph\\mediumEWG.txt";
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
         PrimMST m=new PrimMST(G);
         for(Edge e:m.edges()){
          System.out.println(e+" ");
         }
         System.out.println(m.weight());
       }
     }
}
