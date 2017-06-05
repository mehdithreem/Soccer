package soccer.data;

import soccer.ConnectionFactory;
import soccer.model.Stadium;
import soccer.model.StadiumTeam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class StadiumRepo {
    private static StadiumRepo theRepository = new StadiumRepo();
    public static StadiumRepo getRepository() {
        return theRepository;
    }

    public List<Stadium> getAvailableStadiums() {
        List<Stadium> result = new ArrayList<>();

        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from stadium");

            while ( rs.next() ) {
                Stadium stadium = new Stadium(
                        rs.getString("name"),
                        rs.getInt("capacity"),
                        rs.getInt("price")
                );
                result.add(stadium);
            }

            rs.close();
            s.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }

        return result;
    }

    public StadiumTeam getByTeam(String team) {
        // #TODO: implement
        return new StadiumTeam();
    }

    public void fixGrass(String team) {
        // #TODO: implement
    }

    public void fixSeat(String team) {
        // #TODO: implement
    }

    public void fixToilet(String team) {
        // #TODO: implement
    }

    public void buyStadium(String stadium, String team) {
        // #TODO: implement
    }
}