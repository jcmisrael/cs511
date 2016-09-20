// AssignmentOne.java

import java.util.ArrayList;
import java.util.Arrays;

/**
 * AssignmentOne parses input as interval pairs, finds primes within those pairs,
 * then prints out a list of all primes. Prime finding is done using threads.
 *
 * @author: Jake Israel
 * @version: 1.0
 * @since: 2016-09-08
 */
public class AssignmentOne {

    /**
     * Finds and returns an ordered list of prime numbers inbetween the passed
     * Integer intervals.
     *
     * @param intervals A list of Integer pairs representing intervals
     * @return An array list of primes found between the passed intervals
     */
    public ArrayList<Integer> lprimes(ArrayList<Integer[]> intervals){
        int i;
        Integer[] interval;

        // Lists for threads, and shared memory result arrays
        ArrayList<Thread> threads = new ArrayList<Thread>();
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> result = new ArrayList<Integer>();

        // reate and start threads
        for(i = 0; i < intervals.size(); ++i){
            results.add(new ArrayList<Integer>());
            interval = intervals.get(i);
            threads.add(new Thread(new PrimeFinder(interval[0],
                                                   interval[1],
                                                   results.get(i))));
        }
        for(Thread t : threads)
            t.start();

        // Wait for threads to terminate
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        // Join results
        for(ArrayList<Integer> r : results)
            result.addAll(r);
        return result;
    }

    /**
     * main function
     *
     * @param args Input, should be interval pairs
     */
    public static void main(String[] args){
        AssignmentOne primes = new AssignmentOne();
        ArrayList<Integer[]> input = new ArrayList<Integer[]>();
        ArrayList<Integer> results = new ArrayList<Integer>();
        int i;
        Integer[] interval;
        Integer lbound;
        Integer rbound;

        if(args.length <= 1){
            System.out.println("Please enter valid input.");
        } else {
            try {
                for(i = 0; i < args.length / 2; ++i){
                    lbound = Integer.valueOf(args[i * 2]);
                    rbound = Integer.valueOf(args[(i * 2) + 1]);

                    if(lbound < 0 ||
                       rbound < 0 ||
                       rbound < lbound){
                        System.out.println("Please enter valid input in the form of positive integer pairs.");
                        System.exit(1);
                    }
                    interval = new Integer[]{Integer.valueOf(args[i*2]),
                                             Integer.valueOf(args[(i*2) + 1])};
                    input.add(interval);
                }

                results = primes.lprimes(input);

                for(Integer prime : results)
                    System.out.print(prime.toString() + " ");

            } catch (NumberFormatException e) {
                System.out.println("Error, invalid input. Only positive integers are accepted.");
                e.printStackTrace();
            }
        }
    }
}
