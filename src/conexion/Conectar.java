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
import javax.swing.JOptionPane;

public class Conectar {
    private String baseDatos = "ListaDeTarea";
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "prueba";
    private String pass = "123";
    private String driver = "com.mysql.cj.jdbc.Driver";
    
    Connection con;
    
    
    //conectamos a bd
    public Connection conectado(){
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url+baseDatos, user, pass);
            System.out.println("Conectado perrin");
        } catch (Exception e) {
            System.err.println(e);
        }
        
        
        return cn;
    }
    
    
    //mostramos listas creadas
    public void mostrarListas(){
        try {
            con = conectado();
            
            PreparedStatement ps = con.prepareStatement("select idLista, fechaInicio, fechaTermino\n" +
                                                                "from lista;");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idLista");
                String fechainicio = rs.getString("fechaInicio");
                String fechaTermino = rs.getString("fechaTermino");
            } else {
                JOptionPane.showMessageDialog(null, "No se encuentran registros");
            }
            
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    //eliminar registros
    public void eliminar(int id){
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
    
    
}
