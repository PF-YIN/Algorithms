//基于有序数组的符号表 二分查找
import java.util.*;
public class BinarySearchST<K extends Comparable<K>,V>{
  private K[] keys;
  private V[] values;
  int N;
  public BinarySearchST(int n){
    keys=(K[])new Comparable[n];
    values=(V[])new Object[n];
  }
  public BinarySearchST() {
        this(2);
    }
  public int size(){return N;}
  public boolean isEmpty(){return N==0;}
  public boolean contains(K key){
    if (key == null) throw new NullPointerException("");
    return get(key)!=null;
  }
  public V get(K key){
    if(key==null) throw new NullPointerException();
    if(isEmpty()) return null;
    int i=rank(key);
    if(i<N&&keys[i].compareTo(key)==0)return values[i];
    else                              return null;
  }
  public int rank(K key){
    if(key==null) throw new NullPointerException();
    int lo=0;int hi=N-1;
    while(lo<=hi){
      int mid=lo+(hi-lo)/2;
      int cmp=key.compareTo(keys[mid]);
      if(cmp==0)return mid;
      else if(cmp<0) hi=mid-1;
      else      lo=mid+1;
    }
    return lo;
  };
  public void put(K key,V value){
    if (key == null) throw new NullPointerException("first argument to put() is null");
  	if (value == null) {
          delete(key);
          return;
      }
    int i=rank(key);
    //key already exit
    if(i<N&&keys[i].compareTo(key)==0){
      values[i]=value;
      return ;
    }
    if(keys.length==N)resize(N*2);
    for (int j=N; j>i;j-- ) {
      keys[j]=keys[j-1];
      values[j]=values[j-1];
    }
      keys[i]=key;
      values[i]=value;
      N++;
    }

  private void resize(int n){
    K[] a=(K[])new Comparable[n];
    V[] b=(V[])new Object[n];
    for(int i=0;i<N;i++){
      a[i]=keys[i];
      b[i]=values[i];
    }
    keys=a;
    values=b;
  }
  public void delete(K key){
    if(key==null) throw new NullPointerException("");
    if(isEmpty()) return ;
    int j=rank(key);
    if(j==N||keys[j].compareTo(key)!=0){return;}
    for(int i=j;i<N-1;i++){
      keys[i]=keys[i+1];
      values[i]=values[i+1];
    }
    N--;
    keys[N]=null;
    values[N]=null;
    if(N>0&&N==keys.length/4)resize(keys.length/2);
  }
  public Iterable<K> keys() {
       return keys(min(), max());
   }
   public Iterable<K> keys(K lo, K hi) {
        if (lo == null) throw new NullPointerException("first argument to size() is null");
        if (hi == null) throw new NullPointerException("second argument to size() is null");

        List<K> a = new ArrayList<K>();
        // if (lo == null && hi == null) return queue;
        if (lo == null) throw new NullPointerException("lo is null in keys()");
        if (hi == null) throw new NullPointerException("hi is null in keys()");
        if (lo.compareTo(hi) > 0) return a;
        for (int i = rank(lo); i < rank(hi); i++)
            a.add(keys[i]);
        if (contains(hi)) a.add(keys[rank(hi)]);
        return a;
    }
 public K min() {
        if (isEmpty()) return null;
        return keys[0];
    }
  public K max() {
       if (isEmpty()) return null;
       return keys[N-1];
   }
   public K floor(K key){
     if(key==null)throw new NullPointerException("");
     int i=rank(key);
     if(i<N&&key.compareTo(keys[i])==0)return keys[i];
     if(i==0)return null;
     else return keys[i-1];
   }
   public  K ceiling(K key){
     if(key==null)throw new NullPointerException("");
     int i=rank(key);
     if(i==N)return null;
     else return keys[i];
   }
  public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
            st.put("a", 0);
            st.put("b", 1);
            st.put("c", 2);
            st.put("d", 3);
            st.put("e", 4);
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}
