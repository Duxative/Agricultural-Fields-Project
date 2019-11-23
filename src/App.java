import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.   load(getClass().getResource("/Vistas/Dashboard.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Manage Software");
        primaryStage.getIcons().add(new Image("Images/icono.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
