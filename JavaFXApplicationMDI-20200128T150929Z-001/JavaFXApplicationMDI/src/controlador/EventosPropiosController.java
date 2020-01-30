/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.clases.Evento2;

/**
 * FXML Controller class
 *
 * @author tarde
 */
public class EventosPropiosController implements Initializable {

    @FXML
    private Button btn_aniadir;
    @FXML
    private Button btn_actualizar;
    @FXML
    private DatePicker fechaEvento;
    @FXML
    private TextField nombreEvento;
    @FXML
    private TextField lugarEvento;
    @FXML
    private TextField precioEvento;
    @FXML
    private TableView dataGridView1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn<Evento2, String> codigoEv = new TableColumn<>("Nº de evento");

        codigoEv.setCellValueFactory(new PropertyValueFactory<Evento2, String>("idEvent"));

        TableColumn<Evento2, String> codigoUser = new TableColumn<>("Nº del organizador");
        codigoUser.setCellValueFactory(new PropertyValueFactory<Evento2, String>("idUser"));

        TableColumn<Evento2, String> date = new TableColumn<>("Fecha");
        date.setCellValueFactory(new PropertyValueFactory<Evento2, String>("date"));

        TableColumn<Evento2, String> place = new TableColumn<>("Lugar");
        place.setCellValueFactory(new PropertyValueFactory<Evento2, String>("place"));

        TableColumn<Evento2, String> name = new TableColumn<>("Nombre del evento");
        name.setCellValueFactory(new PropertyValueFactory<Evento2, String>("name"));

        TableColumn<Evento2, String> price = new TableColumn<>("Precio");
        price.setCellValueFactory(new PropertyValueFactory<Evento2, String>("price"));
        
        dataGridView1.getColumns().addAll(codigoEv, codigoUser,date,place,name,price);
        mostrarDatos();
    }
    private void mostrarDatos(){
        final ObservableList<Evento2> datos = FXCollections.observableArrayList();
        try {
            Conexion conectabd = new Conexion();
            Connection conectado = conectabd.obtenerConexion();
            ResultSet rs = conectabd.selectEventosPropios(conectado);
            dataGridView1.getItems().clear();
            while (rs.next()) {
                String codigoEv = rs.getString(1);
                String codigoUser = rs.getString(2);
                String fecha = rs.getString(3);
                String lugar = rs.getString(4);
                String nombre = rs.getString(5);
                String precio = rs.getString(6);
                dataGridView1.getItems().addAll(new Evento2(codigoEv,codigoUser,fecha,lugar,nombre,precio));
            }
            dataGridView1.setVisible(true);
            dataGridView1.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
            conectabd.desconectar(conectado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de consulta.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @FXML
    private void añadir(ActionEvent event) {
       try {
            boolean camposCompletos = devuelveEstadoCajas();
            if (camposCompletos == true)
            {
                Conexion conectabd = new Conexion();
                Connection conectado = conectabd.obtenerConexion();
                conectabd.insertEventoPropio(conectado,formatearFecha(),lugarEvento.getText(),nombreEvento.getText(),Float.parseFloat(precioEvento.getText()));
                conectabd.desconectar(conectado);
                JOptionPane.showMessageDialog(null, "Registro insertado correctamente.", "Inserción", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajas();
                mostrarDatos();
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Introducir decimales con punto, no coma.", "Error de inserción.", JOptionPane.ERROR_MESSAGE);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de inserción.", JOptionPane.ERROR_MESSAGE);
        }
    }
    @FXML
    private void actualizar(ActionEvent event) {
         Evento2 e=(Evento2) dataGridView1.getSelectionModel().getSelectedItem();
         String fechaEv;
         if(e==null){
             JOptionPane.showMessageDialog(null, "Debes seleccionar el registro a modificar en la tabla y escribir los campos que desees modificar.", "Información", JOptionPane.INFORMATION_MESSAGE);
         }
         else{
             String idEv=e.getIdEvent();
             if(fechaEvento.getValue()==null){
                 fechaEv=e.getDate();
             }
             else{fechaEv=formatearFecha();}
             String nombreEv=nombreEvento.getText();
             if(nombreEv.length()==0){
                 nombreEv=e.getName();
             }
             String lugarEv=lugarEvento.getText();
             if(lugarEv.length()==0){
                 lugarEv=e.getPlace();
             }
             String precioEv=precioEvento.getText();
             if(precioEv.length()==0){
                 precioEv=e.getPrice();
             }
                try{
                    Conexion conectabd = new Conexion();
                    Connection conectado = conectabd.obtenerConexion();
                    conectabd.updateEventoPropio(conectado,Integer.parseInt(idEv),fechaEv,lugarEv,nombreEv,Float.parseFloat(precioEv));
                    conectabd.desconectar(conectado);
                    JOptionPane.showMessageDialog(null,"Registro modificado");
                    limpiarCajas();
                    mostrarDatos();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex, "Error en la modificación del registro.", JOptionPane.ERROR_MESSAGE);
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Introducir decimales con punto, no coma.", "Error de formato numérico.", JOptionPane.ERROR_MESSAGE);
                }
                
         }
    }
    public String formatearFecha(){
        LocalDate ld=fechaEvento.getValue();
        String fechaFormateada = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return fechaFormateada;
    }
    public void limpiarCajas(){
        fechaEvento.setValue(null);
        lugarEvento.setText("");
        nombreEvento.setText("");
        precioEvento.setText("");
    }
    private boolean devuelveEstadoCajas() {
        boolean estado = true;
        String aux;
        aux = nombreEvento.getText().trim();
        if (aux.length() == 0) {
            JOptionPane.showMessageDialog(null, "Introduzca un valor en el nombre del evento.", "Operación", JOptionPane.INFORMATION_MESSAGE);
            estado = false;
            nombreEvento.requestFocus(); 
        }
        if (estado == true)
        {
            aux = lugarEvento.getText().trim();
            if (aux.length() == 0) {
                JOptionPane.showMessageDialog(null, "Introduzca un valor en el lugar del evento.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                estado = false;
                lugarEvento.requestFocus();
            }
        }
        if (estado == true)
        {
            aux = precioEvento.getText().trim();
            if (aux.length() == 0) {
                JOptionPane.showMessageDialog(null, "Introduzca un valor en el precio del evento.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                estado = false;
                precioEvento.requestFocus();
            }
        }
        if (estado == true)
        {
            if (fechaEvento.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Introduzca la fecha del evento.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                estado = false;
                fechaEvento.requestFocus();
            }
        }
        return estado;
    }
    @FXML
    private void pulsaTecla(KeyEvent event){
        if(event.getCode()==KeyCode.DELETE){
            Evento2 e=(Evento2) dataGridView1.getSelectionModel().getSelectedItem();
            try{
                Alert alert =new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar eliminación del evento");
                alert.setHeaderText("Eliminar evento");
                alert.setContentText("¿Seguro que deseas eliminar el evento seleccionado?");
                Optional<ButtonType> respuesta=alert.showAndWait();
                if(respuesta.get()==ButtonType.OK){
                    Conexion conectabd = new Conexion();
                    Connection conectado = conectabd.obtenerConexion();
                    conectabd.deleteEventoPropio(conectado,Integer.parseInt(e.getIdEvent()));
                    conectabd.desconectar(conectado);
                    JOptionPane.showMessageDialog(null,"Registro eliminado");
                }
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex, "Error en la eliminación del registro.", JOptionPane.ERROR_MESSAGE);
            }
            mostrarDatos();
            limpiarCajas();
            dataGridView1.getSelectionModel().clearSelection();
        }
    }
}
