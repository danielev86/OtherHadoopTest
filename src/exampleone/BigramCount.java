package exampleone;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.fs.*;
public class BigramCount extends Configured implements Tool{
	
	public int run(String[] args)throws IOException, ClassNotFoundException, InterruptedException{
		Job job = new Job(getConf(),"BigramCount");
		job.setJarByClass(exampleone.BigramCount.class);
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapOutputKeyClass(BigramWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(BigramCountMapper.class);
		job.setReducerClass(BigramCountReducer.class);
		
		
		
		return job.waitForCompletion(true)?0:1;
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ToolRunner.run(new BigramCount(), args);

	}

}
