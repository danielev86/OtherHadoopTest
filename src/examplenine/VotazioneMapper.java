package examplenine;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.*;
public class VotazioneMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
	
	
	public void map(LongWritable key,IntWritable value,Context ctx)throws IOException,InterruptedException{
		String[] tmp = value.toString().split("\t");
		ctx.write(new Text(tmp[0]), new IntWritable(Integer.parseInt(tmp[1])));
	}

}
