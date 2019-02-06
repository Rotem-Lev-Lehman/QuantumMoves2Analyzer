import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class BasicPathInfo {
    private int id;
    private Session sessionId;
    //private int packageId;
    private int levelId;
    private String pathId;
    //private String seedPathId;
    private int optimizationIteration;
    private double duration;
    private double finalFidelity;
    //private int terminationCondition;
    //private int fullPathPropagation;
    //private int nfbgsRestart;
    //private int clientTime;
    private Date createdAt;
    private Date updatedAt;

    public static HashMap<String, BasicPathInfo> basicPathInfos;

    public static void initializeBasicPathInfos(String path){
        System.out.println("initializing basicPathInfos");
        basicPathInfos = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            String first = scanner.nextLine(); //delete first line...

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                BasicPathInfo curr = BasicPathInfo.Parse(line);
                basicPathInfos.put(curr.pathId, curr);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done initializing basicPathInfos");
    }

    public BasicPathInfo(int id, Session sessionId, int levelId, String pathId, int optimizationIteration, double duration, double finalFidelity, Date createdAt, Date updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.levelId = levelId;
        this.pathId = pathId;
        this.optimizationIteration = optimizationIteration;
        this.duration = duration;
        this.finalFidelity = finalFidelity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Session getSessionId() {
        return sessionId;
    }

    public void setSessionId(Session sessionId) {
        this.sessionId = sessionId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public int getOptimizationIteration() {
        return optimizationIteration;
    }

    public void setOptimizationIteration(int optimizationIteration) {
        this.optimizationIteration = optimizationIteration;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getFinalFidelity() {
        return finalFidelity;
    }

    public void setFinalFidelity(double finalFidelity) {
        this.finalFidelity = finalFidelity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static BasicPathInfo Parse(String str){
        //todo complete this function
        BasicPathInfo basicPathInfo = null;
        try {
            String[] split = str.split(";");
            int id = Integer.parseInt(split[0]);
            int sessionId = Integer.parseInt(split[1]);
            Session mySession = Session.sessions.get(sessionId);
            //int packageId;
            int levelId = Integer.parseInt(split[3]);
            String pathId = split[4];
            //String seedPathId;
            int optimizationIteration = Integer.parseInt(split[6]);
            double duration = Double.parseDouble(split[7]);
            double finalFidelity = Double.parseDouble(split[8]);
            //int terminationCondition;
            //int fullPathPropagation;
            //int nfbgsRestart;
            //int clientTime;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Date createdAt = formatter.parse(split[13]);
            Date updatedAt = formatter.parse(split[14]);
            basicPathInfo = new BasicPathInfo(id, mySession, levelId, pathId, optimizationIteration, duration, finalFidelity, createdAt, updatedAt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicPathInfo;
    }
}
