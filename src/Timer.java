/*
 * Task 1,2,3,4
 * Name: H.H.Welgama
 * IIT Number: 20221109
 * UOW Number: w1998723
 * I confirm that I understand what plagiarism / collusion /
 * contract cheating is and have read and understood
 * the section on Assessment Offences in the Essential Information for Students.
 * The work that I have submitted is entirely my own.
 * Any work from other authors is duly referenced and acknowledged.
 */
import java.util.concurrent.TimeUnit;

/**
 * Class to measure elapsed time.
 */
public class Timer {

    private long startTime;
    private long stopTime;
    private long elapsedTime;

    //Method to start the timer.
    public void start() {
        startTime = System.currentTimeMillis();
    }

    //Method to stop the timer.
    public void stop() {
        stopTime = System.currentTimeMillis();
    }

    //Method to calculate the elapsed time.
    public long time() {
        elapsedTime = stopTime - startTime;
        return (elapsedTime);
    }
    public String toString() {
        return "Elapsed time in milliseconds: " + time() + " milliseconds.\n" +
                "Elapsed time in seconds: " + TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.MILLISECONDS) + " seconds.\n" +
                "Elapsed time in minutes: " + TimeUnit.MINUTES.convert(elapsedTime, TimeUnit.MILLISECONDS) + " minutes.";
    }
}
