package exampletwo;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class UserVisit extends Configured implements Tool{
	
	public int run(String[] args)throws IOException, ClassNotFoundException,InterruptedException{
		Job job = new Job(getConf(),"UserVisit");
		job.setJarByClass(exampletwo.UserVisit.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapperClass(UserVisitMapper.class);
		job.setReducerClass(UserVisitReducer.class);
		
		return job.waitForCompletion(true)?0:1;
	}
	
	public static void main(String[] args)throws Exception{
		
		ToolRunner.run(new UserVisit(), args);
		
	}
	

}
