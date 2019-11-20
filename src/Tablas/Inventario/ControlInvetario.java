package Tablas.Inventario;

import Tablas.Cuadro.Arbol;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

public class ControlInvetario {

    public static void llenarCategorias(JFXComboBox categorias, Connection connection){
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM categorias");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categorias.getItems().add(rs.getString(2));

            }

        } catch (SQLException ex) {
        ex.printStackTrace();
        }

    }
    public static void llenarProducto(JFXComboBox comboBox,Connection connection){
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM inventario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox.getItems().add(rs.getString(3));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static String tomarCategoria(JFXComboBox categoria){
        if (categoria.getValue() == null){
           return "null";
        }else {
            return categoria.getValue().toString();
        }
    }

}

