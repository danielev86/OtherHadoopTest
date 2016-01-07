package examplefour;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;
import java.io.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.fs.*;
public class ImpiegatiJob extends Configured implements Tool {
	public int run(String[] args)throws IOException,ClassNotFoundException,InterruptedException{
		Job job = new Job(getConf(),"ImpiegatiJob");
		job.setJarByClass(ImpiegatiJob.class);
		job.setMapOutputKeyClass(BigramWritable.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		job.setMapperClass(ImpiegatiMapper.class);
		job.setReducerClass(ImpiegatiReducer.class);
		return job.waitForCompletion(true)?0:1;
		
	}
	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		ToolRunner.run(new ImpiegatiJob(), args);

	}

}
