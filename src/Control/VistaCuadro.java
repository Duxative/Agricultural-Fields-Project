package Control;

import DB.DBConnection;
import DB.Query;
import Tablas.*;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VistaCuadro implements Initializable {

    private static Connection connection = (Connection) DBConnection.getConnection();

    @FXML
    private JFXTreeTableView tablaArbol, cuadroTerreno, cuadroDaño, cuadroHistorial;
    @FXML
    private Text titulo;
    @FXML
    private JFXTextField arbol,terreno,daños,historial;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.llenarTablasDeCuadro(tablaArbol,cuadroDaño,cuadroTerreno,cuadroHistorial,connection);
        Vista.establecerTitulo(titulo);
       Vista.establecerInformacionDeTablas(arbol,daños,terreno,historial,tablaArbol,cuadroDaño,cuadroTerreno,cuadroHistorial);


    }

    @FXML
    void Salir(MouseEvent click){

    }
}
