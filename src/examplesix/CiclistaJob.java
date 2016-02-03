package examplesix;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.*;

public class CiclistaJob extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ToolRunner.run(new CiclistaJob(), args);

	}

	@Override
	public int run(String[] args) throws Exception,IOException,ClassNotFoundException{
		Job job = new Job(getConf(),"CiclistaJob");
		job.setJarByClass(CiclistaJob.class);
		job.setMapOutputKeyClass(BigramWritable.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setMapperClass(CiclistaMapper.class);
		job.setReducerClass(CiclistaReducer.class);
		return job.waitForCompletion(true)?0:1;
	}

}
