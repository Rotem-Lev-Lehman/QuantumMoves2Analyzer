import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondTester extends Tester {
    public SecondTester(String resultsFolder, String errorsFolder) {
        super(resultsFolder, errorsFolder);
    }

    public void CheckHowUsersUseOptimizationOverTime(){
        System.out.println("Starting CheckHowUsersUseOptimizationOverTime");

        String path = resultsFolder + "\\CheckHowUsersUseOptimizationOverTime.csv";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            int maxGamesNum = 0;

            List<List<List<Double>>> level_gameNum_optNumForEachUser = new ArrayList<>(User.levelsNum);
            for(int i = 0; i < User.levelsNum; i++){
                level_gameNum_optNumForEachUser.add(new ArrayList<>());
            }
            for(User user : User.users.values()){
                List<List<TwoIntTuple>> level_gamesNumAndOptimizationUntilNow = user.getOptimizationInteractionOverTime();
                for(int i = 0; i < User.levelsNum; i++){
                    List<TwoIntTuple> gamesNumAndOptimizationUntilNow = level_gamesNumAndOptimizationUntilNow.get(i);

                    if(gamesNumAndOptimizationUntilNow.size() > maxGamesNum)
                        maxGamesNum = gamesNumAndOptimizationUntilNow.size();

                    for(int j = 0; j < gamesNumAndOptimizationUntilNow.size(); j++){
                        if(level_gameNum_optNumForEachUser.get(i).size() < j + 1)
                            level_gameNum_optNumForEachUser.get(i).add(new ArrayList<>());

                        level_gameNum_optNumForEachUser.get(i).get(j).add((double)gamesNumAndOptimizationUntilNow.get(j).getSecond());
                    }
                }
            }

            writer.append("level id,(average/standard deviation),\\,games num");
            for(int i = 0; i < maxGamesNum; i++)
                writer.append("," + (i + 1));
            writer.append("\n");

            for(int i = 0; i < User.levelsNum; i++){
                List<double[]> meanAndSDForEachGameNum = new ArrayList<>();
                for(int j = 0; j < level_gameNum_optNumForEachUser.get(i).size(); j++){
                    double[] meanAndSD = Main.calculateMeanAndSD(level_gameNum_optNumForEachUser.get(i).get(j));
                    meanAndSDForEachGameNum.add(meanAndSD);
                }
                writer.append("" + i + ",average,,");
                for(double[] meanAndSD : meanAndSDForEachGameNum){
                    writer.append("," + meanAndSD[0]);
                }
                writer.append("\n");
                writer.append("" + i + ",standard deviation,,");
                for(double[] meanAndSD : meanAndSDForEachGameNum){
                    writer.append("," + meanAndSD[1]);
                }
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done CheckHowUsersUseOptimizationOverTime");

    }

    public void HistogramOfUsersWithPercentageOfGamesOptimizedInAIntervalOfGames() {
        System.out.println("Starting HistogramOfUsersWithPercentageOfGamesOptimizedInAIntervalOfGames");

        String path = resultsFolder + "\\HistogramOfUsersWithPercentageOfGamesOptimizedInAIntervalOfGames.csv";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            int maxIntervalsNum = 0;
            int intervalSize = 10;

            List<List<List<Double>>> level_Interval_optNumForEachUser = new ArrayList<>(User.levelsNum);
            for(int i = 0; i < User.levelsNum; i++){
                level_Interval_optNumForEachUser.add(new ArrayList<>());
            }
            for(User user : User.users.values()) {
                List<List<IntBoolTuple>> level_gamesNumAndOptimizationIsDone = user.getOptimizationIsDoneInEachTime();
                for (int i = 0; i < User.levelsNum; i++) {
                    int currIntervalRemainders = intervalSize;
                    int currIntervalIndex = 0;
                    int optimizationsInCurrInterval = 0;

                    List<IntBoolTuple> gamesNumAndOptimizationIsDone = level_gamesNumAndOptimizationIsDone.get(i);

                    int currIntervalNum = (int)Math.ceil(((double) gamesNumAndOptimizationIsDone.size()) / ((double) intervalSize));
                    if (currIntervalNum > maxIntervalsNum)
                        maxIntervalsNum = currIntervalNum;

                    for (int j = 0; j < gamesNumAndOptimizationIsDone.size(); j++) {
                        if (gamesNumAndOptimizationIsDone.get(j).isFlag()) {
                            optimizationsInCurrInterval++;
                        }
                        currIntervalRemainders--;

                        if (currIntervalRemainders == 0) {
                            if (level_Interval_optNumForEachUser.get(i).size() < currIntervalIndex + 1)
                                level_Interval_optNumForEachUser.get(i).add(new ArrayList<>());

                            level_Interval_optNumForEachUser.get(i).get(currIntervalIndex).add(((double) optimizationsInCurrInterval) / ((double) intervalSize));

                            currIntervalIndex++;
                            currIntervalRemainders = intervalSize;
                            optimizationsInCurrInterval = 0;
                        }
                    }
                    if (currIntervalRemainders < intervalSize) {
                        if (level_Interval_optNumForEachUser.get(i).size() < currIntervalIndex + 1)
                            level_Interval_optNumForEachUser.get(i).add(new ArrayList<>());

                        level_Interval_optNumForEachUser.get(i).get(currIntervalIndex).add(((double) optimizationsInCurrInterval) / ((double) (intervalSize - currIntervalRemainders)));

                    }
                }
            }

            writer.append("level id,(average/standard deviation/amount),\\,interval num(each containing " + intervalSize + " games)");
            for(int i = 0; i < maxIntervalsNum; i++)
                writer.append("," + (i + 1));
            writer.append("\n");

            for(int i = 0; i < User.levelsNum; i++){
                List<double[]> meanAndSDForEachGameNum = new ArrayList<>();
                List<Integer> amountOfUsers = new ArrayList<>();
                for(int j = 0; j < level_Interval_optNumForEachUser.get(i).size(); j++){
                    List<Double> curr = level_Interval_optNumForEachUser.get(i).get(j);
                    double[] meanAndSD = Main.calculateMeanAndSD(curr);
                    meanAndSDForEachGameNum.add(meanAndSD);
                    int amount = curr.size();
                    amountOfUsers.add(amount);
                }
                writer.append("" + i + ",average,,");
                for(double[] meanAndSD : meanAndSDForEachGameNum){
                    writer.append("," + meanAndSD[0]);
                }
                writer.append("\n");
                writer.append("" + i + ",standard deviation,,");
                for(double[] meanAndSD : meanAndSDForEachGameNum){
                    writer.append("," + meanAndSD[1]);
                }
                writer.append("\n");
                writer.append("" + i + ",amount,,");
                for(Integer amount : amountOfUsers){
                    writer.append("," + amount);
                }
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done HistogramOfUsersWithPercentageOfGamesOptimizedInAIntervalOfGames");
    }

    public void Histogram_Of_People_Who_Done_Amount_Of_Games() {
        System.out.println("Starting Histogram_Of_People_Who_Done_Amount_Of_Games");

        String path = resultsFolder + "\\Histogram_Of_People_Who_Done_Amount_Of_Games.csv";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            int maxIntervalsNum = 0;
            int intervalSize = 10;

            List<Map<Integer,List<Double>>> level_Interval_optNumForEachUser = new ArrayList<>(User.levelsNum);
            List<Integer> max_per_level = new ArrayList<>(User.levelsNum);
            for(int i = 0; i < User.levelsNum; i++){
                level_Interval_optNumForEachUser.add(new HashMap<>());
                max_per_level.add(0);
            }

            for(User user : User.users.values()) {
                List<List<IntBoolTuple>> level_gamesNumAndOptimizationIsDone = user.getOptimizationIsDoneInEachTime();
                for (int i = 0; i < User.levelsNum; i++) {
                    /*
                    int currIntervalRemainders = intervalSize;
                    int currIntervalIndex = 0;
                    int optimizationsInCurrInterval = 0;
                    */
                    List<IntBoolTuple> gamesNumAndOptimizationIsDone = level_gamesNumAndOptimizationIsDone.get(i);
                    if(gamesNumAndOptimizationIsDone.size() == 0)
                        continue;

                    int currIntervalNum = (int)Math.ceil(((double) gamesNumAndOptimizationIsDone.size()) / ((double) intervalSize));
                    if (currIntervalNum > maxIntervalsNum)
                        maxIntervalsNum = currIntervalNum;

                    if(currIntervalNum > max_per_level.get(i))
                        max_per_level.set(i, currIntervalNum);

                    List<Double> curr = level_Interval_optNumForEachUser.get(i).get(currIntervalNum);
                    if(curr == null){
                        curr = new ArrayList<>();
                        level_Interval_optNumForEachUser.get(i).put(currIntervalNum,curr);
                    }
                    curr.add(calculate_percentage_of_optimizations(gamesNumAndOptimizationIsDone));
                    /*
                    for (int j = 0; j < gamesNumAndOptimizationIsDone.size(); j++) {
                        if (gamesNumAndOptimizationIsDone.get(j).isFlag()) {
                            optimizationsInCurrInterval++;
                        }
                        currIntervalRemainders--;

                        if (currIntervalRemainders == 0) {
                            if (level_Interval_optNumForEachUser.get(i).size() < currIntervalIndex + 1)
                                level_Interval_optNumForEachUser.get(i).add(new ArrayList<>());

                            level_Interval_optNumForEachUser.get(i).get(currIntervalIndex).add(((double) optimizationsInCurrInterval) / ((double) intervalSize));

                            currIntervalIndex++;
                            currIntervalRemainders = intervalSize;
                            optimizationsInCurrInterval = 0;
                        }
                    }
                    if (currIntervalRemainders < intervalSize) {
                        if (level_Interval_optNumForEachUser.get(i).size() < currIntervalIndex + 1)
                            level_Interval_optNumForEachUser.get(i).add(new ArrayList<>());

                        level_Interval_optNumForEachUser.get(i).get(currIntervalIndex).add(((double) optimizationsInCurrInterval) / ((double) (intervalSize - currIntervalRemainders)));

                    }
                    */
                }
            }

            writer.append("level id,(average/standard deviation/amount),\\,interval num");
            for(int i = 0; i < maxIntervalsNum; i++)
                writer.append("," + (i + 1));
            writer.append("\n");

            for(int i = 0; i < User.levelsNum; i++){
                List<double[]> meanAndSDForEachGameNum = new ArrayList<>();
                List<Integer> amountOfUsers = new ArrayList<>();
                for(int j = 1; j <= max_per_level.get(i); j++){
                    List<Double> curr = level_Interval_optNumForEachUser.get(i).get(j);
                    double[] meanAndSD;
                    int amount;
                    if(curr == null) {
                        meanAndSD = null;
                        amount = 0;
                    }
                    else {
                        meanAndSD = Main.calculateMeanAndSD(curr);
                        amount = curr.size();
                    }
                    meanAndSDForEachGameNum.add(meanAndSD);
                    amountOfUsers.add(amount);
                }
                writer.append("" + i + ",average,,");
                for(double[] meanAndSD : meanAndSDForEachGameNum){
                    if(meanAndSD != null)
                        writer.append("," + meanAndSD[0]);
                    else
                        writer.append(",");
                }
                writer.append("\n");
                writer.append("" + i + ",standard deviation,,");
                for(double[] meanAndSD : meanAndSDForEachGameNum){
                    if(meanAndSD != null)
                        writer.append("," + meanAndSD[1]);
                    else
                        writer.append(",");
                }
                writer.append("\n");
                writer.append("" + i + ",amount,,");
                for(Integer amount : amountOfUsers){
                    writer.append("," + amount);
                }
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done Histogram_Of_People_Who_Done_Amount_Of_Games");
    }

    private Double calculate_percentage_of_optimizations(List<IntBoolTuple> gamesNumAndOptimizationIsDone) {
        int count = 0;
        for (int i = 0; i < gamesNumAndOptimizationIsDone.size(); i++) {
            if (gamesNumAndOptimizationIsDone.get(i).isFlag())
                count++;
        }
        return (double) count / (double) gamesNumAndOptimizationIsDone.size();
    }


}
