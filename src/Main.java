import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static String mainPath;
    public static String resultsPath;
    public static String errorsPath;

    public static String seedNotExistingErrorPath;

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

        Session.initializeSessions(sessionsPath);
        BasicPathInfo.initializeBasicPathInfos(basicPathInfoPath);
        //OptimizedFidelity.initializeOptimizedFidelities(optimizedFidelityPath);
        //UserInteraction.initializeUserInteractions(userInteractionsPath);

        //amountsOfBasicPathInfosCalculations(amountsPath);
        //sessionsDataCalculations(sessionsDataPath);
        //amountsOfOptimizationsCalculations(amountsOfOptimizationsPath);
        //amountsOfIterationsOfOptimizationsCalculations(amountsOfIterationsOfOptimizationsPath);
        usersAndAmountOfOptimizationsDataCalculations(userAndAmountOfOptimizationsDataPath);
        usersAndAvgOfLevelOfOptimizationsDataCalculations(usersAndAvgOfLevelOfOptimizationsDataPath);
    }

    private static void initializeErrorsPath(){
        errorsPath = mainPath + "\\errorsWithTheData";

        seedNotExistingErrorPath = errorsPath + "\\seedNotExistingError.csv";

        try {
            FileWriter seedNotExistingWriter = new FileWriter(seedNotExistingErrorPath);
            seedNotExistingWriter.append("path id, seed id\n");
            seedNotExistingWriter.flush();
            seedNotExistingWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static void amountsOfOptimizationsCalculations(String path){
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
