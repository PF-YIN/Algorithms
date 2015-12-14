 import java.util.*;
//基于无顺序链表的符号表
public class SequentialSearchST<K,V>{
  private Node first;
  private int N;
  private class Node{
    private K key;
    private V value;
    private Node next;
    public Node(K key,V value,Node next){
      this.key=key;
      this.value=value;
      this.next=next;
    }
  }
  public V get(K key){
    for(Node x=first;x!=null;x=x.next){
      if(key.equals(x.key))return x.value;
    }
    return null;
  }
  public void put(K key,V value){
	if (key == null) throw new NullPointerException("first argument to put() is null");
	if (value == null) {
        delete(key);
        return;
    }
    for (Node x=first; x!=null; x=x.next) {
      if (key.equals(x.key)){
        x.value=value;
        return ;
      }
    }
      first=new Node(key,value,first);
      N++;

  }

  public int size(){return  N;}

  public Iterable<K> keys(){
    ArrayList<K> a=new ArrayList<K>();
    for (Node x=first; x!=null; x=x.next) {
      a.add(x.key);
    }
    return a;
  }
  public void delete(K key){
    if(key==null)throw  new NullPointerException("");
    first=delete(key,first);
  }
  public Node delete(K key,Node x){
    if(key==null)return null;
    if(key.equals(x.key)){
      N--;
      return x.next;
    }
    x.next=delete(key,x.next);
    return x;
  }
  public boolean contains(K key){
    if(key==null)throw  new NullPointerException("");
    return  get(key)!=null;}
  public boolean isEmpty(){return size()==0;}

  public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
            st.put("a", 0);
            st.put("b", 1);
            st.put("c", 2);
            st.put("d", 3);
            st.put("e", 4);
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}
