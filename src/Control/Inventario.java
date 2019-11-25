package Control;

import Botones.Ventanas;
import DB.DBConnection;
import DB.Query;
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
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Inventario implements Initializable {
    private static Connection connection = (Connection) DBConnection.getConnection();
    @FXML
    private JFXTreeTableView inventoryTable;
    @FXML
    private JFXComboBox comboAdd,comboRemove,comboCat,comboCat2;
    @FXML
    private JFXTextField searchField, ArticleField, DescriptionField,quantityField,addField,removeField, estado;
    @FXML
    private StackPane stackPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Vista.iniciarInventario(comboCat,comboCat2,comboAdd,comboRemove,inventoryTable,searchField,connection);


    }

    @FXML
    void btnAddArticle(MouseEvent click){
        Vista.agregarFila(stackPane,comboCat2,ArticleField,quantityField,DescriptionField,estado,inventoryTable,comboAdd,comboRemove,connection);
    }

    @FXML
    void btnAdd(MouseEvent click){
        Query.updateSumar(stackPane,connection,comboAdd,addField,inventoryTable);
    }

    @FXML
    void btnRemove(MouseEvent click){
        Query.updateRestar(stackPane,connection,comboRemove,removeField,inventoryTable);
    }
    @FXML
    void abrirVentaAccion(KeyEvent F2){
        if (F2.getCode().equals(KeyCode.F2)){
            Ventanas.abrirAddAction();
        }
    }
    @FXML
    void delete(KeyEvent event){
        if (event.getCode().equals(KeyCode.DELETE)){
            Tablas.Inventario.Inventario.eliminarConTecla(inventoryTable,connection);
        }
    }
}
