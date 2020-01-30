/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.clases.Evento;
import modelo.Metodos;

/**
 * FXML Controller class
 *
 * @author tarde
 */
public class TodoseventosController implements Initializable {

    @FXML
    private Label labeltodos;
    @FXML
    private TableView<Evento> dataGridView1;
    private Evento evento;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*   this.metodos=JavaFXAlejandro_Monedero.metodo;
        nombreAlumno.requestFocus(); //Código del alumno*/
        //Define el campo en la TableView
        TableColumn<Evento, String> nombreEvento = new TableColumn<>("Nombre");
        //Asocia el campo con el modelo CAlumnos
        nombreEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("name_events"));

        //Nombre del alumno
        TableColumn<Evento, String> lugarEvento = new TableColumn<>("Lugar");
        lugarEvento.setCellValueFactory(new PropertyValueFactory<>("place_events"));

        //Apellidos del alumno
        TableColumn<Evento, String> fechaEvento = new TableColumn<>("Fecha");
        fechaEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("date_events"));

        //DNI del alumno
        TableColumn<Evento, String> precioEvento = new TableColumn<>("Precio");
        precioEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("price"));

        //Añadir las columnas a la TableView
        dataGridView1.getColumns().addAll(nombreEvento, lugarEvento, fechaEvento, precioEvento);
        new Metodos().seleccionarDatost_event(dataGridView1);
        /*dataGridView1.getSelectionModel().selectedItemProperty().addListener((obs,OldSelection,NewSelection) ->{
            if(NewSelection!=null){
                
                try {
                    ventanaDatos(new Evento(dataGridView1.getSelectionModel().selectedItemProperty().get().getname_events(),
                            dataGridView1.getSelectionModel().selectedItemProperty().get().getdate_events(),
                             dataGridView1.getSelectionModel().selectedItemProperty().get().getplace_events(),
                             dataGridView1.getSelectionModel().selectedItemProperty().get().getprice()
                    ));
                } catch (Exception ex) {
                    Logger.getLogger(TodoseventosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });*/

    }
    
     public void ventanaDatos(Evento evento)throws Exception{
             FXMLLoader loader= new FXMLLoader(controlador.DetalleseventosController.class.getResource("/vista/DetallesEventos.fxml"));
          
           // DetalleseventosController control;
            
           // Parent root =(Parent) loader.load();
           
            Stage stage3=new Stage();
            stage3.setScene(new Scene(((Pane)loader.load())));
             DetalleseventosController control=loader.getController();
            control.initData(evento);
            stage3.initModality(Modality.APPLICATION_MODAL);
            stage3.setTitle("Detalles Evento");
            //stage3.setScene(new Scene(root));
            stage3.show();
         
    }

    @FXML
    private void seleccionarEvento(MouseEvent event) {
       evento= dataGridView1.getSelectionModel().getSelectedItem();
                if(evento!=null){
                
                try {
                    ventanaDatos(evento);
                } catch (Exception ex) {
                    Logger.getLogger(TodoseventosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }

   
       
}
