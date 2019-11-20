package Tablas.Inventario;

import DB.DBConnection;
import Tablas.Cuadro.Arbol;
import Tablas.Dashboard.Viaje;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.lang.invoke.SwitchPoint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventario extends RecursiveTreeObject<Inventario> {
    StringProperty categoria, producto,cantidad,descripcion,estado;

    public Inventario(String categoria,String producto, String cantidad, String descripcion, String estado) {

        this.producto = new SimpleStringProperty(producto);
        this.cantidad =  new SimpleStringProperty(cantidad);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.estado = new SimpleStringProperty(estado);
        this.categoria = new SimpleStringProperty(categoria);
    }

    public static void llenarTabla(JFXTreeTableView treeView, Connection connection){
        JFXTreeTableColumn<Inventario, String> producto= new JFXTreeTableColumn<>("Producto");
        producto.setPrefWidth(100);
        producto.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inventario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inventario, String> param) {
                return param.getValue().getValue().producto;
            }
        });

        JFXTreeTableColumn<Inventario, String> cantidad= new JFXTreeTableColumn<>("Cantidad");
        cantidad.setPrefWidth(100);
        cantidad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inventario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inventario, String> param) {
                return param.getValue().getValue().cantidad;
            }
        });
        JFXTreeTableColumn<Inventario, String> descripcion= new JFXTreeTableColumn<>("Descripcion");
        descripcion.setPrefWidth(100);
        descripcion.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inventario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inventario, String> param) {
                return param.getValue().getValue().descripcion;
            }
        });
        JFXTreeTableColumn<Inventario, String> estado= new JFXTreeTableColumn<>("Estado");
        estado.setPrefWidth(100);
        estado.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inventario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inventario, String> param) {
                return param.getValue().getValue().estado;
            }
        });
        JFXTreeTableColumn<Inventario, String> categoria= new JFXTreeTableColumn<>("");
        categoria.setPrefWidth(0);
        categoria.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Inventario, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Inventario, String> param) {
                return param.getValue().getValue().categoria;
            }
        });

        ObservableList<Inventario> inventarios = FXCollections.observableArrayList();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM inventario");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventarios.add(new Inventario(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException ex) {

            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Inventario> root = new RecursiveTreeItem<Inventario>(inventarios, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(producto,cantidad,descripcion,estado,categoria);

        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }
    public static void eliminarConTecla(JFXTreeTableView treeView,Connection connection){
        int row = treeView.getSelectionModel().getSelectedIndex();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM SELECT * FROM inventario");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while (rs.next()){
                if ( aux == row){
                    try {
                        Connection con = (Connection) DBConnection.getConnection();
                        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("DELETE FROM SELECT * FROM inventario WHERE id=?");
                        preparedStatement.setString(1, rs.getString(1));
                        preparedStatement.executeUpdate();
                    }catch (SQLException e){e.printStackTrace();}
                }
                aux++;
            }
        } catch (SQLException ex) {

        }
    }

    public static void buscarArbol(JFXTextField textField, JFXTreeTableView treeView){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeView.setPredicate(new Predicate<TreeItem<Inventario>>() {
                    @Override
                    public boolean test(TreeItem<Inventario> treeItem) {
                        boolean flag= treeItem.getValue().categoria.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }
    public static void vaciarComboBox(JFXComboBox comboBox, Connection connection) {
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM inventario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (comboBox.getItems().isEmpty()) {
                } else {
                    comboBox.getItems().remove(0);
                }

            }

        } catch (SQLException ex) {

        }
    }

}
