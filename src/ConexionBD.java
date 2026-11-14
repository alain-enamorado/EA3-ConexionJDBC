import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la validación de conexiones a bases de datos
 * mediante la API JDBC de Java.
 *
 * La clase utiliza DriverManager para establecer la conexión
 * y try-with-resources para garantizar su cierre.
 */
public class ConexionBD {
    public void probarConexion(String url, String usuario, char[] contrasena)
        throws ClassNotFoundException, SQLException {
            Class.forName("org.sqlite.JDBC");
            try (Connection conexion = DriverManager.getConnection(
                url, usuario, new String(contrasena))) {

                }
    }
}