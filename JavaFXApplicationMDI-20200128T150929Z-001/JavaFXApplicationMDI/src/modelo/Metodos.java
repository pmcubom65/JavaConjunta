/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import br.com.supremeforever.suprememdiwindow.MDIWindow;
import controlador.variableGlobal;
import modelo.clases.Evento;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.clases.Groups;
import modelo.clases.event_groups;
import javafxapplicationmdi.JavaFXApplicationMDI;
import static javafxapplicationmdi.JavaFXApplicationMDI.mDIWindow;

/**
 *
 * @author tarde
 */
public class Metodos {
    
    private static JavaFXApplicationMDI loader; 
            
    public void seleccionarDatosTGroup(TableView<Groups> tabla) {
        
        //Datos por SELECT de SQL
        //final ObservableList<CAlumnos> datos = FXCollections.observableArrayList();
        try {
            Conexion conectabd= new Conexion();
            Connection conectado=conectabd.obtenerConexion();
            String sql = "SELECT id_group,name_group,logo_group,obs_group FROM t_group";
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView
            tabla.getItems().clear();
            while (rs.next()) {
                String id_group = rs.getString(1);
                String name_group = rs.getString(2);
                String logo_group = rs.getString(3);
                String obs_group = rs.getString(4);
                
                //Añade cada registro a la TableView
                
                tabla.getItems().addAll(new Groups(id_group, name_group, logo_group, obs_group));
            }
            //Visualizar la TableView.
            tabla.setVisible(true);
            //Redimensiona cada columna al tamaño de sus datos.
            tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void insercionDatosTGroup(String nombre,String logo,String obs) {
        try {
            
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql = "insert into t_group values(0,'" + nombre + "'  ,'" + logo + "','"+obs+"')";
            Statement stm = conectado.createStatement();
            stm.executeUpdate(sql);
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente", "Operación", JOptionPane.INFORMATION_MESSAGE);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"No se ha podido insertar el grupo, nombre duplicado","El nombre del grupo ya existe", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void actualizarDatosTGroup(String nombre,String logo,String obs,int grupo){
        try{
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql = "UPDATE `t_group` SET `name_group` = '"+nombre+"', `logo_group` = '"+ logo+"', `obs_group` = '"+obs+"' WHERE `t_group`.`id_group` = "+grupo+"";
            Statement stm = conectado.createStatement();
            stm.executeUpdate(sql);
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
            JOptionPane.showMessageDialog(null,"Registro modificado correctamente", "Operación", JOptionPane.INFORMATION_MESSAGE);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"No se ha podido modificar el grupo, nombre duplicado","El nombre del grupo ya existe", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public String generarCopia(File origen){
        File destino=null;
        try{
            destino=new File("src/images/"+origen.getName());
            if (destino.exists())
                destino.delete();
                destino.createNewFile();
            FileInputStream fis = new FileInputStream(origen); //inFile -> Archivo a copiar
            FileOutputStream fos = new FileOutputStream(destino); //outFile -> Copia del archivo
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            fis.close();
            fos.close();
        }catch (IOException ioe) {
            System.err.println("Error al Generar Copia");
        }
        return destino.getName();
    }
    
    public String seleccionarImagen(){
        String nombre=null;
        File img=null;
        JFileChooser jfc = new JFileChooser();
        //f=new File("\\images/"+img.getName());
        //jfc.setCurrentDirectory(f);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg","png");
        jfc.setFileFilter(filter);
        jfc.setDialogTitle("Selecciona la imagen");
        int returnVal = jfc.showDialog(null, "Elegir");
        if (returnVal == JFileChooser.APPROVE_OPTION){
            img = jfc.getSelectedFile();
            
            if(!url(img).equals("images")){
            nombre=generarCopia(img);
            System.out.println(url(img));
            }
            else
                nombre=img.getName();
        }
        else
            return null;
        return nombre;
    }
    
    public String url(File f){
      
       String cadena;
       String[]ruta;
       ruta=f.getParent().split("\\\\");
       cadena=ruta[ruta.length-1];
        return cadena;
    }
    
    /*
    public String cambiarRuta(String ruta){
        String cadena=null;
        StringBuilder stb=new StringBuilder(ruta);
        while (stb.indexOf("\\")!=-1){
            int pos=stb.indexOf("\\");
            stb.replace(pos, pos+1, "/");
        }
        cadena=new String(stb.toString());
        return cadena;
    }
    */
    public boolean borrarDatosTGroup(int grupo){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Borrar Registro");
            alert.setHeaderText("Borrar Registro");
            alert.setContentText("¿Seguro de eliminar el registro?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                
                Conexion conectabd = new Conexion();
                Connection conectado = conectabd.obtenerConexion();
                String sql = "DELETE FROM `t_group` WHERE `t_group`.`id_group` = "+grupo+"";
                Statement stm = conectado.createStatement();
                stm.executeUpdate(sql);
                conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
                JOptionPane.showMessageDialog(null,"Registro borrado correctamente", "Operación", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"No se ha podido modificar el grupo, nombre duplicado","El nombre del grupo ya existe", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    //Javi
        public void seleccionarTodosDatosEvent_group(TableView<event_groups> data,String nombre_evento) {
        
        //Datos por SELECT de SQL
        //final ObservableList<CAlumnos> datos = FXCollections.observableArrayList();
        try {
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql = "SELECT `id_ev_group`, `name_events`, `name_group`,`hour_ev_group` FROM `t_events_groups` JOIN t_event ON id_event=t_event.id_events JOIN t_group ON t_events_groups.id_group=t_group.id_group where name_events='"+nombre_evento +"'ORDER by  hour_ev_group DESC,id_ev_group ASC";
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView
            data.getItems().clear();
            while (rs.next()) {
                String id_ev_group = rs.getString(1);
                String name_event = rs.getString(2);
                String name_group = rs.getString(3);
                String hour_event_group = rs.getString(4);
                
                //Añade cada registro a la TableView
                data.getItems().addAll(new event_groups(id_ev_group, name_event, name_group, hour_event_group));
            }
            
            //Redimensiona cada columna al tamaño de sus datos.
            data.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            conectabd.desconectar(conectado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void seleccionarDatosEvent_group(ChoiceBox choiceBox) {
        
        //Datos por SELECT de SQL
        //final ObservableList<CAlumnos> datos = FXCollections.observableArrayList();
        try {
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql = "SELECT `name_events` FROM `t_event` where id_users_events="+variableGlobal.id_active;
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView
            
            while (rs.next()) {
                String id_ev_group = rs.getString(1);
                choiceBox.getItems().add(id_ev_group);
            }
            conectabd.desconectar(conectado);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    public void seleccionarDatosGruposEvent_group(ChoiceBox choiceBox,String nomEvent) {
        
        //Datos por SELECT de SQL
        //final ObservableList<CAlumnos> datos = FXCollections.observableArrayList();
        try {
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql =  "SELECT `name_group` FROM `t_group`";
            
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView
            
            while (rs.next()) {
                String id_ev_group = rs.getString(1);
                choiceBox.getItems().add(id_ev_group);
            }
            conectabd.desconectar(conectado);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }
    //crear metodo comprobar hora
   
    public void añadirEvent_group(String nomEvent,String nomGrupo,String hora,TableView<event_groups> data) {
        String id_group=null;
        String id_event=null;
        
        //Datos por SELECT de SQL
        //final ObservableList<CAlumnos> datos = FXCollections.observableArrayList();
        try {
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql =  "SELECT `id_group` FROM `t_group` where name_group='"+nomGrupo+"'";
            String sql2 =  "SELECT `id_events` FROM `t_event` where name_events='"+nomEvent+"'";
            
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView
            
            while (rs.next()) {
                id_group = rs.getString(1);
            }
            rs = stm.executeQuery(sql2);
            while (rs.next()) {
                id_event = rs.getString(1);
            }
            
            String a=" INSERT INTO `t_events_groups`(`id_ev_group`, `id_group`, `id_event`, `hour_ev_group`)"
                    + "VALUES (null,"+id_group+","+id_event+",'"+hora+"')";
            
            stm.executeUpdate(a);
            
            seleccionarTodosDatosEvent_group(data, nomEvent);
            
            conectabd.desconectar(conectado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean comprobarHoraEvent_group(String hora){
        String a=":";
        if(hora.length()!=5){
            return false;
        }
        for(int i=0;i<hora.length();i++){
            if(i!=2){
                if(!Character.isDigit(hora.charAt(i))){
                    return false;
                }
            }else{
                if(!a.equals(Character.toString(hora.charAt(i)))){
                    return false;
                }
            }
        }
        return true;
        
    }
    
    
    public void actualizacionDatosEvent_group(String hora,String grupo,String evento,TableView<event_groups> data) {
        String id_event="";
        try {
            
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql2="SELECT `id_ev_group` FROM `t_events_groups` JOIN t_event ON "
                    + " t_event.id_events=id_event JOIN t_group "
                    + "ON t_group.id_group=t_events_groups.id_group "
                    + "where t_group.name_group='"+grupo+"' and t_event.name_events='"+evento+"'";
            
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql2);
            while (rs.next()) {
                id_event = rs.getString(1);
            }
            
            String sql ="UPDATE `t_events_groups` SET`hour_ev_group`='"+hora+"' WHERE id_ev_group="+id_event ;
            
            stm.executeUpdate(sql);
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
            
            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.", "Operación", JOptionPane.INFORMATION_MESSAGE);
            
            seleccionarTodosDatosEvent_group(data, evento);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void borrarDatosEvent_group(int id,String evento,TableView<event_groups> data) {
        String id_event="";
        try {
            
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Borrar Registro");
            alert.setHeaderText("Borrar Registro");
            alert.setContentText("¿Seguro de eliminar el registro?");
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK) {
                
                String sql2="DELETE FROM `t_events_groups` WHERE id_ev_group="+id;
                
                Statement stm = conectado.createStatement();
                stm.executeUpdate(sql2);
                JOptionPane.showMessageDialog(null, "Registro borrado correctamente.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                seleccionarTodosDatosEvent_group(data, evento);
            }
            
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
            
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Monedero
    
    public void seleccionarDatost_event(TableView<Evento> dataGridView1) {

        try {
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql = "SELECT name_events,place_events,date_events,price,id_events FROM t_event";
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
//Se borran los datos de la TableView
            dataGridView1.getItems().clear();
            while (rs.next()) {

                String nombre = rs.getString(1);
                String lugar = rs.getString(2);
                String fecha = rs.getString(3);
                String precio = rs.getString(4);
                String evento = rs.getString(5);

//AÃ±ade cada registro a la TableView
                dataGridView1.getItems().addAll(new Evento(nombre, lugar, fecha, precio,evento));
            }
//Visualizar la TableView.
            dataGridView1.setVisible(true);
//Redimensiona cada columna al tamaÃ±o de sus datos.
            dataGridView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }

    }
    // MARIA
    
    public void insercionDatosR(String nombre, String apellidos, String usuario, String contrasenia, String dni) {
        try {
           
                Conexion conectabd = new Conexion();
                Connection conectado = conectabd.obtenerConexion();
                String sql ="insert into t_users values(0,'" + usuario+ "' ,'" + contrasenia+ "' ,"+ 2 +",'" + nombre + "'  ,'" + apellidos+ "'  ,'"+ dni + "')";
                Statement stm = conectado.createStatement();
                stm.executeUpdate(sql);
                conectabd.desconectar(conectado); //Desconecta la conexiÃ³n actual a la BBDD.
                JOptionPane.showMessageDialog(null,
                        "Usuario registrado correctamente.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                mDIWindow.closeWindow();
                
        }catch (SQLException ex) {
           
            if(ex.getErrorCode()==1062){
                JOptionPane.showMessageDialog(null, "Nombre de usuario o DNI ya existente.", "Error de SQL.", JOptionPane.ERROR_MESSAGE);
            }

            else
                JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);

    }
    }
   
     public void insercionDatosIS(String usuario, String contrasenia) {
        try {
           
                Conexion conectabd = new Conexion();
                Connection conectado = conectabd.obtenerConexion();
                String sql = "select id_user,cod_types_user from t_users where nick_user= '"+ usuario + " ' and pass_user = '" + contrasenia + "';";
                Statement stm = conectado.createStatement();
                ResultSet rs;
                rs =stm.executeQuery(sql);
             
                    if(rs.next()==true){
                       
                             new variableGlobal(Integer.parseInt(rs.getString(1)));
                        if (Integer.parseInt(rs.getString(2))==1){
                            loader.activarMenusAdmin();
                        }
                        else
                            loader.activarMenusUser();
                        
                             conectabd.desconectar(conectado); //Desconecta la conexiÃ³n actual a la BBDD.
                             JOptionPane.showMessageDialog(null,"Sesión iniciada correctamente.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                             mDIWindow.closeWindow();
                        }
                    
                    else
                         JOptionPane.showMessageDialog(null, "Introduzca datos válidos", "Usuario o contraseña erróneo", JOptionPane.ERROR_MESSAGE);                   
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }

     public void cerrarVentana(){
     mDIWindow.closeWindow();
     }
     public void cargarLoader(JavaFXApplicationMDI loader){
         this.loader=loader;
     }
     
     // Paco
     
     

    public void selectEventosUsuarios(TableView<EventoDeUsuario> dgv, String nif){
        
        try {
            Conexion conexion = new Conexion();
            Connection conectado = conexion.obtenerConexion();
            String sql = "SELECT name_events, date_events, place_events  FROM t_purchase_ev, t_event WHERE nif_purchase_ev='" + nif + "' AND t_purchase_ev.id_event_purchase_ev=t_event.id_events ORDER BY id_purchase_ev DESC";
            Statement stm = conectado.createStatement();
            ResultSet rs;

                rs = stm.executeQuery(sql);
            
            dgv.getItems().clear();

            while (rs.next()) {
                String name_events = rs.getString(1);
                String date_events = rs.getString(2);
                String place_events = rs.getString(3);

                dgv.getItems().addAll(new EventoDeUsuario(name_events, date_events, place_events));
                
                dgv.setVisible(true);
                dgv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            }
            conexion.desconectar(conectado);
            
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectUsuarios(TableView<MT_users> dgv, String usuarioText){
        
        try {
            Conexion conexion = new Conexion();
            Connection conectado = conexion.obtenerConexion();
            String sql = "SELECT id_user, nick_user, nif_user FROM t_users WHERE nick_user LIKE('%" + usuarioText + "%')ORDER BY id_user ASC";
            Statement stm = conectado.createStatement();
            ResultSet rs;

                rs = stm.executeQuery(sql);
            
            dgv.getItems().clear();

            while (rs.next()) {
                String id_user = rs.getString(1);
                String nick_user = rs.getString(2);
                String nif_user = rs.getString(3);
                
                dgv.getItems().addAll(new MT_users(id_user, nick_user, nif_user));
                
                dgv.setVisible(true);
                dgv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            }
            conexion.desconectar(conectado);
            
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
    
 
