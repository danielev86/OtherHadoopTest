package exampleeight;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.util.*;
public class RecitazioneReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable> {
	
	private PriorityQueue<Attori> coda;
	private static final int topK=10;
	
	public void setup(Context ctx){
		coda = new PriorityQueue<Attori>(new Comparator<Attori>(){
			public int compare(Attori a, Attori b){
				return a.getValutazione().compareTo(b.getValutazione());
			}
		});
	}
	
	
	
	public void reduce(Text key,Iterable<DoubleWritable> values, Context ctx)throws IOException,InterruptedException{
		double value = 0.0;
		for(DoubleWritable it:values)
			value+=it.get();
		coda.add(new Attori(key.toString(),value));
		if(coda.size()>topK)
			coda.remove();
	}
	
	
	public void cleanup(Context ctx)throws IOException,InterruptedException{
		List<Attori> list = new ArrayList<Attori>();
		while(!coda.isEmpty())
			list.add(coda.remove());
		for(int i=topK-1;i>=0;i--){
			Attori tmp = list.get(i);
			ctx.write(new Text(tmp.getLastname()), new DoubleWritable(tmp.getValutazione()));
		}
	}

}
