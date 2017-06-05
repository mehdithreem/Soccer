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
        StadiumTeam stadiumTeam = new StadiumTeam();
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from team_stadium where team_name = \'"+team+"\'");

            rs.next();
            stadiumTeam.teamName = team;
            stadiumTeam.stadiumName = rs.getString("stadium_name");
            stadiumTeam.grassQuality = rs.getDouble("grass_quality");
            stadiumTeam.toiletQuality = rs.getDouble("toilet_quality");
            stadiumTeam.seatQuality = rs.getDouble("seat_quality");

            rs.close();
            s.close();

            Statement s2 = c.createStatement();
            ResultSet rs2 = s2.executeQuery
                    ("select * from stadium where name = \'"+ stadiumTeam.stadiumName +"\'");
            rs2.next();
            stadiumTeam.price = rs2.getInt("price");
            stadiumTeam.capacity = rs2.getInt("capacity");

            rs2.close();
            s2.close();

            c.close();

        }
        catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return stadiumTeam;
    }

    public void fixGrass(String team) {
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate
                    ("update team set money = money - "+ 10*(100 - getByTeam(team).grassQuality)+ " where name = '"+team+"\'");
            s.close();

            Statement s2 = c.createStatement();
            s2.executeUpdate
                    ("update team_stadium set grass_quality = 1 where team_name = '"+team+"\'" );
            s2.close();

            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void fixSeat(String team) {
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate
                    ("update team set money = money - "+ 15*(100 - getByTeam(team).seatQuality)+ " where name = '"+team+"\'");
            s.close();

            Statement s2 = c.createStatement();
            s2.executeUpdate
                    ("update team_stadium set seat_quality = 1 where team_name = '"+team+"\'" );
            s2.close();

            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void fixToilet(String team) {
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate
                    ("update team set money = money - "+ 20*(100 - getByTeam(team).toiletQuality)+ " where name = '"+team+"\'" );
            s.close();

            Statement s2 = c.createStatement();
            s2.executeUpdate
                    ("update team_stadium set toilet_quality = 1 where team_name = '"+team+"\'" );
            s2.close();

            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void buyStadium(Stadium stadium, String team) {
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s1 = c.createStatement();
            s1.executeUpdate
                    ("update team set money = money - "+ stadium.getPrice() + " where name = \'"+team+"\'" );
            s1.close();

            Statement s2 = c.createStatement();
            s2.executeUpdate
                    ("with upsert as (" +
                            "  update team_stadium" +
                            "  set (stadium_name, team_name , grass_quality, toilet_quality, seat_quality) = (\'"+stadium+ "\' , \'"+ team + "\' ,1, 1, 1)" +
                            "  where team_name = \'" + team +
                            "\'  returning *)" +
                            "insert into team_stadium (stadium_name, team_name)" +
                            "select \'" +stadium+ "\' , \'"+ team + "\'"+
                            "where not exists (" +
                            "  select 1" +
                            "  from upsert" +
                            "  where upsert.team_name = \'" +team+ "\');");
            s2.close();
            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }
}
