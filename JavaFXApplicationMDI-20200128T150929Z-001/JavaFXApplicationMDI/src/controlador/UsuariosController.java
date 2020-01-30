/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.MT_users;
import modelo.Metodos;

/**
 * FXML Controller class
 *
 * @author FranciscoHO
 */
public class UsuariosController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<MT_users> dataGridView1;
    @FXML
    private Button buscar;
    @FXML
    private TextField usuarioTxt;
    
    private String nif;
    private EventosUsuarioController eUC;
    protected static String codigo;
    protected static String nombre;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            TableColumn<MT_users, String> id_user = new TableColumn<>("Código");
            id_user.setCellValueFactory(new PropertyValueFactory<MT_users, String>("id_user"));

            TableColumn<MT_users, String> nick_user = new TableColumn<>("Nombre");
            nick_user.setCellValueFactory(new PropertyValueFactory<MT_users, String>("nick_user"));

            TableColumn<MT_users, String> nif_user = new TableColumn<>("NIF");
            nif_user.setCellValueFactory(new PropertyValueFactory<MT_users, String>("nif_user"));
            //nif_user.setVisible(false);

            dataGridView1.getColumns().addAll(id_user, nick_user, nif_user);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void visualizarDetalles(){
        
        try{
            FXMLLoader loader = new FXMLLoader(controlador.EventosUsuarioController.class.getResource("/vista/EventosUsuario.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(((Pane) loader.load())));
            
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Eventos de los Usuarios");
            stage.show();
                
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void buscar(ActionEvent event) {
        
        new Metodos().selectUsuarios(dataGridView1, this.usuarioTxt.getText().toLowerCase());
        
    }

    @FXML
    private void seleccionarUsuario(MouseEvent event) {
        try{
            codigo = dataGridView1.getSelectionModel().selectedItemProperty().get().getNif_user();
            nombre = dataGridView1.getSelectionModel().selectedItemProperty().get().getNick_user();
            visualizarDetalles();
        } catch(Exception e){
            //Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
