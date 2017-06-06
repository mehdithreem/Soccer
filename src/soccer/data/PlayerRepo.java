package soccer.data;

import soccer.ConnectionFactory;
import soccer.model.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class PlayerRepo {
    private static PlayerRepo theRepository = new PlayerRepo();
    public static PlayerRepo getRepository() {
        return theRepository;
    }

    public List<Player> getByTeam(String team, Integer year) {
        List<Player> result = new ArrayList<>();

        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery
                    ("select * from contract con, player plyr , person p " +
                            "where con.team =\'"+team+
                            "\' AND con.year = "+year+
                            " AND con.person = plyr.name" +
                            " AND plyr.name = p.name");

            while ( rs.next() ) {
                Player player =new Player (rs.getString("name"),
                        rs.getString("expertise"),
                        rs.getInt("amadegi"),
                        rs.getInt("ghodrat_badani"),
                        rs.getInt("ghodrat_pass"),
                        rs.getInt("toop_giri"),
                        rs.getInt("ghodrat_golzani"),
                        rs.getInt("ghodrat_shoot"),
                        rs.getInt("sorat"),
                        rs.getInt("darvazebani") );

                player.setPrice(rs.getInt("price"));
                result.add(player);
            }
            rs.close();
            s.close();

            s = c.createStatement();
            rs = s.executeQuery("select * from plays_in where team = \'" + team+ "\'");
            while ( rs.next() ) {
                String name = rs.getString("player");
                for (Player player : result)
                    if(player.getName().equals(name)){
                        player.setShirtNumber(rs.getInt("shirt_number"));
                        player.setRole(rs.getString("role"));
                        break;
                    }
            }
            rs.close();
            s.close();
            c.close();

        }
        catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return result;
    }

    public List<Player> getAll(){
        List<Player> result = new ArrayList<>();
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from person p , player plyr where p.name = plyr.name");
            while (rs.next()){
                Player player =new Player (rs.getString("name"),
                        rs.getString("expertise"),
                        rs.getInt("amadegi"),
                        rs.getInt("ghodrat_badani"),
                        rs.getInt("ghodrat_pass"),
                        rs.getInt("toop_giri"),
                        rs.getInt("ghodrat_golzani"),
                        rs.getInt("ghodrat_shoot"),
                        rs.getInt("sorat"),
                        rs.getInt("darvazebani") );

                player.setPrice(rs.getInt("price"));
                result.add(player);
            }
            rs.close();
            s.close();
            c.close();

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return result;
    }

    public List<Player> getAvailables(Integer year) {
        List<Player> players = getAll();
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select name from player, contract where person = name AND year ="+year);

            while(rs.next()){
                String name = rs.getString("name");
                for(Player player : players)
                    if(player.getName().equals(name)){
                        players.remove(player);
                        break;
                    }
            }
            rs.close();
            s.close();
            c.close();

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return players;
    }

    public void updateRolesAndShirts(List<Player> players, String team) {
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from plays_in where team = \'"+team+"\'");
            while ( rs.next() ) {
                String name = rs.getString("player");
                for (Player player : players)
                    if(player.getName().equals(name)){
                        Statement s2 = c.createStatement();
                        s2.executeUpdate("update plays_in " +
                                "set shirt_number = "+player.getShirtNumber()+
                                ", role = \'" + player.getRole() + "\' " +
                                "where player = \'"+ player.getName() + "\'");
                        s2.close();
                        break;
                    }
            }
            rs.close();
            s.close();
            c.close();

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void sellPlayer(Player player, Integer year) {
        // #TODO implement
    }

    public void buyPlayer(Player player, String Team, Integer year) {
        // #TODO implement
    }
}
