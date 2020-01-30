/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapplicationmdi.JavaFXApplicationMDI;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.clases.Evento;
import controlador.variableGlobal;
import static controlador.variableGlobal.id_active;
/**
 * FXML Controller class
 *
 * @author tarde
 */
public class DetalleseventosController implements Initializable {

    @FXML
    private Label labeltodos;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textFecha;
    @FXML
    private TextField textLugar;
    @FXML
    private TextField textPrecio;
    
    private Evento evento;
    @FXML
    private Button compra;
    @FXML
    private Button btn_favoritos;
   

    /**
     * Initializes the controller class.
     */
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         //Comprobamos si el hay una sesión iniciada
        comprobarVisibilidadFavorito(id_active);
        if (comprobarVisibilidadFavorito(id_active) == true) {
            btn_favoritos.setDisable((false));
        } else if (comprobarVisibilidadFavorito(id_active) == false) {
            btn_favoritos.setDisable((true));
        }
    }
    
    public void verDetalles(Evento evento){
        
        
        this.evento=evento;
        
       
    }
    
    public void initData(Evento evento) { 
        verDetalles(evento);
         textNombre.setText(evento.getname_events());
         textFecha.setText(evento.getdate_events());
    textLugar.setText(evento.getplace_events());
    textPrecio.setText(evento.getprice());
    }

    public void setTextFecha(TextField textFecha) {
        this.textFecha = textFecha;
    }

    public void setTextLugar(TextField textLugar) {
        this.textLugar = textLugar;
    }

    public void setTextNombre(TextField textNombre) {
        this.textNombre = textNombre;
    }

    public void setTextPrecio(TextField textPrecio) {
        this.textPrecio = textPrecio;
    }

    public TextField getTextFecha() {
        return textFecha;
    }

    public TextField getTextLugar() {
        return textLugar;
    }

    public TextField getTextNombre() {
        return textNombre;
    }

    public TextField getTextPrecio() {
        return textPrecio;
    }

    @FXML
    private void abrirVentanaCompra(ActionEvent event) throws IOException {
            // JavaFXApplicationMDI.iniciar(evento);
             FXMLLoader loader= new FXMLLoader(controlador.FXMLPurchasesController.class.getResource("/vista/FXMLPurchases.fxml"));
            //Parent root =(Parent) loader.load();
          //  control=loader.getController();
           
            Stage stage3=new Stage();
            stage3.setScene(new Scene(((Pane)loader.load())));
            FXMLPurchasesController control=loader.getController();
          
            JavaFXApplicationMDI.verDetalles(evento);
            control.verDetalles(evento);
            stage3.initModality(Modality.APPLICATION_MODAL);
            stage3.setTitle("Detalles Evento");
            //stage3.setScene(new Scene(root));
            stage3.show();
            /*FXMLLoader loader= new FXMLLoader(controlador.FXMLPurchasesController.class.getResource("/vista/FXMLPurchases.fxml"));
            //Parent root =(Parent) loader.load();
          //  control=loader.getController();
           
            Stage stage3=new Stage();
            stage3.setScene(new Scene(((Pane)loader.load())));
            FXMLPurchasesController control=loader.getController();
          
            JavaFXApplicationMDI.verDetalles(evento);
            control.verDetalles(evento);
            stage3.initModality(Modality.APPLICATION_MODAL);
            stage3.setTitle("Detalles Evento");
            //stage3.setScene(new Scene(root));
            stage3.show();
           */
    }

    @FXML
    private void aniadirFavorito(ActionEvent event) {
        if (id_active == 0) {
            //Abrir inciar sesión
            abrirVentanaLogin();
        } else {
            //Hacer el insert y le redirección a lista de favoritos
            insercionDatosTablaFavorito(id_active, Integer.parseInt(evento.getid_events()));
        }
    }
    
     private boolean comprobarVisibilidadFavorito(int id_active) {
        if (id_active == 0) {
            return false;
        } else {
            return true;
        }
    }

    
    public void abrirVentanaLogin() {
//	ventana v = new ventana();
//	v.setVisible(true);
        System.out.println("Abrimos la ventana iniciarSesion");
    }
    
 private void insercionDatosTablaFavorito(int id_active, int id_event) {
        try {
            modelo.Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            String sql = "INSERT INTO t_events_favorites VALUES (null, " + id_active + ", " + id_event + ")";
            Statement stm = conectado.createStatement();
            stm.executeUpdate(sql);
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
            JOptionPane.showMessageDialog(null, "Has añadido el evento actual a favoritos.", "Operación", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }   
}



  