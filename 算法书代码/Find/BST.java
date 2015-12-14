import java.util.*;
public class BST<K extends Comparable<K>,V>{
  private Node root;
  private class Node{
    private int N;
    private Node left;
    private Node right;
    private K key;
    private V value;
    public Node(K key,V value,int N){
      this.key=key;
      this.value=value;
      this.N=N;
    }
  }
  public int size(){return size(root);}
  public int size(Node x){
    if(x==null) return 0;
    else        return x.N;
  }
  public V get(K key){return get(root,key);}
  private V get(Node x,K key){
    if(x==null)return null;
    int cmp=key.compareTo(x.key);
    if     (cmp<0){ return get(x.left,key);}
    else if(cmp>0){ return get(x.right,key);}
    else return x.value;
  }
  /*
  public V get(K key){
    Node x=root;
    while(x!=null){
      int cmp=key.compareTo(x.key);
      if(cmp<0) x=x.left;
      else if(cmp>0)x=x.right;
      else {
        return x.value;
      }
    }
    return null;
  }
  */
  public void put(K key,V value){
    root=put(root,key,value);
  }
  private Node put(Node x,K key,V value){
    if(x==null) return new Node(key,value,1);
    int cmp=key.compareTo(x.key);
    if     (cmp<0) x.left=put(x.left,key,value);
    else if(cmp>0) x.right=put(x.right,key,value);
    else x.value=value;
    x.N=size(x.left)+1+size(x.right);
    return x;
  }
  public K max(){
    return max(root).key;
  }
  private Node max(Node x){
    if(x.right==null) return x;
    else return max(x.right);
  }
  public K min(){
    return min(root).key;
  }
  private Node min(Node x){
    if(x.left==null)return x;
    else return min(x.left);
  }
  public K floor(K key){
    Node x=floor(root,key);
    if(x==null) return null;
    return x.key;
  }
  private Node floor(Node x,K key){
    if(x==null) return null;
    int cmp=key.compareTo(x.key);
    if(cmp==0) return x;
    if(cmp<0) return floor(x.left,key);
    Node t=floor(x.right,key);
    if(t!=null) return t;
    else return x;
  }
  public K select(int k){
    return select(root,k).key;
  }
  //from 0 index
  private Node select(Node x,int k){
    if(x==null) return null;
    int t=size(x.left);
    if(t>k)       return select(x.left,k);
    else if (t<k) return select(x.right,k-t-1);
    else return x;
  }
  public int rank(K key){
    return rank(root,key);
  }
  private int rank(Node x,K key){
    if(x==null) return 0;
    int cmp=key.compareTo(x.key);
    if(cmp<0) return rank(x.left,key);
    else if(cmp>0) return 1+size(x.left)+rank(x.right,key);
    else {
      return size(x.left);
    }
  }
  public void deleteMin(){
    root=deleteMin(root);
  }
  private Node deleteMin(Node x){
    if(x.left==null) return x.right;
    else x.left=deleteMin(x.left);
    x.N=size(x.left)+1+size(x.right);
    return  x;
  }
  public void delete(K key){
    root=delete(root,key);
  }
  private Node delete(Node x,K key){
    if(x==null)return null;
    int cmp=key.compareTo(x.key);
    if(cmp<0)      delete(x.left,key);
    else if(cmp>0) delete(x.right,key);
    else{
      if(x.right==null) return x.left;
      if(x.left==null) return x.right;
      Node t=x;
      x=min(t.right);
      x.right=deleteMin(t.right);
      x.left=t.left;
    }
      x.N=size(x.left)+1+size(x.right);
      return x;
    }

  public Iterable<K> keys(K lo,K hi){
      List<K> ls=new ArrayList<>();
      keys(root,ls,lo,hi);
      return ls;
  }
  private void keys(Node x,List<K> ls,K lo,K hi){
    if(x==null) return;
    int cmplo=lo.compareTo(x.key);
    int cmphi=hi.compareTo(x.key);
    //绝了，范围先向左移动，当移动动到相等时停止开始添加，然后每添加一个就往右移动一个
    //直到
    if(cmplo<0) keys(x.left,ls,lo,hi);
    if(cmplo<=0&&cmphi>=0){ls.add(x.key);}
    if(cmphi>0) keys(x.right,ls,lo,hi);
  }
  public int height(){
    return height(root);
  }
  private int height(Node x){
    if(x==null)return -1;
    return 1+ Math.max(height(x.left),height(x.right));
  }
  public static void main(String[] args) {
       String test = "S E A R C H E X A M P L E";
       String[] keys = test.split(" ");
       int N = keys.length;
       BST<String, Integer> st = new BST<String, Integer>();
       for (int i = 0; i < N; i++)
           st.put(keys[i], i);

       System.out.println("size = " + st.size());
       System.out.println("min  = " + st.min());
       System.out.println("max  = " + st.max());
       System.out.println();
     }
}
