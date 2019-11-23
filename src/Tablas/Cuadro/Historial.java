package Tablas.Cuadro;

import DB.DBConnection;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Historial extends RecursiveTreeObject<Historial>{
    StringProperty accion,fecha,cuadro,descripcion,producto,receta,clima;

    public Historial(String accion, String fecha, String cuadro, String descripcion, String producto, String receta, String clima) {
        this.accion = new SimpleStringProperty(accion);
        this.fecha = new SimpleStringProperty(fecha);
        this.cuadro = new SimpleStringProperty(cuadro);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.producto = new SimpleStringProperty(producto) ;
        this.receta = new SimpleStringProperty(receta);
        this.clima = new SimpleStringProperty(clima);
    }

    public static void llenarTabla(JFXTreeTableView treeView, Connection connection){
        JFXTreeTableColumn<Historial, String> parametro= new JFXTreeTableColumn<>("Accion");
        parametro.setPrefWidth(100);
        parametro.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().accion;
            }
        });
        JFXTreeTableColumn<Historial, String> parametro2= new JFXTreeTableColumn<>("Fecha");
        parametro2.setPrefWidth(100);
        parametro2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().fecha;
            }
        });
        JFXTreeTableColumn<Historial, String> parametro3= new JFXTreeTableColumn<>("Cuadro");
        parametro3.setPrefWidth(100);
        parametro3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().cuadro;
            }
        });
        JFXTreeTableColumn<Historial, String> parametro4= new JFXTreeTableColumn<>("Descripcion");
        parametro4.setPrefWidth(100);
        parametro4.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().descripcion;
            }
        });
        JFXTreeTableColumn<Historial, String> parametro5= new JFXTreeTableColumn<>("Producto");
        parametro5.setPrefWidth(100);
        parametro5.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().producto;
            }
        });
        JFXTreeTableColumn<Historial, String> parametro6= new JFXTreeTableColumn<>("Receta");
        parametro6.setPrefWidth(100);
        parametro6.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().receta;
            }
        });

        JFXTreeTableColumn<Historial, String> parametro7= new JFXTreeTableColumn<>("Clima");
        parametro7.setPrefWidth(100);
        parametro7.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().clima;
            }
        });
        ObservableList<Historial> historial = FXCollections.observableArrayList();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM historial_acciones");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                    historial.add(new Historial(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                }

        } catch (SQLException ex) {

            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Historial> root = new RecursiveTreeItem<Historial>(historial, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(parametro,parametro2,parametro3,parametro4,parametro5,parametro6,parametro7);

        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }
    public static void eliminarConTecla(JFXTreeTableView treeView,Connection connection){
        int row = treeView.getSelectionModel().getSelectedIndex();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM historial_acciones");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while (rs.next()){
                if ( aux == row){
                    try {
                        Connection con = (Connection) DBConnection.getConnection();
                        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("DELETE FROM historial_acciones WHERE ID_HA=?");
                        preparedStatement.setString(1, rs.getString(1));
                        preparedStatement.executeUpdate();
                        llenarTabla(treeView,connection);
                    }catch (SQLException e){e.printStackTrace();}
                }
                aux++;
            }
        } catch (SQLException ex) {

        }
    }
    public static void buscarDaño(JFXTextField textField, JFXTreeTableView treeView){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeView.setPredicate(new Predicate<TreeItem<Historial>>() {
                    @Override
                    public boolean test(TreeItem<Historial> treeItem) {
                        boolean flag= treeItem.getValue().cuadro.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }
}
