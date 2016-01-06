package exampleone;
import org.apache.hadoop.*;
import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class BigramWritable implements WritableComparable<BigramWritable> {
	
	private Text left;
	private Text right;
	
	public BigramWritable(){
		
	}
	
	
	public BigramWritable(Text l, Text r){
		left = l;
		right = r;
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
			BigramWritable tmp = (BigramWritable) o;
			return left.equals(tmp.left) && right.equals(tmp.right);
		}
		return false;
	}
	
	public String toString(){
		return left.toString()+","+right.toString();
	}
	
	public int compareTo(BigramWritable b){
		int cmp = left.compareTo(b.left);
		if(cmp != 0)
			return cmp;
		return right.compareTo(b.right);
	}

}
