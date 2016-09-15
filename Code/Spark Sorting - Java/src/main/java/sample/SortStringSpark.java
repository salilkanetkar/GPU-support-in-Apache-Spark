package sample;

import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class SortStringSpark 
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
				int randomInt = 0;
			    ArrayList<String> list = new ArrayList<String>();
			    for (int i=0; i<it; i++)
			    {
			    	randomInt = randomGenerator.nextInt(20);
			    	list.add(ran.randomAlphanumeric(randomInt)); //Create a random list of Strings
			    }
			    JavaRDD<String> unsortedRDD = sc.parallelize(list); //Convert the list into a RDD
			    long startTime = System.currentTimeMillis();
			    JavaRDD<String> sortedRDD = unsortedRDD.sortBy(new Function<String,String>() 
			    {
			  	  private static final long serialVersionUID = 1L;
			  	  @Override
			  	  public String call(String value) throws Exception 
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
		    //System.out.println("Average sorting time for "+it+" string values is "+(totaltime/100.0));
		    System.out.println((totaltime/100.0));
	    }    
//	    for(String in : sortedRDD.collect())
//	    {
//	    	System.out.println(in);
//	    }
	}
}
