package Botones;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class General {

    public static void abrirVentana(String path, String Title, int width, int height){
    Stage newScreen = new Stage();
    Parent root = null;
    try{
        root = FXMLLoader.load(General.class.getResource(path));

    }catch (IOException e){
        e.printStackTrace();
    }
    Scene scene = new Scene(root,width,height);
    newScreen.setScene(scene);
    newScreen.setTitle(Title);
    newScreen.setResizable(false);
    newScreen.show();

}

    public static void abrirInventario(){
    abrirVentana("/Vistas/Inventario.fxml","Inventario",900,600);
    }


}
