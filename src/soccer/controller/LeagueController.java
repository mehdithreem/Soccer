package soccer.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import soccer.Session;
import soccer.SharedData;
import soccer.data.LeagueRepo;
import soccer.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class LeagueController {

    @FXML private ComboBox<Integer> leagueCB;
    @FXML private TableView<Game> games;

    @FXML
    private void initialize() {
        List<Integer> leagues = LeagueRepo.getRepository().getLeagueByTeam(Session.getSession().getTeam());
        List<Game> gameRows = new ArrayList<>();

        for(Integer lid : leagues) {
            gameRows.addAll(LeagueRepo.getRepository().getLeagueGames(lid));
        }

        TableColumn col0 = new TableColumn("League");
        col0.setCellValueFactory(new PropertyValueFactory<>("league"));

        TableColumn col1 = new TableColumn("Time");
        col1.setCellValueFactory(new PropertyValueFactory<>("start_time"));

        TableColumn col2 = new TableColumn("Host");
        col2.setCellValueFactory(new PropertyValueFactory<>("host_team"));

        TableColumn col3 = new TableColumn("Guest");
        col3.setCellValueFactory(new PropertyValueFactory<>("guest_team"));

        TableColumn col4 = new TableColumn("Winner");
        col4.setCellValueFactory(new PropertyValueFactory<>("is_host_win"));

        col4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Game, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Game, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                String result;
                if (p.getValue().getIs_host_win() == 0)
                    result = "TIE";
                else if (p.getValue().getIs_host_win() > 0)
                    result = p.getValue().getHost_team();
                else
                    result = p.getValue().getGuest_team();
                return new ReadOnlyObjectWrapper(result);
            }
        });

        games.getColumns().addAll(col0,col1,col2,col3,col4);
        games.setItems(FXCollections.observableArrayList(gameRows));

        List<Integer> availableLeagues = LeagueRepo.getRepository().getAvailable(Session.getSession().getTeam());

        leagueCB.setItems(FXCollections.observableArrayList(availableLeagues));
    }

    public void join() {
        Integer lid = leagueCB.getValue();
        LeagueRepo.getRepository().joinLeague(lid, Session.getSession().getTeam());
        SharedData.getData().mainFrameController.ShowLeague();
    }
}
