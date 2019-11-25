package Control;

import DB.DBConnection;
import Tablas.Dashboard.Nuez;
import Tablas.Vista;
import Validaciones.Validar;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Variedad implements Initializable {
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXTextField primera, quebrada, germinada, vana, ruez, aceitosa;
    @FXML
    private JFXComboBox variedad;
    private static Connection connection = (Connection) DBConnection.getConnection();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Nuez.llenarComboVariedad(connection,variedad);
    }

    @FXML
    void agregar() {
        if ((primera.getText().isEmpty() && Validar.soloNumeros(primera) ) || (quebrada.getText().isEmpty() && Validar.soloNumeros(quebrada)) || (germinada.getText().isEmpty() && Validar.soloNumeros(germinada) ) ||
                (vana.getText().isEmpty() && Validar.soloNumeros(vana)) || (ruez.getText().isEmpty() && Validar.soloNumeros(ruez)) || (aceitosa.getText().isEmpty() && Validar.soloNumeros(aceitosa)) ||
                variedad.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        }else{
            Vista.updateGrafica(connection,primera,quebrada,germinada,vana,ruez,aceitosa);
        }

    }
}
