import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

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
                            level_gameNum_optNumForEachUser.add(new ArrayList<>());

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
                writer.append("" + i + ",average,");
                for(double[] meanAndSD : meanAndSDForEachGameNum){
                    writer.append("," + meanAndSD[0]);
                }
                writer.append("\n");
                writer.append("" + i + ",standard deviation,");
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
}
