import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private int userId;
    private int amountOfBasicPathInfosDone;
    private List<Session> sessions;

    public static HashMap<Integer, User> users = new HashMap<>();

    public static User addSession(Session session){
        int wantedUserId = session.getUserId();
        User curr = users.get(wantedUserId);
        if(curr == null){
            curr = new User(wantedUserId);
            users.put(wantedUserId, curr);
        }
        curr.sessions.add(session);

        return curr;
    }

    private User(int userId) {
        this.userId = userId;
        this.sessions = new ArrayList<>();
        this.amountOfBasicPathInfosDone = 0;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public int getAmountOfBasicPathInfosDone() {
        return amountOfBasicPathInfosDone;
    }

    public void calculateAmountOfBasicPathInfosDone() {
        int amount = 0;
        for(Session session : sessions){
            amount += session.getBasicPathInfos().size();
        }
        amountOfBasicPathInfosDone = amount;
    }
}
