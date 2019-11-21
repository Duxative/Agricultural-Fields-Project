package Control;

import Botones.Accion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class VistaEnfermedad implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    void btnInventario(MouseEvent event){
        Accion.abrirInventario();
    }
    @FXML
    void abrirVentaAccion(KeyEvent F2){
        if (F2.getCode().equals(KeyCode.F2)){
            Accion.abrirAddAction();
        }
    }


}
