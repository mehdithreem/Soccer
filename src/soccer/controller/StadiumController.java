package soccer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import soccer.Session;
import soccer.SharedData;
import soccer.data.StadiumRepo;
import soccer.model.Stadium;
import soccer.model.StadiumTeam;

import java.text.DecimalFormat;


/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class StadiumController {
    @FXML private Text stadiumName;
    @FXML private Text price;
    @FXML private Text capacity;
    @FXML private Text grassPercent;
    @FXML private Text toiletPercent;
    @FXML private Text seatPercent;

    @FXML private TableView stadiumTable;
    @FXML private TableColumn tableName;
    @FXML private TableColumn tableCapacity;
    @FXML private TableColumn tablePrice;

    @FXML
    private void initialize() {
        StadiumTeam stadiumTeam = StadiumRepo.getRepository().getByTeam(Session.getSession().getTeam());

        stadiumName.setText(stadiumTeam.stadiumName);
        price.setText(String.valueOf(stadiumTeam.price));
        capacity.setText(String.valueOf(stadiumTeam.capacity));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        grassPercent.setText(df.format(stadiumTeam.grassQuality*100)+"%");
        toiletPercent.setText(df.format(stadiumTeam.toiletQuality*100)+"%");
        seatPercent.setText(df.format(stadiumTeam.seatQuality*100)+"%");

        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        stadiumTable.setItems(FXCollections.observableArrayList(StadiumRepo.getRepository().getAvailableStadiums()));
    }

    public void fixGrass() {
        StadiumRepo.getRepository().fixGrass(Session.getSession().getTeam());
        SharedData.getData().mainFrameController.ShowStadium();
    }

    public void fixSeat() {
        StadiumRepo.getRepository().fixSeat(Session.getSession().getTeam());
        SharedData.getData().mainFrameController.ShowStadium();
    }

    public void fixToilet() {
        StadiumRepo.getRepository().fixToilet(Session.getSession().getTeam());
        SharedData.getData().mainFrameController.ShowStadium();
    }

    public void buyStadium() {
        Stadium stadium = (Stadium) stadiumTable.getSelectionModel().getSelectedItem();

        StadiumRepo.getRepository().buyStadium(stadium, Session.getSession().getTeam());
        SharedData.getData().mainFrameController.ShowStadium();
    }

}
