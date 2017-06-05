package soccer.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import soccer.Session;
import soccer.SharedData;
import soccer.data.DealerRepo;
import soccer.model.Dealer;

/**
 * Created by mehdithreem on 6/6/2017 AD.
 */
public class DealerController {

    @FXML private Text dealerName;
    @FXML private Text leverage;

    @FXML private TableView available;
    @FXML private TableColumn name;
    @FXML private TableColumn availableLeverage;
    @FXML private TableColumn price;

    @FXML
    public void initialize() {
        Dealer dealer = DealerRepo.getRepository().getByTeam(Session.getSession().getTeam(), SharedData.getData().currentYear);

        dealerName.setText(dealer.getName());
        leverage.setText(String.valueOf(dealer.getLeverage() * 100) + "%");

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableLeverage.setCellValueFactory(new PropertyValueFactory<>("leverage"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        available.setItems(FXCollections.observableArrayList(DealerRepo.getRepository().getAvailable(SharedData.getData().currentYear)));
    }

    public void buy() {
        Dealer dealer = (Dealer) available.getSelectionModel().getSelectedItem();
        DealerRepo.getRepository().buyDealer(dealer, SharedData.getData().currentYear);
        SharedData.getData().mainFrameController.ShowDealer();
    }
}
