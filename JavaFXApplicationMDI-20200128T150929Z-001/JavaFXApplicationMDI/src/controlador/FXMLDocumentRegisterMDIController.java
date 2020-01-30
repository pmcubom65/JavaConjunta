/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafxapplicationmdi.JavaFXApplicationMDI;

import javax.swing.JOptionPane;
import modelo.Metodos;

/**
 * FXML Controller class
 *
 * @author tarde
 */
public class FXMLDocumentRegisterMDIController implements Initializable {

    @FXML
    private TextField r_nombre;
    @FXML
    private TextField r_apellidos;
    @FXML
    private TextField r_usuario;
    @FXML
    private TextField r_contraseña;
    @FXML
    private Button btn_registrarse;
    @FXML
    private TextField r_dni;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    

    @FXML
    private void onClick(ActionEvent event) {
        if(comprobarCampos()){
            new Metodos().insercionDatosR(r_nombre.getText(), r_apellidos.getText(), r_usuario.getText(), r_contraseña.getText(), r_dni.getText());
        }
       
          
    }
    
    private boolean comprobarCampos(){
        
        if(r_nombre.getText().isEmpty()||r_apellidos.getText().isEmpty()||r_usuario.getText().isEmpty()||r_contraseña.getText().isEmpty()){
              JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error de SQL.", JOptionPane.ERROR_MESSAGE);
              return false;
        }
        if(!comprobarDNI()){
            return false;
        }
        
        return true;        
    }
    
    private boolean comprobarDNI(){
                
        if(r_dni.getText().length()!=9){
            JOptionPane.showMessageDialog(null, "Longitud del DNI incorrecta", "Error de SQL.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!Character.isLetter(r_dni.getText().charAt(8))){
            JOptionPane.showMessageDialog(null, "El DNI tiene 8 números y acaba en letra", "Error de SQL.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        for (int i = 0; i < 8; i++) {
              if(!Character.isDigit(r_dni.getText().charAt(i))){
                JOptionPane.showMessageDialog(null, "El DNI tiene 8 números y acaba en letra", "Error de SQL.", JOptionPane.ERROR_MESSAGE);

                  return false;
              }
        }
        
      return true;  
        
    }
}
