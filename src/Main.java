import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static String mainPath;
    public static String resultsPath;
    public static String errorsPath;


    public static String seedNotExistingErrorPath;
    public static String duplicatesWithTheSameIdsErrorPath;

    public static HashSet<String> seedsDontExistButInOptimizedFidelity;
    public static String seedsDontExistButInOptimizedFidelityPath;

    public static HashSet<String> MissingOptimizationsOptimizedFidelity;
    public static String MissingOptimizationsOptimizedFidelityPath;

    public static HashSet<StringIntTuple> ResetOfIterationsOptimizedFidelity;
    public static String ResetOfIterationsOptimizedFidelityPath;

    public static void main(String[] args){
        mainPath = "C:\\Users\\Rotem\\Desktop\\quantum moves\\Quantum moves 2";
        resultsPath = mainPath + "\\javaResults";

        initializeErrorsPath();

        String sessionsPath = mainPath + "\\sessions.csv";
        String basicPathInfoPath = mainPath + "\\basic_path_info.csv";
        String optimizedFidelityPath = mainPath + "\\optimized_fidelity.csv";
        String userInteractionsPath = mainPath + "\\user_interaction.csv";

        String amountsPath = resultsPath + "\\amountsOfBasicPathInfos.csv";
        String sessionsDataPath = resultsPath + "\\sessionsData.csv";
        String amountsOfOptimizationsPath = resultsPath + "\\amountsOfOptimizationsData.csv";
        String amountsOfIterationsOfOptimizationsPath =  resultsPath + "\\amountsOfIterationsOfOptimizationsData.csv";
        String userAndAmountOfOptimizationsDataPath = resultsPath + "\\userAndAmountOfOptimizationsData.csv";
        String usersAndAvgOfLevelOfOptimizationsDataPath = resultsPath + "\\usersAndAvgOfLevelOfOptimizationsData.csv";
        String checkWhenTheUsersStopsOptimizationPath = resultsPath + "\\checkWhenTheUsersStopsOptimization.csv";

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
        checkWhenTheUserStopsOptimization(checkWhenTheUsersStopsOptimizationPath);

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
        errorsPath = mainPath + "\\errorsWithTheData";

        seedNotExistingErrorPath = errorsPath + "\\seedNotExistingError.csv";
        duplicatesWithTheSameIdsErrorPath = errorsPath + "\\duplicatesWithTheSameIdsError.csv";

        seedsDontExistButInOptimizedFidelity = new HashSet<>();
        seedsDontExistButInOptimizedFidelityPath = errorsPath + "\\seedsDontExistButInOptimizedFidelity.csv";

        MissingOptimizationsOptimizedFidelity = new HashSet<>();
        MissingOptimizationsOptimizedFidelityPath = errorsPath + "\\MissingOptimizationsOptimizedFidelity.csv";

        ResetOfIterationsOptimizedFidelity = new HashSet<>();
        ResetOfIterationsOptimizedFidelityPath = errorsPath + "\\ResetOfIterationsOptimizedFidelity.csv";

        /*
        try {
            FileWriter seedNotExistingWriter = new FileWriter(duplicatesWithTheSameIdsErrorPath);
            seedNotExistingWriter.append("path id\n");
            seedNotExistingWriter.flush();
            seedNotExistingWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        /*
        try {
            FileWriter seedNotExistingWriter = new FileWriter(seedNotExistingErrorPath);
            seedNotExistingWriter.append("path id, seed id\n");
            seedNotExistingWriter.flush();
            seedNotExistingWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
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
            writer.append("diff of last iteration\n");
            for(User user : User.users.values()){
                for(Session session : user.getSessions()){
                    for(BasicPathInfo seed : session.getBasicPathInfos()){ // move on the seeds
                        OptimizedFidelity last = seed.getLast();
                        if(last != null) {
                            // it's not a non optimized path
                            OptimizedFidelity prev = last.getPrevious();
                            if(prev != null && last != prev){
                                // it has at least two optimized fidelities
                                double fidelityDiff = last.getFidelity() - prev.getFidelity();
                                writer.append("" + fidelityDiff + "\n");
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
