package exampleeight;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.*;
import java.lang.Double;
public class RecitazioneMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{
	
	
	public void map(LongWritable key, Text value,Context ctx)throws IOException,InterruptedException{
		String[] tmp = value.toString().split(",");
		ctx.write(new Text(tmp[0]), new DoubleWritable(Double.parseDouble(tmp[1])));
	}

}
