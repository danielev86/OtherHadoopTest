package exampleone;
import java.util.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class BigramCountReducer extends Reducer<BigramWritable,IntWritable,Text,IntWritable> {
	
	public void reduce(BigramWritable key, Iterable<IntWritable> values, Context ctx)throws IOException, InterruptedException{
		int sum = 0;
		for(IntWritable it:values)
			sum += it.get();
		ctx.write(new Text(key.toString()),new IntWritable(sum));
	}

}
