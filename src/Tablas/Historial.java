package Tablas;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Connection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class Historial extends RecursiveTreeObject<Historial>{
    StringProperty accion,fecha,cuadro,descripcion,producto,recetas,clima;

    public Historial(String accion, String fecha, String cuadro, String descripcion, String producto, String recetas, String clima) {
        this.accion = new SimpleStringProperty(accion);
        this.fecha = new SimpleStringProperty(fecha);
        this.cuadro = new SimpleStringProperty(cuadro);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.producto = new SimpleStringProperty(producto) ;
        this.recetas = new SimpleStringProperty(recetas);
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
        JFXTreeTableColumn<Historial, String> parametro= new JFXTreeTableColumn<>("Accion");
        parametro.setPrefWidth(100);
        parametro.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Historial, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Historial, String> param) {
                return param.getValue().getValue().accion;
            }
        });
    }
}
