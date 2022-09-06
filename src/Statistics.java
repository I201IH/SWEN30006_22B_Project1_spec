package src;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Statistics {
    private int score = 0;
    private int round = 1;
    private String difficulty = null;
    //score, each piece time, average score, difficulty level
    // Tetris class
    public Statistics(int score, String diff){
        this.score = score;
        this.difficulty = diff;
    }

    /** This method is used to store scores in a game
     *
     */

    public void storeScore(){

    }

    public void printStatistics(){
        try (PrintWriter pw =
                     new PrintWriter(new FileWriter("Statistics.txt"))) {

            pw.println("Difficulty: " + difficulty);
            pw.println("Average score per round: 1234");
            pw.println("------------------------------------------");
            pw.format("Round #" + round + "\n" +
                    "Score: %d\n", score);

        } catch (IOException e) {
            e.printStackTrace();
        }
        round++;

    }

}
