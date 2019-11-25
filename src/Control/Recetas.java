package Control;

import Botones.Ventanas;
import DB.DBConnection;
import DB.Query;
import Tablas.Vista;
import Validaciones.Validar;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Recetas implements Initializable {
    private static Connection connection = (Connection) DBConnection.getConnection();

    @FXML
    private Text text1, text2, text3, text4, text5, text6;
    @FXML
    private JFXTextField d1, d2, d3, d4, d5, d6;
    @FXML
    private JFXComboBox cb1, cb2, cb3, cb4, cb5, cb6, c1, c2, c3, c4, c5, c6;
    @FXML
    private JFXButton dashboard, enfermedades;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.llenarComboBoxRecetas(cb1, cb2, cb3, cb4, cb5, cb6, connection);
        Vista.llenarComboBoxCuadros(c1, c2, c3, c4, c5, c6, connection);

    }

    @FXML
    void btnDash(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/Dashboard.fxml"));
        Scene scene = dashboard.getScene();
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
    void btnEnfermedades(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/Enfermedades.fxml"));
        Scene scene = enfermedades.getScene();
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
    void btnInventario(MouseEvent event) throws SQLException {
        Ventanas.abrirInventario();
    }

    @FXML
    void agregarReceta1(MouseEvent event) {
        if (d1.getText().isEmpty() || c1.getSelectionModel().getSelectedItem() == null || cb1.getSelectionModel().getSelectedItem() == null) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertAction(text1, d1, c1, cb1);
        }
    }

    @FXML
    void agregarReceta2(MouseEvent event) {
        if (d2.getText().isEmpty() || c2.getSelectionModel().getSelectedItem() == null || cb2.getSelectionModel().getSelectedItem() == null) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertAction(text2, d2, c2, cb2);
        }

    }

    @FXML
    void agregarReceta3(MouseEvent event) {
        if (d3.getText().isEmpty() || c3.getSelectionModel().getSelectedItem() == null || cb3.getSelectionModel().getSelectedItem() == null) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertAction(text3, d3, c3, cb3);
        }

    }

    @FXML
    void agregarReceta4(MouseEvent event) {
        if (d4.getText().isEmpty() || c4.getSelectionModel().getSelectedItem() == null || cb4.getSelectionModel().getSelectedItem() == null) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertAction(text4, d4, c4, cb4);
        }
    }

    @FXML
    void agregarReceta5(MouseEvent event) {
        if (d5.getText().isEmpty() || c5.getSelectionModel().getSelectedItem() == null || cb5.getSelectionModel().getSelectedItem() == null) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertAction(text5, d5, c5, cb5);
        }

    }

    @FXML
    void agregarReceta6(MouseEvent event) {
        if (d6.getText().isEmpty() || c6.getSelectionModel().getSelectedItem() == null || cb6.getSelectionModel().getSelectedItem() == null) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertAction(text6, d6, c6, cb6);
        }

    }

    @FXML
    void abrirVentaAccion(KeyEvent F2) {
        if (F2.getCode().equals(KeyCode.F2)) {
            Ventanas.abrirAddAction();
        }
    }
}
