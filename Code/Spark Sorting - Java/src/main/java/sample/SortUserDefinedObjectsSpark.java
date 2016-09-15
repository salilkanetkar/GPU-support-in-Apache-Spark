package sample;
import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

class Student implements Serializable, Comparable<Student>
{
	private static final long serialVersionUID = 1L;
	int id;
	String name;
	Student(int p1, String p2) 
	{
		id = p1;
		name = p2;
	}
	@Override
	public int compareTo(Student student) 
	{  
		return (this.id < student.id) ? -1: (this.id > student.id) ? 1:0 ;  
	}
}

public class SortUserDefinedObjectsSpark 
{
	public static void main(String[] args) 
	{
		SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]");
	    JavaSparkContext sc = new JavaSparkContext(conf);
	    for(int it=1000000;it<=9000000;it=it+1000000)
	    {	
		    long totaltime = 0;
		    for(int k=0;k<100;k++)
		    {
			    RandomStringUtils ran = new RandomStringUtils();
				Random randomGenerator = new Random();
				int randomInt,randomInt1 = 0;
				ArrayList<Student> list = new ArrayList<Student>();
				for (int i=0; i<it; i++)
			    {
			    	randomInt = randomGenerator.nextInt(20);
			    	randomInt1 = randomGenerator.nextInt(200);
			    	list.add(new Student(randomInt,ran.randomAlphabetic(randomInt))); //Create a random list of Students
			    }
				JavaRDD<Student> unsortedRDD = sc.parallelize(list); //Convert the list into a RDD
			    long startTime = System.currentTimeMillis();
			    JavaRDD<Student> sortedRDD = unsortedRDD.sortBy(new Function<Student,Student>() 
			    {
			  	  private static final long serialVersionUID = 1L;
			  	  @Override
			  	  public Student call(Student value) throws Exception 
			  	  {
			  	    return value;
			  	  }
			  	}, true, 1 ); //Sort the RDD
			    long endTime   = System.currentTimeMillis();
			    if(k != 0)
			    {	
			    	totaltime = totaltime + (endTime - startTime);
			    }
		    }    
		    //System.out.println("Average sorting time for "+it+" student values is "+(totaltime/100.0));
		    System.out.println((totaltime/100.0));
	   }    
//	    for(Student s : sortedRDD.collect())
//	    {
//	    	System.out.println("Student ID is "+s.id+" and name is "+s.name+".");
//	    }
	}
}
