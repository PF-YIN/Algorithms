import  java.util.*;
public class Edge implements Comparable<Edge>{
  private final int v;
  private final int w;
  private double weight;
  public Edge(int v,int w,double weight){
    this.v=v;
    this.w=w;
    this.weight=weight;
  }
  public int either(){return v;}
  public int other(int vex){
    if     (vex==v)return w;
    else if(vex==w)return v;
    else throw new RuntimeException("");
  }
  public double weight(){return weight;}
  public int compareTo(Edge that){
    if      (this.weight<that.weight)return -1;
    else if (this.weight>that.weight)return  1;
    else                             return  0;
  }
  public String toString(){
    return String.format("%d-%d %.5f",v,w,weight);
  }
}
