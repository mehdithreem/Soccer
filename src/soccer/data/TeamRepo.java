package soccer.data;

import soccer.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class TeamRepo {
    private static TeamRepo theRepository = new TeamRepo();
    public static TeamRepo getRepository() {
        return theRepository;
    }

    public List<String> getAllTeamsNames() {
        List<String> result = new ArrayList<>();

        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from team");

            while ( rs.next() ) {
                result.add(rs.getString("name"));
            }

            rs.close();
            s.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }

        return result;
    }

    public void createTeam(String name) {
        try {
            Connection c = ConnectionFactory.getConnection();
            c.setAutoCommit(false);

            Statement s = c.createStatement();

            s.executeUpdate("insert into team VALUES (\'" + name + "\', 900000000)");

            s.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public Integer getTeamMoney(String team) {
        Integer result = null;
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select money from team t where t.name = \'" + team + "\'");

            rs.next();
            result = rs.getInt("money");

            rs.close();
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }

        return result;
    }
}
