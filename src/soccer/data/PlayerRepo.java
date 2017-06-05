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
        List<Player> result = new ArrayList<>();

        result.add(new Player("Mahdi", "Goalkeeper", 12,12,12,12,
                12,12,12,12,12,"bench", 1200));
        result.add(new Player("Mahdi2", "Goalkeeper", 12,12,12,12,
                12,12,12,12,12,"forward", 1200));
        return result;
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
