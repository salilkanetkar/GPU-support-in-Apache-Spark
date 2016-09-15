import scala.Tuple2;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import java.util.ArrayList;
import java.io.Serializable;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

class Student implements Serializable
{
  int id;
  String name;
  Student(int p1, String p2) {
    id = p1;
    name = p2;
  }
}

class GPUIntSorter {

  /* Sort Function for Vector of Objects with associated Integer Sort Key */
  public <T> JavaRDD<T> sort(JavaRDD<T> logData, List<Integer> sort_key, JavaSparkContext sc) {
    Map<Integer, ArrayList<Integer>> sort_keys2index = new HashMap<Integer, ArrayList<Integer>>();
    int size = sort_key.size();
    int val;
    for(int i = 0; i < size; i++) {
      val = sort_key.get(i);
      if(sort_keys2index.get(val) == null)  // To deal with duplicate elements
      {
        ArrayList<Integer> x = new ArrayList<Integer>();
        x.add(i);
        sort_keys2index.put(sort_key.get(i), x);
      }
      else {
        ArrayList<Integer> x = sort_keys2index.get(val);
        x.add(i);
        sort_keys2index.put(sort_key.get(i), x);
      }
    }
    JavaRDD<Integer> keyData = sc.parallelize(sort_key);
    String scriptPath = new String("../Desktop/College/CS249Cloud/sort_ints");
    JavaRDD<String> return_pipeRDD = keyData.pipe(scriptPath);
    List<String> key_results = return_pipeRDD.collect();
    List<T> results = new ArrayList<T>();
    List<T> original = logData.collect();
    String prev_key = ""; // Handle duplicates
    for(String key : key_results) {
      if(key.equals(prev_key))
        continue;
      val = Integer.parseInt(key);
      ArrayList<Integer> x = sort_keys2index.get(val);
      for (int index : x) {
        results.add(original.get(index));
      }
      prev_key = key;
    }
    JavaRDD<T> RDDresults = sc.parallelize(results);
    return RDDresults;
  }

    /* Sort Function for Simple Vector of Ints */
    public <T> JavaRDD<String>  sort_nokey(JavaRDD<T> logData) {
      String scriptPath = new String("../Desktop/College/CS249Cloud/sort_ints");
      JavaRDD<String> return_pipeRDD = logData.pipe(scriptPath);
      return return_pipeRDD;
    }
}

class GPUFloatSorter {

    /* Sort Function for Vector of Objects with associated Float Sort Key */
    public <T> JavaRDD<T> sort(JavaRDD<T> logData, List<Float> sort_key, JavaSparkContext sc) {
      Map<Float, ArrayList<Integer>> sort_keys2index = new HashMap<Float, ArrayList<Integer>>();
      int size = sort_key.size();
      float val;
      for(int i = 0; i < size; i++) {
        val = sort_key.get(i);
        if(sort_keys2index.get(val) == null)  // To deal with duplicate elements
        {
          ArrayList<Integer> x = new ArrayList<Integer>();
          x.add(i);
          sort_keys2index.put(sort_key.get(i), x);
        }
        else {
          ArrayList<Integer> x = sort_keys2index.get(val);
          x.add(i);
          sort_keys2index.put(sort_key.get(i), x);
        }
      }
      JavaRDD<Float> keyData = sc.parallelize(sort_key);
      String scriptPath = new String("../Desktop/College/CS249Cloud/sort_floats");
      JavaRDD<String> return_pipeRDD = keyData.pipe(scriptPath);
      List<String> key_results = return_pipeRDD.collect();
      List<T> results = new ArrayList<T>();
      List<T> original = logData.collect();
      String prev_key = "";
      for(String key : key_results) {
        if(key.equals(prev_key))
          continue;
        val = Float.parseFloat(key);
        ArrayList<Integer> x = sort_keys2index.get(val);
        for (int index : x) {
          results.add(original.get(index));
        }
        prev_key = key;
      }
      JavaRDD<T> RDDresults = sc.parallelize(results);
      return RDDresults;
    }

    /* Sort Function for Simple vector of Floats! */
    public <T> JavaRDD<String> sort_nokey(JavaRDD<T> logData) {
      String scriptPath = new String("../Desktop/College/CS249Cloud/sort_floats");
      JavaRDD<String> return_pipeRDD = logData.pipe(scriptPath);
      return return_pipeRDD;
    }
}

public class SimpleApp {

  public void display(JavaRDD<String> data) {
    List<String> results = data.collect();
    for(String std : results) {
      System.out.println(std);
    }
  }

  public void display2(JavaRDD<Student> data) {
    List<Student> results = data.collect();
    for(Student std : results) {
      System.out.println(std.name + " " + std.id);
    }
  }

  public static void main(String[] args) {
    SimpleApp s = new SimpleApp();
    String logFile = "../sample.txt"; // Read a file of integers!
    SparkConf conf = new SparkConf().setAppName("Simple Application");
    JavaSparkContext sc = new JavaSparkContext(conf);

    /* Create list of data */
    // JavaRDD<String> logData = sc.textFile(logFile).cache();
    List<Integer> sort_key = new ArrayList<Integer>();
    sort_key.add(5); //100
    sort_key.add(4); //90
    sort_key.add(3); //80
    sort_key.add(2); //70
    sort_key.add(1); //60
    sort_key.add(11); //5012.111
    sort_key.add(122); //40
    sort_key.add(9); //30
    sort_key.add(2); //20.33
    sort_key.add(3); //10.5334
    List<Student> data = new ArrayList<Student>();
    data.add(new Student(1, "Suket1"));
    data.add(new Student(2, "Suket2"));
    data.add(new Student(3, "Suket3"));
    data.add(new Student(4, "Suket4"));
    data.add(new Student(5, "Suket5"));
    data.add(new Student(6, "Suket6"));
    data.add(new Student(7, "Suket7"));
    data.add(new Student(8, "Suket8"));
    data.add(new Student(9, "Suket9"));
    data.add(new Student(10, "Suket10"));
    JavaRDD<Student> logData = sc.parallelize(data);
    /* Sort Data */
    GPUIntSorter gpu = new GPUIntSorter();
    logData = gpu.sort(logData, sort_key, sc);
    s.display2(logData);
  }

}

/*
Execution -
mvn package
~/Spark/spark-1.6.1/bin/spark-submit --class "SimpleApp" --master local[4] target/simple-project-1.0.jar
*/
