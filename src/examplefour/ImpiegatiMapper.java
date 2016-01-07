package examplefour;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class ImpiegatiMapper extends Mapper<LongWritable,Text,BigramWritable,DoubleWritable>{
	
	private BigramWritable bigram = new BigramWritable();
	
	
	public void map(LongWritable key, Text value, Context ctx)throws IOException, InterruptedException{
		String lines = value.toString();
		String[] tmp = lines.split(",");
		bigram.set(new Text(tmp[0]), new Text(tmp[2]));
		ctx.write(bigram, new DoubleWritable(Double.parseDouble(tmp[1])));
	}

}
