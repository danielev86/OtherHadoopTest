package examplefive;
import java.io.*;
import org.apache.hadoop.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
public class ProdottiJob extends Configured implements Tool{
	
	public int run(String[] args)throws IOException,ClassNotFoundException,InterruptedException{
		Job job = new Job(getConf(),"ProdottiJob");
		job.setJarByClass(ProdottiJob.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(ProdottiMapper.class);
		job.setReducerClass(ProdottiReducer.class);

		return job.waitForCompletion(true)?0:1;
	}
	
	public static void main(String[] args)throws Exception{
		ToolRunner.run(new ProdottiJob(), args);
		
	}

}
