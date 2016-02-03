package exampleseven;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.util.*;
import java.io.*;
public class InvertedIndexReducer extends Reducer<Text,Text,Text,Text>{
	private static final String sep = ",";
	public void reduce(Text key, Iterable<Text> value, Context ctx)throws IOException, InterruptedException{
		StringBuilder list = new StringBuilder();
		boolean firstValue = true;
		for(Text it:value){
			
			if(!firstValue)
				list.append(sep);
			list.append(it.toString());
			
		}
		
		ctx.write(key, new Text(list.toString()));
	}

}
