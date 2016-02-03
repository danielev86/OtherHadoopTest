package examplefive;
import org.apache.hadoop.*;
import org.apache.hadoop.io.*;
import java.io.*;
import org.apache.hadoop.mapreduce.*;
public class ProdottiMapper extends Mapper<LongWritable,Text,Text,DoubleWritable>{
	
	public void map(LongWritable key, Text value, Context ctx)throws IOException,InterruptedException{
		String line = value.toString();
		String[] tmp = line.split("\t");
		ctx.write(new Text(tmp[0]), new DoubleWritable(Double.parseDouble(tmp[2])));
	}

}
