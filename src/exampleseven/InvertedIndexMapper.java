package exampleseven;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.*;
import java.util.*;

public class InvertedIndexMapper extends Mapper<LongWritable,Text,Text,Text>{
	private static final Text indice=new Text();
	public void map(LongWritable key,Text value,Context ctx)throws InterruptedException, IOException{
		
		String[] tmp = value.toString().split("\t");
		indice.set(tmp[0]);
		
		StringTokenizer tokenString = new StringTokenizer(tmp[1]);
		
		while(tokenString.hasMoreTokens()){
			String partOne = tokenString.nextToken();
			ctx.write(new Text(partOne), indice);
		}

		
		
	}
	

}
