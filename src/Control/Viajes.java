package Control;

import DB.DBConnection;
import Tablas.Vista;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Viajes implements Initializable {
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXTextField destino,conductor,comentario;
    @FXML
    private JFXDatePicker fechaViaje;
    @FXML
    private JFXComboBox estado;
    private static Connection connection = (Connection) DBConnection.getConnection();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.llenarComboEstado(estado,connection);
        fechaViaje.setValue(LocalDate.now());
    }

    @FXML
    void agregar(){
        Vista.agregarFilaViajes(stackPane,estado,destino,fechaViaje,conductor,comentario);
        destino.clear();
        conductor.clear();
        comentario.clear();
    }
}
