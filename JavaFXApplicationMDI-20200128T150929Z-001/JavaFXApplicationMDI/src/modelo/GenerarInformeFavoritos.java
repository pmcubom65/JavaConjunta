/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.variableGlobal;
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

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactoryContext;

public class GenerarInformeFavoritos {
     public void mostrarInforme() {
         
       
         
         
         
         
         
         
        try {
//Creación de un map para guardar la estructura de los datos del registro de la tabla alumnos.
            HashMap map = null;
//Creación de una lista o Array de map para guardar los maps de datos.
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//Acceso a la base de datos
                Conexion conectabd=new Conexion();
       //     ModeloAccesoDatos conectabd = new ModeloAccesoDatos();
       
       
            Connection conectado = conectabd.obtenerConexion();
            String sql = "SELECT distinct tef.id_ev, tef.id_ev_fav, tef.id_user_ev_fav, te.date_events, te.name_events, te.place_events FROM t_events_favorites tef, t_event te where tef.id_ev=te.id_events and tef.id_user_ev_fav="+variableGlobal.id_active;
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                map = new HashMap();

              
                map.put("id_ev", rs.getString(1));
                map.put("id_ev_fav", rs.getString(2));
                map.put("id_user_ev_fav", rs.getString(3));
                map.put("date_events", rs.getString(4));
                map.put("name_events", rs.getString(5));
                 map.put("place_events", rs.getString(6));

                
                list.add(map); //se añade al map a la lista de map.
            }
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
//Se convierte la lista de datos al formato que acepta Jasper.
            JRBeanCollectionDataSource fuenteDeDatos = new JRBeanCollectionDataSource(list);


            JasperPrint print
                    = JasperFillManager.fillReport(GenerarInformeFavoritos.class.getResourceAsStream("/informes/inf_evento_favorito.jasper"),
                            map, fuenteDeDatos);
//Visualizamos el visor con el informe y los datos formateados.
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex, "Error.", JOptionPane.ERROR_MESSAGE);
        System.out.println(ex.getStackTrace().toString());
        }
    
    
}
}