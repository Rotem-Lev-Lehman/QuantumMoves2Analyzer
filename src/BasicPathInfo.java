import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class BasicPathInfo {
    private int id;
    private Session sessionId;
    //private int packageId;
    private int levelId;
    private String pathId;
    private String seedPathId;
    private int optimizationIteration;
    private double duration;
    private double finalFidelity;
    //private int terminationCondition;
    //private int fullPathPropagation;
    //private int nfbgsRestart;
    //private int clientTime;
    private Date createdAt;
    private Date updatedAt;

    private BasicPathInfo optimization;
    private BasicPathInfo seed;
    private OptimizedFidelity first; //will apply only to optimizations
    private OptimizedFidelity last; //will apply only to optimizations

    public static HashMap<String, BasicPathInfo> basicPathInfos;
    private static int nextID = -1;

    public static void initializeBasicPathInfos(String path){
        System.out.println("initializing basicPathInfos");
        basicPathInfos = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            String first = scanner.nextLine(); //delete first line...

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                BasicPathInfo curr = BasicPathInfo.Parse(line);
                if(curr != null)
                    basicPathInfos.put(curr.pathId, curr);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done initializing basicPathInfos");
    }

    public BasicPathInfo(int id, Session sessionId, int levelId, String pathId, String seedPathId, int optimizationIteration, double duration, double finalFidelity, Date createdAt, Date updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.levelId = levelId;
        this.pathId = pathId;
        this.seedPathId = seedPathId;
        this.optimizationIteration = optimizationIteration;
        this.duration = duration;
        this.finalFidelity = finalFidelity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.optimization = null;
        this.seed = null;
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

    public String getSeedPathId() {
        return seedPathId;
    }

    public void setSeedPathId(String seedPathId) {
        this.seedPathId = seedPathId;
    }

    public BasicPathInfo getOptimization() {
        return optimization;
    }

    public void setOptimization(BasicPathInfo optimization) {
        this.optimization = optimization;
    }

    public BasicPathInfo getSeed() {
        return seed;
    }

    public void setSeed(BasicPathInfo seed) {
        this.seed = seed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicPathInfo that = (BasicPathInfo) o;
        return levelId == that.levelId &&
                optimizationIteration == that.optimizationIteration &&
                Double.compare(that.duration, duration) == 0 &&
                Double.compare(that.finalFidelity, finalFidelity) == 0 &&
                Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(seedPathId, that.seedPathId) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, levelId, seedPathId, optimizationIteration, duration, finalFidelity, createdAt, updatedAt);
    }

    public static BasicPathInfo Parse(String str){
        //todo complete this function
        BasicPathInfo basicPathInfo = null;
        try {
            String[] split = str.split(";");
            for(int i = 0; i < split.length;i++)
                split[i] = split[i].replaceAll("\"","");

            int id = Integer.parseInt(split[0]);
            int sessionId = Integer.parseInt(split[1]);
            Session mySession = Session.sessions.get(sessionId);
            //int packageId;
            int levelId = Integer.parseInt(split[3]);
            String pathId = split[4];
            String seedPathId = split[5];
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
            basicPathInfo = new BasicPathInfo(id, mySession, levelId, pathId, seedPathId, optimizationIteration, duration, finalFidelity, createdAt, updatedAt);

            BasicPathInfo seed = basicPathInfos.get(seedPathId);
            if(pathId.equals(seedPathId)) { // this is the seed...
                if(seed != null){
                    //I already exist! (A fake one...)
                    if(seed.id >=0) {
                        //add to the errors list
                        /*
                        FileWriter seedNotExistingErrorWriter = new FileWriter(Main.duplicatesWithTheSameIdsErrorPath,true);
                        seedNotExistingErrorWriter.append("" + seedPathId + "\n");
                        seedNotExistingErrorWriter.flush();
                        seedNotExistingErrorWriter.close();

                        */
                        //throw new Exception("Weird! there was not supposed to be another seed like me...");
                        return null; //ignore this row
                    }

                    basicPathInfo.optimization = seed.optimization; //get the optimization back to the one it belongs to...
                    if(basicPathInfo.optimization == null)
                        throw new Exception("Weird! there was no optimization for the fake seed...");
                    basicPathInfo.optimization.seed = basicPathInfo;

                    mySession.removeBasicPathInfo(seed); //no need of the fake one any more...
                    basicPathInfos.remove(seedPathId);
                }
                mySession.addBasicPathInfo(basicPathInfo);
            }
            else{
                //there is already one like me :)
                if(seed == null) {
                    seed = new BasicPathInfo(nextID, mySession, levelId, seedPathId, seedPathId, optimizationIteration, duration, finalFidelity, createdAt, updatedAt);
                    nextID--;
                    basicPathInfos.put(seedPathId, seed);
                    mySession.addBasicPathInfo(seed);

//                    //add to the errors list
//                    FileWriter seedNotExistingErrorWriter = new FileWriter(Main.seedNotExistingErrorPath,true);
//                    seedNotExistingErrorWriter.append("" + pathId + "," + seedPathId + "\n");
//                    seedNotExistingErrorWriter.flush();
//                    seedNotExistingErrorWriter.close();
                }
                basicPathInfo.seed = seed;
                if(seed.optimization != null){
                    if(seed.optimization.optimizationIteration < basicPathInfo.optimizationIteration)
                        seed.optimization = basicPathInfo;
                }
                else
                    seed.optimization = basicPathInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicPathInfo;
    }
}
