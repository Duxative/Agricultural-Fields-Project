package Control;

import Botones.Accion;
import DB.DBConnection;
import DB.Query;
import Tablas.Inventario.ControlInvetario;
import Tablas.Vista;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Inventario implements Initializable {
    private static Connection connection = (Connection) DBConnection.getConnection();
    @FXML
    private JFXTreeTableView inventoryTable;
    @FXML
    private JFXComboBox comboAdd,comboRemove;
    @FXML
    private JFXTextField searchField, ArticleField, DescriptionField,quantityField,unitField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.iniciarInventario(comboAdd,comboRemove,inventoryTable,searchField,connection);
    }

    @FXML
    void btnAddArticle(MouseEvent click){
        Vista.agregarFila(ArticleField,quantityField,DescriptionField,unitField,inventoryTable,comboAdd,comboRemove,connection);
    }

    @FXML
    void btnAdd(MouseEvent click){

    }

    @FXML
    void btnRemove(MouseEvent click){

    }
    @FXML
    void abrirVentaAccion(KeyEvent F2){
        if (F2.getCode().equals(KeyCode.F2)){
            Accion.abrirAddAction();
        }
    }
}
