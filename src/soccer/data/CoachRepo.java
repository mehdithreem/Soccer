package soccer.data;

import soccer.ConnectionFactory;
import soccer.model.Coach;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class CoachRepo {
    private static CoachRepo theRepository = new CoachRepo();
    public static CoachRepo getRepository() {
        return theRepository;
    }

    public List<Coach> getByTeam(String team) {
        List<Coach> result = new ArrayList<>();
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from contract con, coach c, person p where " +
                                                " con.team = \'"+ team+"\' AND c.name = p.name and con.person = c.name");

            while (rs.next()){
                Coach coach = new Coach(
                        rs.getString("name"),
                        rs.getString("duty"),
                        rs.getInt("exprience"),
                        rs.getInt("price")
                );
                result.add(coach);
            }
            rs.close();
            s.close();
            c.close();
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return result;
    }

    public List<Coach> getAll(){
        List<Coach> result = new ArrayList<>();
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from coach c , person p where p.name = c.name");

            while (rs.next()){
                Coach coach = new Coach(
                        rs.getString("name"),
                        rs.getString("duty"),
                        rs.getInt("exprience"),
                        rs.getInt("price")
                );
                result.add(coach);
            }
            rs.close();
            s.close();
            c.close();
        } catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return result;
    }

    public List<Coach> getFreeCoaches(Integer year) {
        List<Coach> coaches = getAll();
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select name from coach, contract where person = name AND year ="+year);

            while(rs.next()){
                String name = rs.getString("name");
                for(Coach coach : coaches)
                    if(coach.getName().equals(name)){
                        coaches.remove(coach);
                        break;
                    }
            }
            rs.close();
            s.close();
            c.close();

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return coaches;
    }

    public void buyCoach(String name, String team, Integer year) {
        System.out.println(name+" ,"+team+" ,"+year);
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate("select buy_coach(\'"+name+"\' ,"+year+", \'"+team+"\')");
            s.close();

            Statement s2 = c.createStatement();
            s2.executeUpdate("select influence_all (\'" +team+ "\' , \'" +name+ "\')");

            s2.close();
            c.close();

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
