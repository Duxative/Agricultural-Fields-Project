package Control;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddAction implements Initializable {
    @FXML
    JFXDatePicker fecha;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
@FXML
    void fechaActual(KeyEvent F1){
        if (F1.getCode().equals(KeyCode.F1)){
            fecha.setValue(LocalDate.now());
        }
}
@FXML
    void agregar(){

}
}
