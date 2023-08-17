/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author NICOLAS
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Conectar {

    private String baseDatos = "ListaDeTarea";
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "prueba";
    private String pass = "123";
    private String driver = "com.mysql.cj.jdbc.Driver";

    private Connection con;

    //conectamos a bd
    public Connection conectado() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url + baseDatos, user, pass);
        } catch (Exception e) {
            System.err.println(e);
        }

        return cn;
    }

    //mostramos listas creadas
    public ArrayList<String> mostrarListas() {
        ArrayList<String> registro = new ArrayList<>();
        
        try {
            con = conectado();
            PreparedStatement ps = con.prepareStatement("select idLista, fechaInicio, fechaTermino\n"
                    + "from lista;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idLista");
                String fechainicio = rs.getString("fechaInicio");
                String fechaTermino = rs.getString("fechaTermino");
                
                String nuevoid = String.valueOf(id);
                
                String elRegistro = nuevoid+" - "+fechainicio+" - "+fechaTermino;
                
                registro.add(elRegistro);
            }
            con.close();
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return registro;
    }

    //eliminar registros
    public void eliminar(int id) {
        try {
            con = conectado();

            PreparedStatement ps = con.prepareStatement("delete from lista where idLista = ?;");
            ps.setInt(1, id);

            int res = ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Lista de tarea Eliminada");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
            }

            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    //agregar registro a base de datos
    public void agregar(String lista, String fechaInicio, String fechaTermino) {
        try {
            con = conectado();
            
            PreparedStatement ps = con.prepareStatement("insert into lista(lista, fechaInicio, fechaTermino)\n" +
                                                        "	values\n" +
                                                        "		(?, ?, ?);");
            ps.setString(1, lista);
            ps.setString(2, fechaInicio);
            ps.setString(3, fechaTermino);
            
            int res = ps.executeUpdate();
            
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Agregado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar una nueva lista");
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
