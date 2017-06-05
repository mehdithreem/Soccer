package soccer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import soccer.Main;
import soccer.Session;
import soccer.SharedData;
import soccer.data.TeamRepo;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class SelectTeamController {

    @FXML private ChoiceBox<String> CurrentTeamsCB;
    @FXML private TextField TeamNameTF;

    @FXML
    private void initialize() {
        CurrentTeamsCB.setItems(FXCollections.observableArrayList(TeamRepo.getRepository().getAllTeamsNames()));
    }

    public void Enter() {
        Session.getSession().setTeam(CurrentTeamsCB.getValue());
        SharedData.getData().main.getPrimaryStage().close();
        SharedData.getData().main.initMainFrame();
    }

    public void CreateTeam() {
        TeamRepo.getRepository().createTeam(TeamNameTF.getText());
        this.initialize();
        TeamNameTF.setText("");
    }
}
