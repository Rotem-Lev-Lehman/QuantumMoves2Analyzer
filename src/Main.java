import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static String mainPath;
    public static String resultsPath;
    public static String scoresPath;
    public static String errorsPath;
    public static String correctedBasicPathErrorsPath;


    public static String seedNotExistingErrorPath;
    public static String duplicatesWithTheSameIdsErrorPath;

    public static HashSet<String> seedsDontExistButInOptimizedFidelity;
    public static String seedsDontExistButInOptimizedFidelityPath;

    public static HashSet<String> MissingOptimizationsOptimizedFidelity;
    public static String MissingOptimizationsOptimizedFidelityPath;

    public static HashSet<StringIntTuple> ResetOfIterationsOptimizedFidelity;
    public static String ResetOfIterationsOptimizedFidelityPath;

    public static HashSet<StringTuple> MissingSessions;
    public static String MissingSessionsPath;

    public static void main(String[] args){
        mainPath = "C:\\Users\\Rotem\\Desktop\\עבודה אצל קובי\\quantum moves\\Quantum moves 2\\data versions\\data version 2\\Updated_session_table\\Updated_session_table";
        resultsPath = mainPath + "\\results";
        //scoresPath = resultsPath + "\\scores";

        initializeErrorsPath();

        String sessionsPath = mainPath + "\\sessions.csv";
        String basicPathInfoPath = mainPath + "\\basic_path_info_corrected.csv";
        String optimizedFidelityPath = mainPath + "\\optimized_fidelity.csv";
        String userInteractionsPath = mainPath + "\\user_interaction.csv";

        String amountsPath = resultsPath + "\\amountsOfBasicPathInfos.csv";
        String sessionsDataPath = resultsPath + "\\sessionsData.csv";
        String amountsOfOptimizationsPath = resultsPath + "\\amountsOfOptimizationsData.csv";
        String amountsOfIterationsOfOptimizationsPath =  resultsPath + "\\amountsOfIterationsOfOptimizationsData.csv";
        String userAndAmountOfOptimizationsDataPath = resultsPath + "\\userAndAmountOfOptimizationsData.csv";
        String usersAndAvgOfLevelOfOptimizationsDataPath = resultsPath + "\\usersAndAvgOfLevelOfOptimizationsData.csv";
        String checkWhenTheUsersStopsOptimizationPath = resultsPath + "\\checkWhenTheUsersStopsOptimization.csv";

        String timeBinsHighScoreWithOptimizationMeanPath = scoresPath + "\\timeBinsHighScoreWithOptimizationMean.csv";
        String timeBinsHighScoreWithoutOptimizationMeanPath = scoresPath + "\\timeBinsHighScoreWithoutOptimizationMean.csv";
        String timeBinsAvgScoreWithOptimizationMeanPath = scoresPath + "\\timeBinsAvgScoreWithOptimizationMean.csv";
        String timeBinsAvgScoreWithoutOptimizationMeanPath = scoresPath + "\\timeBinsAvgScoreWithoutOptimizationMean.csv";
        String timeBinsHighScoreWithOptimizationSDPath = scoresPath + "\\timeBinsHighScoreWithOptimizationSD.csv";
        String timeBinsHighScoreWithoutOptimizationSDPath = scoresPath + "\\timeBinsHighScoreWithoutOptimizationSD.csv";
        String timeBinsAvgScoreWithOptimizationSDPath = scoresPath + "\\timeBinsAvgScoreWithOptimizationSD.csv";
        String timeBinsAvgScoreWithoutOptimizationSDPath = scoresPath + "\\timeBinsAvgScoreWithoutOptimizationSD.csv";

        String duplicatesError = errorsPath + "\\duplicates.csv";
        String zeroOptimizationsUsers = errorsPath + "\\usersWhoDoneZeroOptimizations.csv";

        Session.initializeSessions(sessionsPath);
        BasicPathInfo.initializeBasicPathInfos(basicPathInfoPath);
        OptimizedFidelity.initializeOptimizedFidelities(optimizedFidelityPath);
        //UserInteraction.initializeUserInteractions(userInteractionsPath);

        //amountsOfBasicPathInfosCalculations(amountsPath);
        //sessionsDataCalculations(sessionsDataPath);
        //amountsOfOptimizationsCalculations(amountsOfOptimizationsPath,zeroOptimizationsUsers);
        //amountsOfIterationsOfOptimizationsCalculations(amountsOfIterationsOfOptimizationsPath);
        //usersAndAmountOfOptimizationsDataCalculations(userAndAmountOfOptimizationsDataPath);
        //usersAndAvgOfLevelOfOptimizationsDataCalculations(usersAndAvgOfLevelOfOptimizationsDataPath);
        //checkErrorsWithMultipulBasicPathInfosOptimizations(duplicatesError);
        //WriteToCsvSeedsDontExistButInOptimizedFidelity();
        //WriteToCsvMissingOptimizationsOptimizedFidelity();
        //WriteToCsvResetOfIterationsOptimizedFidelity();
        //checkWhenTheUserStopsOptimization(checkWhenTheUsersStopsOptimizationPath);
        //checkTimeBinsScoresForEachLevel(timeBinsHighScoreWithOptimizationMeanPath,timeBinsAvgScoreWithOptimizationMeanPath,timeBinsHighScoreWithoutOptimizationMeanPath,timeBinsAvgScoreWithoutOptimizationMeanPath,timeBinsHighScoreWithOptimizationSDPath,timeBinsAvgScoreWithOptimizationSDPath,timeBinsHighScoreWithoutOptimizationSDPath,timeBinsAvgScoreWithoutOptimizationSDPath);

        //SaveErrorMissingSessions();

        //Run testers:
        //runFirstTester();
        runSecondTester();
    }

    private static void SaveErrorMissingSessions() {
        try {
            FileWriter errorsWriter = new FileWriter(MissingSessionsPath);
            errorsWriter.append("Session id,path seed id\n");
            for(StringTuple tuple : MissingSessions){
                errorsWriter.append(tuple.toString() + "\n");
            }
            errorsWriter.flush();
            errorsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runFirstTester() {
        System.out.println("Starting first tester");

        String firstResults = resultsPath + "\\firstResults";
        String firstErrors = errorsPath + "\\firstErrors";

        FirstTester firstTester = new FirstTester(firstResults, firstErrors);

        //firstTester.HowMuchPressedOptimizationInEveryTimeBinAndLevel();
        //firstTester.levelNumForMaxScoreWithAndWithoutOptimization();
        //firstTester.OptimizationsAverageOfDifferences();
        //firstTester.rankOfFidelityForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt();
        //firstTester.TimeBinsForEachUserCheck_NumOfPressOnOpt_and_ImproveOfFidelityFromOpt();
        //firstTester.HowMuchOptimizationsIterationsForBestSeed();

        System.out.println("Done first tester");
    }

    private static void runSecondTester(){
        System.out.println("Starting second tester");

        String secondResults = resultsPath + "\\secondResults";
        String secondErrors = errorsPath + "\\secondErrors";

        SecondTester secondTester = new SecondTester(secondResults, secondErrors);

        secondTester.CheckHowUsersUseOptimizationOverTime();

        System.out.println("Done second tester");
    }

    private static void WriteToCsvSeedsDontExistButInOptimizedFidelity() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(seedsDontExistButInOptimizedFidelityPath));
            writer.append("seed id\n");
            for(String seedId : seedsDontExistButInOptimizedFidelity){
                writer.append("" + seedId + "\n");
            }

            writer.flush();
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void WriteToCsvMissingOptimizationsOptimizedFidelity() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(MissingOptimizationsOptimizedFidelityPath));
            writer.append("seed id\n");
            for(String seedId : MissingOptimizationsOptimizedFidelity){
                writer.append("" + seedId + "\n");
            }

            writer.flush();
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void WriteToCsvResetOfIterationsOptimizedFidelity() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ResetOfIterationsOptimizedFidelityPath));
            writer.append("seed id,iteration of reset\n");
            for(StringIntTuple tuple : ResetOfIterationsOptimizedFidelity){
                writer.append("" + tuple.str + "," + tuple.num + "\n");
            }

            writer.flush();
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void initializeErrorsPath(){
        errorsPath = mainPath + "\\errors with version 2\\errors with current version";

        correctedBasicPathErrorsPath = errorsPath + "\\Corrected path errors";

        //seedNotExistingErrorPath = errorsPath + "\\seedNotExistingError.csv";
        //duplicatesWithTheSameIdsErrorPath = errorsPath + "\\duplicatesWithTheSameIdsError.csv";

        //seedsDontExistButInOptimizedFidelity = new HashSet<>();
        //seedsDontExistButInOptimizedFidelityPath = errorsPath + "\\seedsDontExistButInOptimizedFidelity.csv";

        MissingOptimizationsOptimizedFidelity = new HashSet<>();
        MissingOptimizationsOptimizedFidelityPath = errorsPath + "\\MissingOptimizationsOptimizedFidelity.csv";

        ResetOfIterationsOptimizedFidelity = new HashSet<>();
        ResetOfIterationsOptimizedFidelityPath = errorsPath + "\\ResetOfIterationsOptimizedFidelity.csv";


        MissingSessions = new HashSet<>();
        MissingSessionsPath = correctedBasicPathErrorsPath + "\\MissingSessions.csv";

        seedsDontExistButInOptimizedFidelity = new HashSet<>();
        seedsDontExistButInOptimizedFidelityPath = correctedBasicPathErrorsPath + "\\seedsDontExistButInOptimizedFidelity.csv";

        seedNotExistingErrorPath = correctedBasicPathErrorsPath + "\\seedNotExistingError.csv";
        duplicatesWithTheSameIdsErrorPath = correctedBasicPathErrorsPath + "\\duplicatesWithTheSameIdsError.csv";

//
//        try {
//            FileWriter seedNotExistingWriter = new FileWriter(duplicatesWithTheSameIdsErrorPath);
//            seedNotExistingWriter.append("path id\n");
//            seedNotExistingWriter.flush();
//            seedNotExistingWriter.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//
//        try {
//            FileWriter seedNotExistingWriter = new FileWriter(seedNotExistingErrorPath);
//            seedNotExistingWriter.append("path id, seed id\n");
//            seedNotExistingWriter.flush();
//            seedNotExistingWriter.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
    /*
    public static void checkErrorsWithMultipulBasicPathInfosOptimizations(String path){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("seed,opt\n");
            for(Session session : Session.sessions.values()){
                for(BasicPathInfo basicPathInfo : session.getBasicPathInfos()){
                    if(!basicPathInfo.getPathId().equals(basicPathInfo.getSeedPathId()))
                        continue;
                    int maxIterations = 0;
                    Date curr = basicPathInfo.getCreatedAt();
                    for(BasicPathInfo opt : basicPathInfo.getOptimizations()){
                        if(opt.getOptimizationIteration() > maxIterations) {
                            if (curr.after(opt.getCreatedAt()))
                                System.out.println("problem!");
                            curr = opt.getCreatedAt();
                            maxIterations = opt.getOptimizationIteration();
                        }
                        else{
                            if(basicPathInfo.equals(opt))
                                writer.append("" + basicPathInfo.getPathId() + "," + opt.getPathId() + "\n");
                        }
                    }
                }
            }

            writer.flush();
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    */

    public static void checkWhenTheUserStopsOptimization(String path){
        System.out.println("Starting to check When The User Stops Optimization");

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("diff of fidelity of last iteration,final iteration\n");
            for(User user : User.users.values()){
                for(Session session : user.getSessions()){
                    for(BasicPathInfo seed : session.getBasicPathInfos()){ // move on the seeds
                        OptimizedFidelity last = seed.getLast();
                        if(last != null) {
                            // it's an optimized path
                            OptimizedFidelity prev = last.getPrevious();
                            if(prev != null && last != prev){
                                // it has at least two optimized fidelities
                                double fidelityDiff = last.getFidelity() - prev.getFidelity();
                                int iteration = last.getIteration();
                                writer.append("" + fidelityDiff + "," + iteration + "\n");
                            }
                        }
                    }
                }
            }
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Finished checking When The User Stops Optimization");
    }

    public static void checkTimeBinsScoresForEachLevel(String timeBinsHighScoreWithOptimizationMeanPath,String timeBinsAvgScoreWithOptimizationMeanPath,String timeBinsHighScoreWithoutOptimizationMeanPath,String timeBinsAvgScoreWithoutOptimizationMeanPath,String timeBinsHighScoreWithOptimizationSDPath,String timeBinsAvgScoreWithOptimizationSDPath,String timeBinsHighScoreWithoutOptimizationSDPath,String timeBinsAvgScoreWithoutOptimizationSDPath){
        System.out.println("Starting to calculate time bins scores for each level");

        try {
            BufferedWriter writerHighWithMean = new BufferedWriter(new FileWriter(timeBinsHighScoreWithOptimizationMeanPath));
            BufferedWriter writerAvgWithMean = new BufferedWriter(new FileWriter(timeBinsAvgScoreWithOptimizationMeanPath));
            BufferedWriter writerHighWithoutMean = new BufferedWriter(new FileWriter(timeBinsHighScoreWithoutOptimizationMeanPath));
            BufferedWriter writerAvgWithoutMean = new BufferedWriter(new FileWriter(timeBinsAvgScoreWithoutOptimizationMeanPath));

            BufferedWriter writerHighWithSD = new BufferedWriter(new FileWriter(timeBinsHighScoreWithOptimizationSDPath));
            BufferedWriter writerAvgWithSD = new BufferedWriter(new FileWriter(timeBinsAvgScoreWithOptimizationSDPath));
            BufferedWriter writerHighWithoutSD = new BufferedWriter(new FileWriter(timeBinsHighScoreWithoutOptimizationSDPath));
            BufferedWriter writerAvgWithoutSD = new BufferedWriter(new FileWriter(timeBinsAvgScoreWithoutOptimizationSDPath));

            List<List<List<Double>>> highScoreTimeBinLevelWithOptimization = new ArrayList<>(User.levelsNum);
            List<List<List<Double>>> averageScoreTimeBinLevelWithOptimization = new ArrayList<>(User.levelsNum);
            List<List<List<Double>>> highScoreTimeBinLevelWithoutOptimization = new ArrayList<>(User.levelsNum);
            List<List<List<Double>>> averageScoreTimeBinLevelWithoutOptimization = new ArrayList<>(User.levelsNum);
            Collection<User> users = User.users.values();

            for(int i = 0; i < User.levelsNum; i++){
                highScoreTimeBinLevelWithOptimization.add(new ArrayList<>(User.timeBinsNum));
                averageScoreTimeBinLevelWithOptimization.add(new ArrayList<>(User.timeBinsNum));
                highScoreTimeBinLevelWithoutOptimization.add(new ArrayList<>(User.timeBinsNum));
                averageScoreTimeBinLevelWithoutOptimization.add(new ArrayList<>(User.timeBinsNum));

                for(int j = 0; j < User.timeBinsNum; j++){
                    highScoreTimeBinLevelWithOptimization.get(i).add(new LinkedList<>());
                    averageScoreTimeBinLevelWithOptimization.get(i).add(new LinkedList<>());
                    highScoreTimeBinLevelWithoutOptimization.get(i).add(new LinkedList<>());
                    averageScoreTimeBinLevelWithoutOptimization.get(i).add(new LinkedList<>());
                }
            }

            for(User user : users){
                user.calculateScoresTimeBinLevel();

                for(int i = 0; i < User.levelsNum; i++) {
                    for (int j = 0; j < User.timeBinsNum; j++) {
                        if (user.getHighScoreTimeBinLevelWithOptimization(i, j) > 0)
                            highScoreTimeBinLevelWithOptimization.get(i).get(j).add(user.getHighScoreTimeBinLevelWithOptimization(i, j));
                        if (user.getAverageScoreTimeBinLevelWithOptimization(i, j) > 0)
                            averageScoreTimeBinLevelWithOptimization.get(i).get(j).add(user.getAverageScoreTimeBinLevelWithOptimization(i, j));
                        if (user.getHighScoreTimeBinLevelWithoutOptimization(i, j) > 0)
                            highScoreTimeBinLevelWithoutOptimization.get(i).get(j).add(user.getHighScoreTimeBinLevelWithoutOptimization(i, j));
                        if (user.getAverageScoreTimeBinLevelWithoutOptimization(i, j) > 0)
                            averageScoreTimeBinLevelWithoutOptimization.get(i).get(j).add(user.getAverageScoreTimeBinLevelWithoutOptimization(i, j));
                    }
                }
            }

            writerAvgWithMean.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            writerAvgWithoutMean.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            writerAvgWithoutSD.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            writerAvgWithSD.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            writerHighWithMean.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            writerHighWithoutMean.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            writerHighWithoutSD.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");
            writerHighWithSD.append("level id \\ time bin,0,1,2,3,4,5,6,7,8,9,10,11\n");

            for(int i = 0; i < User.levelsNum; i++){
                writerAvgWithMean.append("" + i);
                writerAvgWithoutMean.append("" + i);
                writerAvgWithoutSD.append("" + i);
                writerAvgWithSD.append("" + i);
                writerHighWithMean.append("" + i);
                writerHighWithoutMean.append("" + i);
                writerHighWithoutSD.append("" + i);
                writerHighWithSD.append("" + i);

                for(int j = 0; j < User.timeBinsNum; j++){
                    double[] meanAndSDHighWith = calculateMeanAndSD(highScoreTimeBinLevelWithOptimization.get(i).get(j));
                    double[] meanAndSDAvgWith = calculateMeanAndSD(averageScoreTimeBinLevelWithOptimization.get(i).get(j));
                    double[] meanAndSDHighWithout = calculateMeanAndSD(highScoreTimeBinLevelWithoutOptimization.get(i).get(j));
                    double[] meanAndSDAvgWithout = calculateMeanAndSD(averageScoreTimeBinLevelWithoutOptimization.get(i).get(j));

                    writerAvgWithMean.append("," + meanAndSDAvgWith[0]);
                    writerAvgWithoutMean.append("," + meanAndSDAvgWithout[0]);
                    writerAvgWithoutSD.append("," + meanAndSDAvgWithout[1]);
                    writerAvgWithSD.append("," + meanAndSDAvgWith[1]);
                    writerHighWithMean.append("," + meanAndSDHighWith[0]);
                    writerHighWithoutMean.append("," + meanAndSDHighWithout[0]);
                    writerHighWithoutSD.append("," + meanAndSDHighWithout[1]);
                    writerHighWithSD.append("," + meanAndSDHighWith[1]);
                }

                writerAvgWithMean.append("\n");
                writerAvgWithoutMean.append("\n");
                writerAvgWithoutSD.append("\n");
                writerAvgWithSD.append("\n");
                writerHighWithMean.append("\n");
                writerHighWithoutMean.append("\n");
                writerHighWithoutSD.append("\n");
                writerHighWithSD.append("\n");
            }

            writerAvgWithMean.flush();
            writerAvgWithMean.close();
            writerAvgWithoutMean.flush();
            writerAvgWithoutMean.close();
            writerAvgWithoutSD.flush();
            writerAvgWithoutSD.close();
            writerAvgWithSD.flush();
            writerAvgWithSD.close();
            writerHighWithMean.flush();
            writerHighWithMean.close();
            writerHighWithoutMean.flush();
            writerHighWithoutMean.close();
            writerHighWithoutSD.flush();
            writerHighWithoutSD.close();
            writerHighWithSD.flush();
            writerHighWithSD.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Finished calculating time bins scores for each level");
    }

    public static double[] calculateMeanAndSD(List<Double> numArray)
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.size();

        if(length == 0)
            length = 1;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return new double[]{mean,Math.sqrt(standardDeviation/length)};
    }

    public static void usersAndAvgOfLevelOfOptimizationsDataCalculations(String path){
        System.out.println("Starting to calculate users and avg of level of optimizations data");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("user id, avg of level of optimizations\n");
            for(User user : User.users.values()){
                user.calculateAverageOptimizationLevel();
                writer.append("" + user.getUserId() + "," + user.getAverageOptimizationLevel() + "\n");
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished calculating users and avg of level of optimizations data");
    }

    public static void usersAndAmountOfOptimizationsDataCalculations(String path){
        System.out.println("Starting to calculate users and amount of optimizations data");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("user id, amount of optimizations\n");
            for(User user : User.users.values()){
                user.calculateAmountOfOptimizationsDone();
                writer.append("" + user.getUserId() + "," + user.getAmountOfOptimizationsDone() + "\n");
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished calculating users and amount of optimizations data");
    }

    /*
    public static void amountsOfIterationsOfOptimizationsCalculations(String path){
        System.out.println("Starting to calculate amounts of iterations of optimizations vector");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("amount of iterations of optimizations,amount of users\n");
            int max = 0;
            for(User user : User.users.values()){
                user.calculateAverageOptimizationLevel();
                if(user.getAverageOptimizationLevel() > max)
                    max = user.getAverageOptimizationLevel();
            }
            List<User> users = new ArrayList<>(User.users.values());
            Collections.sort(users, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getAverageOptimizationLevel() - o2.getAverageOptimizationLevel();
                }
            });
            int currentNum = 0;
            int currentAmountOfUsers = 0;
            for(int i = 0; i < users.size(); i++){
                if(users.get(i).getAverageOptimizationLevel() == currentNum) {
                    currentAmountOfUsers++;

                    if(i == users.size() - 1){ //add the last one too
                        writer.append("" + currentNum + "," + currentAmountOfUsers + "\n");
                    }
                }
                else{
                    writer.append("" + currentNum + "," + currentAmountOfUsers + "\n");
                    currentNum++;
                    currentAmountOfUsers = 0;
                    i--; //check again for this user...
                }
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Finished calculating amounts of iterations of optimizations vector");
    }
    */

    public static void amountsOfOptimizationsCalculations(String path, String path2){
        System.out.println("Starting to calculate amounts of optimizations vector");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("amount of optimizations,amount of users\n");
            int max = 0;
            for(User user : User.users.values()){
                user.calculateAmountOfOptimizationsDone();
                if(user.getAmountOfOptimizationsDone() > max)
                    max = user.getAmountOfOptimizationsDone();
            }
            List<User> users = new ArrayList<>(User.users.values());
            Collections.sort(users, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getAmountOfOptimizationsDone() - o2.getAmountOfOptimizationsDone();
                }
            });
            int currentNum = 0;
            int currentAmountOfUsers = 0;
            for(int i = 0; i < users.size(); i++){
                if(users.get(i).getAmountOfOptimizationsDone() == currentNum) {
                    currentAmountOfUsers++;

                    if(i == users.size() - 1){ //add the last one too
                        writer.append("" + currentNum + "," + currentAmountOfUsers + "\n");
                    }
                }
                else{
                    writer.append("" + currentNum + "," + currentAmountOfUsers + "\n");
                    currentNum++;
                    currentAmountOfUsers = 0;
                    i--; //check again for this user...
                }
            }

            writer.flush();
            writer.close();

            writer = new BufferedWriter(new FileWriter(path2));
            writer.append("user id\n");
            for(User user : users){
                if(user.getAmountOfOptimizationsDone() == 0)
                    writer.append("" + user.getUserId() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Finished calculating amounts of optimizations vector");
    }

    public static void sessionsDataCalculations(String path){
        System.out.println("Starting to calculate sessions data");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("session id, amount of path infos\n");
            for(Session session : Session.sessions.values()){
                writer.append("" + session.getId() + "," + session.getBasicPathInfos().size() + "\n");
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished calculating sessions data");
    }

    public static void amountsOfBasicPathInfosCalculations(String path){
        System.out.println("Starting to calculate amounts vector");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.append("amount of Basic path infos,amount of users\n");
            int max = 0;
            for(User user : User.users.values()){
                user.calculateAmountOfBasicPathInfosDone();
                if(user.getAmountOfBasicPathInfosDone() > max)
                    max = user.getAmountOfBasicPathInfosDone();
            }
            List<User> users = new ArrayList<>(User.users.values());
            Collections.sort(users, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getAmountOfBasicPathInfosDone() - o2.getAmountOfBasicPathInfosDone();
                }
            });
            int currentNum = 0;
            int currentAmountOfUsers = 0;
            for(int i = 0; i < users.size(); i++){
                if(users.get(i).getAmountOfBasicPathInfosDone() == currentNum) {
                    currentAmountOfUsers++;

                    if(i == users.size() - 1){ //add the last one too
                        writer.append("" + currentNum + "," + currentAmountOfUsers + "\n");
                    }
                }
                else{
                    writer.append("" + currentNum + "," + currentAmountOfUsers + "\n");
                    currentNum++;
                    currentAmountOfUsers = 0;
                    i--; //check again for this user...
                }
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Finished calculating amounts vector");
    }
}
