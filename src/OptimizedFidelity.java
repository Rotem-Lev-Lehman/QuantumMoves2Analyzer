import java.util.Date;

public class OptimizedFidelity {
    private int id;
    private BasicPathInfo pathId;
    private int iteration;
    private double fidelity;
    private boolean isIntermediateStep;
    //private int clientTime;
    private Date createdAt;
    private Date updatedAt;

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

    private OptimizedFidelity Parse(String str){
        //todo complete this function
    }
}
