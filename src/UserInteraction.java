import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class UserInteraction {
    private int id;
    private Session sessionId;
    private int eventId;
    private BasicPathInfo pathId;
    /*
    private float currentScore;
    private int optimizationSteps;
    private float xMin;
    private float xMax;
    private float yMin;
    private float yMax;
    private boolean isZoomed;
    private boolean isShowGraph;
    private float timeFactor;
    private float mousePositionX;
    private float mousePositionY;
    private int millisSinceLevelStart;
    private int millisSinceLastEvent;
    private int millisInteractionStart;
    */
    private boolean isGhostOn;
    //private int clientTime;
    private Date createdAt;
    private Date updatedAt;


    public static HashMap<Integer, UserInteraction> userInteractions;

    public static void initializeUserInteractions(String path){
        System.out.println("initializing userInteractions");
        userInteractions = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            String first = scanner.nextLine(); //delete first line...

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                //UserInteraction curr = UserInteraction.Parse(line);
                //userInteractions.put(curr.id, curr);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done initializing userInteractions");
    }

    public UserInteraction(int id, Session sessionId, int eventId, BasicPathInfo pathId, boolean isGhostOn, Date createdAt, Date updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.eventId = eventId;
        this.pathId = pathId;
        this.isGhostOn = isGhostOn;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public BasicPathInfo getPathId() {
        return pathId;
    }

    public void setPathId(BasicPathInfo pathId) {
        this.pathId = pathId;
    }

    public boolean isGhostOn() {
        return isGhostOn;
    }

    public void setGhostOn(boolean ghostOn) {
        isGhostOn = ghostOn;
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

    /*
    public static UserInteraction Parse(String str){
        //todo complete this function
    }
    */
}
