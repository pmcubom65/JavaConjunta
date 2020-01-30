/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarde
 */
public class MT_videos {
    public static ArrayList<String> cargarGrupos(){
        ArrayList<String> resultados=new ArrayList<String>();
    Conexion acceso=new Conexion();
        Connection conexion=acceso.obtenerConexion();
        String sql="SELECT name_group from t_group";
        try{
        Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
            resultados.add(rs.getString(1));
            }
    }   catch (SQLException ex) {
            //Logger.getLogger(VideosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    return resultados;
}
}
