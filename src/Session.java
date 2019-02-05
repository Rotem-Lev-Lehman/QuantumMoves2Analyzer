import java.util.Date;

public class Session {
    private int id;
    //private int gameId;
    //private int installId;
    private int userId;
    //private int metricId;
    //private String sessionToken;
    //private String authType;
    private Date createdAt;

    public Session(int id, int userId, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
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

    private Session Parse(String str){
        //todo complete this function
    }
}
