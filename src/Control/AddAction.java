package Control;

import DB.DBConnection;
import Tablas.Vista;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddAction implements Initializable {
    @FXML
    JFXDatePicker fecha;
    @FXML
    JFXTextField accion,producto,descripcion,condicionesClimaticas;
    @FXML
    JFXComboBox receta,cuadro;
    @FXML
    private StackPane stackPane;
    private static Connection connection = (Connection) DBConnection.getConnection();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fecha.setValue(LocalDate.now());
        Vista.llenarComboCuadros(cuadro,connection);
        Vista.llenarComboBoxRecetasCuadro(receta,connection);

    }

@FXML
    void agregar(){
    Vista.agregarFileAccion(stackPane,accion,producto,descripcion,condicionesClimaticas,receta,cuadro,fecha);
    }
}
