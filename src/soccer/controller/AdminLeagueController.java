package soccer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import soccer.SharedData;
import soccer.data.LeagueRepo;
import soccer.model.PendingLeague;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class AdminLeagueController {

    @FXML ComboBox<Integer> league;
    @FXML TextField round;

    @FXML TableView openLeagues;
    @FXML TableColumn openLeagueID;
    @FXML TableColumn membersCount;

    @FXML
    private void initialize() {
        league.setItems(FXCollections.observableArrayList(
                LeagueRepo.getRepository().getClosedNotDone()
        ));

        openLeagueID.setCellValueFactory(new PropertyValueFactory<>("league"));
        membersCount.setCellValueFactory(new PropertyValueFactory<>("memberCount"));

        openLeagues.setItems(FXCollections.observableArrayList(
                LeagueRepo.getRepository().getPendingLeagues()
        ));
    }

    public void createNewLeague() {
        LeagueRepo.getRepository().createNew(SharedData.getData().currentYear);
        SharedData.getData().adminFrameController.ShowLeague();
    }

    public void playGames() {
        LeagueRepo.getRepository().playRound(league.getValue(), Integer.valueOf(round.getText()));
        SharedData.getData().adminFrameController.ShowLeague();
    }

    public void startLeague() {
        PendingLeague pendingLeague = (PendingLeague) openLeagues.getSelectionModel().getSelectedItem();
        LeagueRepo.getRepository().startLeague(pendingLeague.getLeague());
        SharedData.getData().adminFrameController.ShowLeague();
    }
}
