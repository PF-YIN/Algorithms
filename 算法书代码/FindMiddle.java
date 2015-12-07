//找出中位数，利用快排的思想，可以让时间复杂度变为线性的
public class FindMiddle{
  public static void main(String[] args) {
    Integer[] a={6,3,5,5,5,2,2,4,1,8,9,2,4,6,8,8,9,0,1,5,7,8,9,0,0};
    int k=a.length/2;//中位数
    int x=select(a,k);
    System.out.println(x);
    for (int s :a ) {
      System.out.print(s);
    }
  }
  public static int select(Comparable[] a,int k){
    shuffle(a);
    int lo=0;int hi=a.length-1;
    while(hi>lo){
        int j=partition(a,lo,hi);
        if(j==k)return (int)a[k];
        if(k<j)hi=j-1;
        else lo=j+1;
    }
    return (int)a[k];
  }
  public static int partition(Comparable[] a,int lo,int hi){
    int i=lo;int j=hi+1;
    Comparable v=a[lo];
    while(true){
      while(less(a[++i],v))if(i==hi)break;
      while(less(v,a[--j]))if(j==lo)break;
      if(i>=j)break;
      exch(a,i,j);
    }
    exch(a,lo,j);
    return j;
  }
  public static void exch(Comparable[] a,int i,int j){
  Comparable temp=a[j];
  a[j]=a[i];
  a[i]=temp;
  }
  public static boolean less(Comparable a,Comparable b){
      return a.compareTo(b)<0;
    }
    public static void shuffle(Object[] a) {
          //打乱数组
          Random rr=new Random();
          if (a == null) throw new NullPointerException("argument array is null");
          int N = a.length;
          for (int i = 0; i < N; i++) {
              int r = i + rr.nextInt(N-i);     // between i and N-1
              Object temp = a[i];
              a[i] = a[r];
              a[r] = temp;
          }
      }
}
