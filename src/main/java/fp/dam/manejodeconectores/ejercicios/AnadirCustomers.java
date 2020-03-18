/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.manejodeconectores.ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author manuel
 */
public class AnadirCustomers {

    /**
     * @param args the command line arguments
     */
    
    private static final String db = "chinook.db";
    
    public static void main(String[] args) {
        // TODO code application logic here
        pedirDatosCustomer();
    }
    
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
    
    private static void insertCustomer(Connection con, String nombre, String apellidos, String compania, String direccion, String ciudad,
            String provincia, String pais, String codPostal, String movil, String fax, String email) {
        try {
            //Fixate que no código SQL o valor do nome e "?". Este valor engadiremolo despois
            String sql = "INSERT INTO customers(FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            //Aquí e cando engadimos o valor do nome
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellidos);
            pstmt.setString(3, compania);
            pstmt.setString(4, direccion);
            pstmt.setString(5, ciudad);
            pstmt.setString(6, provincia);
            pstmt.setString(7, pais);
            pstmt.setString(8, codPostal);
            pstmt.setString(9, movil);
            pstmt.setString(10, fax);
            pstmt.setString(11, email);
            pstmt.executeUpdate();
            System.out.println("Customer engadido con éxito");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void pedirDatosCustomer(){
        String nombre = insertarString("Inserta el nombre: ");
        String apellidos = insertarString("Inserta los apellidos: ");
        String compania = insertarString("Inserta la conpañia: ");
        String direccion = insertarString("Inserta la dirección: ");
        String ciudad = insertarString("Inserta la ciudad: ");
        String provincia = insertarString("Inserta la provincia: ");
        String pais = insertarString("Inserta el país: ");
        String codPostal = insertarString("Inserta el código postal: ");
        String movil = insertarString("Inserta el numero de movil: ");
        String fax = insertarString("Inserta el numero de fax");
        String email = insertarString("Inserta el email: "); 
        Connection con = connectDatabase(db);
        insertCustomer(con, nombre, apellidos, compania, direccion, ciudad, provincia, pais, codPostal, movil, fax, email);
        desconnetDatabase(con);       
    }
    
    public static String insertarString(String texto){
        System.out.println(texto);
        Scanner sc = new Scanner(System.in);
        String dato = sc.nextLine();
        return dato;
    }
    
    public static int insertarInt(String texto){
        System.out.println(texto);
        Scanner sc = new Scanner(System.in);
        String dato = sc.nextLine();
        int datoInt = -1;
        try {
            datoInt = Integer.parseInt(dato);
        } catch (NumberFormatException e) {
            System.out.println("El dato no es un int, prueba de nuevo.");
            insertarInt(texto);
        }
        return datoInt;
    }
    
}
