package examplefour;
import java.io.*;

import org.apache.hadoop.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class BigramWritable implements WritableComparable<BigramWritable>{
	
	private Text left;
	private Text right;
	
	public BigramWritable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Text getLeft() {
		return left;
	}



	public void setLeft(Text left) {
		this.left = left;
	}



	public Text getRight() {
		return right;
	}



	public void setRight(Text right) {
		this.right = right;
	}



	public BigramWritable(Text left,Text right){
		this.left=left;
		this.right=right;
	}
	
	
	public void readFields(DataInput in)throws IOException{
		left = new Text(in.readUTF());
		right = new Text(in.readUTF());
	}
	
	public void write(DataOutput out)throws IOException{
		out.writeUTF(left.toString());
		out.writeUTF(right.toString());
	}
	
	public void set(Text l, Text r){
		left = l;
		right = r;
	}
	
	
	public int hashCode(){
		return left.hashCode()+right.hashCode();
	}
	
	public boolean equals(Object o){
		if(o instanceof BigramWritable){
			BigramWritable bw = (BigramWritable) o;
			return left.equals(bw.left) && right.equals(bw.right);
		}else return false;
	}
	
	public int compareTo(BigramWritable b){
		int cmp = left.compareTo(b.left);
		if(cmp>0)
			return cmp;
		else return right.compareTo(b.right);
	}

}
