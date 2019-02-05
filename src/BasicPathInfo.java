import java.util.Date;

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

    public BasicPathInfo Parse(String str){
        //todo complete this function
    }
}
