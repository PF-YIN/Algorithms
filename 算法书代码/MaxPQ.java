public class MaxPQ<T extends Comparable<T>>{
  public static void main(String[] args) {

  }
  private T[] pq;
  private int n=0;
  public  MaxPQ(int maxN){
    pq=(T[])new Comparable[maxN+1];
  }
  public boolean isEmpty(){return n==0;}
  public int size() {return n;}
  public insert(T v){
    pq[++n]=v;
    swim(n);
  }
  public T delMax(){
    T max=pq[1];
    exch(1,n--);
    pq[n+1]=null;
    sink(1);
    return max;
  }
  private boolean less(int i,int j){return pq[i].compareTo(pq[j]);}
  private exch(int i,int j){T tem=pq[i];pq[i]=pq[j];pq[j]=tem;}
  private void swim(int k){
    while(k>1&&less(k/2,k)){
      exch(k/2,k);
      k=k/2;
    }
  }
  private void sink(int k){
    while(k*2<=n){
      int j=k*2;
      if(j<n&&less(j,j+1))j++;
      if(!less(k,j)) break;
      exch(k,j);
      k=j;
    }
  }
}
