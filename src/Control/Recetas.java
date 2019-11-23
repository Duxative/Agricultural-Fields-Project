package Control;

import Botones.Ventanas;
import DB.DBConnection;
import DB.Query;
import Tablas.Vista;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.llenarComboBoxRecetas(cb1, cb2, cb3, cb4, cb5, cb6, connection);
        Vista.llenarComboBoxCuadros(c1, c2, c3, c4, c5, c6, connection);

    }

    @FXML
    void btnDash(MouseEvent event) {
        Ventanas.abrirDashboard(event);
    }

    @FXML
    void btnEnfermedades(MouseEvent event) {
        Ventanas.abrirEnfermedades(event);
    }

    @FXML
    void btnInventario(MouseEvent event) throws SQLException {
        Ventanas.abrirInventario();
    }

    @FXML
    void agregarReceta1(MouseEvent event) {
        Query.insertAction(text1, d1, c1, cb1);
    }

    @FXML
    void agregarReceta2(MouseEvent event) {
        Query.insertAction(text2, d2, c2, cb2);
    }

    @FXML
    void agregarReceta3(MouseEvent event) {
        Query.insertAction(text3, d3, c3, cb3);
    }

    @FXML
    void agregarReceta4(MouseEvent event) {
        Query.insertAction(text4, d4, c4, cb4);
    }

    @FXML
    void agregarReceta5(MouseEvent event) {
        Query.insertAction(text5, d5, c5, cb5);
    }

    @FXML
    void agregarReceta6(MouseEvent event) {
        Query.insertAction(text6, d6, c6, cb6);
    }

    @FXML
    void abrirVentaAccion(KeyEvent F2) {
        if (F2.getCode().equals(KeyCode.F2)) {
            Ventanas.abrirAddAction();
        }
    }
}
