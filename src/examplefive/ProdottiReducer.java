package examplefive;
import java.io.*;
import java.util.*;
import org.apache.hadoop.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class ProdottiReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable>{
	private static final int topK =3;
	private PriorityQueue<PairProdotti> coda;
	public void setup(Context ctx){
		
		coda = new PriorityQueue<PairProdotti>(new Comparator<PairProdotti>(){
			public int compare(PairProdotti p1,PairProdotti p2){
				return p1.getValue().compareTo(p2.getValue());
			}
		});
		
	}
	
	public void reduce(Text key, Iterable<DoubleWritable> values,Context ctx)throws IOException,InterruptedException{
		Double v = 0.0;
		for(DoubleWritable it:values)
			v += it.get();
		coda.add(new PairProdotti(key.toString(),v));
		if(coda.size()>3)
			coda.remove();
		
	}
	
	
	public void cleanup(Context ctx)throws IOException,InterruptedException{
		List<PairProdotti> list = new ArrayList<PairProdotti>();
		while(!coda.isEmpty())
			list.add(coda.remove());
		for(int i=topK-1;i>=0;i--){
			PairProdotti tmp = list.get(i);
			ctx.write(new Text(tmp.getProdotto()), new DoubleWritable(tmp.getValue()));
		}
			
	}

}
