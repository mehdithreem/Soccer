package soccer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import soccer.data.StadiumRepo;


/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class StadiumController {
    @FXML Text stadiumName;
    @FXML Text price;
    @FXML Text capacity;
    @FXML Text grassPercent;
    @FXML Text toiletPercent;
    @FXML Text seatPercent;

    @FXML TableView stadiumTable;
    @FXML TableColumn tableName;
    @FXML TableColumn tableCapacity;
    @FXML TableColumn tablePrice;

    @FXML
    private void initialize() {
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        stadiumTable.setItems(FXCollections.observableArrayList(StadiumRepo.getRepository().getAvailableStadiums()));
    }

    public void fixGrass() {

    }

    public void fixSeat() {

    }

    public void fixToilet() {

    }

    public void buyStadium() {

    }

}
