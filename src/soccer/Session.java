package soccer;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class Session {
    private static Session theSession = new Session();
    public static Session getSession() {
        return theSession;
    }

    private String team;

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}
