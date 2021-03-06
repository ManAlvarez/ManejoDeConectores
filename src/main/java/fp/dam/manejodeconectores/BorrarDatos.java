/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.manejodeconectores;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author manuel
 */
public class BorrarDatos {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args){
        String db = "novaBaseDeDatos.db";
        Connection con = connectDatabase(db);
        deletePerson(con, "Manuel2");
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
    Método que imprime tódalas persoas
    */
    private static void printPeople(Connection con){
        try
        {

            Statement statement = con.createStatement();

            //Probamos a realizar unha consulta
            ResultSet rs = statement.executeQuery("select * from person");
            
            while(rs.next()){
                //imprimimos o nome de todolas persoas
                System.out.println("Nome = " + rs.getString("nome"));
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    /*
    Este método borra unha persoa da base de datos segundo o seu nome
    */
    private static void deletePerson(Connection con, String nome){
        try{
            String sql = "DELETE FROM person WHERE nome = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.executeUpdate();
            System.out.println("Persoa borrada con éxito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
}
