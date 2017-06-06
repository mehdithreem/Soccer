package soccer.data;

import soccer.ConnectionFactory;
import soccer.model.Dealer;
import soccer.model.Player;

import javax.xml.bind.Element;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class DealerRepo {
    private static DealerRepo theRepository = new DealerRepo();
    public static DealerRepo getRepository() {
        return theRepository;
    }

    public Dealer getByTeam(String team, Integer currentYear) {
        Double leverage = 0.0;
        Integer price = 0;
        String name = "";
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery
                    ("select * from contract con, dealer dl , person p " +
                            "where con.person = dl.name AND p.name = con.person " +
                            "AND con.year = "+currentYear +
                            " AND con.team = \'"+team+"\'");
            while (rs.next()) {
                leverage = rs.getDouble("leverage");
                price = rs.getInt("price");
                name = rs.getString("name");
            }
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return new Dealer(name, leverage, price);
    }

    public List<Dealer> getAll(){
        List<Dealer> result = new ArrayList<>();
        try {
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from dealer d , person p where p.name = d.name");
            while (rs.next()) {
                Dealer dealer = new Dealer(
                        rs.getString("name"),
                        rs.getDouble("leverage"),
                        rs.getInt("price"));
                result.add(dealer);
            }
        } catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return result;
    }

    public List<Dealer> getAvailable(Integer currentYear) {
        List<Dealer> dealers = getAll();
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select name from dealer, contract where person = name AND year ="+currentYear);

            while(rs.next()){
                String name = rs.getString("name");
                for(Dealer dealer : dealers)
                    if(dealer.getName().equals(name)){
                        dealers.remove(dealer);
                        break;
                    }
            }
            rs.close();
            s.close();
            c.close();

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return dealers;
    }

    public void buyDealer(Dealer dealer, Integer currentYear, String team ) {
        //System.out.println(dealer.getName() +" ,"+currentYear +" ,"+team);
        try{
            Connection c = ConnectionFactory.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate("select buy_dealer(\'"+dealer.getName()+"\' ,"+currentYear+", \'"+team+"\')");

            s.close();
            c.close();

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
