package soccer.data;

import soccer.model.Dealer;

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

    public Dealer getByTeam(String name, Integer currentYear) {
        // #TODO implement
        return new Dealer("a", 3.4, 31);
    }

    public List<Dealer> getAvailable(Integer currentYear) {
        // #TODO implement
        return new ArrayList<>();
    }

    public void buyDealer(Dealer dealer, Integer currentYear) {
        // #TODO implement
    }
}
