package examplethree;
import org.apache.hadoop.io.*;
import java.io.*;
import org.apache.hadoop.mapreduce.*;
public class BigramWritable implements WritableComparable<BigramWritable>{
	
	
	private Text leftBigram;
	private Text rightBigram;
	
	
	
	public void readFields(DataInput in)throws IOException{
		leftBigram = new Text(in.readUTF());
		rightBigram = new Text(in.readUTF());
		
	}
	
	
	public void write(DataOutput out)throws IOException{
		out.writeUTF(leftBigram.toString());
		out.writeUTF(rightBigram.toString());
	}
	
	public void set(Text l, Text r){
		leftBigram = l;
		rightBigram = r;
	}
	
	public Text getLeft(){
		return leftBigram;
	}
	
	public Text getRight(){
		return rightBigram;
	}
	
	
	public int hashCode(){
		return leftBigram.hashCode()+rightBigram.hashCode();
	}
	
	
	public boolean equals(Object bw){
		if(bw instanceof BigramWritable){
			BigramWritable ow = (BigramWritable) bw;
			return leftBigram.equals(ow.leftBigram) && rightBigram.equals(ow.rightBigram);

		}
		return false;
	}
	
	
	public int compareTo(BigramWritable bw){
		int cmp = leftBigram.compareTo(bw.leftBigram);
		if(cmp!=0) return cmp;
		else return rightBigram.compareTo(bw.rightBigram);
		
	}

}
