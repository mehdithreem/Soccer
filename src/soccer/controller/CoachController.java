package soccer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import soccer.Session;
import soccer.SharedData;
import soccer.data.CoachRepo;
import soccer.model.Coach;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class CoachController {

    @FXML private TableView current;
    @FXML private TableColumn currentName;
    @FXML private TableColumn currentDuty;
    @FXML private TableColumn currentExperience;

    @FXML private TableView available;
    @FXML private TableColumn availableName;
    @FXML private TableColumn availableDuty;
    @FXML private TableColumn availableExperience;

    @FXML
    private void initialize() {
        currentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        currentDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));
        currentExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));

        current.setItems(FXCollections.observableArrayList(CoachRepo.getRepository().getByTeam(Session.getSession().getTeam())));

        availableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableDuty.setCellValueFactory(new PropertyValueFactory<>("duty"));
        availableExperience.setCellValueFactory(new PropertyValueFactory<>("experience"));

        available.setItems(FXCollections.observableArrayList(CoachRepo.getRepository().getFreeCoaches()));
    }

    public void buySelected() {
        Coach coach = (Coach) available.getSelectionModel().getSelectedItem();

        CoachRepo.getRepository().buyCoach(coach.getName(), Session.getSession().getTeam());
        SharedData.getData().mainFrameController.ShowCoach();
    }
}
