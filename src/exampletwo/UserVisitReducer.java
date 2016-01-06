package exampletwo;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class UserVisitReducer extends Reducer<Text,Text,Text,IntWritable>{
	
	public void reduce(Text key, Iterable<Text> values,Context ctx)throws IOException, InterruptedException{
		int valore = 0;
		for(Text it:values){
			valore++;
		}
		ctx.write(key, new IntWritable(valore));
	}

}
