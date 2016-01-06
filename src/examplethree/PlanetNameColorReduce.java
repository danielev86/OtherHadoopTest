package examplethree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class PlanetNameColorReduce extends Reducer<BigramWritable,DoubleWritable,Text,DoubleWritable>{
	private PriorityQueue<Tripla> list_p;
	private static final int topK = 10;
	public void setup(Context ctx){
		list_p = new PriorityQueue<Tripla>(10,new Comparator<Tripla>(){
			public int compare(Tripla t1,Tripla t2){
				return t1.getValore().compareTo(t2.getValore());
			}
		});
	}
	
	public void reduce(BigramWritable key, Iterable<DoubleWritable> values,Context ctx)throws IOException, InterruptedException{
		Double val = 0.0;
		for(DoubleWritable it:values)
			val+=it.get();
		list_p.add(new Tripla(key.getLeft().toString(),key.getRight().toString(),val));
		if(list_p.size()>topK)
			list_p.remove();
		
	}
	
	public void cleanup(Context ctx)throws IOException,InterruptedException{
		List<Tripla> tmp = new ArrayList<Tripla>();
		while(!list_p.isEmpty())
			tmp.add(list_p.remove());
		for(int i=topK-1;i>=0;i--){
			Tripla tripla = tmp.get(i);
			ctx.write(new Text(tripla.getPrimo()+","+tripla.getSecondo()), new DoubleWritable(tripla.getValore()));
		}
	}
}