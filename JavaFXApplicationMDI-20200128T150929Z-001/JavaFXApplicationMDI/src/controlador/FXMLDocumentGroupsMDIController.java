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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Metodos;
import modelo.clases.*;

/**
 * FXML Controller class
 *
 * @author tarde
 */
public class FXMLDocumentGroupsMDIController implements Initializable {

    @FXML
    private Button btn_aniadir;
    @FXML
    private Button btn_actualizar;
    @FXML
    private Button btn_logo;
    @FXML
    private TextField campo_nombre_grupo;
    @FXML
    private TableView<Groups> dataGridView1;
    @FXML
    private TextArea campo_observaciones;
    @FXML
    private ImageView image_Logo;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        
        campo_nombre_grupo.requestFocus(); 

        TableColumn<Groups, String> id_group = new TableColumn<>("ID grupo");
        id_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("id_group"));

        
        TableColumn<Groups, String> name_group = new TableColumn<>("Nombre grupo");
        name_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("name_group"));

       
        TableColumn<Groups, String> logo_group = new TableColumn<>("Logo grupo");
        logo_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("logo_group"));

       
        TableColumn<Groups, String> obs_group = new TableColumn<>("Observaciones");
        obs_group.setCellValueFactory(new PropertyValueFactory<Groups, String>("obs_group"));

      
        dataGridView1.getColumns().addAll(id_group, name_group, logo_group, obs_group);
        
        
        actualizarGrid();
    }    

    
    private void actualizarGrid(){
        new Metodos().seleccionarDatosTGroup(dataGridView1);
    }
    
    private void vaciarCampos(){
    campo_nombre_grupo.setText("");
    campo_observaciones.setText("");
    image_Logo.setImage(null);
    }
    
    public String url(){
       String cadena;
       String[]ruta;
       ruta=image_Logo.getImage().impl_getUrl().split("/");
       cadena="/"+ruta[ruta.length-2]+"/"+ruta[ruta.length-1];
        return cadena;
    }
    
    @FXML
    private void insercionDatos(ActionEvent event) {
      if(campo_nombre_grupo.getText().isEmpty() || campo_observaciones.getText().isEmpty() || image_Logo.getImage()==null){
          JOptionPane.showMessageDialog(null,"Debe rellenar todos los campos incluida la imagen.","Error.",JOptionPane.ERROR_MESSAGE);
      }
      else{
      new Metodos().insercionDatosTGroup(campo_nombre_grupo.getText(), url(), campo_observaciones.getText());
      actualizarGrid();
      vaciarCampos();
      }
     
    }


    @FXML
    private void seleccionarGrupo(MouseEvent event) {
        vaciarCampos();
        Groups g=dataGridView1.getSelectionModel().getSelectedItem();
        campo_nombre_grupo.setText(g.getname_group());
        campo_observaciones.setText(g.getobs_group());
        image_Logo.setImage(new Image(g.getlogo_group()));
    }

    @FXML
    private void actualizarGrupo(ActionEvent event) {
         if(campo_nombre_grupo.getText().isEmpty() || campo_observaciones.getText().isEmpty() || image_Logo.getImage()==null){
          JOptionPane.showMessageDialog(null,"Debe Rellenar los campos.","Error.",JOptionPane.ERROR_MESSAGE);
      }
         else if(dataGridView1.getSelectionModel().getSelectedItem()==null)
              JOptionPane.showMessageDialog(null,"Debe seleccionar una fila de la tabla.","Error.",JOptionPane.ERROR_MESSAGE);
      else{
            Groups g=dataGridView1.getSelectionModel().getSelectedItem();
            new Metodos().actualizarDatosTGroup(campo_nombre_grupo.getText(), url(), campo_observaciones.getText(), Integer.parseInt(g.getid_group()));
            actualizarGrid();
            vaciarCampos();
            dataGridView1.getSelectionModel().clearSelection();
    }
    }

    @FXML
    private void buscarImagen(ActionEvent event) throws InterruptedException {
       
       String nombre=new Metodos().seleccionarImagen();
       if(nombre!=null){
       image_Logo.setImage(null);
       Image imagen=new Image("/images/"+nombre);
       image_Logo.setImage(imagen);
       }
    }

    @FXML
    private void pulsaTeclado(KeyEvent event) {
             
        if (event.getCode()==KeyCode.DELETE){
            Groups g=dataGridView1.getSelectionModel().getSelectedItem();
           boolean borrar=( new Metodos().borrarDatosTGroup(Integer.parseInt(g.getid_group())));
           if (borrar){  actualizarGrid();
            vaciarCampos();
             dataGridView1.getSelectionModel().clearSelection();
           } }
        }
        
    }
 
    

