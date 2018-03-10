package assessment.question3;

import java.time.Instant;
import java.util.Date;

/**
 * Question 3:
 * Design a comprehensive timing measure tool, which can be used to measure time 
 * consumed for any independent method in other class.
 * @author Ginny
 *
 */
public class TimingTool {
	
	private static long startTime = 0;
	private static long endTime = 0;
	private static long executionTime = 0;
	
	public static void start() {
		startTime = Instant.now().toEpochMilli();
		System.out.println("Start time: " + new Date(startTime));
	}
	
	public static void stop() {
		endTime = Instant.now().toEpochMilli();
		System.out.println("End time: " + new Date(endTime));
		executionTime();
	}
	
	public static void executionTime() {
		executionTime = endTime - startTime;
		System.out.println("Elapsed time in milliseconds: " + executionTime);
	}
}
