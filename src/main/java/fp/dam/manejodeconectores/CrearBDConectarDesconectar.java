/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.manejodeconectores;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manuel
 */
public class CrearBDConectarDesconectar {

    public static void main(String[] args) {
        String db = "novaBaseDeDatos.db";
        //createDatabase(db);
        //Connection con = connectDatabase(db);
        //desconnetDatabase(con);
    }

     /*
    Este método crea unha nova base de datos en SQLLite co nome que se lle pasa como argunmento
    */
    private static void createDatabase(String filename){
        String databaseFile = "jdbc:sqlite:/home/manuel/" + filename;
        
        try{
            Connection connection = DriverManager.getConnection(databaseFile);
            if(connection != null){
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("A base de datos foi creada");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    /*
    Esta clase conéctase a base de datos SQLLite que se lle pasa o nome da base de datos
     */
    private static Connection connectDatabase(String filename) {
        Connection connection = null;
        try {
            //Creamos a conexión a base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:/home/manuel/" + filename);
            System.out.println("Conexión realizada con éxito");
            return connection;

        } catch (SQLException e) {
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

}
