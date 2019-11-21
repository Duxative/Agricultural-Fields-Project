package Tablas;

import Control.Dashboard;
import DB.Query;
import Tablas.Cuadro.Arbol;
import Tablas.Cuadro.Daños;
import Tablas.Cuadro.Historial;
import Tablas.Cuadro.Terreno;
import Tablas.Dashboard.ProblemaDeCuadro;
import Tablas.Dashboard.Viaje;
import Tablas.Inventario.ControlInvetario;
import Tablas.Inventario.Inventario;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vista {
    // Vista Cuadro
    public static void llenarTablasDeCuadro(JFXTreeTableView arbol, JFXTreeTableView daños, JFXTreeTableView terreno, JFXTreeTableView historial, Connection connection){
        Arbol.llenarTabla(arbol,connection);
        Daños.llenarTabla(daños,connection);
        Terreno.llenarTabla(terreno,connection);
        Historial.llenarTabla(historial,connection);
    }
    public static void establecerTitulo(Text titulo){
        titulo.setText(Dashboard.titulo());
    }
    public static void establecerInformacionDeTablas(JFXTextField arbolField,JFXTextField dañoField,JFXTextField terrenoField,JFXTextField historialField,JFXTreeTableView arbol, JFXTreeTableView daños, JFXTreeTableView terreno, JFXTreeTableView historial){
        Arbol.buscarArbol(arbolField,arbol);
        Daños.buscarDaño(dañoField,daños);
        Terreno.buscarDaño(terrenoField,terreno);
        Historial.buscarDaño(historialField,historial);
        arbolField.setText(Dashboard.cuadro());
        dañoField.setText(Dashboard.cuadro());
        terrenoField.setText(Dashboard.cuadro());
        historialField.setText(Dashboard.cuadro());
    }
    // Vista Dashboard
    public static void llenarTablasDashboard(JFXTreeTableView problemas, JFXTreeTableView viajes,Connection connection){
        ProblemaDeCuadro.llenarTabla(problemas,connection);
        Viaje.llenarTabla(viajes,connection);

    }
    public static void iniciarInventario(JFXComboBox comboAdd, JFXComboBox comboRemove, JFXTreeTableView inventoryTable, JFXTextField searchField, Connection connection){
        ControlInvetario.llenarProducto(comboAdd,connection);
        ControlInvetario.llenarProducto(comboRemove,connection);
        Tablas.Inventario.Inventario.llenarTabla(inventoryTable,connection);
        Tablas.Inventario.Inventario.buscarArbol(searchField,inventoryTable);
    }
    public static void agregarFila(JFXTextField producto,JFXTextField cantidad,JFXTextField descripcion,JFXTextField estado,JFXTreeTableView treeTableView,JFXComboBox comboAdd,JFXComboBox comboRemove,Connection connection){
        Query.insertRow(producto,cantidad,descripcion,estado);
        Inventario.llenarTabla(treeTableView,connection);
        Inventario.vaciarComboBox(comboAdd,connection);
        Inventario.vaciarComboBox(comboRemove,connection);
        ControlInvetario.llenarProducto(comboAdd,connection);
        ControlInvetario.llenarProducto(comboRemove,connection);
        producto.clear();
        cantidad.clear();
        descripcion.clear();
        estado.clear();
    }
    public static void agregarReceta(Text producto, JFXTextField dosis, JFXTextField cuadro, JFXComboBox tipoAplicion){
        Query.insertAction(producto,dosis,cuadro,tipoAplicion);
        dosis.clear();
        cuadro.clear();

    }
    public static void llenarComboBoxDaños(JFXComboBox comboBox1,JFXComboBox comboBox2,JFXComboBox comboBox3,JFXComboBox comboBox4,JFXComboBox comboBox5,JFXComboBox comboBox6,Connection connection){
        try {

            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM cuadros");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox1.getItems().add(rs.getString(2));
                comboBox2.getItems().add(rs.getString(2));
                comboBox3.getItems().add(rs.getString(2));
                comboBox4.getItems().add(rs.getString(2));
                comboBox5.getItems().add(rs.getString(2));
                comboBox6.getItems().add(rs.getString(2));


            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void llenarComboBoxRecetas(JFXComboBox comboBox1,JFXComboBox comboBox2,JFXComboBox comboBox3,JFXComboBox comboBox4,JFXComboBox comboBox5,JFXComboBox comboBox6,Connection connection){
        try {

            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM tipe_de_riego");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox1.getItems().add(rs.getString(2));
                comboBox2.getItems().add(rs.getString(2));
                comboBox3.getItems().add(rs.getString(2));
                comboBox4.getItems().add(rs.getString(2));
                comboBox5.getItems().add(rs.getString(2));
                comboBox6.getItems().add(rs.getString(2));


            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    }

