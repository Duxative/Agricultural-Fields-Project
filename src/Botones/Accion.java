package Botones;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Accion {
// Acciones de ventana en general


    public static String tomarNombreDeBoton(JFXButton btn){
        return btn.getText();
    }
    public static String tomarNombreDeBoton(MouseEvent event){
        int fin = event.getSource().toString().length()-1;
        int inicio = event.getSource().toString().indexOf("'")+1;

        return event.getSource().toString().substring(inicio,fin);

    }
    public static void abrirVentana(String path, String Title, int width, int height){
    Stage newScreen = new Stage();
    Parent root = null;
    try{
        root = FXMLLoader.load(Accion.class.getResource(path));

    }catch (IOException e){
        e.printStackTrace();
    }
    Scene scene = new Scene(root,width,height);
    newScreen.setScene(scene);
    newScreen.setTitle(Title);
    newScreen.setResizable(false);
    newScreen.show();

}
    public static void cerrarVentanaActual(MouseEvent event){
        JFXButton btn = (JFXButton) event.getSource();
        Stage current = (Stage) btn.getScene().getWindow();
        current.close();
 }

 // Acciones de ventanas especificas
    public static void abrirInventario(){
    abrirVentana("/Vistas/Inventario.fxml","Inventario",900,600);
    }
    public static void abrirEnfermedades(MouseEvent event){abrirVentana("/Vistas/VistaEnfermedad.fxml","Enfermedades",1366,768);cerrarVentanaActual(event);}
    public static void abrirRecetas(MouseEvent event){abrirVentana("/Vistas/Recetas.fxml","Recetas.css",1366,768);cerrarVentanaActual(event);}
    public static void abrirCuadro(MouseEvent event){
        abrirVentana("/Vistas/VistaCuadro.fxml","Cuadro "+tomarNombreDeBoton(event),900,600);

    }
    public static void abrirDashboard(MouseEvent event){abrirVentana("/Vistas/Dashboard.fxml","Dashboard",1366,768);cerrarVentanaActual(event);}



}
