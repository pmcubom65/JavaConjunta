package modelo;

import controlador.variableGlobal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    public Connection conexion;

    public Connection obtenerConexion() {
       // String basededatos = "sql7315953";
       //String basededatos = "gestion_festival";
        String basededatos="s163c766_gestion_festival";
       
       
       //String usuario = "root";
       // String usuario = "sql7315953";
        //String password = "";
       //String password = "Segundodam";
        
       String usuario="s163c766_alumno";
        String password="alumno1dam";
             

        try {
            Class.forName("com.mysql.jdbc.Driver");
          conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + basededatos, usuario, password);
             //conexion = DriverManager.getConnection("jdbc:mysql://sql2.freesqldatabase.com:3306/" + basededatos, usuario, password);
        } catch (Exception e) {
            e.printStackTrace();
         }

        return conexion;
    }

    public void desconectar(Connection conexion) {

        try {
            conexion.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    
    public ResultSet selectEventosPropios(Connection conect) throws SQLException{
            String sql = "SELECT * FROM t_event where id_users_events="+variableGlobal.id_active;
            Statement stm = conect.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            return rs;
    }
    public void insertEventoPropio(Connection c,String date,String place,String name,float price)throws SQLException{
                String sql = "insert into t_event values(0,"+ variableGlobal.id_active +",'" + date + "','" + place + "','" + name + "',"+price+")";
                Statement stm = c.createStatement();
                stm.executeUpdate(sql);
    }
    public void deleteEventoPropio(Connection c,int event)throws SQLException{
        String sql = "delete from t_event where id_events="+event+" and id_users_events="+variableGlobal.id_active;
        Statement stm = c.createStatement();
        stm.executeUpdate(sql);
    }
    public void updateEventoPropio(Connection c,int id,String fecha,String lugar,String nombre,float precio)throws SQLException{
        String sql = "update t_event set date_events='"+fecha+"',place_events='"+lugar+"',name_events='"+nombre+"',price="+precio+" where id_events="+id;
        Statement stm = c.createStatement();
        stm.executeUpdate(sql);
    }


}
