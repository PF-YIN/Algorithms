package Graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class SymbolGraph{
  private Map<String,Integer> st;
  private String[] keys;
  private Graph G;
  //构造了两次，首先构造map和keys 然后图
  public SymbolGraph(String filename,String sp) throws IOException{
    st=new TreeMap<String ,Integer>();
    try(BufferedReader in=Files.newBufferedReader(Paths.get(filename))){
    	String s=null;
    while((s=in.readLine())!=null){
    	String[] a=s.split(sp);
    	//System.out.println(a[0]);
    	for(int i=0;i<a.length;i++){
    		if(!st.containsKey(a[i])){
    			st.put(a[i], st.size());
    		}
    	}
    }
    keys=new String[st.size()];
    for(String name:st.keySet()){
    	keys[st.get(name)]=name;
    }
    }
    try(BufferedReader in=Files.newBufferedReader(Paths.get(filename))){
       G=new Graph(st.size());
       String ss=null;
       while((ss=in.readLine())!=null){
    	   String[] a=ss.split(sp);
    	   int v=st.get(a[0]);
    	   for(int i=1;i<a.length;i++){
    		   G.addEdge(v, st.get(a[i]));
    	   }
       }
    }
}
  public boolean contains(String s){return st.containsKey(s);}
  public int index(String s){return st.get(s);}
  public String name(int v){return keys[v];}
  public Graph G(){return G;}
  public static void main(String[] args) throws IOException {
      SymbolGraph sg = new SymbolGraph("C:\\Users\\Administrator\\Algorithms\\算法书代码\\Graph\\routes.txt", " ");
      Graph G = sg.G();

          String source = "LAX";
          if (sg.contains(source)) {
              int s = sg.index(source);
              for (int v : G.adj(s)) {
                  System.out.println("   " + sg.name(v));
              }
          }
          else {
        	  System.out.println("input not contain '" + source + "'");

      }
  }
}
