import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Session {
    private int id;
    //private int gameId;
    //private int installId;
    private int userId;
    private User currentUser;
    //private int metricId;
    //private String sessionToken;
    //private String authType;
    private Date createdAt;
    private Date updatedAt;
    private List<BasicPathInfo> basicPathInfos;

    public static HashMap<Integer, Session> sessions;

    public static void initializeSessions(String path){
        System.out.println("initializing sessions");
        sessions = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)));
            String first = scanner.nextLine(); //delete first line...

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                Session curr = Session.Parse(line);
                sessions.put(curr.id, curr);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done initializing sessions");
    }

    private Session(int id, int userId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.currentUser = null;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.basicPathInfos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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

    public void addBasicPathInfo(BasicPathInfo basicPathInfo){
        basicPathInfos.add(basicPathInfo);
    }

    public List<BasicPathInfo> getBasicPathInfos() {
        return basicPathInfos;
    }

    public void setBasicPathInfos(List<BasicPathInfo> basicPathInfos) {
        this.basicPathInfos = basicPathInfos;
    }

    private static Session Parse(String str) {
        //todo complete this function
        Session session = null;
        try {
            String[] split = str.split(";");
            for(int i = 0; i < split.length;i++)
                split[i] = split[i].replaceAll("\"","");

            int id = Integer.parseInt(split[0]);
            //int gameId;
            //int installId;
            int userId = Integer.parseInt(split[3]);
            //int metricId;
            //String sessionToken;
            //String authType;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

            Date createdAt = formatter.parse(split[7]);
            Date updatedAt = formatter.parse(split[8]);
            session = new Session(id, userId, createdAt, updatedAt);
            User currentUser = User.addSession(session);
            session.currentUser = currentUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
}
