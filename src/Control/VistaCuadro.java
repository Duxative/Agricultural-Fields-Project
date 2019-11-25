package Control;

import Botones.Ventanas;
import DB.DBConnection;
import Tablas.*;
import Tablas.Cuadro.Arbol;
import Tablas.Cuadro.Daños;
import Tablas.Cuadro.Historial;
import Tablas.Cuadro.Terreno;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
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
import java.util.ResourceBundle;

public class VistaCuadro implements Initializable {

    private static Connection connection = (Connection) DBConnection.getConnection();

    @FXML
    private JFXTreeTableView tablaArbol, cuadroTerreno, cuadroDaño, cuadroHistorial;
    @FXML
    private Text titulo;
    @FXML
    private JFXTextField arbol,terreno,daños,historial;
    @FXML
    private JFXComboBox cuadro;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.llenarTablasDeCuadro(tablaArbol,cuadroDaño,cuadroTerreno,cuadroHistorial,connection);
        Vista.establecerTitulo(titulo);
        Vista.establecerInformacionDeTablas(arbol,daños,terreno,historial,tablaArbol,cuadroDaño,cuadroTerreno,cuadroHistorial);
        Vista.llenarComboCuadros(cuadro,connection);



    }

    @FXML
    void actualizar(MouseEvent click){

    }
    @FXML
    void abrirVentaAccion(KeyEvent F2){
        if (F2.getCode().equals(KeyCode.F2)){
            Ventanas.abrirAddAction();
        }
    }
    @FXML
    void deleteArbol(KeyEvent event){
        if (event.getCode().equals(KeyCode.DELETE)){
            Arbol.eliminarConTecla(tablaArbol,connection);
        }
    }
    @FXML
    void deleteTerreno(KeyEvent event){
        if (event.getCode().equals(KeyCode.DELETE)){
            Terreno.eliminarConTecla(cuadroTerreno,connection);
        }
    }
    @FXML
    void deleteHistorial(KeyEvent event){
        if (event.getCode().equals(KeyCode.DELETE)){
            Historial.eliminarConTecla(cuadroHistorial,connection);
        }
    }
    @FXML
    void deleteDaños(KeyEvent event){
        if (event.getCode().equals(KeyCode.DELETE)){
            Daños.eliminarConTecla(cuadroDaño,connection);
        }
    }

}
