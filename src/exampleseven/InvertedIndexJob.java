package exampleseven;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import java.io.*;

public class InvertedIndexJob extends Configured implements Tool{
	
	public int run(String[] args)throws InterruptedException, IOException, ClassNotFoundException{
		Job job = new Job(getConf(),"InvertedIndexJob");
		job.setJarByClass(InvertedIndexJob.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		job.setMapperClass(InvertedIndexMapper.class);
		job.setReducerClass(InvertedIndexReducer.class);
		
		return job.waitForCompletion(true)? 0:1;
	}
	

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ToolRunner.run(new InvertedIndexJob(), args);

	}

}
