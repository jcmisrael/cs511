// Assignment2.java
// Entrypoint for Assignment2

package edu.stevens.jisrael.cs511.assignment2;

/** Start the simulation */
public class Assignment2 {
    public static void main(String[] args){
        Thread thread = new Thread(new Gym());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        } 
    }
}
