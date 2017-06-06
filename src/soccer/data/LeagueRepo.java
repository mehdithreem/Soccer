package soccer.data;

import soccer.ConnectionFactory;
import soccer.model.Game;
import soccer.model.PendingLeague;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class LeagueRepo {
    private static LeagueRepo theRepository = new LeagueRepo();
    public static LeagueRepo getRepository() {
        return theRepository;
    }

    public List<Integer> getAvailable(String team) {
        List<Integer> result = new ArrayList<>();

        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery(
                    "select key from league l " +
                            "where (l.closed = FALSE) AND " +
                            "not exists(select * from league_team lt" +
                                " where lt.league = l.key and lt.team = \'" + team + "\')");

            while (rs.next())
                result.add(rs.getInt("key"));

            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }

        return result;
    }

    public List<PendingLeague> getPendingLeagues() {
        List<PendingLeague> result = new ArrayList<>();

        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery(
                    "select key, count(lt.team) as member_count " +
                            "from league l, league_team lt " +
                            "where (l.closed = FALSE) AND (lt.league = l.key)");

            while (rs.next())
                result.add(new PendingLeague(
                        rs.getInt("key"),
                        rs.getInt("member_count")
                ));

            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }

        return result;
    }

    public List<Integer> getClosedNotDone() {
        List<Integer> result = new ArrayList<>();

        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery(
                    "select key from league l " +
                            "where (l.closed = TRUE) AND (l.done = FALSE)");

            while (rs.next())
                result.add(rs.getInt("key"));

            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }

        return result;
    }

    public List<Game> getLeagueGames(Integer league_id) {
        List<Game> result = new ArrayList<>();
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery(
                    "select * from game g " +
                            "where g.league = " + league_id);

            while (rs.next()) {
                result.add(new Game(
                        rs.getString("host_team"),
                        rs.getString("guest_team"),
                        rs.getInt("start_time"),
                        rs.getInt("league"),
                        rs.getInt("ticket_price"),
                        rs.getInt("is_host_win")
                ));
            }

            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return result;
    }

    public List<Integer> getLeagueByTeam(String team) {
        List<Integer> result = new ArrayList<>();
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery(
                    "select * from league_team lt" +
                        " where lt.team = \'" + team + "\'");

            while (rs.next()) {
                result.add(rs.getInt("league"));
            }

            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return result;
    }

    public void joinLeague(Integer lid, String team) {
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            s.executeUpdate(
                    "insert into league_team(league, team) " +
                            "VALUES (" + lid + ",\'" + team + "\')");

            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void createNew(Integer year) {
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            s.executeUpdate(
                    "INSERT INTO league(year) VALUES (" + year + ")");

            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void playRound(Integer league, Integer round) {
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();

            s.executeQuery(
                    "SELECT batch_match(" + league + ", " + round + ")");

            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void startLeague(Integer league) {
        try {
            Connection c = ConnectionFactory.getConnection();

            Statement s = c.createStatement();
            s.executeQuery(
                    "SELECT league_timeset(" + league + ")");
            s.close();

            Statement s2 = c.createStatement();
            s.executeUpdate("UPDATE league set closed = TRUE WHERE key = " + league);
            s2.close();

            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }
}
