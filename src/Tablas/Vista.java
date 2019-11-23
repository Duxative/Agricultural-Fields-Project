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
import Validaciones.Validar;
import com.jfoenix.controls.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vista {
    //Llenado de ComboBox
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
    public static void llenarComboCategorias(JFXComboBox comboBox,JFXComboBox comboBox2, Connection connection){
        try {

            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM categorias");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox.getItems().add(rs.getString(2));
                comboBox2.getItems().add(rs.getString(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void llenarComboCuadros(JFXComboBox comboBox, Connection connection){
        try {

            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM cuadros");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox.getItems().add(rs.getString(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static void llenarComboBoxCuadros(JFXComboBox comboBox1,JFXComboBox comboBox2,JFXComboBox comboBox3,JFXComboBox comboBox4,JFXComboBox comboBox5,JFXComboBox comboBox6,Connection connection){
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
    public static void llenarComboEstado(JFXComboBox comboBox,Connection connection){
        try {

            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM estado");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox.getItems().add(rs.getString(2));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Llenar tablas
    public static void llenarTablasDeCuadro(JFXTreeTableView arbol, JFXTreeTableView daños, JFXTreeTableView terreno, JFXTreeTableView historial, Connection connection){
        Arbol.llenarTabla(arbol,connection);
        Daños.llenarTabla(daños,connection);
        Terreno.llenarTabla(terreno,connection);
        Historial.llenarTabla(historial,connection);
    }
    public static void llenarTablasDashboard(JFXTreeTableView problemas, JFXTreeTableView viajes,Connection connection){
        ProblemaDeCuadro.llenarTabla(problemas,connection);
        Viaje.llenarTabla(viajes,connection);

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
    public static void iniciarInventario(JFXComboBox comboCat,JFXComboBox comboCat2,JFXComboBox comboAdd, JFXComboBox comboRemove, JFXTreeTableView inventoryTable, JFXTextField searchField, Connection connection){
        ControlInvetario.llenarProducto(comboAdd,connection);
        ControlInvetario.llenarProducto(comboRemove,connection);
        Tablas.Inventario.Inventario.llenarTabla(inventoryTable,connection);
        Tablas.Inventario.Inventario.buscarArbol(searchField,inventoryTable);
        Vista.llenarComboCategorias(comboCat,comboCat2,connection);
        ControlInvetario.cambiarTabla(comboCat,inventoryTable);
    }


    //Agregar filas
    public static void agregarFila(StackPane stackPane,JFXComboBox categoria, JFXTextField producto,JFXTextField cantidad,JFXTextField descripcion,JFXTextField estado,JFXTreeTableView treeTableView,JFXComboBox comboAdd,JFXComboBox comboRemove,Connection connection){
        if (producto.getText().isEmpty() || (cantidad.getText().isEmpty() || cantidad.getText().length()>10) || descripcion.getText().isEmpty() || estado.getText().isEmpty() || categoria.getSelectionModel().getSelectedItem() == null){
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertRowInventario(producto,cantidad,descripcion,estado,categoria,treeTableView,connection);
            producto.clear();
            cantidad.clear();
            descripcion.clear();
            estado.clear();
        }

        Inventario.llenarTabla(treeTableView,connection);
        Inventario.vaciarComboBox(comboAdd,connection);
        Inventario.vaciarComboBox(comboRemove,connection);
        ControlInvetario.llenarProducto(comboAdd,connection);
        ControlInvetario.llenarProducto(comboRemove,connection);

    }
    public static void agregarFileAccion(StackPane stackPane,JFXTextField accion, JFXTextField producto, JFXTextField descripcion, JFXTextField condiciones, JFXComboBox receta, JFXComboBox cuadro, JFXDatePicker fecha){
    if (accion.getText().equals("") || producto.getText().equals("") || cuadro.getSelectionModel().getSelectedItem() == null){

        Validar.popUpElementosVacios(stackPane);
    }else {
        Query.insertRowAction(accion,producto,descripcion,condiciones,receta,cuadro,fecha);
    }
    }
    public static void agregarFilaViajes(StackPane stackPane, JFXComboBox comboBox,JFXTextField textField,JFXDatePicker datePicker,JFXTextField conductor, JFXTextField comentario){
        if (comboBox.getSelectionModel().getSelectedItem() == null || textField.getText().equals("") || datePicker.getValue().equals("") || conductor.getText().equals("")  ){
            Validar.popUpElementosVacios(stackPane);
        }else {
            Query.insertViaje(datePicker,textField,comboBox,conductor,comentario);
        }

    }
    //Eliminar filas


    //Otros
    public static void establecerTitulo(Text titulo){
        titulo.setText(Dashboard.titulo());
    }


    }

