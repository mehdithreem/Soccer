package soccer.data;

import soccer.model.Player;

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
        // #TODO implement
        return new ArrayList<>();
    }

    public List<Player> getAvailables(Integer year) {
        // #TODO implemnet
        return new ArrayList<>();
    }

    public void updateRolesAndShirts(List<Player> players, String Team) {
        // #TODO implement
    }

    public void sellPlayer(Player player, Integer year) {
        // #TODO implement
    }

    public void buyPlayer(Player player, String Team, Integer year) {
        // #TODO implement
    }
}
