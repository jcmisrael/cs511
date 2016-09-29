// Client.java
// Implementation for client

package edu.stevens.jisrael.cs511.assignment2;

import edu.stevens.jisrael.cs511.assignment2.containers.Exercise;
import java.util.Random;
import java.util.ArrayList;
import java.util.Map;

public class Client implements Runnable {
    private int id;
    private ArrayList<Exercise> routine;
    private Gym gym;

    public Client(int _id, Gym _gym){
        this.id = _id;
        this.gym = _gym;
        this.routine = new ArrayList<Exercise>();
    }

    public void addExercise(Exercise e){
        routine.add(e);
    }

    public void run(){
        for(Exercise e: routine){
            try{
                System.out.println("Client " + this.id + " grabbing weights");
                gym.grabWeights(e.weights);
                System.out.println("Client " + this.id + " grabbed weights");
                System.out.println("Client " + this.id + " acquiring apparatuses");
                gym.apparatuses[e.at.index].acquire();
                System.out.println("Client " + this.id + " working out");
                Thread.sleep(e.duration);
                System.out.println("Client " + this.id + " done working out");
                gym.apparatuses[e.at.index].release();
                gym.releaseWeights(e.weights);
            } catch (Exception ec){
                ec.printStackTrace();
            }
        }
    }

    private static final Random RANDOM = new Random();
    public static Client generateRandom(int _id, Gym _gym){
        Client c = new Client(_id, _gym);
        int numExercises = RANDOM.nextInt(5) + 15;
        int i;

        for(i = 0; i < numExercises; ++i){
            c.addExercise(Exercise.generateRandom());
        }
        return c;
    }
}
