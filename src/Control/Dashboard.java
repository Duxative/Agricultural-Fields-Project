package Control;

import Botones.Ventanas;
import DB.DBConnection;
import DB.Query;
import Tablas.Dashboard.Nuez;
import Tablas.Dashboard.ProblemaDeCuadro;
import Tablas.Dashboard.Viaje;
import Tablas.Vista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private JFXButton SieteA, SieteB, Ochoa, Ochob, Inventario, Enfermedades, Recetas;
    @FXML
    private JFXTreeTableView tablaProblemas, tablaViajes;
    @FXML
    private BarChart<?,?> barChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private JFXToggleButton atajos;
    @FXML
    private JFXComboBox variedad;
    @FXML
    private Pane shortcuts;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;

    private static Connection connection = (Connection) DBConnection.getConnection();

    public static String titulo = "";

    public static String titulo() {
        return titulo;
    }

    public static String cuadro = "";

    public static String cuadro() {
        return cuadro;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Nuez.llenarComboVariedad(connection,variedad);
        Nuez.change(variedad,barChart,connection);
        Vista.atajos(atajos,shortcuts);
        Vista.llenarTablasDashboard(tablaProblemas, tablaViajes, connection);
        try {
            Vista.colorCuadro(connection,SieteA,SieteB,Ochoa,Ochob);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnEnfermedades(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/Enfermedades.fxml"));
        Scene scene = Enfermedades.getScene();
        root.translateXProperty().set(scene.getWidth());

        stackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackPane.getChildren().remove(anchorPane);
        });
        timeline.play();
    }

    @FXML
    void btnRecetas(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/Recetas.fxml"));
        Scene scene = Enfermedades.getScene();
        root.translateXProperty().set(scene.getWidth());

        stackPane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackPane.getChildren().remove(anchorPane);
        });
        timeline.play();
    }

    @FXML
    void btnInventario(MouseEvent event) {
        Ventanas.abrirInventario();
    }

    @FXML
    void abrirCuadro(MouseEvent event) throws SQLException {
        titulo = "Cuadro " + Ventanas.tomarNombreDeBoton(event);
        cuadro = Ventanas.tomarNombreDeBoton(event);
        Ventanas.abrirCuadro(event);

    }

    @FXML
    void abrirVentaAccion(KeyEvent key) {
        switch (key.getCode()) {
            case F2:
                Ventanas.abrirAddAction();
                break;
            case F3:
                Ventanas.abrirViaje();
                break;
            case F4:
                Ventanas.abrirVariedad();
                break;
            case F5:
                Ventanas.abrirDashboard(Inventario);
                break;
        }
    }

    @FXML
    void deleteV(KeyEvent event) {
       if (event.getCode().equals(KeyCode.DELETE)) {
            Viaje.eliminarConTecla(tablaViajes,connection);
        }
    }
    @FXML
    void deleteP(KeyEvent event) {
      if (event.getCode().equals(KeyCode.DELETE)) {
            ProblemaDeCuadro.eliminarConTecla(tablaProblemas,connection);
        }
    }

}
