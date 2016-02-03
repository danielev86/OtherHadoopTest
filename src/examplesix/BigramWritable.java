package examplesix;

import org.apache.hadoop.io.*;
import java.io.*;

public class BigramWritable implements WritableComparable<BigramWritable> {
	public Text left;
	public Text right;
	
	
	public void readFields(DataInput in)throws IOException{
		left = new Text(in.readUTF());
		right = new Text(in.readUTF());
		
	}
	
	
	public void write(DataOutput out)throws IOException{
		out.writeUTF(left.toString());
		out.writeUTF(right.toString());
	}
	
	public void set(Text left, Text right){
		this.left=left;
		this.right=right;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigramWritable other = (BigramWritable) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}


	public int compareTo(BigramWritable bw){
		int cmp = left.compareTo(bw.left);
		if(cmp!=0) return cmp;
		else return right.compareTo(bw.right);
		
	}




}
