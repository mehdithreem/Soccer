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
            ResultSet rs = s.executeQuery("select * from team_stadium where team_name = "+team);

            stadiumTeam.teamName = team;
            stadiumTeam.stadiumName = rs.getString("stadium_name");
            stadiumTeam.grassQuality = rs.getInt("grass_quality");
            stadiumTeam.toiletQuality = rs.getInt("toilet_quality");
            stadiumTeam.seatQuality = rs.getInt("seat_quality");

            rs.close();
            s.close();

            Statement s2 = c.createStatement();
            ResultSet rs2 = s2.executeQuery
                    ("select * from stadium where name = '"+ stadiumTeam.stadiumName +"'");
            stadiumTeam.price = rs2.getInt("price");
            stadiumTeam.capacity = rs2.getInt("capacity");

            System.out.println("StadiumTeam : "+stadiumTeam.teamName + " ," + stadiumTeam.stadiumName+ " ,"+ stadiumTeam.toiletQuality);

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
            ResultSet rs = s.executeQuery
                    ("update team set money = money - "+ 10*(100 - getByTeam(team).grassQuality)+ " where name = "+team );

            rs.close();
            s.close();

            Statement s2 = c.createStatement();
            ResultSet rs2 = s2.executeQuery
                    ("update team_stadium set grass_quality = 100 where team_name = " + team );
            rs2.close();
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
            ResultSet rs = s.executeQuery
                    ("update team set money = money - "+ 15*(100 - getByTeam(team).seatQuality)+ " where name = "+team );

            rs.close();
            s.close();

            Statement s2 = c.createStatement();
            ResultSet rs2 = s2.executeQuery
                    ("update team_stadium set seat_quality = 100 where team_name = " + team );
            rs2.close();
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
            ResultSet rs = s.executeQuery
                    ("update team set money = money - "+ 20*(100 - getByTeam(team).toiletQuality)+ " where name = "+team );

            rs.close();
            s.close();

            Statement s2 = c.createStatement();
            ResultSet rs2 = s2.executeQuery
                    ("update team_stadium set toilet_quality = 100 where team_name = " + team );
            rs2.close();
            s2.close();

            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void buyStadium(String stadium, String team) {
        System.out.println("chete? :"+stadium+team);
        try{
            Connection c = ConnectionFactory.getConnection();

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery
                    ("select * from stadium where name = \'"+ stadium +"\'");
            Integer price = rs.getInt("price");
            System.out.println("Ba inja umad ke! "+price);
            rs.close();
            s.close();
            c.close();


            Connection c1 = ConnectionFactory.getConnection();
            Statement s1 = c.createStatement();
            ResultSet rs1 = s1.executeQuery
                    ("update team set money = money - "+ price + " where name = \'"+team+"\'" );
            rs1.close();
            s1.close();
            System.out.println("inja namiai?! ");
            c1.close();


            Connection c2 = ConnectionFactory.getConnection();
            Statement s2 = c.createStatement();
            ResultSet rs2 = s2.executeQuery
                    ("with upsert as (" +
                            "  update team_stadium" +
                            "  set (stadium_name, team_name , grass_quality, toilet_quality, seat_quality) = (\'"+stadium+ "\' , \'"+ team + "\' ,100, 100, 100)" +
                            "  where team_name = \'" + team +
                            "\'  returning *)" +
                            "insert into stadium_name (stadium_name, team_name, grass_quality, toilet_quality, seat_quality)" +
                            "select \'" +stadium+ "\' , \'"+ team + "\' , 100, 100, 100"+
                            "where not exists (" +
                            "  select 1" +
                            "  from upsert" +
                            "  where upsert.team_name = \'" +team+ "\');");
            rs2.close();
            s2.close();
            c2.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }
}
