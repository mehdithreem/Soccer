package soccer.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import soccer.Session;
import soccer.SharedData;
import soccer.data.PlayerRepo;
import soccer.model.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class PlayerController {
    @FXML private TableView current;
    @FXML private TableView available;

    @FXML
    public void initialize() {
        List<TableColumn> currentCols = new ArrayList<>();

        ObservableList<String> cbValues = FXCollections.observableArrayList("goalkeeper", "bench", "forward", "defender");

        current.setEditable(true);

        TableColumn roleCol = new TableColumn("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleCol.setCellFactory(ComboBoxTableCell.forTableColumn(cbValues));
        roleCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Player, String> t) {
                        ((Player) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRole(t.getNewValue());
                    }
                }
        );
        roleCol.setEditable(true);
        current.getColumns().add(roleCol);

        TableColumn<Player, Integer> shirtCol = new TableColumn("Shirt Number");
        shirtCol.setCellValueFactory(new PropertyValueFactory<Player, Integer>("shirtNumber"));
        shirtCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
        shirtCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Player, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Player, Integer> t) {
                        ((Player) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setShirtNumber(t.getNewValue());
                    }
                }
        );
        shirtCol.setEditable(true);
        current.getColumns().add(shirtCol);

        for(Field f : Player.class.getDeclaredFields()) {
            if (f.getName().equals("shirtNumber") || f.getName().equals("role") || f.getName().equals("price"))
                continue;

            TableColumn col = new TableColumn(f.getName());
            col.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
            currentCols.add(col);
        }

        current.setItems(FXCollections.observableArrayList(
                PlayerRepo.getRepository().getByTeam(Session.getSession().getTeam(), SharedData.getData().currentYear)
        ));

        current.getColumns().addAll(currentCols);

        List<TableColumn> availableCols = new ArrayList<>();

        for(Field f : Player.class.getDeclaredFields()) {
            if (f.getName().equals("role") || f.getName().equals("shirtNumber"))
                continue;

            TableColumn col = new TableColumn(f.getName());
            col.setCellValueFactory(new PropertyValueFactory<>(f.getName()));
            availableCols.add(col);
        }

        available.setItems(FXCollections.observableArrayList(
                PlayerRepo.getRepository().getAvailables(SharedData.getData().currentYear)
        ));

        available.getColumns().addAll(availableCols);
        
    }

    public void sell() {
        Player player = (Player) current.getSelectionModel().getSelectedItem();

        PlayerRepo.getRepository().sellPlayer(player, SharedData.getData().currentYear);
        SharedData.getData().mainFrameController.ShowPlayer();
    }

    public void save() {
        List<Player> players = new ArrayList<>();

        for(Object o : current.getItems())
            players.add((Player) o);

        PlayerRepo.getRepository().updateRolesAndShirts(players, Session.getSession().getTeam());
        SharedData.getData().mainFrameController.ShowPlayer();
    }

    public void buy() {
        Player player = (Player) available.getSelectionModel().getSelectedItem();
        PlayerRepo.getRepository().buyPlayer(player, Session.getSession().getTeam(), SharedData.getData().currentYear);
        SharedData.getData().mainFrameController.ShowPlayer();
    }
}
