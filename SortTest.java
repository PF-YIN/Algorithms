import java.util.*;
public class SortTest{
   public static void main(String[] args) {
     Integer[] a={6,3,5,5,5,2,2,4,1};
     healSort(a);
     for (int b :a ) {
       System.out.print(b);
     }
  }

  //选择排序
  public static void selectSort(Comparable[] a){
    for (int i=0; i<a.length;i++ ) {
      for (int j=i+1; j<a.length; j++) {
        if(less(a[j],a[i]))exch(a,i,j);
      }
    }
  }

  //插入排序
  public static void insertSort(Comparable[] a){
    for(int i=0;i<a.length;i++){
      for(int j=i;j>0&&less(a[j],a[j-1]);j--){
        exch(a,j,j-1);
      }
    }
  }

  //希尔排序
  public static void shellSort(Comparable[] a){
    int h=1;
    while(h<a.length/3)h=h*3+1;
    while(h>0){
    for (int i=h; i<a.length; i++) {
      for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
        exch(a,j,j-h);
      }
    }
    h=h/3;
  }
  }

  /*归并排序，自上而下
  */
  private static Comparable[] aux;
  private static void merge(Comparable[] a,int lo,int mid,int hi){
    int i=lo;int j=mid+1;
    for (int k=lo;k<=hi;k++ ) {
      aux[k]=a[k];
    }
    for (int k=lo;k<=hi;k++) {
      if     (i>mid)              a[k]=aux[j++];
      else if(j>hi)               a[k]=aux[i++];
      else if(less(aux[j],aux[i]))a[k]=aux[j++];
      else                        a[k]=aux[i++];
    }
  }
  public static void mergeSort(Comparable[] a){
    aux=new Comparable[a.length];
    sort(a,0,a.length-1);
  }
  private static void sort(Comparable[] a,int lo,int hi){
    if(hi<=lo)return;
    int mid=lo+(hi-lo)/2;
    sort(a,lo,mid);
    sort(a,mid+1,hi);
    merge(a,lo,mid,hi);
  }
  //归并排序，自下而上
  public  static void mergeSort1(Comparable [] a){
    int n=a.length;
    aux=new Comparable[n];
    for (int sz=1; sz<n; sz=sz+sz) {
      for (int lo=0; lo<n-sz;lo+=sz+sz) {
        merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,n-1));
      }
    }
  }

  //快排
  public static void quickSort(Comparable[] a){
   shuffle(a);
   sort3way(a,0,a.length-1);
  }
  private static void sort1(Comparable[] a,int lo,int hi){
    if(hi<=lo)return;
    //改进 ,当数组太小时，用插入排序代替，M取5-15
    //if(hi<=lo+M){Inserttion.sort(a,lo,hi);return;}
    int j=partition(a,lo,hi);
    sort1(a,lo,j-1);
    sort1(a,j+1,hi);
  }
  //改进的三项切分快速排序
  private static void sort3way(Comparable[] a,int lo,int hi){
    if(hi<=lo)return ;
    int lt=lo; int i=lo+1; int gt=hi;
    Comparable v=a[lo];
    while(i<=gt){
      int cmp=a[i].compareTo(v);
      if      (cmp<0){exch(a,i,lt);lt++;i++;}
      else if (cmp>0){exch(a,i,gt);gt--;}
      else    i++;
    }
    sort3way(a,lo,lt-1);
    sort3way(a,gt+1,hi);
  }
  private static int partition(Comparable[] a,int lo,int hi){
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

  //堆排序
  public static void healSort(Comparable[] a){
    int n=a.length;
    for(int i=n/2;i>=1;i--){
      sink(a,i,n);
    }
    while(n>1){
      exch1(a,1,n--);
      sink(a,1,n);
    }
  }
  private static void sink(Comparable[] a,int k,int n){
    while(k*2<=n){
      int j=k*2;
      if(j<n&&less1(a,j,j+1))j++;
      if(!less1(a,k,j))break;
      exch1(a,k,j);
      k=j;
    }
  }
  //堆排序专用比较交换方法,因为堆是从1开始的。
  private static boolean less1(Comparable[] a,int i,int j){
    return a[i-1].compareTo(a[j-1])<0;
  }
  private static void exch1(Comparable[] a,int i,int j){
    Comparable v=a[i-1];
    a[i-1]=a[j-1];
    a[j-1]=v;
  }


  ////通用方法
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

  public static void exch(Comparable[] a,int i,int j){
  Comparable temp=a[j];
  a[j]=a[i];
  a[i]=temp;
  }
  public static boolean less(Comparable a,Comparable b){
      return a.compareTo(b)<0;
    }
}
