// PrimeFinder.java

import java.util.ArrayList;

/**
 * Class PrimeFinder for multithreaded prime finding
 *
 * @author: Jake Israel
 * @version: 1.0
 * @since: 2016-09-09
 */
public class PrimeFinder implements Runnable {
    private Integer start;
    private Integer end;
    private ArrayList<Integer> primes;

    /**
     * Implemented from runnable interface
     */
    public void run(){
        int i;
        for(i = start; i < end; ++i){
            if(isPrime(i))
                primes.add(new Integer(i));
        }
    }

    /**
     * Class constructor passing a start and end integer, and a share memory object
     */
    public PrimeFinder(Integer _start, Integer _end, ArrayList<Integer> _primes){
        start = _start;
        end = _end;
        primes = _primes;
    }

    // Adapted directly from pseudocode at https://en.wikipedia.org/wiki/Primality_test#Pseudocode
    /**
     * Returns whether or not a passed integer is prime
     *
     * @param n A non-negative int
     * @returns Whether or not the number is prime
     */
    public boolean isPrime(int n){
        int i;
        if(n == 2 || n == 3){
            return true;
        } else if(n <= 1 ||
                  n % 2 == 0 ||
                  n % 3 == 0){
            return false;
        } else {
            for(i = 5; i*i <= n; i = i + 6){
                if(n % i == 0 || n % (i + 2) == 0)
                    return false;
            }
            return true;
        }
    }
}
