package examplesix;
import org.apache.hadoop.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.*;
public class CiclistaMapper extends Mapper<LongWritable,Text,BigramWritable,DoubleWritable>{
	
	private static BigramWritable bigram = new BigramWritable();
	
	
	public void map(LongWritable key,Text value,Context ctx)throws IOException, InterruptedException{
		String tmp = value.toString();
		String[] split =  tmp.split(",");
		bigram.set(new Text(split[0]), new Text(split[1]));
		ctx.write(bigram, new DoubleWritable(Double.parseDouble(split[2])));
	}

}
