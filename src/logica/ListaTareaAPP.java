/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import vistas.Menu;
import conexion.Conectar;


/**
 *
 * @author NICOLAS
 */
public class ListaTareaAPP {
    public static void main(String[] args) {
        Menu men = new Menu();
        
        men.setVisible(true);
        men.setResizable(false);
        men.setLocationRelativeTo(null);
        
        
        Conectar con = new Conectar();
        con.conectado();
    }
}
