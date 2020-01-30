/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static controlador.UsuariosController.codigo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.EventoDeUsuario;
import modelo.ImprimirInformeEventosUsuario;
import modelo.Metodos;

/**
 * FXML Controller class
 *
 * @author FranciscoHO
 */
public class EventosUsuarioController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView<EventoDeUsuario> dataGridView1;
    @FXML
    private Button btnImprimir;
    @FXML
    private TextField nombreTxt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try{
            
            TableColumn<EventoDeUsuario, String> name_events = new TableColumn<>("Nombre");
            name_events.setCellValueFactory(new PropertyValueFactory<EventoDeUsuario, String>("name_events"));

            TableColumn<EventoDeUsuario, String> date_events = new TableColumn<>("Fecha");
            date_events.setCellValueFactory(new PropertyValueFactory<EventoDeUsuario, String>("date_events"));

            TableColumn<EventoDeUsuario, String> place_events = new TableColumn<>("Dirección");
            place_events.setCellValueFactory(new PropertyValueFactory<EventoDeUsuario, String>("place_events"));

            dataGridView1.getColumns().addAll(name_events, date_events, place_events);

            new Metodos().selectEventosUsuarios(dataGridView1, UsuariosController.codigo);
            nombreTxt.setText(UsuariosController.nombre);
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void imprimir(ActionEvent event) {
        
        try{
            new ImprimirInformeEventosUsuario().mostrarInforme(dataGridView1.getSelectionModel().selectedItemProperty().get().getName_events(), UsuariosController.nombre);
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex,"Error en el informe.",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
