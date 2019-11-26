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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Vista {
    //Llenado de ComboBox
    public static void llenarComboBoxRecetas(JFXComboBox comboBox1, JFXComboBox comboBox2, JFXComboBox comboBox3, JFXComboBox comboBox4, JFXComboBox comboBox5, JFXComboBox comboBox6, Connection connection) {
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
    public static void llenarComboBoxRecetasCuadro(JFXComboBox comboBox1, Connection connection) {
        try {

            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM recetas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox1.getItems().add(rs.getString(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void llenarComboCategorias(JFXComboBox comboBox, JFXComboBox comboBox2, Connection connection) {
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

    public static void llenarComboCuadros(JFXComboBox comboBox, Connection connection) {
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

    public static void llenarComboBoxCuadros(JFXComboBox comboBox1, JFXComboBox comboBox2, JFXComboBox comboBox3, JFXComboBox comboBox4, JFXComboBox comboBox5, JFXComboBox comboBox6, Connection connection) {
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

    public static void llenarComboEstado(JFXComboBox comboBox, Connection connection) {
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
    public static void llenarTablasDeCuadro(JFXTreeTableView arbol, JFXTreeTableView daños, JFXTreeTableView terreno, JFXTreeTableView historial, Connection connection) {
        Arbol.llenarTabla(arbol, connection);
        Daños.llenarTabla(daños, connection);
        Terreno.llenarTabla(terreno, connection);
        Historial.llenarTabla(historial, connection);
    }

    public static void llenarTablasDashboard(JFXTreeTableView problemas, JFXTreeTableView viajes, Connection connection) {
        ProblemaDeCuadro.llenarTabla(problemas, connection);
        Viaje.llenarTabla(viajes, connection);

    }

    public static void establecerInformacionDeTablas(JFXTextField arbolField, JFXTextField dañoField, JFXTextField terrenoField, JFXTextField historialField, JFXTreeTableView arbol, JFXTreeTableView daños, JFXTreeTableView terreno, JFXTreeTableView historial) {
        Arbol.buscarArbol(arbolField, arbol);
        Daños.buscarDaño(dañoField, daños);
        Terreno.buscarDaño(terrenoField, terreno);
        Historial.buscarDaño(historialField, historial);
        arbolField.setText(Dashboard.cuadro());
        dañoField.setText(Dashboard.cuadro());
        terrenoField.setText(Dashboard.cuadro());
        historialField.setText(Dashboard.cuadro());
    }

    public static void iniciarInventario(JFXComboBox comboCat, JFXComboBox comboCat2, JFXComboBox comboAdd, JFXComboBox comboRemove, JFXTreeTableView inventoryTable, JFXTextField searchField, Connection connection) {
        ControlInvetario.llenarProducto(comboAdd, connection);
        ControlInvetario.llenarProducto(comboRemove, connection);
        Tablas.Inventario.Inventario.llenarTabla(inventoryTable, connection);
        Tablas.Inventario.Inventario.buscarArbol(searchField, inventoryTable);
        Vista.llenarComboCategorias(comboCat, comboCat2, connection);
        ControlInvetario.cambiarTabla(comboCat, inventoryTable);
    }


    //Agregar filas
    public static void agregarFila(StackPane stackPane, JFXComboBox categoria, JFXTextField producto, JFXTextField cantidad, JFXTextField descripcion, JFXTextField estado, JFXTreeTableView treeTableView, JFXComboBox comboAdd, JFXComboBox comboRemove, Connection connection) {
        if (producto.getText().isEmpty() || (cantidad.getText().isEmpty() || cantidad.getText().length() > 10) || descripcion.getText().isEmpty() || estado.getText().isEmpty() || categoria.getSelectionModel().getSelectedItem() == null) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertRowInventario(producto, cantidad, descripcion, estado, categoria, treeTableView, connection);
            producto.clear();
            cantidad.clear();
            descripcion.clear();
            estado.clear();
        }

        Inventario.llenarTabla(treeTableView, connection);
        Inventario.vaciarComboBox(comboAdd, connection);
        Inventario.vaciarComboBox(comboRemove, connection);
        ControlInvetario.llenarProducto(comboAdd, connection);
        ControlInvetario.llenarProducto(comboRemove, connection);

    }

    public static void agregarFileAccion(StackPane stackPane, JFXTextField accion, JFXTextField producto, JFXTextField descripcion, JFXTextField condiciones, JFXComboBox receta, JFXComboBox cuadro, JFXDatePicker fecha) {
        if (accion.getText().equals("") || producto.getText().equals("") || cuadro.getSelectionModel().getSelectedItem() == null) {

            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertRowAction(accion, producto, descripcion, condiciones, receta, cuadro, fecha);
        }
    }

    public static void agregarFilaViajes(StackPane stackPane, JFXComboBox comboBox, JFXTextField textField, JFXDatePicker datePicker, JFXTextField conductor, JFXTextField comentario) {
        if (comboBox.getSelectionModel().getSelectedItem() == null || textField.getText().equals("") || datePicker.getValue().equals("") || conductor.getText().equals("")) {
            Validar.popUpElementosVacios(stackPane);
        } else {
            Query.insertViaje(datePicker, textField, comboBox, conductor, comentario);
        }

    }
    //Eliminar filas


    //Otros
    public static void establecerTitulo(Text titulo) {
        titulo.setText(Dashboard.titulo());
    }

    public static void atajos(JFXToggleButton toggleButton, Pane pane) {
        toggleButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (toggleButton.isSelected()) {
                    pane.setVisible(true);
                } else {
                    pane.setVisible(false);
                }
            }
        });
    }

    public static void colorCuadro(Connection connection, JFXButton SA, JFXButton SB, JFXButton OA, JFXButton OB) throws SQLException {
        String cuadro = "";
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                cuadro = "7A";
            }
            if (i == 1) {
                cuadro = "7B";
            }
            if (i == 2) {
                cuadro = "8A";
            }
            if (i == 3) {
                cuadro = "8B";
            }
            switch (cuadro) {
                case "7A":
                    if (ProblemaDeCuadro.contarProblemas7A(connection) <= 1) {
                        SA.setStyle("-fx-background-color: #28C76F;");
                    }
                    if (ProblemaDeCuadro.contarProblemas7A(connection) >= 2 && ProblemaDeCuadro.contarProblemas7A(connection) <= 3) {
                        SA.setStyle("-fx-background-color: YELLOW;");
                    }
                    if (ProblemaDeCuadro.contarProblemas7A(connection) > 3) {
                        SA.setStyle("-fx-background-color: RED; -fx-text-fill: WHITE;");

                    }
                    break;
                case "7B":
                    if (ProblemaDeCuadro.contarProblemas7B(connection) <= 1) {
                        SB.setStyle("-fx-background-color: #28C76F;");
                    }
                    if (ProblemaDeCuadro.contarProblemas7B(connection) >= 2 && ProblemaDeCuadro.contarProblemas7B(connection) <= 3) {
                        SB.setStyle("-fx-background-color: YELLOW;");
                    }
                    if (ProblemaDeCuadro.contarProblemas7B(connection) > 3) {
                        SB.setStyle("-fx-background-color: RED;");
                        SB.setStyle("-fx-text-fill: WHITE;");
                    }
                    break;
                case "8A":
                    if (ProblemaDeCuadro.contarProblemas8A(connection) <= 1) {
                        OA.setStyle("-fx-background-color: #28C76F;");
                    }
                    if (ProblemaDeCuadro.contarProblemas8A(connection) >= 2 && ProblemaDeCuadro.contarProblemas8A(connection) <= 3) {
                        OA.setStyle("-fx-background-color: YELLOW;");
                    }
                    if (ProblemaDeCuadro.contarProblemas8A(connection) > 3) {
                        OA.setStyle("-fx-background-color: RED;");
                        OA.setStyle("-fx-text-fill: WHITE;");
                    }
                    break;
                case "8B":
                    if (ProblemaDeCuadro.contarProblemas8B(connection) <= 1) {
                        OB.setStyle("-fx-background-color: #28C76F;");
                    }
                    if (ProblemaDeCuadro.contarProblemas8B(connection) >= 2 && ProblemaDeCuadro.contarProblemas8B(connection) <= 3) {
                        OB.setStyle("-fx-background-color: YELLOW;");
                    }
                    if (ProblemaDeCuadro.contarProblemas8B(connection) > 3) {
                        OB.setStyle("-fx-background-color: RED;");
                        OB.setStyle("-fx-text-fill: WHITE;");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void updateDePrimera(Connection connection, JFXTextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE nuez SET de_primera=" + cantidad);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateQuebrada(Connection connection, JFXTextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE nuez SET quebrada=" + cantidad);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateGerminada(Connection connection, JFXTextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE nuez SET germinada=" + cantidad);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateVana(Connection connection, JFXTextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE nuez SET vana=" + cantidad);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateConRuez(Connection connection, JFXTextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE nuez SET con_ruez=" + cantidad);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateAceitosa(Connection connection, JFXTextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("UPDATE nuez SET aceitosa=" + cantidad);
           ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateGrafica(Connection connection, JFXTextField dePrimera, JFXTextField quebrada, JFXTextField germinada, JFXTextField vana, JFXTextField conRuez, JFXTextField aceitosa) {
        updateDePrimera(connection, dePrimera);
        updateQuebrada(connection, quebrada);
        updateGerminada(connection, germinada);
        updateVana(connection, vana);
        updateConRuez(connection, conRuez);
        updateAceitosa(connection, aceitosa);
        dePrimera.clear();
        quebrada.clear();
        germinada.clear();
        vana.clear();
        conRuez.clear();
        aceitosa.clear();

    }


}

