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

public class Terreno extends RecursiveTreeObject<Terreno> {
    StringProperty fertilidad, estadoActual,tipoRiego,fecha,cuadro;

    public Terreno(String fertilidad, String estadoActual, String tipoRiego, String fecha, String cuadro) {
        this.fertilidad = new SimpleStringProperty(fertilidad);
        this.estadoActual = new SimpleStringProperty(estadoActual);
        this.tipoRiego = new SimpleStringProperty(tipoRiego) ;
        this.fecha = new SimpleStringProperty(fecha);
        this.cuadro = new SimpleStringProperty(cuadro);
    }

    public static void llenarTabla(JFXTreeTableView treeView, Connection connection){
        JFXTreeTableColumn<Terreno, String> parametro= new JFXTreeTableColumn<>("Fertilidad");
        parametro.setPrefWidth(100);
        parametro.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Terreno, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Terreno, String> param) {
                return param.getValue().getValue().fertilidad;
            }
        });
        JFXTreeTableColumn<Terreno, String> parametro2= new JFXTreeTableColumn<>("Estado actual");
        parametro2.setPrefWidth(100);
        parametro2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Terreno, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Terreno, String> param) {
                return param.getValue().getValue().estadoActual;
            }
        });
        JFXTreeTableColumn<Terreno, String> parametro3= new JFXTreeTableColumn<>("Tipo de riego");
        parametro3.setPrefWidth(100);
        parametro3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Terreno, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Terreno, String> param) {
                return param.getValue().getValue().tipoRiego;
            }
        });
        JFXTreeTableColumn<Terreno, String> parametro4= new JFXTreeTableColumn<>("Fecha");
        parametro4.setPrefWidth(100);
        parametro4.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Terreno, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Terreno, String> param) {
                return param.getValue().getValue().fecha;
            }
        });
        JFXTreeTableColumn<Terreno, String> cuadro= new JFXTreeTableColumn<>("Cuadro");
        cuadro.setPrefWidth(100);
        cuadro.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Terreno, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Terreno, String> param) {
                return param.getValue().getValue().cuadro;
            }
        });
        ObservableList<Terreno> terrenos = FXCollections.observableArrayList();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM terreno");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                terrenos.add(new Terreno(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException ex) {

            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Terreno> root = new RecursiveTreeItem<Terreno>(terrenos, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(parametro,parametro2,parametro3,parametro4,cuadro);

        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }
    public static void eliminarConTecla(JFXTreeTableView treeView,Connection connection){
        int row = treeView.getSelectionModel().getSelectedIndex();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM terreno");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while (rs.next()){
                if ( aux == row){
                    try {
                        Connection con = (Connection) DBConnection.getConnection();
                        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("DELETE FROM terreno WHERE id=?");
                        preparedStatement.setString(1, rs.getString(1));
                        preparedStatement.executeUpdate();
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
                treeView.setPredicate(new Predicate<TreeItem<Terreno>>() {
                    @Override
                    public boolean test(TreeItem<Terreno> treeItem) {
                        boolean flag= treeItem.getValue().cuadro.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }
}
