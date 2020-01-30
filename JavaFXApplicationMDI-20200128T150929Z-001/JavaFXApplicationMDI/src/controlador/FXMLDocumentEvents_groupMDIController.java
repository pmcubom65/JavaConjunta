package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import modelo.clases.event_groups;
import modelo.Metodos;

public class FXMLDocumentEvents_groupMDIController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    public TableView<event_groups> dataGridView1;
    @FXML
    public Button btn_add;
    @FXML
    public Button btn_update;
    @FXML
    public ChoiceBox list_events;
    @FXML
    public ChoiceBox list_groups_ev;
    @FXML
    public TextField campo_hora;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(new Metodos().comprobarHoraEvent_group(campo_hora.getText())){
            
            if(list_groups_ev.getValue()==null){
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error Dialog");
                alert2.setHeaderText("Look, an Error Dialog");
                alert2.setContentText("Selecciona grupo");
                
                alert2.showAndWait();
            }else{
                //new metodos().añadirEvent_group(list_events.getValue().toString(),list_groups_ev.getValue().toString(), campo_hora.getText(),dataGridView1);
                new Metodos().actualizacionDatosEvent_group(campo_hora.getText(), list_groups_ev.getValue().toString(),  list_events.getValue().toString(),dataGridView1);
            }
            
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Foramato de hora incorrecto HH:MM");
            
            alert.showAndWait();
            
            
        }
        
        
        
    }
    
    @FXML
    private void handleButtonAction2(ActionEvent event) {
        
        if(new Metodos().comprobarHoraEvent_group(campo_hora.getText())){
            
            if(list_groups_ev.getValue()==null){
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error Dialog");
                alert2.setHeaderText("Look, an Error Dialog");
                alert2.setContentText("Selecciona grupo");
                
                alert2.showAndWait();
            }else{
                new Metodos().añadirEvent_group(list_events.getValue().toString(),list_groups_ev.getValue().toString(), campo_hora.getText(),dataGridView1);
                //new metodos().actualizacionDatos(campo_hora.getText(), list_groups_ev.getValue().toString(),  list_events.getValue().toString(),dataGridView1);
            }
            
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Foramato de hora incorrecto HH:MM");
            
            alert.showAndWait();
            
            
        }
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        TableColumn<event_groups, String> id_ev_group = new TableColumn<>("Código");
        //Asocia el campo con el modelo event_groups
        id_ev_group.setCellValueFactory(new PropertyValueFactory<event_groups, String>("id_ev_group"));
        
        //Nombre del grupo
        TableColumn<event_groups, String> id_group = new TableColumn<>("Grupo");
        id_group.setCellValueFactory(new PropertyValueFactory<event_groups, String>("name_group"));
        
        //evento
        TableColumn<event_groups, String> id_event = new TableColumn<>("Evento");
        id_event.setCellValueFactory(new PropertyValueFactory<event_groups, String>("name_event"));
        
        //hora
        TableColumn<event_groups, String> hour_ev = new TableColumn<>("Hora");
        hour_ev.setCellValueFactory(new PropertyValueFactory<event_groups, String>("hour_event_group"));
        
        
        //Añadir las columnas a la TableView
        dataGridView1.getColumns().addAll(id_ev_group, id_event,id_group, hour_ev);
        
        new Metodos().seleccionarDatosEvent_group(list_events);
        
        
        list_events.setValue(list_events.getItems().get(0));
        
        new Metodos().seleccionarTodosDatosEvent_group(dataGridView1, list_events.getItems().get(0).toString());
        new Metodos().seleccionarDatosGruposEvent_group(list_groups_ev,list_events.getItems().get(0).toString());
        
        list_events.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue ov,Number Value,Number new_number){
                new Metodos().seleccionarTodosDatosEvent_group(dataGridView1, list_events.getItems().get(new_number.intValue()).toString());
            }
        });
    }
    
    
    @FXML
    private void pulsaTeclado(KeyEvent event){
        
        if(event.getCode()==KeyCode.DELETE){
            event_groups g=dataGridView1.getSelectionModel().getSelectedItem();
            new Metodos().borrarDatosEvent_group(Integer.parseInt(g.getId_ev_group()), list_events.getItems().get(0).toString(), dataGridView1);
            
        }
        
    }
    
    
    
}
