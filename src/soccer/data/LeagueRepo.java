package soccer.data;

import soccer.model.Game;

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

    public List<String> getAvailable() {
        // #TODO implement
        return new ArrayList<>();
    }

    public List<Game> getLeagueGames(Integer league_id) {
        // #TODO implement
        return new ArrayList<>();
    }

    public List<Integer> getLeagueByTeam(String team) {
        // #TODO implement
        return new ArrayList<>();
    }

    public void joinLeague(Integer lid, String team) {
        // #TODO implement
    }
}
