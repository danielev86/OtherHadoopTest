package examplethree;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class PlanetNameColor extends Configured implements Tool{
	
	public int run(String[] args)throws IOException, ClassNotFoundException,InterruptedException{
		Job job = new Job(getConf(),"PlanetNameColor");
		job.setJarByClass(examplethree.PlanetNameColor.class);
		
		FileInputFormat.addInputPath(job,new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapOutputKeyClass(BigramWritable.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		
		job.setMapperClass(PlanetNameColorMapper.class);
		job.setReducerClass(PlanetNameColorReduce.class);
		
		
		return job.waitForCompletion(true)?0:1;
	}
	
	public static void main(String[] args)throws Exception{
		ToolRunner.run(new PlanetNameColor(), args);
	}

}
