package sample;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import java.util.ArrayList;
import java.util.Random;

public class SortFloatSpark 
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
			    Random random= new Random();
			    ArrayList<Double> list = new ArrayList<Double>();
			    for (int i=0; i<it; i++)
			    {
			    	list.add((double)(random.nextDouble())); //Create a random list of Doubles
			    }
			    JavaRDD<Double> unsortedRDD = sc.parallelize(list); //Convert the list into a RDD
			    long startTime = System.currentTimeMillis();
			    JavaRDD<Double> sortedRDD = unsortedRDD.sortBy(new Function<Double,Double>() 
			    {
			  	  private static final long serialVersionUID = 1L;
			  	  @Override
			  	  public Double call(Double value) throws Exception 
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
		    //System.out.println("Average sorting time for "+it+" floating values is "+(totaltime/100.0));
		    System.out.println((totaltime/100.0));
	   }    
//	    for(Double in : sortedRDD.collect())
//	    {
//	    	System.out.println(in);
//	    }
	}
}
