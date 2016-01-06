package examplethree;
import org.apache.hadoop.io.*;
import java.io.*;
import org.apache.hadoop.mapreduce.*;
import examplethree.BigramWritable;
public class PlanetNameColorMapper extends Mapper<LongWritable,Text,BigramWritable,DoubleWritable>{
	private static final BigramWritable bw = new BigramWritable();
	public void map(LongWritable key, Text value,Context ctx) throws IOException,InterruptedException{
		String line = value.toString();
		String[] tmp = line.split(",");
		bw.set(new Text(tmp[0]),new Text(tmp[1]));
		ctx.write(bw, new DoubleWritable(Double.parseDouble(tmp[3])));
	}
}
