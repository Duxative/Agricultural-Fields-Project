package Control;

import Botones.Accion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private JFXButton SieteA,SieteB,Ochoa,Ochob,Inventario,Enfermedades,Recetas;
    @FXML
    private JFXTreeTableView tablaProblemas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    void btnEnfermedades(MouseEvent event){
        Accion.abrirEnfermedades(event);
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
    void abrirCuadro(MouseEvent event){
        Accion.abrirCuadro(event);

    }

}
