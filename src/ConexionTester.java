import javax.swing.*; // paquete coin componenetes graficos swing
import java.awt.*; // paquete con componentes graficos  awt. Usaremos clases accesibles BorderLayout, GridLayout y Color.
import java.sql.SQLException;

/*aplicacion de escritorio para validar conexion JDBC */
public class ConexionTester extends JFrame {

    /*ventana priincipal de la aplicacion */
    public ConexionTester() {
        super("Validación de Conexión JDBC");

        /*configuracion de la ventana principal */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this referencia al objeto ConexionTester q construimos
        this.setSize(600, 400);
        /*organizacion de la ventana con borderLayout */
        this.setLayout(new BorderLayout());
        JPanel panelConfiguracion = new JPanel(new GridLayout(3, 2, 10, 10));//usamos 3 filas y 2 columnas para organizar los campos solicitados
        panelConfiguracion.add(new JLabel("URL de conexion: "));
        JTextField campoUrl = new JTextField("jdbc:sqlite:inventario.db");
        panelConfiguracion.add(campoUrl);
        panelConfiguracion.add(new JLabel("Usuario: "));
        JTextField campoUsuario = new JTextField();
        panelConfiguracion.add(campoUsuario);
        panelConfiguracion.add(new JLabel("Contraseña: "));//campo de texto para ingresar la contraseña
        JPasswordField campoContrasena = new JPasswordField();//
        panelConfiguracion.add(campoContrasena);//
        this.add(panelConfiguracion, BorderLayout.NORTH); //para mostrar la interfaz swing en la parte superior del JFrame
        
        /*boton para iniciar la conexion JDBC */
        JButton botonProbar = new JButton("Probar conexion"); //crea el boton con ese texto
        this.add(botonProbar, BorderLayout.SOUTH); // muestra el boton en la parte inferior del JFrame

        /*area para mostrar resultados de la conexion*/
        JTextArea areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setLineWrap(true);
        areaResultados.setWrapStyleWord(true);
        this.add(new JScrollPane(areaResultados), BorderLayout.CENTER); //para mostrar la interfaz swing en el centro del JFrame
        
        //Recupera la url ingresada cuando el usuario presiona el boton
        botonProbar.addActionListener(e -> {
            
            String url = campoUrl.getText();
            String usuario = campoUsuario.getText();
            char[] contrasena = campoContrasena.getPassword();
            ConexionBD conexionBD =  new ConexionBD(); //crea un objeto para gestional la conexion JBDC

            try {
                    conexionBD.probarConexion(url, usuario, contrasena);
                    areaResultados.setForeground(Color.GREEN);
                    areaResultados.setText("Conexion exitosa");
                } catch (ClassNotFoundException ex) {
                    areaResultados.setForeground(Color.RED);
                    areaResultados.setText("Driver JDBC no encontradi:\n" + ex.getMessage());

                } catch (SQLException ex) {
                    areaResultados.setForeground(Color.RED);
                    areaResultados.setText(
                        "Error de conexión JDBC:\n" +
                        "Mensaje: " + ex.getMessage() +
                        "\nEstado SQL: " + ex.getSQLState() +
                        "\nCódigo de error: " + ex.getErrorCode()
                );
                }
        });

        this.setVisible(true); //para hacer visible la ventana
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConexionTester());

    }
}
        