package Control;

import Botones.Ventanas;
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
        Ventanas.abrirDashboard(event);
    }
    @FXML
    void btnRecetas(MouseEvent event){
        Ventanas.abrirRecetas(event);
    }
    @FXML
    void btnInventario(MouseEvent event){
        Ventanas.abrirInventario();
    }
    @FXML
    void abrirVentaAccion(KeyEvent F2){
        if (F2.getCode().equals(KeyCode.F2)){
            Ventanas.abrirAddAction();
        }
    }


}
