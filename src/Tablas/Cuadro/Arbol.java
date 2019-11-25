package Tablas.Cuadro;

import Control.VistaCuadro;
import DB.DBConnection;
import Tablas.Inventario.Inventario;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arbol extends RecursiveTreeObject<Arbol> {

    StringProperty edad, estado_actual, plaga_o_enfermedad, cultivo, cuadro;
    IntegerProperty cantidad_de_arboles;

    public Arbol() {
        super();
    }

    public Arbol(String edad, String estado_actual, String plaga_o_enfermedad, int cantidad_de_arboles, String cultivo, String cuadro) {
        this.edad = new SimpleStringProperty(edad);
        this.estado_actual = new SimpleStringProperty(estado_actual);
        this.plaga_o_enfermedad = new SimpleStringProperty(plaga_o_enfermedad);
        this.cantidad_de_arboles = new SimpleIntegerProperty(cantidad_de_arboles);
        this.cultivo = new SimpleStringProperty(cultivo);
        this.cuadro = new SimpleStringProperty(cuadro);
    }

    public static void llenarTabla(JFXTreeTableView treeView, Connection connection) {

        JFXTreeTableColumn<Arbol, String> edad = new JFXTreeTableColumn<>("Edad");
        edad.setPrefWidth(100);
        edad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Arbol, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Arbol, String> param) {
                return param.getValue().getValue().edad;
            }
        });
        JFXTreeTableColumn<Arbol, String> estado_actual = new JFXTreeTableColumn<>("Estado actual");
        estado_actual.setPrefWidth(100);
        estado_actual.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Arbol, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Arbol, String> param) {
                return param.getValue().getValue().estado_actual;
            }
        });

        JFXTreeTableColumn<Arbol, String> plaga_o_enfermedad = new JFXTreeTableColumn<>("Plaga/Enfermedad");
        plaga_o_enfermedad.setPrefWidth(100);
        plaga_o_enfermedad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Arbol, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Arbol, String> param) {
                return param.getValue().getValue().plaga_o_enfermedad;
            }
        });

        JFXTreeTableColumn<Arbol, String> cantidad_de_arboles = new JFXTreeTableColumn<>("Cantidad");
        cantidad_de_arboles.setPrefWidth(100);
        cantidad_de_arboles.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Arbol, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Arbol, String> param) {
                return param.getValue().getValue().cantidad_de_arboles.asString();
            }
        });

        JFXTreeTableColumn<Arbol, String> cultivo = new JFXTreeTableColumn<>("Cultivo");
        cultivo.setPrefWidth(100);
        cultivo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Arbol, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Arbol, String> param) {

                return param.getValue().getValue().cultivo;
            }
        });




        ObservableList<Arbol> arboles = FXCollections.observableArrayList();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM arbol");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                arboles.add(new Arbol(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
            }

        } catch (SQLException ex) {

            Logger.getLogger(Arbol.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Arbol> root = new RecursiveTreeItem<Arbol>(arboles, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(edad, estado_actual, plaga_o_enfermedad, cantidad_de_arboles, cultivo);
        //edad, estado_actual, plaga_o_enfermedad, cultivo, cuadro
        treeView.setRoot(root);
        treeView.setEditable(true);
        ObservableList<String> estados = FXCollections.observableArrayList("Bueno","Malo","En pausa");
        ObservableList Enfermedades = FXCollections.observableArrayList("Pulgon negro", "Pulgon blanco","Pulgon amarillo","Topos","Ninguna");
        ObservableList cultivos = FXCollections.observableArrayList("Nuez","Chile","Sandia","Melon");
        estado_actual.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(estados));
        plaga_o_enfermedad.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(Enfermedades));
        cultivo.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(cultivos));
        cantidad_de_arboles.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        treeView.setShowRoot(false);

    }

    public static void eliminarConTecla(JFXTreeTableView treeView, Connection connection) {
        int row = treeView.getSelectionModel().getSelectedIndex();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM arbol");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while (rs.next()) {
                if (aux == row) {
                    try {
                        Connection con = (Connection) DBConnection.getConnection();

                        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("DELETE FROM arbol WHERE ID_arb=?");
                        preparedStatement.setInt(1, rs.getInt(1));
                        preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                aux++;
            }
        } catch (SQLException ex) {

        }
    }

    public static void buscarArbol(JFXTextField textField, JFXTreeTableView treeView) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeView.setPredicate(new Predicate<TreeItem<Arbol>>() {
                    @Override
                    public boolean test(TreeItem<Arbol> treeItem) {
                        boolean flag = treeItem.getValue().cuadro.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = new SimpleStringProperty(estado_actual);
    }

    public void setPlaga_o_enfermedad(String plaga_o_enfermedad) {
        this.plaga_o_enfermedad = new SimpleStringProperty(plaga_o_enfermedad);
    }

    public void setCultivo(String cultivo) {
        this.cultivo = new SimpleStringProperty(cultivo);
    }
}
