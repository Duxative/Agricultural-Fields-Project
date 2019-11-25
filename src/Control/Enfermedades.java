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

public class Enfermedades implements Initializable {
    public static Connection connection = (Connection) DBConnection.getConnection();
    @FXML
    private Text text1,text2,text3,text4,text5,text6;
    @FXML
    private JFXTextField s1,s2,s3,s4,s5,s6,c1,c2,c3,c4,c5,c6;
    @FXML
    private JFXComboBox cb1,cb2,cb3,cb4,cb5,cb6;
    @FXML
    private JFXButton dashboard,recetas;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Vista.llenarComboBoxCuadros(cb1,cb2,cb3,cb4,cb5,cb6,connection);
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
    void btnRecetas(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/Recetas.fxml"));
        Scene scene = recetas.getScene();
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
        if (s1.getText().isEmpty() || c1.getText().isEmpty() || cb1.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        }else {
            Query.insertDaño(text1,s1,c1,cb1);
        }

    }
    @FXML
    void agregarReceta2(MouseEvent event) {
        if (s2.getText().isEmpty() || c2.getText().isEmpty() || cb2.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        }else {
            Query.insertDaño(text2,s2,c2,cb2);
        }

    }
    @FXML
    void agregarReceta3(MouseEvent event) {
        if (s3.getText().isEmpty() || c3.getText().isEmpty() || cb3.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        }else {
            Query.insertDaño(text3,s3,c3,cb3);
        }

    }
    @FXML
    void agregarReceta4(MouseEvent event) {
        if (s4.getText().isEmpty() || c4.getText().isEmpty() || cb4.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        }else {
            Query.insertDaño(text4,s4,c4,cb4);
        }

    }
    @FXML
    void agregarReceta5(MouseEvent event) {
        if (s5.getText().isEmpty() || c5.getText().isEmpty() || cb5.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        }else {
            Query.insertDaño(text5,s5,c5,cb5);
        }

    }
    @FXML
    void agregarReceta6(MouseEvent event) {
        if (s6.getText().isEmpty() || c6.getText().isEmpty() || cb6.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        }else {
            Query.insertDaño(text6,s6,c6,cb6);
        }

    }
    @FXML
    void abrirVentaAccion(KeyEvent F2){
        if (F2.getCode().equals(KeyCode.F2)){
            Ventanas.abrirAddAction();
        }
    }

}
