package Botones;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ventanas implements Initializable {
// Acciones de ventana en general


    public static String tomarNombreDeBoton(JFXButton btn) {
        return btn.getText();
    }

    public static String tomarNombreDeBoton(MouseEvent event) {
        int fin = event.getSource().toString().length() - 1;
        int inicio = event.getSource().toString().indexOf("'") + 1;

        return event.getSource().toString().substring(inicio, fin);

    }

    public static void abrirVentana(String path, String Title, int width, int height) {
        Stage newScreen = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Ventanas.class.getResource(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, width, height);
        newScreen.setScene(scene);
        newScreen.setTitle(Title);
        newScreen.setResizable(false);
        newScreen.show();

    }


    public static void cerrarVentanaActual(MouseEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        Stage current = (Stage) btn.getScene().getWindow();
        current.close();
    }

    public static void cerrarVentanaActual(JFXButton btn) {

        Stage current = (Stage) btn.getScene().getWindow();
        current.close();
    }

    // Acciones de ventanas especificas
    public static void abrirInventario() {
        abrirVentana("/Vistas/Inventario.fxml", "Inventario", 900, 600);
    }

    public static void abrirEnfermedades(MouseEvent event) {
        abrirVentana("/Vistas/Enfermedades.fxml", "Enfermedades", 1366, 768);
        cerrarVentanaActual(event);
    }

    public static void abrirRecetas(MouseEvent event) {
        abrirVentana("/Vistas/Recetas.fxml", "Recetas", 1366, 768);
        cerrarVentanaActual(event);
    }

    public static void abrirCuadro(MouseEvent event) {
        abrirVentana("/Vistas/VistaCuadro.fxml", "Vista de cuadros", 900, 600);

    }

    public static void abrirDashboard(MouseEvent event) {
        abrirVentana("/Vistas/Dashboard.fxml", "Dashboard", 1366, 768);
        cerrarVentanaActual(event);
    }

    public static void abrirDashboard(JFXButton btn) {

        abrirVentana("/Vistas/Dashboard.fxml", "Dashboard", 1366, 768);
        cerrarVentanaActual(btn);
    }

    public static void abrirAddAction() {
        abrirVentana("/Vistas/AddAction.fxml", "Acciones", 310, 250);
    }

    public static void abrirViaje() {
        abrirVentana("/Vistas/Viajes.fxml", "Viajes", 310, 280);
    }

    public static void abrirVariedad() {
        abrirVentana("/Vistas/Variedad.fxml", "Viajes", 180, 280);
    }

    public static void popAlert(StackPane stackPane, String titulo, String texto) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text(titulo));
        dialogLayout.setBody(new Text(texto));
        JFXButton ok = new JFXButton("Ok");
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(ok);
        dialog.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
