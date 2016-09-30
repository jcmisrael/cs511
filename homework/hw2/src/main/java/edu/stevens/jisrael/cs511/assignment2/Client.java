/**
 * Client.java
 * Client implementation for assignment2. Performs a set routine at a gym
 */

package edu.stevens.jisrael.cs511.assignment2;

// Package imports
import edu.stevens.jisrael.cs511.assignment2.containers.Exercise;
import java.util.Random;
import java.util.ArrayList;
import java.util.Map;


/**
 * Class Client
 * Client implementation
 * @author Jake Israel
 * @version 1.0.0
 */
public class Client implements Runnable {
    private int id;
    private ArrayList<Exercise> routine;
    private Gym gym;

    /**
     * Constructor for Client object
     * @param _id The clients unique identifier number
     * @param _gym A reference to the gym object the client is running in
     */
    public Client(int _id, Gym _gym){
        this.id = _id;
        this.gym = _gym;
        this.routine = new ArrayList<Exercise>();
    }

    /**
     * This functions adds an exercise to the clients routine
     * @param e The exercise to be added
     */
    public void addExercise(Exercise e){
        routine.add(e);
    }

    /**
     * The run method for the clients
     * Performs a set of exercises in accordance with its routines
     * by grabbing the appropriate semaphores from the gym object
     */
    @Override
    public void run(){
        for(Exercise e: routine){
            try{
                System.out.println("Client " + this.id + " starting exercise: " + e);
                gym.grabWeights(e.weights);
                gym.apparatuses[e.at.index].acquire();
                Thread.sleep(e.duration);
                gym.apparatuses[e.at.index].release();
                gym.releaseWeights(e.weights);
                System.out.println("Client " + this.id + " finished exercise: " + e);
            } catch (Exception ec){
                ec.printStackTrace();
            }
        }
    }

    /**
     * Generates a random client with a given
     * unique client id
     * @return A randomly generated client
     */
    private static final int EXERCISE_RANGE = 5;
    private static final int EXERCISE_MINIMUM = 15;
    private static final Random RANDOM = new Random();
    public static Client generateRandom(int _id, Gym _gym){
        Client c = new Client(_id, _gym);
        int numExercises = RANDOM.nextInt(Client.EXERCISE_RANGE) + Client.EXERCISE_MINIMUM;
        int i;

        for(i = 0; i < numExercises; ++i){
            c.addExercise(Exercise.generateRandom());
        }
        return c;
    }
}
