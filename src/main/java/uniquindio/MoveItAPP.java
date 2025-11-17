package uniquindio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uniquindio.Controller.Navegacion;

public class MoveItAPP extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Navegacion.reiniciarHistorial();
        Navegacion.setStagePrincipal(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/Login.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/ElementosApoyo/MoveItLogo.png")));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        primaryStage.setTitle("MoveIt");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}