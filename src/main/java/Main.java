import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;



public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws IOException {

        //Loads fxml file from resources folder to open Javafx GUI

        FXMLLoader loader = new FXMLLoader(getClass().getResource("genius.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("genius.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GENIUS!");
        primaryStage.show();

    }

    //Launches Javafx GUI

    public static void main(String[] args)
    {

        launch(args);

    }

        }






