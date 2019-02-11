import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class OptimizedFidelity {
    private int id;
    private BasicPathInfo seedPath;
    private int iteration;
    private double fidelity;
    private boolean isIntermediateStep;
    //private int clientTime;
    private Date createdAt;
    private Date updatedAt;

    private OptimizedFidelity next;
    private OptimizedFidelity previous;

    public static HashMap<String, OptimizedFidelity> optimizedFidelities;

    public static void initializeOptimizedFidelities(String path){
        System.out.println("initializing optimizedFidelities");
        optimizedFidelities = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            String first = scanner.nextLine(); //delete first line...

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                OptimizedFidelity curr = OptimizedFidelity.Parse(line);
                if(curr != null)
                    optimizedFidelities.put(curr.seedPath.getSeedPathId(), curr);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done initializing optimizedFidelities");
    }

    public OptimizedFidelity(int id, BasicPathInfo seedPath, int iteration, double fidelity, boolean isIntermediateStep, Date createdAt, Date updatedAt) {
        this.id = id;
        this.seedPath = seedPath;
        this.iteration = iteration;
        this.fidelity = fidelity;
        this.isIntermediateStep = isIntermediateStep;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.next = null;
        this.previous = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BasicPathInfo getSeedPath() {
        return seedPath;
    }

    public void setSeedPath(BasicPathInfo seedPath) {
        this.seedPath = seedPath;
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

    public OptimizedFidelity getNext() {
        return next;
    }

    public void setNext(OptimizedFidelity next) {
        this.next = next;
    }

    public OptimizedFidelity getPrevious() {
        return previous;
    }

    public void setPrevious(OptimizedFidelity previous) {
        this.previous = previous;
    }

    private static OptimizedFidelity Parse(String str){
        //todo complete this function
        OptimizedFidelity optimizedFidelity = null;
        try {
            String[] split = str.split(";");
            for(int i = 0; i < split.length;i++)
                split[i] = split[i].replaceAll("\"","");

            int id = Integer.parseInt(split[0]);
            String seedPathStr = split[4]; //seed path id
            BasicPathInfo seedPathId = BasicPathInfo.basicPathInfos.get(seedPathStr);
            int iteration = Integer.parseInt(split[5]);
            double fidelity = Double.parseDouble(split[6]);
            int isIntermediateStepInt = Integer.parseInt(split[7]);
            boolean isIntermediateStep = isIntermediateStepInt == 1; // true = 1, false = 0
            //int clientTime;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Date createdAt = formatter.parse(split[9]);
            Date updatedAt = formatter.parse(split[10]);
            optimizedFidelity = new OptimizedFidelity(id, seedPathId, iteration, fidelity, isIntermediateStep, createdAt, updatedAt);

            if(seedPathId == null) {
                //add to errors set
                //Main.seedsDontExistButInOptimizedFidelity.add(seedPathStr);
                //throw the exception
                //throw new Exception("seed does not exist");
                return null;
            }

            OptimizedFidelity first = seedPathId.getFirst();
            OptimizedFidelity last = seedPathId.getLast();

            if(first == null){
                if(last != null)
                    throw new Exception("The last must be null if the first is null");

                //if(iteration > 1 && !isIntermediateStep)
                if(iteration > 1) {
                    //add to errors set
                    //Main.MissingOptimizationsOptimizedFidelity.add(seedPathStr);
                    //throw the exception
                    //throw new Exception("The sample should have had iteration <= 1");
                }

                seedPathId.setFirst(optimizedFidelity);
                seedPathId.setLast(optimizedFidelity);
            }
            else {
                if (last == null)
                    throw new Exception("The last can't be null if the first is not null");

                if (last.iteration == optimizedFidelity.iteration) {
                    if (!last.isIntermediateStep || optimizedFidelity.isIntermediateStep) {
                        //add to errors set
                        //Main.ResetOfIterationsOptimizedFidelity.add(new StringIntTuple(seedPathStr,iteration));
                        //throw new Exception("The last must be an intermediate step and the current must be a final step");

                        //this error may just be ignored... lets assume the next iterations will be proceeding in the same way it would have when the iterations were ok
                    }
                } else if (last.iteration != optimizedFidelity.iteration - 1) {
                    //if(!Main.ResetOfIterationsOptimizedFidelity.contains(new StringIntTuple(seedPathStr,last.iteration)))
                      //  Main.MissingOptimizationsOptimizedFidelity.add(seedPathStr);
                    //throw new Exception("There must be a linear approach...");

                    //also can be ignored...
                }

                //its all good, set everything up!
                last.next = optimizedFidelity;
                optimizedFidelity.previous = last;

                seedPathId.setLast(optimizedFidelity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return optimizedFidelity;
    }
}
