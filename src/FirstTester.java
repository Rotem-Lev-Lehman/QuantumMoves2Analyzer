import java.io.BufferedWriter;
import java.io.FileWriter;

public class FirstTester extends Tester {

    public FirstTester(String resultsFolder, String errorsFolder) {
        super(resultsFolder, errorsFolder);
    }

    public void HowMuchPressedOptimizationInEveryTimeBinAndLevel(){
        System.out.println("Starting HowMuchPressedOptimizationInEveryTimeBinAndLevel");

        String path = resultsFolder + "\\HowMuchPressedOptimizationInEveryTimeBinAndLevel.csv";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            int[][] numWithOptimization = new int[User.levelsNum][];
            for (int i = 0; i < User.levelsNum; i++) {
                numWithOptimization[i] = new int[User.timeBinsNum];
                for (int j = 0; j < User.timeBinsNum; j++) {
                    numWithOptimization[i][j] = 0;
                }
            }

            for (Session session : Session.sessions.values()) {
                for (BasicPathInfo seed : session.getBasicPathInfos()) {
                    int levelId = seed.getLevelId();
                    int timeBin = (int) Math.floor(seed.getDuration() * 10);
                    if (timeBin == 12)
                        timeBin = 11;

                    if (seed.getOptimization() != null) {
                        numWithOptimization[levelId][timeBin]++;
                    }
                }
            }

            writer.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            for (int i = 0; i < User.levelsNum; i++) {
                writer.append("" + i);
                for (int j = 0; j < User.timeBinsNum; j++) {
                    writer.append("," + numWithOptimization[i][j]);
                }
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done HowMuchPressedOptimizationInEveryTimeBinAndLevel");
    }

    public void levelNumForMaxScoreWithAndWithoutOptimization(){
        System.out.println("Starting levelNumForMaxScoreWithAndWithoutOptimization");

        String withPath = resultsFolder + "\\levelNumForMaxScoreWithOptimization.csv";
        String withoutPath = resultsFolder + "\\levelNumForMaxScoreWithoutOptimization.csv";

        double threshHold = 0;
        try{
            BufferedWriter withWriter = new BufferedWriter(new FileWriter(withPath));
            BufferedWriter withoutWriter = new BufferedWriter(new FileWriter(withoutPath));

            withWriter.append("user id,level id,time bin num,high score\n");
            withoutWriter.append("user id,level id,time bin num,high score\n");

            for(User user : User.users.values()){
                user.calculateScoresTimeBinLevel();

                TwoIntAndOneDoubleTuple with = user.getLevelIdAndTimeBinMaxScoreWithOptimization();
                TwoIntAndOneDoubleTuple without = user.getLevelIdAndTimeBinMaxScoreWithoutOptimization();

                if(with.getThird() > threshHold)
                    withWriter.append("" + user.getUserId() + "," + with.toString() + "\n");
                if(without.getThird() > threshHold)
                    withoutWriter.append("" + user.getUserId() + "," + without.toString() + "\n");
            }

            withWriter.flush();
            withWriter.close();
            withoutWriter.flush();
            withoutWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done levelNumForMaxScoreWithAndWithoutOptimization");
    }

    public void OptimizationsAverageOfDifferences(){
        System.out.println("Starting OptimizationsAverageOfDifferences");

        String path = resultsFolder + "\\OptimizationsAverageOfDifferences.csv";

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            writer.append("avg of differences\n");
            for(Session session : Session.sessions.values()){
                for(BasicPathInfo seed : session.getBasicPathInfos()){
                    OptimizedFidelity curr = seed.getFirst();
                    double diff = 0;
                    int amount = 0;
                    while (curr != null){
                        if(curr.getNext() != null)
                        {
                            if(!(curr.getNext().getIteration() > curr.getIteration() + 1)){ // make sure there was no jump in between
                                diff += (curr.getNext().getFidelity() - curr.getFidelity());
                                amount++;
                            }
                        }
                        curr = curr.getNext();
                    }

                    if(amount > 0){
                        diff /= amount;
                        writer.append("" + diff + "\n");
                    }
                }
            }
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done OptimizationsAverageOfDifferences");
    }

    public void rankOfFidelityForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt(){
        System.out.println("Starting rankOfFidelityForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt");

        String pathNumOfPress = resultsFolder + "\\rankOfFidelityForEachUserCheck_NumOfPressOnOpt.csv";
        String pathImprovement = resultsFolder + "\\rankOfFidelityForEachUserCheck_ImproveOfFidelityFromOpt.csv";

        try{
            BufferedWriter writerNumOfPress = new BufferedWriter(new FileWriter(pathNumOfPress));
            BufferedWriter writerImprovement = new BufferedWriter(new FileWriter(pathImprovement));

            writerNumOfPress.append("user id");
            writerImprovement.append("user id");

            for(int i = 0; i < User.divisionNumForRankOfBasicPathInfosByScore; i++){
                writerNumOfPress.append(",rank " + i);
                writerImprovement.append(",rank " + i);
            }

            writerNumOfPress.append("\n");
            writerImprovement.append("\n");

            for(User user : User.users.values()){
                user.calculateAmountOfOptimizationsDone();
                if(user.getAmountOfOptimizationsDone() == 0) // only users who have done at least one optimization are counted in this calculation...
                    continue;

                user.calculateFidelityRankingOf_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt();

                writerNumOfPress.append("" + user.getUserId());
                writerImprovement.append("" + user.getUserId());

                double[] numOfPressed = user.getNumOfPressOnOptForEachRank();
                double[] improvement = user.getImprovementInFidelityForEachRank();
                for(int i = 0; i < User.divisionNumForRankOfBasicPathInfosByScore; i++){
                    writerNumOfPress.append("," + numOfPressed[i]);
                    writerImprovement.append("," + improvement[i]);
                }

                writerNumOfPress.append("\n");
                writerImprovement.append("\n");
            }

            writerNumOfPress.flush();
            writerNumOfPress.close();
            writerImprovement.flush();
            writerImprovement.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done rankOfFidelityForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt");
    }

    public void TimeBinsForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt(){
        System.out.println("Starting TimeBinsForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt");

        String pathNumOfPress = resultsFolder + "\\TimeBinsForEachUserCheck_NumOfPressOnOpt.csv";
        String pathImprovement = resultsFolder + "\\TimeBinsForEachUserCheck_ImproveOfFidelityFromOpt.csv";

        try{
            BufferedWriter writerNumOfPress = new BufferedWriter(new FileWriter(pathNumOfPress));
            BufferedWriter writerImprovement = new BufferedWriter(new FileWriter(pathImprovement));

            writerNumOfPress.append("user id");
            writerImprovement.append("user id");

            for(int i = 0; i < User.timeBinsNum; i++){
                writerNumOfPress.append(",time bin " + i);
                writerImprovement.append(",time bin " + i);
            }

            writerNumOfPress.append("\n");
            writerImprovement.append("\n");

            for(User user : User.users.values()){
                user.calculateAmountOfOptimizationsDone();
                if(user.getAmountOfOptimizationsDone() == 0) // only users who have done at least one optimization are counted in this calculation...
                    continue;

                user.calculateTimeBinsOf_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt();

                writerNumOfPress.append("" + user.getUserId());
                writerImprovement.append("" + user.getUserId());

                double[] numOfPressed = user.getNumOfPressOnOptForEachRankByTimeBins();
                double[] improvement = user.getImprovementInFidelityForEachRankByTimeBins();
                for(int i = 0; i < User.timeBinsNum; i++){
                    writerNumOfPress.append("," + numOfPressed[i]);
                    writerImprovement.append("," + improvement[i]);
                }

                writerNumOfPress.append("\n");
                writerImprovement.append("\n");
            }

            writerNumOfPress.flush();
            writerNumOfPress.close();
            writerImprovement.flush();
            writerImprovement.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Done TimeBinsForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt");
    }
}
