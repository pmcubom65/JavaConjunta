/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author FranciscoHO
 */
public class ImprimirInformeEventosUsuario {
    public void mostrarInforme(String name_events, String nombreUsuario) {
        try{
            
            HashMap map=null;
            
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            
            Conexion conectabd=new Conexion();
            Connection conectado=conectabd.obtenerConexion();
            String sql = "SELECT DISTINCT t_event.name_events, t_event.date_events, t_event.place_events,t_group.name_group, t_group.obs_group FROM t_event,t_events_groups, t_group WHERE name_events = '" + name_events + "' ORDER BY name_events ASC";
            Statement stm=conectado.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            
            
            while (rs.next()) {
                map = new HashMap();
                
                map.put("Nombre", nombreUsuario);
                map.put("NombreEvento", rs.getString(1));
                map.put("Fecha", rs.getString(2));
                map.put("Direccion", rs.getString(3));
                map.put("NombreGrupo", rs.getString(4));
                map.put("Estilo", rs.getString(5));
                list.add(map);
            }
            conectabd.desconectar(conectado);
            
            JRBeanCollectionDataSource fuenteDeDatos = new JRBeanCollectionDataSource(list);
            
            JasperPrint print = JasperFillManager.fillReport(ImprimirInformeEventosUsuario.class.getResourceAsStream("/modelo/clases/EventosUsuario.jasper"), map,fuenteDeDatos);
            
            JasperViewer.viewReport(print,false);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex,"Error.",JOptionPane.ERROR_MESSAGE);
        }
    }
}
