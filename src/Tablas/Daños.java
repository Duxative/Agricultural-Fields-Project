package Tablas;

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

public class Daños extends RecursiveTreeObject<Daños> {
    StringProperty lugar, sintoma, causa, cuadro;

    public Daños(){
        super();
    }

    public Daños(String lugar, String sintoma, String causa,String cuadro) {
        this.lugar = new SimpleStringProperty(lugar);
        this.sintoma = new SimpleStringProperty(sintoma);
        this.causa = new SimpleStringProperty(causa);
        this.cuadro = new SimpleStringProperty(cuadro);
    }

    public static void llenarTabla(JFXTreeTableView treeView, Connection connection) {

        JFXTreeTableColumn<Daños, String> lugar = new JFXTreeTableColumn<>("Lugar");
        lugar.setPrefWidth(100);
        lugar.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Daños, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Daños, String> param) {
                return param.getValue().getValue().lugar;
            }
        });
        JFXTreeTableColumn<Daños, String> sintoma = new JFXTreeTableColumn<>("Sintoma");
        sintoma.setPrefWidth(100);
        sintoma.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Daños, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Daños, String> param) {
                return param.getValue().getValue().sintoma;
            }
        });

        JFXTreeTableColumn<Daños, String> causa = new JFXTreeTableColumn<>("Causa");
        causa.setPrefWidth(100);
        causa.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Daños, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Daños, String> param) {
                return param.getValue().getValue().causa;
            }
        });


        ObservableList<Daños> daños = FXCollections.observableArrayList();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM daños");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                daños.add(new Daños(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }

        } catch (SQLException ex) {

            Logger.getLogger(Daños.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<Daños> root = new RecursiveTreeItem<Daños>(daños, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(lugar,sintoma,causa);

        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }
    public static void eliminarConTecla(JFXTreeTableView treeView,Connection connection){
        int row = treeView.getSelectionModel().getSelectedIndex();
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM daños");
            ResultSet rs = ps.executeQuery();
            int aux = 0;
            while (rs.next()){
                if ( aux == row){
                    try {
                        Connection con = (Connection) DBConnection.getConnection();
                        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("DELETE FROM daños WHERE id=?");
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
                treeView.setPredicate(new Predicate<TreeItem<Daños>>() {
                    @Override
                    public boolean test(TreeItem<Daños> treeItem) {
                        boolean flag= treeItem.getValue().cuadro.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }


}
