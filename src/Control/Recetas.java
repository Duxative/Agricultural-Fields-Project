package Control;

import Botones.Accion;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Recetas implements Initializable {

    private JFXButton Inventario,Enfermedades,Dashboard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnDash(MouseEvent event){
        Accion.abrirDashboard(event);
    }
    @FXML
    void btnEnfermedades(MouseEvent event){
        Accion.abrirEnfermedades(event);
    }

    @FXML
    void btnInventario(MouseEvent event){
        Accion.abrirInventario();
    }

    @FXML
    void agregarReceta(MouseEvent event){


    }

}
