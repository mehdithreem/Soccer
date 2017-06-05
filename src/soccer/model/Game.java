package soccer.model;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class Game {
    private String host_team;
    private String guest_team;
    private Integer start_time;
    private Integer league;
    private Integer ticket_price;
    private Integer is_host_win;

    public Game(String host_team, String guest_team, Integer start_time, Integer league, Integer ticket_price, Integer is_host_win) {
        this.host_team = host_team;
        this.guest_team = guest_team;
        this.start_time = start_time;
        this.league = league;
        this.ticket_price = ticket_price;
        this.is_host_win = is_host_win;
    }

    public String getHost_team() {
        return host_team;
    }

    public void setHost_team(String host_team) {
        this.host_team = host_team;
    }

    public String getGuest_team() {
        return guest_team;
    }

    public void setGuest_team(String guest_team) {
        this.guest_team = guest_team;
    }

    public Integer getStart_time() {
        return start_time;
    }

    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }

    public Integer getLeague() {
        return league;
    }

    public void setLeague(Integer league) {
        this.league = league;
    }

    public Integer getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Integer ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Integer getIs_host_win() {
        return is_host_win;
    }

    public void setIs_host_win(Integer is_host_win) {
        this.is_host_win = is_host_win;
    }
}
