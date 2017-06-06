package soccer.model;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class PendingLeague {
    private Integer league;
    private Integer memberCount;

    public PendingLeague(Integer league, Integer memberCount) {
        this.league = league;
        this.memberCount = memberCount;
    }

    public Integer getLeague() {
        return league;
    }

    public void setLeague(Integer league) {
        this.league = league;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }
}
