package examplenine;
import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class VotazioneJob extends Configured implements Tool{
	public int run(String[] args)throws IOException,InterruptedException,Exception{
		Job job = new Job(getConf(),"VotazioneJob");
		job.setJarByClass(VotazioneJob.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapperClass(VotazioneMapper.class);
		job.setReducerClass(VotazioneReducer.class);
		return job.waitForCompletion(true)? 0:1;
	}
	
	public static void main(String[] args)throws Exception{
		ToolRunner.run(new VotazioneJob(), args);
	}

}
