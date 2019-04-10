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
    private int timeBin;
    //private int terminationCondition;
    //private int fullPathPropagation;
    //private int nfbgsRestart;
    //private int clientTime;
    private Date createdAt;
    private Date updatedAt;

    private BasicPathInfo optimization;
    private BasicPathInfo seed;
    private OptimizedFidelity first; //will apply only to seeds
    private OptimizedFidelity last; //will apply only to seeds

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

    public BasicPathInfo(int id, Session sessionId, int levelId, String pathId, String seedPathId, int optimizationIteration, double duration, double finalFidelity, int timeBin, Date createdAt, Date updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.levelId = levelId;
        this.pathId = pathId;
        this.seedPathId = seedPathId;
        this.optimizationIteration = optimizationIteration;
        this.duration = duration;
        this.finalFidelity = finalFidelity;
        //new edit:
        this.timeBin = timeBin;
        //end of new edit
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.optimization = null;
        this.seed = null;

        this.first = null;
        this.last = null;
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

    public OptimizedFidelity getFirst() {
        return first;
    }

    public void setFirst(OptimizedFidelity first) {
        this.first = first;
    }

    public OptimizedFidelity getLast() {
        return last;
    }

    public void setLast(OptimizedFidelity last) {
        this.last = last;
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
                split[i] = split[i].replaceAll("\"","").replaceAll(",",".");
            /*
            New order:

            0 - index;
            1 - "session_id";
            2 - "id";
            3 - "package_id";
            4 - "level_id";
            5 - "path_id";
            6 - "seed_path_id";
            7 - "optimization_iteration";
            8 - "duration";
            9 - "final_fidelity";
            10 - "termination_condition";
            11 - "full_path_propagation";
            12 - "nfbgs_restart";
            13 - "client_time";
            14 - "created_at";
            15 - "updated_at";
            16 - "is_optimized";
            17 - "fidelity_db";
            18 - "fidelity_check";
            19 - "ret_code";
            20 - "n_poi";
            21 - "order_in_game";
            22 - "level_name";
            23 - "T_QSL";
            24 - "dt_qengine";
            25 - "duration_npoi";


            26 - "time_bin"
             */
            int id = Integer.parseInt(split[2]); //previous: int id = Integer.parseInt(split[0]);
            int sessionId = Integer.parseInt(split[1]);
            Session mySession = Session.sessions.get(sessionId);

            //int packageId;
            int levelId = Integer.parseInt(split[4]); //previous: int levelId = Integer.parseInt(split[3]);
            String pathId = split[5]; //previous: String pathId = split[4];
            String seedPathId = split[6]; //previous: String seedPathId = split[5];
            int optimizationIteration = Integer.parseInt(split[7]); //previous: int optimizationIteration = Integer.parseInt(split[6]);
            double duration = Double.parseDouble(split[8]); //previous: double duration = Double.parseDouble(split[7]);
            double finalFidelity = Double.parseDouble(split[9]); //previous: double finalFidelity = Double.parseDouble(split[8]);
            int timeBin = Integer.parseInt(split[26]); // new edit


            if(mySession == null){
                //Main.MissingSessions.add(new StringTuple(sessionId + "", seedPathId));
                //throw new Exception("Missing session");
                return null;
            }
            /*
            if(!checkTimeBin(timeBin, duration))
                throw new Exception("The time bin calculation was not correct");
            */
            //int terminationCondition;
            //int fullPathPropagation;
            //int nfbgsRestart;
            //int clientTime;

            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date createdAt = formatter.parse(split[14]); //previous: Date createdAt = formatter.parse(split[13]);
            Date updatedAt = formatter.parse(split[15]); //previous: Date updatedAt = formatter.parse(split[14]);
            basicPathInfo = new BasicPathInfo(id, mySession, levelId, pathId, seedPathId, optimizationIteration, duration, finalFidelity, timeBin, createdAt, updatedAt);

            BasicPathInfo seed = basicPathInfos.get(seedPathId);
            if(pathId.equals(seedPathId)) { // this is the seed...
                if(seed != null){
                    //I already exist! (A fake one...)
                    if(seed.id >=0) {
                        //add to the errors list
//
//                        FileWriter seedNotExistingErrorWriter = new FileWriter(Main.duplicatesWithTheSameIdsErrorPath,true);
//                        seedNotExistingErrorWriter.append("" + seedPathId + "\n");
//                        seedNotExistingErrorWriter.flush();
//                        seedNotExistingErrorWriter.close();


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
                    seed = new BasicPathInfo(nextID, mySession, levelId, seedPathId, seedPathId, optimizationIteration, duration, finalFidelity, timeBin, createdAt, updatedAt);
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

    /**
     * @return true if the level can be optimized(num 6-20) or false otherwise(num 0-5)
     */
    public boolean isOptimizableLevel() {
        return levelId > 5;
    }

    /**
     * @return true if the path is a seed (seedPathId == pathId)
     */
    public boolean isSeed() {
        return seedPathId.equals(pathId);
    }

    private static boolean checkTimeBin(int timeBin1, double duration1){
        int timeBin2 = (int)Math.floor(duration1*10);
        if(timeBin2 == 12)
            timeBin2= 11;

        return timeBin1 == timeBin2;
    }

    public int getTimeBin(){
        /*
        int timeBin = (int)Math.floor(duration*10);
        if(timeBin == 12)
            timeBin = 11;

        return timeBin;
        */
        return this.timeBin;
    }
}
