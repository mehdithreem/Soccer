package soccer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import soccer.controller.MainFrameController;
import soccer.controller.SelectTeamController;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public void initMainFrame() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("presentation/MainFrame.fxml"));
            rootLayout = (BorderPane) loader.load();

            MainFrameController controller = loader.getController();
            SharedData.getData().mainFrameController = controller;
            controller.ShowStadium();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initSelectTeam() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("presentation/SelectTeam.fxml"));
            rootLayout = (BorderPane) loader.load();

            SelectTeamController controller = loader.getController();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        SharedData.getData().main = this;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Soccer");
        primaryStage.setResizable(false);
//        primaryStage.setScene(new Scene(root, 300, 275));

//        initMainFrame();
        initSelectTeam();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
