import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class OptimizedFidelity {
    private int id;
    private BasicPathInfo pathId;
    private int iteration;
    private double fidelity;
    private boolean isIntermediateStep;
    //private int clientTime;
    private Date createdAt;
    private Date updatedAt;

    public static HashMap<Integer, OptimizedFidelity> optimizedFidelities;

    public static void initializeOptimizedFidelities(String path){
        System.out.println("initializing optimizedFidelities");
        optimizedFidelities = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            String first = scanner.nextLine(); //delete first line...

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                OptimizedFidelity curr = OptimizedFidelity.Parse(line);
                optimizedFidelities.put(curr.id, curr);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done initializing optimizedFidelities");
    }

    public OptimizedFidelity(int id, BasicPathInfo pathId, int iteration, double fidelity, boolean isIntermediateStep, Date createdAt, Date updatedAt) {
        this.id = id;
        this.pathId = pathId;
        this.iteration = iteration;
        this.fidelity = fidelity;
        this.isIntermediateStep = isIntermediateStep;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BasicPathInfo getPathId() {
        return pathId;
    }

    public void setPathId(BasicPathInfo pathId) {
        this.pathId = pathId;
    }

    public int getIteration() {
        return iteration;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public double getFidelity() {
        return fidelity;
    }

    public void setFidelity(double fidelity) {
        this.fidelity = fidelity;
    }

    public boolean isIntermediateStep() {
        return isIntermediateStep;
    }

    public void setIntermediateStep(boolean intermediateStep) {
        isIntermediateStep = intermediateStep;
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

    private static OptimizedFidelity Parse(String str){
        //todo complete this function
        OptimizedFidelity optimizedFidelity = null;
        try {
            String[] split = str.split(";");
            for(int i = 0; i < split.length;i++)
                split[i] = split[i].replaceAll("\"","");

            int id = Integer.parseInt(split[0]);
            String pathStr = split[3];
            BasicPathInfo pathId = BasicPathInfo.basicPathInfos.get(pathStr);
            int iteration = Integer.parseInt(split[5]);
            double fidelity = Double.parseDouble(split[6]);
            int isIntermediateStepInt = Integer.parseInt(split[7]);
            boolean isIntermediateStep = isIntermediateStepInt == 1; // true = 1, false = 0
            //int clientTime;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Date createdAt = formatter.parse(split[9]);
            Date updatedAt = formatter.parse(split[10]);
            optimizedFidelity = new OptimizedFidelity(id, pathId, iteration, fidelity, isIntermediateStep, createdAt, updatedAt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optimizedFidelity;
    }
}
