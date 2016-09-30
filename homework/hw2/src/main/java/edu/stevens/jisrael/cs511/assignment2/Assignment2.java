/**
 * Assignment2.java
 * Entrypoint for cs511's assignment2
 */
package edu.stevens.jisrael.cs511.assignment2;

/** Start the simulation */
public class Assignment2 {
    public static void main(String[] args){
        // Start the gym thread
        Thread thread = new Thread(new Gym());
        thread.start();
        // Wait for gym to finish or 
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
