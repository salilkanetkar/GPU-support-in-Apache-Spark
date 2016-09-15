package sample;
import scala.Tuple2;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class SimpleApp {
public static void main(String[] args) {
    String logFile = "C:/Users/Salil Kanetkar/Desktop/sample.txt"; // Read a file of integers!
    SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> logData = sc.textFile(logFile).cache();
    //String scriptPath = new String("H:/UCLA Spring 2016/249 - Cloud Computing/Project/GPUCodes/CS249Cloud/sort.exe");  
    //JavaRDD<String> return_pipeRDD = logData.pipe(scriptPath);
    //List<String> results = return_pipeRDD.collect();
    JavaRDD<String> sortedRDD = logData.sortBy( new Function<String,String>() {
  	  private static final long serialVersionUID = 1L;
  	  @Override
  	  public String call( String value ) throws Exception {
  	    return value;
  	  }
  	}, true, 1 );
    for(String str : sortedRDD.collect())
    {
    	System.out.println(str);
    }
  }
}

/*
Execution -
mvn package
~/Spark/spark-1.6.1/bin/spark-submit --class "SimpleApp" --master local[4] target/simple-project-1.0.jar
*/
//sortedRDD.collect().foreach(println);
//sortedRDD.foreachPartition(new FlatMapFunction<String,Iterator<String>>() {
//
//  public void call(Iterator<String> arg0) throws Exception {
//
//      while(arg0.hasNext()) {
//          System.out.println(arg0.next());
//      }
//
//  }
//});
