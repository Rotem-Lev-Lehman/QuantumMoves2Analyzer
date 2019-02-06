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
    //private int metricId;
    //private String sessionToken;
    //private String authType;
    private Date createdAt;
    private Date updatedAt;

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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    private static Session Parse(String str) {
        //todo complete this function
        Session session = null;
        try {
            String[] split = str.split(";");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
}
