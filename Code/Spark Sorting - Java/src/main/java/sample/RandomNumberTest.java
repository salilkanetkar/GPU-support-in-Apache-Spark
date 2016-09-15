package sample;
import org.apache.commons.lang.RandomStringUtils;
import java.util.Random;
public class RandomNumberTest 
{
	 public static void main(String[] args)
	 {
		 RandomStringUtils ran = new RandomStringUtils();
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(20);
		 String x = ran.randomAlphanumeric(randomInt);
		 System.out.println(x);
	 }
}
