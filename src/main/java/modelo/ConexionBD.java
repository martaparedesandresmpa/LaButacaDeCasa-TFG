package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

   
    public static Connection conectar() {
        Connection conexion = null;
        
        
        String url = "jdbc:mysql://localhost:3306/la_butaca_db";
        String usuario = "root";
        String clave = "Molomolo1"; // <--- CAMBIA ESTO POR TU CLAVE DE MYSQL

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        
        return conexion;
    }
}
