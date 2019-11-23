package DB;

import Tablas.Inventario.Inventario;
import Validaciones.Validar;
import com.jfoenix.controls.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Query {

    public static String Cultivo(Connection connection) throws SQLException {
        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM arbol");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(6);
        } else {
            return "";
        }

    }

    public static void insertRowInventario(JFXTextField producto, JFXTextField cantidad, JFXTextField descripcion, JFXTextField estado, JFXComboBox categoria, JFXTreeTableView treeTableView, Connection connection) {
        try {
            Connection con = (Connection) DBConnection.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("INSERT INTO inventario(categoria,producto,cantidad,Descripcion,Estado) values (?,?,?,?,?)");
            preparedStatement.setString(1, categoria.getSelectionModel().getSelectedItem().toString());
            preparedStatement.setString(2, producto.getText());
            preparedStatement.setString(3, cantidad.getText());
            preparedStatement.setString(4, descripcion.getText());
            preparedStatement.setString(5, estado.getText());
            preparedStatement.executeUpdate();
            Inventario.llenarTabla(treeTableView, connection);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertRowAction(JFXTextField accion, JFXTextField producto, JFXTextField descripcion, JFXTextField condiciones, JFXComboBox receta, JFXComboBox cuadro, JFXDatePicker fecha) {
        try {
            Connection con = (Connection) DBConnection.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("INSERT INTO historial_acciones(accion,fecha,cuadro,descripcion,producto,receta,clima) values (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, accion.getText());
            preparedStatement.setString(2, fecha.getValue().toString());
            preparedStatement.setString(3, cuadro.getSelectionModel().getSelectedItem().toString());
            preparedStatement.setString(4, descripcion.getText());
            preparedStatement.setString(5, producto.getText());
            if (receta.getSelectionModel().getSelectedItem() == null) {
                preparedStatement.setString(6, "Ninguna");
            } else {
                preparedStatement.setString(6, receta.getSelectionModel().getSelectedItem().toString());
            }

            preparedStatement.setString(7, condiciones.getText());
            preparedStatement.executeUpdate();
            accion.clear();
            producto.clear();
            descripcion.clear();
            condiciones.clear();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void changeQuantity(Connection connection, int cantidad) {

    }

    public static void insertAction(Text producto, JFXTextField dosis, JFXComboBox cuadro, JFXComboBox tipoAplicion) {
        try {
            Connection con = (Connection) DBConnection.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("INSERT INTO historial_acciones(accion,fecha,cuadro,descripcion,producto,receta,clima) values (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, "Se agrego");
            preparedStatement.setString(2, "Fecha actual");
            preparedStatement.setString(3, cuadro.getSelectionModel().getSelectedItem().toString());
            preparedStatement.setString(4, tipoAplicion.getSelectionModel().getSelectedItem().toString());
            preparedStatement.setString(5, producto.getText());
            preparedStatement.setString(6, "nombre de receta");
            preparedStatement.setString(7, "ASD");
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertViaje(JFXDatePicker datePicker, JFXTextField textField, JFXComboBox comboBox, JFXTextField conductor, JFXTextField comentario) {
        try {
            Connection con = (Connection) DBConnection.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("INSERT INTO viajes(destino,estado,fecha,conductor,comentario) values (?,?,?,?,?)");
            preparedStatement.setString(1, textField.getText());
            preparedStatement.setString(2, comboBox.getSelectionModel().getSelectedItem().toString());
            preparedStatement.setString(3, datePicker.getValue().toString());
            preparedStatement.setString(4, conductor.getText());
            if (comentario.getText().equals("")) {
                preparedStatement.setString(5, "Ninguno");
            } else {
                preparedStatement.setString(5, comentario.getText());
            }

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String regresarSintoma(Text text) {
        int inicio = text.getText().toString().indexOf(':') + 1;

        return text.getText().toString().substring(inicio, text.getText().length());
    }

    public static void insertDaño(Text Sintoma, JFXTextField lugar, JFXTextField causa, JFXComboBox cuadro) {
        try {
            Connection con = (Connection) DBConnection.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("INSERT INTO daños(Lugar,Sintoma,Causa,cuadro) values (?,?,?,?)");
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO problema_en_cuadros(cuadro,fecha,problema) values(?,?,?)");
            preparedStatement.setString(1, lugar.getText());
            preparedStatement.setString(2, regresarSintoma(Sintoma));
            preparedStatement.setString(3, causa.getText());
            preparedStatement.setString(4, cuadro.getSelectionModel().getSelectedItem().toString());
            ps.setString(1, cuadro.getSelectionModel().getSelectedItem().toString());
            ps.setString(2, LocalDate.now().toString());
            ps.setString(3, regresarSintoma(Sintoma));

            preparedStatement.executeUpdate();
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        lugar.clear();
        causa.clear();
    }

    public static void updateRestar(StackPane stackPane, Connection connection, JFXComboBox comboProducto, JFXTextField textField, JFXTreeTableView treeTableView) {
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM inventario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (comboProducto.getSelectionModel().getSelectedItem().toString().equals(rs.getString(3))) {
                    int cantidad = rs.getInt(4);

                    try {
                        int auxiliar = Integer.parseInt(textField.getText());
                        auxiliar = cantidad - auxiliar;
                        if (auxiliar < 0) {
                            Validar.popUpValorNegativo(stackPane);
                        } else {
                            String sql = "UPDATE inventario SET cantidad=? WHERE producto=?";
                            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
                            preparedStatement.setInt(1, auxiliar);
                            preparedStatement.setString(2, comboProducto.getSelectionModel().getSelectedItem().toString());

                            preparedStatement.executeUpdate();
                            Inventario.llenarTabla(treeTableView,connection);
                            textField.clear();
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void updateSumar(StackPane stackPane, Connection connection, JFXComboBox comboProducto, JFXTextField textField, JFXTreeTableView treeTableView) {
        try {
            com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM inventario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (comboProducto.getSelectionModel().getSelectedItem().toString().equals(rs.getString(3))) {
                    int cantidad = rs.getInt(4);

                    try {
                        int auxiliar = Integer.parseInt(textField.getText());
                        auxiliar = cantidad + auxiliar;
                        if (auxiliar < 0) {
                            Validar.popUpValorNegativo(stackPane);
                        } else {
                            String sql = "UPDATE inventario SET cantidad=? WHERE producto=?";
                            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
                            preparedStatement.setInt(1, auxiliar);
                            preparedStatement.setString(2, comboProducto.getSelectionModel().getSelectedItem().toString());

                            preparedStatement.executeUpdate();
                            Inventario.llenarTabla(treeTableView,connection);
                            textField.clear();
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

