package soccer;

import soccer.controller.AdminFrameController;
import soccer.controller.MainFrameController;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class SharedData {
    private static SharedData theData = new SharedData();
    public static SharedData getData() {
        return theData;
    }

    public Main main;
    public MainFrameController mainFrameController;
    public AdminFrameController adminFrameController;
    public Integer currentYear;
}
