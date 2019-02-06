import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args){
        String mainPath = "C:\\Users\\Rotem\\Desktop\\quantum moves\\Quantum moves 2";
        String resultsPath = mainPath + "\\javaResults";

        String sessionsPath = mainPath + "\\sessions.csv";
        String basicPathInfoPath = mainPath + "\\basic_path_info.csv";
        String optimizedFidelityPath = mainPath + "\\optimized_fidelity.csv";
        String userInteractionsPath = mainPath + "\\user_interaction.csv";

        String amountsPath = resultsPath + "\\amountsOfBasicPathInfos.csv";
        String sessionsDataPath = resultsPath + "\\sessionsData.csv";

        Session.initializeSessions(sessionsPath);
        BasicPathInfo.initializeBasicPathInfos(basicPathInfoPath);
        //OptimizedFidelity.initializeOptimizedFidelities(optimizedFidelityPath);
        //UserInteraction.initializeUserInteractions(userInteractionsPath);

        //amountsOfBasicPathInfosCalculations(amountsPath);
        sessionsDataCalculations(sessionsDataPath);
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
