package examplesix;
import org.apache.hadoop.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.*;
import java.util.*;
public class CiclistaReducer extends Reducer<BigramWritable,DoubleWritable,Text,DoubleWritable>{
	private PriorityQueue<Ciclista> coda;
	private static final int topk = 5;
	
	public void setup(Context ctx){
		coda = new PriorityQueue<Ciclista>(topk,new Comparator<Ciclista>(){
			public int compare(Ciclista c1,Ciclista c2){
				return (int) (c1.getValue()-c2.getValue());
			}
		});
	}
	
	public void reduce(BigramWritable key,Iterable<DoubleWritable> val,Context ctx)throws IOException,InterruptedException{
		Double valore = 0.0;
		for(DoubleWritable it:val)
			valore +=it.get();
		coda.add(new Ciclista(key.left.toString(),key.right.toString(),valore));
		if(coda.size()>topk)
			coda.remove();
	}
	public void cleanup(Context ctx)throws IOException,InterruptedException{
		List<Ciclista> list = new ArrayList<Ciclista>();
		while(!coda.isEmpty())
			list.add(coda.remove());
		for(int i = topk-1;i>=0;i--){
			Ciclista tmp = list.get(i);
			ctx.write(new Text(tmp.getNome()+" "+tmp.getCognome()), new DoubleWritable(tmp.getValue()));
		}
	}
}
