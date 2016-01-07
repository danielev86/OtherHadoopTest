package examplefour;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.util.*;
import java.io.*;
public class ImpiegatiReducer extends Reducer<BigramWritable,DoubleWritable,Text,Text>{
	
	private PriorityQueue<Impiegati> coda;
	private static final int topK=5;
	
	public void setup(Context ctx){
		coda = new PriorityQueue<Impiegati>(new Comparator<Impiegati>(){
			public int compare(Impiegati i1,Impiegati i2){
				return i1.getDepartimento().compareTo(i2.getDepartimento());
			}
		});
	}
	
	
	public void reduce(BigramWritable key,Iterable<DoubleWritable> values, Context ctx)throws IOException,InterruptedException{
		Double v = 0.0;
		for(DoubleWritable it:values)
			v+=it.get();
		coda.add(new Impiegati(key.getLeft().toString(),key.getRight().toString(),v));
		if(coda.size()>5)
			coda.remove();
	}
	
	
	public void cleanup(Context ctx)throws IOException,InterruptedException{
		List<Impiegati> list = new ArrayList<Impiegati>();
		while(!coda.isEmpty())
			list.add(coda.remove());
		for(int i=topK-1;i>=0;i--){
			Impiegati tmp = list.get(i);
			ctx.write(new Text(tmp.getDepartimento()), new Text(tmp.getImpiegato()));
		}
	}

}
