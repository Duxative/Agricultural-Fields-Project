package DB;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {

    public static String Cultivo(Connection connection) throws SQLException {
        PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM arbol");
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString(6);
    }

    public static void insertRow(JFXTextField producto,JFXTextField cantidad,JFXTextField descripcion,JFXTextField estado){
        try {
            Connection con = (Connection) DBConnection.getConnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("INSERT INTO inventario(categoria,producto,cantidad,Descripcion,Estado) values (?,?,?,?,?)");
            preparedStatement.setString(1, "random");
            preparedStatement.setString(2, producto.getText());
            preparedStatement.setString(3, cantidad.getText());
            preparedStatement.setString(4, descripcion.getText());
            preparedStatement.setString(5, estado.getText());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void changeQuantity(Connection connection, int cantidad){

        }

public static void insertAction(Text producto, JFXTextField dosis, JFXTextField cuadro, JFXCheckBox tipoAplicion){
    try {
        Connection con = (Connection) DBConnection.getConnection();
        PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement("INSERT INTO historial_acciones(accion,fecha,cuadro,descripcion,producto,receta,clima) values (?,?,?,?,?,?,?)");
        preparedStatement.setString(1, "Se agrego");
        preparedStatement.setString(2, "Fecha actual");
        preparedStatement.setString(3, cuadro.getText());
        preparedStatement.setString(4, tipoAplicion.getText());
        preparedStatement.setString(5, producto.getText());
        preparedStatement.setString(6,"nombre de receta");
        preparedStatement.setString(7,"ASD");
        preparedStatement.executeUpdate();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}

