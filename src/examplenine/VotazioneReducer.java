package examplenine;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.*;
import java.util.*;
public class VotazioneReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	
	private PriorityQueue<VotoEsame> coda;
	private static final int topK=10;
	
	
	public void setup(Context ctx){
		coda = new PriorityQueue<VotoEsame>(new Comparator<VotoEsame>(){
			public int compare(VotoEsame a, VotoEsame b){
				return a.getVoto().compareTo(b.getVoto());
			}
		});
	}
	
	
	public void reduce(Text key, Iterable<IntWritable> values,Context ctx)throws IOException,InterruptedException{
		int value=0;
		for(IntWritable it:values)
			value+=it.get();
		coda.add(new VotoEsame(key.toString(),value));
		if(coda.size()>topK)
			coda.remove();
			
	}
	
	
	public void cleanup(Context ctx)throws IOException,InterruptedException{
		List<VotoEsame> list = new ArrayList<VotoEsame>();
		while(!coda.isEmpty())
			list.add(coda.remove());
		for(int i=topK-1;i>=0;i--){
			VotoEsame tmp = list.get(i);
			ctx.write(new Text(tmp.getMatricola()), new IntWritable(tmp.getVoto()));
		}
	}

}
