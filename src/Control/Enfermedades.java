package Control;

import Botones.Accion;
import DB.DBConnection;
import DB.Query;
import Tablas.Vista;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.llenarComboBoxDaños(cb1,cb2,cb3,cb4,cb5,cb6,connection);
    }
    @FXML
    void btnDash(MouseEvent event){
        Accion.abrirDashboard(event);
    }
    @FXML
    void btnRecetas(MouseEvent event){
        Accion.abrirRecetas(event);
    }

    @FXML
    void btnInventario(MouseEvent event) throws SQLException {
        Accion.abrirInventario();
    }
    @FXML
    void agregarReceta1(MouseEvent event) {
        Query.insertDaño(text1,s1,c1,cb1);
    }
    @FXML
    void agregarReceta2(MouseEvent event) {
        Query.insertDaño(text2,s2,c2,cb2);
    }
    @FXML
    void agregarReceta3(MouseEvent event) {
        Query.insertDaño(text3,s3,c3,cb3);
    }
    @FXML
    void agregarReceta4(MouseEvent event) {
        Query.insertDaño(text4,s4,c4,cb4);
    }
    @FXML
    void agregarReceta5(MouseEvent event) {
        Query.insertDaño(text5,s5,c5,cb5);
    }
    @FXML
    void agregarReceta6(MouseEvent event) {
        Query.insertDaño(text6,s6,c6,cb6);
    }
    @FXML
    void abrirVentaAccion(KeyEvent F2){
        if (F2.getCode().equals(KeyCode.F2)){
            Accion.abrirAddAction();
        }
    }

}
