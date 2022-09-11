package src;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Statistics {
    private int score = 0;
    private int round = 1;
    private double averageScore = 0;
    private String difficulty = null;
    private ArrayList<Integer> scoreList = new ArrayList<Integer>();
    private LinkedHashMap<String, Integer> count;
    private ArrayList<LinkedHashMap<String, Integer>> countList = new ArrayList<>();

    //private ArrayList<Integer> list = new ArrayList<Integer>();
    //score, each piece time, average score, difficulty level
    // Tetris class
    public Statistics(int score, String diff, LinkedHashMap<String, Integer> count) {
        this.score = score;
        this.difficulty = diff;
        this.count = count;
    }

    /**
     * This method is used to store scores in a game
     */
    public void
    storeScore(int score) {
        scoreList.add(score);
    }

    /**
     * This method is used to store counts of blocks in a game
     */
    public void
    storeCount(LinkedHashMap<String, Integer> count) {
        countList.add(count);
    }

    public void printStatistics(int score, LinkedHashMap<String, Integer> count) {
        try (PrintWriter pw =
                     new PrintWriter(new FileWriter("Statistics.txt"))) {
            storeScore(score);
            storeCount(count);
            pw.println("Difficulty: " + difficulty);
            averageScore = calculateAverageScore(scoreList);
            pw.println("Average score per round: " + averageScore);
            pw.println("------------------------------------------");

            //pw.format("Round #" + round + "\n" + "Score: %d\n", score);
            for (int i = 0; i < scoreList.size(); i++) {
                pw.println("Round #" + (i + 1));
                pw.println("Score: " + scoreList.get(i));

                // prints out the number of blocks
                countList.get(i).entrySet().forEach(entry -> pw.println(entry.getKey() + ": " + entry.getValue()));
                pw.println("------------------------------------------");
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //public void resetScore(){
        //count.replaceAll((k,v)->v=0 );
    //}

    //Calculate the average score
    public double calculateAverageScore(ArrayList<Integer> scoreList) {
        /* another way to calculate average score
        System.out.println(scoreList);
        int sum = 0;
        for (Integer element : scoreList) {
            sum += element;
        }
        double avg = sum / scoreList.size();
        System.out.println(sum / scoreList.size());
        return avg;

         */
        return scoreList.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }
}
