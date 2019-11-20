package Tablas.Dashboard;

import DB.DBConnection;
import Tablas.Cuadro.Historial;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Viaje extends RecursiveTreeObject<Viaje> {
    StringProperty destino,estado,fecha,conductor,comentario;

    public Viaje(String destino, String estado, String fecha, String conductor, String comentario) {
        this.destino = new SimpleStringProperty(destino);
        this.estado = new SimpleStringProperty(estado);
        this.fecha = new SimpleStringProperty(fecha);
        this.conductor = new SimpleStringProperty(conductor);
        this.comentario = new SimpleStringProperty(comentario);
    }
    public static void llenarTabla(JFXTreeTableView treeView, Connection connection){
        JFXTreeTableColumn<Viaje, String> destino= new JFXTreeTableColumn<>("Destino");
        destino.setPrefWidth(100);
        destino.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Viaje, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Viaje, String> param) {
                return param.getValue().getValue().destino;
            }
        });
        JFXTreeTableColumn<Viaje, String> estado= new JFXTreeTableColumn<>("Estado");
        estado.setPrefWidth(100);
        estado.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Viaje, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Viaje, String> param) {
                return param.getValue().getValue().estado;
            }
        });
        JFXTreeTableColumn<Viaje, String> fecha= new JFXTreeTableColumn<>("Fecha");
        fecha.setPrefWidth(100);
        fecha.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Viaje, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Viaje, String> param) {
                return param.getValue().getValue().fecha;
            }
        });
        JFXTreeTableColumn<Viaje, String> conductor= new JFXTreeTableColumn<>("Conductor");
        conductor.setPrefWidth(100);
        conductor.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Viaje, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Viaje, String> param) {
                return param.getValue().getValue().conductor;
            }
        });
        JFXTreeTableColumn<Viaje, String> comentario= new JFXTreeTableColumn<>("Comentario");
        comentario.setPrefWidth(100);
        comentario.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Viaje, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Viaje, String> param) {
                return param.getValue().getValue().comentario;
            }
        });

        ObservableList<Viaje> viajes = FXCollections.observableArrayList();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM informacion_de_viajes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                viajes.add(new Viaje(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException ex) {

            Logger.getLogger(Viaje.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Viaje> root = new RecursiveTreeItem<Viaje>(viajes, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(destino,estado,fecha,conductor,comentario);

        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }
    public static void eliminarConTecla(JFXTreeTableView treeView,Connection connection){
        int row = treeView.getSelectionModel().getSelectedIndex();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM SELECT * FROM informacion_de_viajes");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while (rs.next()){
                if ( aux == row){
                    try {
                        Connection con = (Connection) DBConnection.getConnection();
                        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("DELETE FROM SELECT * FROM informacion_de_viajes WHERE id=?");
                        preparedStatement.setString(1, rs.getString(1));
                        preparedStatement.executeUpdate();
                    }catch (SQLException e){e.printStackTrace();}
                }
                aux++;
            }
        } catch (SQLException ex) {

        }
    }
}
