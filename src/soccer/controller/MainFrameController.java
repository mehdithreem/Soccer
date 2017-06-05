package soccer.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import soccer.Main;
import soccer.Session;
import soccer.SharedData;
import soccer.data.TeamRepo;

import java.io.IOException;

public class MainFrameController {

    @FXML private Text teamName;
    @FXML private Text teamMoney;
    @FXML private Text pageName;

    @FXML
    private void initialize() {
        this.teamName.setText(Session.getSession().getTeam());
        this.pageName.setText("Loading ...");
        updateMoney();
    }

    public void updateMoney() {
        this.teamMoney.setText(String.valueOf(TeamRepo.getRepository().getTeamMoney(Session.getSession().getTeam())));
    }

    public void exit() {
        Session.getSession().setTeam(null);
        SharedData.getData().main.getPrimaryStage().close();
        SharedData.getData().main.initSelectTeam();
    }

    public void ShowStadium() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("presentation/Stadium.fxml"));
            AnchorPane stadium = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            SharedData.getData().main.getRootLayout().setCenter(stadium);
            pageName.setText("Stadium Manager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
