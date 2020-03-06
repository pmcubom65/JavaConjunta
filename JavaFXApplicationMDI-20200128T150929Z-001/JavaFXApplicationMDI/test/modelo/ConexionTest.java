
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ConexionTest {
    
       String basededatos="gestion_festival";
       String usuario="root";
       String password="";
    
    public ConexionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of obtenerConexion method, of class Conexion.
     */
    @Test
    public void testObtenerConexion() {
        System.out.println("obtenerConexion");
        java.sql.Statement st=null;
        java.sql.Statement st2=null;
        Conexion instance = new Conexion();
      
        Connection result = instance.obtenerConexion();
       
        Connection mitest=null;
        try {
     
        mitest=DriverManager.getConnection("jdbc:mysql://localhost:3306/" + basededatos, usuario, password);
            st=mitest.createStatement();
            st2=result.createStatement();
    
        } catch (Exception e) {}
      
        assertSame(mitest instanceof Connection, result instanceof Connection);
        assertEquals(st instanceof Statement, st2 instanceof Statement);
      
    try {
        mitest.close();
        result.close();
    }catch (Exception e) {}
      
    }


    @Test
    public void testDesconectar() {
        System.out.println("desconectar");
               Connection mitest=null;
             try {
        mitest=DriverManager.getConnection("jdbc:mysql://localhost:3306/" + basededatos, usuario, password);
        } catch (Exception e) {}
        Connection conexion = null;
        Conexion instance = new Conexion();
        instance.obtenerConexion();
        instance.desconectar(mitest);
        assertNull(instance);
    }

  
    @Test
    public void testSelectEventosPropios() throws Exception {
        System.out.println("selectEventosPropios");
        Connection mitest=null;
             try {
        mitest=DriverManager.getConnection("jdbc:mysql://localhost:3306/" + basededatos, usuario, password);
        } catch (Exception e) {}
       
        Conexion instance = new Conexion();
        Connection miconexion=instance.obtenerConexion();
        ResultSet expResult = null;
        ResultSet result = instance.selectEventosPropios(mitest);
        
        assertFalse(result instanceof ResultSet);
   
    }


   
}
