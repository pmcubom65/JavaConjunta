/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package modelo.clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafxapplicationmdi.JavaFXApplicationMDI;
import javax.swing.JOptionPane;
import modelo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alvaro
 */
public class ImprimirOrdenCompra {
    
    
    
    public void mostrarInforme(int id_event, String nif){
        try{
            //Creación de un map para guardar la estructura de los datos del registro de la tabla alumnos.
            HashMap map=null;
            //Creación de una lista o Array de map para guardar los maps de datos.
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            //Acceso a la base de datos
            Conexion conectabd=new Conexion();
            Connection conectado=conectabd.obtenerConexion();
            String sql="select t_purchase_ev.name_purchase_ev, t_purchase_ev.nif_purchase_ev, t_purchase_ev.email_purchase_ev, t_purchase_ev.address_purchase_ev, t_event.price from t_purchase_ev join t_event on t_purchase_ev.id_event_purchase_ev = t_event.id_events where t_purchase_ev.nif_purchase_ev = '" + nif + "' and t_event.id_events = " + id_event ;
            
            Statement stm=conectado.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            //Se llena el array de map con los valores de la tabla de alumnos de la base de datos.
            while (rs.next()) {
                map = new HashMap();
                //Codigo, Nombre, etc son los campos que se han definido en el informe
                //Se canjearán por datos que se extraen con rs.getString(1) siendo el valor del campo Codigo
                map.put("Nombre", rs.getString(1));
                map.put("NIF", rs.getString(2));
                map.put("E-mail", rs.getString(3));
                map.put("Direccion", rs.getString(4));
                map.put("Precio", rs.getString(5));
                list.add(map); //se añade al map a la lista de map.
            }
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
            //Se convierte la lista de datos al formato que acepta Jasper.
            JRBeanCollectionDataSource fuenteDeDatos = new JRBeanCollectionDataSource(list);
            //Se crea un objeto Jasper de impresión, buscando el archivo donde inyectar los datos, el formato
            //del map es decir de la estructura de datos y el conjunto de datos obtenidos a visualizar.
            JasperPrint print =
                    JasperFillManager.fillReport(ImprimirOrdenCompra.class.getResourceAsStream("/modelo/clases/inf_pago_evento.jasper"),map,fuenteDeDatos);
            //Visualizamos el visor con el informe y los datos formateados.
            JasperViewer.viewReport(print,false);
        }catch(Exception ex){
            //JOptionPane.showMessageDialog(null,ex,"Error.",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    public boolean devuelveEstadoCajas(String nombre, String direccion, String nif, String cp, String email)
    {
        //Comprueba que las cajas de texto estén rellenas de datos.
        boolean estado=true;
        if(nombre.length()==0){
            JOptionPane.showMessageDialog(null,"Introduzca un valor en el Nombre.","Operación",JOptionPane.INFORMATION_MESSAGE);
            estado=false;
            //JavaFXApplicationMDI.vista.txtNombre.requestFocus(); //poner el foco en la caja de texto inicada.
        }
        if(estado==true) //evaluamos el campo.
        {
            if(email.length()==0){
                JOptionPane.showMessageDialog(null,"Introduzca un valor en el email.","Operación",JOptionPane.INFORMATION_MESSAGE);
                estado=false;
                //JavaFXApplicationMDI.vista.txtDireccion.requestFocus(); //poner el foco en la caja de texto inicada.
            }
        }
        if(estado==true) //evaluamos el campo.
        {
             if(nif.length()==0){
                JOptionPane.showMessageDialog(null,"Introduzca un valor en el nif.","Operación",JOptionPane.INFORMATION_MESSAGE);
                estado=false;
                //JavaFXApplicationMDI.vista.txtEmail.requestFocus(); //poner el foco en la caja de texto inicada.
            }
        }
        if(estado==true) //evaluamos el campo.
        {
            if(direccion.length()==0){
                
                JOptionPane.showMessageDialog(null,"Introduzca un valor en el NIF.","Operación",JOptionPane.INFORMATION_MESSAGE);
                estado=false;
                //JavaFXApplicationMDI.vista.txtNIF.requestFocus(); //poner el foco en la caja de texto iniciada.
                
            }
        }
        if(estado==true) //evaluamos el campo.
        {
            if(cp.length()==0){
                
                JOptionPane.showMessageDialog(null,"Introduzca un valor en el Codigo Posal.","Operación",JOptionPane.INFORMATION_MESSAGE);
                //JavaFXApplicationMDI.vista.txtCP.requestFocus(); //poner el foco en la caja de texto iniciada.
                 estado=false;
            }else{
                for(int i = 0; i < cp.length(); i++){
                if(!Character.isDigit(cp.charAt(i))){
                    
                     estado=false;
                }
        }
        if(estado==false)JOptionPane.showMessageDialog(null,"CP no valido.","Operación",JOptionPane.INFORMATION_MESSAGE);
        }
        }
        return estado;
    }
    
    
    public boolean añadirRegistro(int id_event, String nombre, String direccion, String nif, String cp, String email, String pago){
        try {
            
                Conexion conectabd=new Conexion();
                Connection conectado=conectabd.obtenerConexion();
                String sql= "INSERT INTO `t_purchase_ev`(`id_purchase_ev`, `id_event_purchase_ev`, `name_purchase_ev`, `email_purchase_ev`, `pay_purchase_ev`, `address_purchase_ev`, `cp_purchase_ev`, `nif_purchase_ev`) VALUES" + "(null ," + id_event + ", '" + nombre + "' , '" + email + "' , '" + pago + "' , '" + direccion+ "' ," + cp + ", '" + nif+ "')";
                Statement stm=conectado.createStatement();
                stm.executeUpdate(sql);
                conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
                JOptionPane.showMessageDialog(null,"Registro insertado correctamente.","Operación",JOptionPane.INFORMATION_MESSAGE);
               // limpiarCajas();
                return true;
        }
        catch(Exception ex){
            //JOptionPane.showMessageDialog(null,ex,"Error de SQL.",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }
    
  /*  private void limpiarCajas()
    {
        //Limpia de datos a todas las cajas.
        JavaFXApplicationMDI.vista.txtNombre.setText("");
        JavaFXApplicationMDI.vista.txtEmail.setText("");
        JavaFXApplicationMDI.vista.txtDireccion.setText("");
        JavaFXApplicationMDI.vista.txtNIF.setText("");
        JavaFXApplicationMDI.vista.txtCP.setText("");
        JavaFXApplicationMDI.vista.txtNombre.requestFocus(); //poner el foco en la caja de texto inicada.
    }*/
    
    
    
    
    
    public void abrirVentana(int id_event,String nombre, String direccion, String nif, String cp, String email, String pago) {
        try{
      if(devuelveEstadoCajas(nombre, direccion, nif, cp, email)){
         // JavaFXApplicationMDI.iniciar();
          if(añadirRegistro(id_event, nombre, direccion, nif, cp, email, pago)){
              mostrarInforme(id_event, nif);
          }
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex,"Ha habido un error.",JOptionPane.ERROR_MESSAGE);
        }
    }
}


