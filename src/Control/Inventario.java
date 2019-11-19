package Control;

import DB.DBConnection;
import Tablas.Arbol;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Inventario implements Initializable {
    private static Connection connection = (Connection) DBConnection.getConnection();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnAddArticle(MouseEvent click){

    }

    @FXML
    void btnAdd(MouseEvent click){

    }

    @FXML
    void btnRemove(MouseEvent click){

    }
}
