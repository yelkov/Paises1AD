package edu.badpals.paises;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/paises","root","root");
            Statement statement = connection.createStatement();
            printDB(statement);

            String insert = "insert into paises (nombre, num_habitantes, capital, moneda) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,"Camboya");
            preparedStatement.setInt(2,16718971);
            preparedStatement.setString(3,"Nom Pen");
            preparedStatement.setString(4,"Riel camboyano");

            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("\n filas afectadas: " + filasAfectadas);
            printDB(statement);

            String update = "UPDATE paises  SET NUM_HABITANTES = NUM_HABITANTES + 1000000 WHERE NOMBRE = 'Camboya'";
            int numFilasActualizadas = statement.executeUpdate(update);
            System.out.println("\n filas afectadas: " + numFilasActualizadas);
            printDB(statement);

            String delete = "DELETE FROM paises WHERE NOMBRE = 'Camboya'";
            int numFilasBorradas = statement.executeUpdate(delete);
            System.out.println("\n filas borradas: " + numFilasBorradas);
            printDB(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printDB(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery("select * from paises");
        System.out.println("Datos de la tabla países : \n");
        while(rs.next()){
            String nombre = rs.getString("nombre");
            int numHabitantes = rs.getInt("num_habitantes");
            String capital = rs.getString("capital");
            String moneda = rs.getString("moneda");
            System.out.println("Nombre: " + nombre + " | Núm. habitantes: " + numHabitantes + " | capital: " + capital + " | moneda: " + moneda);
        }
    }
}