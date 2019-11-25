package Tablas.Dashboard;


import Tablas.Inventario.Inventario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TreeItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

public class Nuez {

    public static void inicializarGrafica(BarChart barChart, Connection connection, JFXComboBox comboBox) throws SQLException {
        com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM nuez WHERE variedad="+comboBox.getSelectionModel().getSelectedItem().toString());
        ResultSet rs = ps.executeQuery();
       while (rs.next()){
           XYChart.Series setl = new XYChart.Series<>();
           setl.getData().add(new XYChart.Data<>("De primera",rs.getInt(3)));
           setl.getData().add(new XYChart.Data<>("Quebrada", rs.getInt(4)));
           setl.getData().add(new XYChart.Data<>("Germinada", rs.getInt(5)));
           setl.getData().add(new XYChart.Data<>("Vana", rs.getInt(6)));
           setl.getData().add(new XYChart.Data<>("Con ruez",rs.getInt(7) ));
           setl.getData().add(new XYChart.Data<>("Aceitosa", rs.getInt(8)));
           barChart.getData().addAll(setl);
       }

        barChart.setLegendVisible(false);
        }
        public static void llenarComboVariedad(Connection connection, JFXComboBox comboBox){
            try {

                com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM nuez");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    comboBox.getItems().add(rs.getString(2));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        public static void change(JFXComboBox comboBox,BarChart barChart, Connection connection){
            comboBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        com.mysql.jdbc.PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM nuez");
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()) {
                            XYChart.Series setl = new XYChart.Series<>();
                            setl.getData().add(new XYChart.Data("De primera", rs.getInt(3)));
                            setl.getData().add(new XYChart.Data("Quebrada", rs.getInt(4)));
                            setl.getData().add(new XYChart.Data("Germinada", rs.getInt(5)));
                            setl.getData().add(new XYChart.Data("Vana", rs.getInt(6)));
                            setl.getData().add(new XYChart.Data("Con ruez", rs.getInt(7)));
                            setl.getData().add(new XYChart.Data("Aceitosa", rs.getInt(8)));
                            barChart.getData().addAll(setl);
                            barChart.setLegendVisible(false);
                        }
                    }catch (SQLException ex){ex.printStackTrace();}



                }
            });
        }

        public static void llenarText(){

        }
    }


