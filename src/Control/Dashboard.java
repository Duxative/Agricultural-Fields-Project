package Control;

import Botones.Ventanas;
import DB.DBConnection;
import DB.Query;
import Tablas.Dashboard.ProblemaDeCuadro;
import Tablas.Dashboard.Viaje;
import Tablas.Vista;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private JFXButton SieteA, SieteB, Ochoa, Ochob, Inventario, Enfermedades, Recetas;
    @FXML
    private JFXTreeTableView tablaProblemas, tablaViajes;

    private static Connection connection = (Connection) DBConnection.getConnection();

    public static String titulo = "";

    public static String titulo() {
        return titulo;
    }

    public static String cuadro = "";

    public static String cuadro() {
        return cuadro;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Vista.llenarTablasDashboard(tablaProblemas, tablaViajes, connection);
    }

    @FXML
    void btnEnfermedades(MouseEvent event) {
        Ventanas.abrirEnfermedades(event);
    }

    @FXML
    void btnRecetas(MouseEvent event) {
        Ventanas.abrirRecetas(event);
    }

    @FXML
    void btnInventario(MouseEvent event) {
        Ventanas.abrirInventario();
    }

    @FXML
    void abrirCuadro(MouseEvent event) throws SQLException {
        titulo = "Cuadro " + Ventanas.tomarNombreDeBoton(event).toString() + " " + Query.Cultivo(connection);
        cuadro = Ventanas.tomarNombreDeBoton(event);
        Ventanas.abrirCuadro(event);
    }

    @FXML
    void abrirVentaAccion(KeyEvent key) {
        switch (key.getCode()) {
            case F2:
                Ventanas.abrirAddAction();
                break;
            case F3:
                Ventanas.abrirViaje();
                break;
            case F5:
                Ventanas.abrirDashboard(Inventario);
                break;
        }
    }

    @FXML
    void deleteV(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            Viaje.eliminarConTecla(tablaViajes,connection);
        }
    }
    @FXML
    void deleteP(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            ProblemaDeCuadro.eliminarConTecla(tablaProblemas,connection);
        }
    }

}
