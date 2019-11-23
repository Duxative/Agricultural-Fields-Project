package Tablas.Dashboard;

import DB.DBConnection;
import Tablas.Cuadro.Historial;
import Tablas.Cuadro.Terreno;
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

public class ProblemaDeCuadro extends RecursiveTreeObject<ProblemaDeCuadro> {
    StringProperty cuadro, fecha, problema;

    public ProblemaDeCuadro(String cuadro, String fecha, String problema) {
        this.cuadro = new SimpleStringProperty(cuadro);
        this.fecha = new SimpleStringProperty(fecha);
        this.problema = new SimpleStringProperty(problema) ;
    }

    public static void llenarTabla(JFXTreeTableView treeView, Connection connection){
        JFXTreeTableColumn<ProblemaDeCuadro, String> cuadro= new JFXTreeTableColumn<>("Cuadro");
        cuadro.setPrefWidth(100);
        cuadro.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProblemaDeCuadro, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProblemaDeCuadro, String> param) {
                return param.getValue().getValue().cuadro;
            }
        });
        JFXTreeTableColumn<ProblemaDeCuadro, String> fecha= new JFXTreeTableColumn<>("Fecha");
        fecha.setPrefWidth(100);
        fecha.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProblemaDeCuadro, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProblemaDeCuadro, String> param) {
                return param.getValue().getValue().fecha;
            }
        });
        JFXTreeTableColumn<ProblemaDeCuadro, String> problema= new JFXTreeTableColumn<>("Problema");
        problema.setPrefWidth(100);
        problema.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProblemaDeCuadro, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProblemaDeCuadro, String> param) {
                return param.getValue().getValue().problema;
            }
        });


        ObservableList<ProblemaDeCuadro> problemaDeCuadros = FXCollections.observableArrayList();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM problema_en_cuadros");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                problemaDeCuadros.add(new ProblemaDeCuadro(rs.getString(2),rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException ex) {

            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<ProblemaDeCuadro> root = new RecursiveTreeItem<ProblemaDeCuadro>(problemaDeCuadros, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(cuadro,fecha,problema);

        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }
    public static void eliminarConTecla(JFXTreeTableView treeView,Connection connection){
        int row = treeView.getSelectionModel().getSelectedIndex();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM problema_en_cuadros");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while (rs.next()){
                if ( aux == row){
                    try {
                        Connection con = (Connection) DBConnection.getConnection();
                        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("DELETE FROM problema_en_cuadros WHERE ID_PEC=?");
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
}
