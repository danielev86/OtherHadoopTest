package exampletwo;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.*;

public class UserVisitMapper extends Mapper<LongWritable,Text,Text,Text>{
	
	public void map(LongWritable key,Text value,Context ctx)throws IOException,InterruptedException{
		String line = value.toString();
		String[] array = line.split("\t");
		ctx.write(new Text(array[0]), new Text(array[1]));
		
	}

}
