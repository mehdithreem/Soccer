package soccer.data;

import soccer.model.Coach;

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
        // #TODO implement
        return new ArrayList<>();
    }

    public List<Coach> getFreeCoaches(Integer year) {
        // #TODO implement
        return new ArrayList<>();
    }

    public void buyCoach(String name, String team, Integer year) {
        // #TODO implement
    }
}
