/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import br.com.supremeforever.suprememdiwindow.MDIWindow;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import modelo.Metodos;

/**
 * FXML Controller class
 *
 * @author tarde
 */



public class FXMLDocumentLoginMDIController implements Initializable {

    @FXML
    private TextField is_contraseña;
    @FXML
    private TextField is_usuario;
    @FXML
    private Button btn_iniciarsesion;

   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    @FXML
    private void onClick(ActionEvent event) {
         new Metodos().insercionDatosIS( is_usuario.getText(), is_contraseña.getText());
    }

    
}
