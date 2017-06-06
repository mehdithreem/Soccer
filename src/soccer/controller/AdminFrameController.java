package soccer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import soccer.Main;
import soccer.Session;
import soccer.SharedData;

import java.io.IOException;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class AdminFrameController {

    @FXML
    private void initialize() {

    }

    public void exit() {
        SharedData.getData().main.getPrimaryStage().close();
        SharedData.getData().main.initSelectTeam();
    }

    public void ShowLeague() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("presentation/AdminLeague.fxml"));
            AnchorPane stadium = loader.load();

            // Set person overview into the center of root layout.
            SharedData.getData().main.getRootLayout().setCenter(stadium);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
