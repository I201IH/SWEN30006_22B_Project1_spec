package src;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the statistics of a game
 */
public class Statistics {
    /**
     * Total score of a game
     */
    private int score = 0;
    /**
     * The round a game is in
     */
    private int round = 1;
    /**
     * Store the average score across all games
     */
    private double averageScore = 0;
    /**
     * The difficulty of a game
     */
    private String difficulty = null;
    /**
     * Store all scores of all games
     */
    private ArrayList<Integer> scoreList = new ArrayList<Integer>();
    /**
     * Store all records of number of blocks in all games
     */
    private ArrayList<LinkedHashMap<String, Integer>> numBlocksList = new ArrayList<>();

    /**
     * Constructor to create an object of class Statistics
     * @param score
     * @param diff
     */
    public Statistics(int score, String diff) {
        this.score = score;
        this.difficulty = diff;
    }

    /**
     * This method is used to store scores in all games
     * @param score Parameter to store the score of a game
     * @return null The method adds all the scores into a list
     */
    public void storeScore(int score) {
        scoreList.add(score);
    }

    /**
     * This method is used to store the records of the number of blocks in all games
     * @param count Parameter to store the record of the number of blocks of a game
     * @return null The method adds all the records of the number of blocks into a list
     */
    public void storeNumBlocks(LinkedHashMap<String, Integer> count) {
        numBlocksList.add(count);
    }

    /**
     * This method is used to print out the statistic into a text file
     * @param score The score of a game
     * @param count The number of blocks in a game
     */
    public void printStatistics(int score, LinkedHashMap<String, Integer> count) {
        try (PrintWriter pw =
                     new PrintWriter(new FileWriter("Statistics.txt"))) {
            storeScore(score);
            storeNumBlocks(count);
            pw.println("Difficulty: " + difficulty);
            averageScore = calculateAverageScore(scoreList);
            pw.println("Average score per round: " + averageScore);
            pw.println("------------------------------------------");

            //pw.format("Round #" + round + "\n" + "Score: %d\n", score);
            for (int i = 0; i < scoreList.size(); i++) {
                pw.println("Round #" + (i + 1));
                pw.println("Score: " + scoreList.get(i));

                // prints out the number of blocks
                numBlocksList.get(i).entrySet().forEach(entry -> pw.println(entry.getKey() + ": " + entry.getValue()));
                pw.println("------------------------------------------");
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate the average score across all games
     * @param scoreList The list of scores of all games
     * @return double Average score from the score list
     */
    public double calculateAverageScore(ArrayList<Integer> scoreList) {
        return scoreList.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }
}
