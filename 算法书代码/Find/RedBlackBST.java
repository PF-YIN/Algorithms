import java.util.*;
public class RedBlackBST<K extends Comparable<K>,V>{
  private Node root;
  private class Node{
    private int N;
    private Node left;
    private Node right;
    private K key;
    private V value;
    private boolean color;
    public Node(K key,V value,int N,boolean color){
      this.key=key;
      this.value=value;
      this.N=N;
      this.color=color;
    }
  }
  private boolean isRed(Node x){
    if(x==null) return false;
    return x.color==true;
  }
  private Node rotateLeft(Node h){
    Node x=h.right;
    h.right=x.left;
    x.left=h;
    x.color=h.color;
    h.color=true;
    x.N=h.N;
    h.N=1+size(h.left)+size(h.right);
    return x;
  }
  private  Node rotateRight(Node h){
    Node x=h.left;
    h.left=x.right;
    x.right=h;
    x.color=h.color;
    h.color=true;
    x.N=h.N;
    h.N=1+size(h.left)+size(h.right);
    return x;
  }
  private void flipColors(Node h){
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
  }

  public  int size(){
    return size(root);
  }
  private int size(Node x){
    if(x==null)return 0;
    else
    return x.N;
  }
  //右红左黑，左旋转
  //左红且左子节点也红，右旋转
  //左右都红，变色
  public void put(K key,V value){
    root=put(root,key,value);
    root.color=false;
  }
  private Node put(Node h,K key,V value){
    if(h==null) return new Node(key,value,1,true);
    int cmp=key.compareTo(h.key);
    if    (cmp<0)h.left=put(h.left,key,value);
    else if(cmp>0)h.right=put(h.right,key,value);
    else h.value=value;
    if(isRed(h.right)&&!isRed(h.left))    h=rotateLeft(h);
    if(isRed(h.left)&&isRed(h.left.left)) h=rotateRight(h);
    if(isRed(h.right)&&isRed(h.left))     flipColors(h);

    h.N=1+size(h.left)+size(h.right);
    return h;
  }
  public V get(K key){return get(root,key);}
  private V get(Node x,K key){
    if(x==null)return null;
    int cmp=key.compareTo(x.key);
    if     (cmp<0){ return get(x.left,key);}
    else if(cmp>0){ return get(x.right,key);}
    else return x.value;
  }

  public void deleteMin(){
    if(root==null) throw new NoSuchElementException("BST underflow");
    // if both children of root are black, set root to red
    if(!isRed(root.left)&&!isRed(root.right))
    root.color=true;
    root=deleteMin(root);
    if(root!=null)root.color=false;
  }
  private Node deleteMin(Node h){
    if(h.left==null)return null;
    if(!isRed(root.left)&&!isRed(root.left.left)){
      h=moveRedLeft(h);
    }
    h.left=deleteMin(h.left);
    return balance(h);
  }
  public void deleteMax() {
       if (isEmpty()) throw new NoSuchElementException("BST underflow");
       // if both children of root are black, set root to red
       if (!isRed(root.left) && !isRed(root.right))
           root.color = true;
       root = deleteMax(root);
       if (!isEmpty()) root.color = false;
   }
   private Node deleteMax(Node h){
     if(isRed(h.left))h=rotateRight(h);
     if(h.right==null) return null;
     if(!isRed(h.right)&&!isRed(h.right.left)){
       h=moveRedRight(h);
     }
     h.right=deleteMax(h.right);
     return balance(h);
   }
   public boolean contains(K key) {
        return get(key) != null;
    }
   public void delete(K key) {
       if (key == null) throw new NoSuchElementException("argument to delete() is null");
       if (!contains(key)) return;

       // if both children of root are black, set root to red
       if (!isRed(root.left) && !isRed(root.right))
           root.color = true;
       root = delete(root, key);
       if (!isEmpty()) root.color = false;
   }
   private Node delete(Node h, K key) {
     if(key.compareTo(h.key)<0){
       if(!isRed(root.left)&&!isRed(root.left.left)){
         h=moveRedLeft(h);
       }
       h.left=delete(h.left,key);
     }
     else{
       if(isRed(h.left))h=rotateRight(h);
       if(key.compareTo(h.key)==0&&(h.right==null))return null;
       if(!isRed(h.right) && !isRed(h.right.left))
        h = moveRedRight(h);
        if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
              }
          else h.right = delete(h.right, key);
        }
        return balance(h);
   }

   private Node moveRedRight(Node h){
     flipColors(h);
     if(isRed(h.left.left)){
         h=rotateRight(h);
          flipColors(h);
     }
       return h;
   }
  private Node moveRedLeft(Node h){
    flipColors(h);
    if(isRed(h.right.left)){
      h.right=rotateRight(h.right);
      h=rotateLeft(h);
      flipColors(h);
    }
    return h;
  }
  private Node balance(Node h){
    if(isRed(h.right))h=rotateLeft(h);
    if(isRed(root.left)&&isRed(root.left.left))h=rotateRight(h);
    if(isRed(root.right)&&isRed(root.left)) flipColors(h);
    h.N = size(h.left) + size(h.right) + 1;
       return h;
  }

  public boolean isEmpty() {
          return root == null;
      }

  public int height() {
        return height(root);
    }
  private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
    public K min() {
         if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
         return min(root).key;
     }
     private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }
    public K max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
      }
    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        if (x.right == null) return x;
        else                 return max(x.right);
    }
    public Iterable<K> keys() {
        if (isEmpty()) return new ArrayList<K>();
        return keys(min(), max());
    }
    public Iterable<K> keys(K lo, K hi) {
        if (lo == null) throw new NullPointerException("first argument to keys() is null");
        if (hi == null) throw new NullPointerException("second argument to keys() is null");

        List<K> ls = new ArrayList<K>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, ls, lo, hi);
        return ls;
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Node x, List<K> ls, K lo, K hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, ls, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) ls.add(x.key);
        if (cmphi > 0) keys(x.right, ls, lo, hi);
    }
    public static void main(String[] args) {
      String a[]={"A","R","G","H","J","D","S","Q","X","Y","C","B","Z"};
           RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
           for (int i = 0; i<a.length; i++) {
               st.put(a[i], i);
           }
           st.deleteMin();
           st.deleteMax();
           for (String s : st.keys())
               System.out.println(s + " " + st.get(s));
           System.out.println();

       }


}
