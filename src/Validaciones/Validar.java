package Validaciones;

import Botones.Ventanas;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.StackPane;

public class Validar {
    public static boolean soloNumeros(JFXTextField textField) {
        String text = textField.getText();
        if (text.matches("[0-9]*")) {
            return true;
        } else {
            return false;
        }
    }
    public  static boolean tamañoAdecuado(JFXTextField text) {
        if (text.getText().length() > 20){
            return false;
        }else{
            return true;
        }
    }
    public static void popUpNumeros(StackPane stackPane){
        Ventanas.popAlert(stackPane,"Advertencia!","Solo se aceptan numeros");
    }
    public static void popUpTamaño(StackPane stackPane){
        Ventanas.popAlert(stackPane,"Advertencia!","El tamaño debe de ser de como maximo 20 caracteres");
    }
    public static void popUpElementosVacios(StackPane stackPane){
        Ventanas.popAlert(stackPane,"Advertencia!", "Faltan campos por llenar");
    }
    public static void popUpValorNegativo(StackPane stackPane){
        Ventanas.popAlert(stackPane,"Advertencia!", "Los valores no pueden ser negativos");
    }
}
