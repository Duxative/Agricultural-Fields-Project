package Control;

import DB.DBConnection;
import Tablas.Arbol;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VistaCuadro implements Initializable {

    private static Connection connection = (Connection) DBConnection.getConnection();

    @FXML
    private JFXTreeTableView tablaArbol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    Arbol.llenarTabla(tablaArbol,connection);
    }

    @FXML
    void Salir(MouseEvent click){
        Arbol.llenarTabla(tablaArbol,connection);
    }
}
