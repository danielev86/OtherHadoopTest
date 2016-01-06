package exampleone;
import org.apache.hadoop.io.*;
import java.io.*;
import java.util.StringTokenizer;

import org.apache.hadoop.mapreduce.*;
public class BigramCountMapper extends Mapper<LongWritable,Text,BigramWritable,IntWritable>{
	
	private static final BigramWritable bw = new BigramWritable();
	private static final IntWritable one = new IntWritable(1);
	
	
	public void map(LongWritable key, Text value,Context ctx) throws IOException, InterruptedException{
		String line = value.toString();
		String prev = null;
		StringTokenizer tokens = new StringTokenizer(line);
		while(tokens.hasMoreTokens()){
			String curr = tokens.nextToken();
			if(prev !=null){
				bw.set(new Text(prev), new Text(curr));
				ctx.write(bw, one);
			}
			prev = curr;
		}
	}

}
