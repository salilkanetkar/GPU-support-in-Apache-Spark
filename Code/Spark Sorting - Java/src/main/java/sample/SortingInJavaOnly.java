package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

public class SortingInJavaOnly 
{
	public static void main(String args[])
	{
//		//For sorting floating point values
//		for(int it=9000000;it<=9000000;it=it+1000000)
//	    {	
//		    long totaltime = 0;
//		    for(int k=0;k<100;k++)
//		    {	
//			    Random random= new Random();
//			    ArrayList<Double> list = new ArrayList<Double>();
//			    for (int i=0; i<it; i++)
//			    {
//			    	list.add((double)(random.nextDouble())); //Create a random list of Doubles
//			    }
//			    long startTime = System.currentTimeMillis();
//			    Collections.sort(list);
//			    long endTime   = System.currentTimeMillis();
//			    totaltime = totaltime + (endTime - startTime);
//		    }    
//		    //System.out.println("Sorting time"+(totaltime/100.0));
//		    System.out.println("Average sorting time for "+it+" floating values is "+(totaltime/100.0));  
//	   }
	
		//For sorting integers
		for(int it=1000000;it<=9000000;it=it+1000000)
	    {	
		    long totaltime = 0;
		    for(int k=0;k<100;k++)
		    {	
			    Random random= new Random();
			    ArrayList<Integer> list = new ArrayList<Integer>();
			    for (int i=0; i<it; i++)
			    {
			    	list.add((int)(random.nextInt(1000000))); //Create a random list of Doubles
			    }
			    long startTime = System.currentTimeMillis();
			    Collections.sort(list);
			    long endTime   = System.currentTimeMillis();
			    totaltime = totaltime + (endTime - startTime);
		    }    
		    //System.out.println("Sorting time"+(totaltime/100.0));
		    System.out.println("Average sorting time for "+it+" integer values is "+(totaltime/100.0));  
	   }
	
	}
}
