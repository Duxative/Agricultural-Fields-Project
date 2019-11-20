package Control;

import Botones.Accion;
import DB.DBConnection;
import DB.Query;
import Tablas.Dashboard.ProblemaDeCuadro;
import Tablas.Dashboard.Viaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private JFXButton SieteA,SieteB,Ochoa,Ochob,Inventario,Enfermedades,Recetas;
    @FXML
    private JFXTreeTableView tablaProblemas,tablaViajes;

    private static Connection connection = (Connection) DBConnection.getConnection();

    public static String titulo = "";
    public static String titulo(){
        return titulo;
    }
    public static String cuadro = "";
    public static String cuadro(){
        return cuadro;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ProblemaDeCuadro.llenarTabla(tablaProblemas,connection);
        Viaje.llenarTabla(tablaViajes,connection);
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
    void abrirCuadro(MouseEvent event) throws SQLException {
        titulo = "Cuadro "+ Accion.tomarNombreDeBoton(event).toString()+" "+Query.Cultivo(connection);
        cuadro = Accion.tomarNombreDeBoton(event);
        Accion.abrirCuadro(event);
    }

}
