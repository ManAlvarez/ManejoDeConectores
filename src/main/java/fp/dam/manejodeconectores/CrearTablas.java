/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.manejodeconectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author manuel
 */
public class CrearTablas {
    
    public static void main(String[] args){
        String db = "novaBaseDeDatos.db";
        Connection con = connectDatabase(db);
        createTablePerson(con);
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
    private static void desconnetDatabase(Connection connection){
        try{
            if(connection != null){
                connection.close();
                System.out.println("Desconexión realizada con éxito");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*
    Método que crea a tabla persona nunha base de datos persoa  
     */
    private static void createTablePerson(Connection con){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS person (\n" +
                    "id integer PRIMARY KEY,\n"+
                    "nome text NOT NULL\n"+
                    ");";

            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Táboa persona creada con éxito");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
