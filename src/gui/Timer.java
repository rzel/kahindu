package gui;
//import java.io.*;
 
public class Timer {
        private long startTime = 0;
        private long endTime = 0;
        private double elapsedTime = 0;
        private boolean stopped = true;
        
        public void start() {
        	stopped = false;
            startTime = System.currentTimeMillis();
        }
        public void stop() {
            if (stopped) return;
            stopped = true;
        	endTime = System.currentTimeMillis();
        	elapsedTime = (endTime - startTime)/1000.0;
        }
        public double getTime() {
        	stop();
        	return elapsedTime;
        }
        public double getElapsedTime() {
        	return (System.currentTimeMillis() - startTime)/1000.0;
        }
        public void print(int N, String message) {
                
                System.out.println(
                        message 
                        +" "
                        + getTime() + " seconds "
                        + N/getTime()+ "  per second");
                start();
        }
        public void print(String message) {
                
                System.out.println(
                        message 
                        +" "
                        + getTime() + " seconds "
                        );
                start();
        }
                
}
