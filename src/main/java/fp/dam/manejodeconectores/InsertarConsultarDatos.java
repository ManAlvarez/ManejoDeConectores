/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.manejodeconectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author manuel
 */
public class InsertarConsultarDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String db = "novaBaseDeDatos.db";        
        Connection con = connectDatabase(db);
        insertPerson(con, "Juan");
        printPeople(con);
        desconnetDatabase(con);

    }

     /*
    Esta clase conéctase a base de datos SQLLite que se lle pasa o nome da base de datos
    */
    private static Connection connectDatabase(String filename){
        Connection connection = null;
        try
        {
            //Creamos a conexión a base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:/home/manuel/" + filename);
            System.out.println("Conexión realizada con éxito");
            return connection;
             
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    /*
    Este método desconectase dunha base de datos en SQLLite a que se lle pasa a conexión
     */
    private static void desconnetDatabase(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Desconexión realizada con éxito");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertPerson(Connection con, String nome) {
        try {
            //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
            String sql = "INSERT INTO person(nome) VALUES(?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            //Aquí e cando engadimos o valor do nome
            pstmt.setString(1, nome);
            pstmt.executeUpdate();
            System.out.println("Persoa engadida con éxito");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    Método que imprime tódalas persoas
     */
    private static void printPeople(Connection con) {
        try {

            Statement statement = con.createStatement();

            //Probamos a realizar unha consulta
            ResultSet rs = statement.executeQuery("select * from person");

            while (rs.next()) {
                //imprimimos o nome de todolas persoas
                System.out.println("Nome = " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
