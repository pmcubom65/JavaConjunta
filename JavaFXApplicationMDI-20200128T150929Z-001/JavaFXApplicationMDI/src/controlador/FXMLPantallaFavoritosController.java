/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import br.com.supremeforever.suprememdiwindow.MDIWindow;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxapplicationmdi.JavaFXApplicationMDI;
import javax.swing.JOptionPane;
import modelo.CEventos;
import modelo.Conexion;
import modelo.GenerarInformeFavoritos;

/**
 * FXML Controller class
 *
 * @author tarde
 */
public class FXMLPantallaFavoritosController implements Initializable {

    @FXML
    private Label minombre;

    @FXML
    private TableView<CEventos> tablafavoritos;

    @FXML
    private Button favoritos;

    @FXML
    private Label error;

    @FXML
    private Button atras;

    @FXML
    void crearInforme(ActionEvent event) {

        GenerarInformeFavoritos gif = new GenerarInformeFavoritos();
        gif.mostrarInforme();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (variableGlobal.id_active == 0) {
            favoritos.setDisable(true);
            tablafavoritos.setDisable(true);
            minombre.setText("");
            error.setVisible(true);
            //      error.setText("Tiene que loguearse para mostrar este contenido");
        } else {
            error.setVisible(false);
            favoritos.setDisable(false);
            tablafavoritos.setDisable(false);

            TableColumn<CEventos, String> id_ev = new TableColumn<>("id_ev");
            id_ev.setCellValueFactory(new PropertyValueFactory<CEventos, String>("id_ev"));
            TableColumn<CEventos, String> id_ev_fav = new TableColumn<>("id_ev_fav");
            id_ev_fav.setCellValueFactory(new PropertyValueFactory<CEventos, String>("id_ev_fav"));

            TableColumn<CEventos, String> id_user_ev_fav = new TableColumn<>("id_user_ev_fav");
            id_user_ev_fav.setCellValueFactory(new PropertyValueFactory<CEventos, String>("id_user_ev_fav"));

            TableColumn<CEventos, String> date_events = new TableColumn<>("date_events");
            date_events.setCellValueFactory(new PropertyValueFactory<CEventos, String>("date_events"));

            TableColumn<CEventos, String> name_events = new TableColumn<>("name_events");
            name_events.setCellValueFactory(new PropertyValueFactory<CEventos, String>("name_events"));

            TableColumn<CEventos, String> place_events = new TableColumn<>("place_events");
            place_events.setCellValueFactory(new PropertyValueFactory<CEventos, String>("place_events"));

            tablafavoritos.getColumns().addAll(id_ev, id_ev_fav, id_user_ev_fav, date_events, name_events, place_events);

            cargarTabla();
        }
    }

    @FXML
    private void seleccionarDatos(ActionEvent event) {

    }

    public void cargarTabla() {
        //******************************************************************************   
        //Datos por SELECT de SQL        
        final ObservableList<CEventos> datos = FXCollections.observableArrayList();
        try {
            Conexion conectabd = new Conexion();

            Connection conectado = conectabd.obtenerConexion();
            //   String sql = "SELECT distinct tef.id_ev, tef.id_ev_fav, tef.id_user_ev_fav, te.date_events, te.name_events, te.place_events FROM t_events_favorites tef, t_event te where tef.id_ev=te.id_events and tef.id_user_ev_fav="+variableGlobal.id_active;

            String sql = "SELECT distinct tef.id_ev, tef.id_ev_fav, tef.id_user_ev_fav, te.date_events, te.name_events, te.place_events,\n"
                    + "CONCAT(tu.nombre_user,\" \", tu.apellidos_user) AS Nombre\n"
                    + "FROM t_events_favorites tef, t_event te,\n"
                    + "t_users tu where tef.id_ev=te.id_events and te.id_users_events=tu.id_user  and tef.id_user_ev_fav=" + variableGlobal.id_active;

            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView        
            tablafavoritos.getItems().clear();
            while (rs.next()) {
                String id_ev = rs.getString(1);
                String id_ev_fav = rs.getString(2);
                String id_user_ev_fav = rs.getString(3);
                String date_events = rs.getString(4);
                String name_events = rs.getString(5);
                String place_events = rs.getString(6);
                String nombre = rs.getString(7);
                minombre.setText(nombre);
                tablafavoritos.getItems().addAll(new CEventos(id_ev, id_ev_fav, id_user_ev_fav, date_events, name_events, place_events));
            }                     //Visualizar la TableView.     
            tablafavoritos.setVisible(true);
//Redimensiona cada columna al tamaño de sus datos.  
            tablafavoritos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void volverAtras(ActionEvent event) {

        JavaFXApplicationMDI j = new JavaFXApplicationMDI();
        //    j.formularioFXML("/vista/FXMLDocument002MDI.fxml","PROVEEDORES");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vista/FXMLDocument002MDI.fxml"));
        Parent nuevoparent = null;
        try {
            nuevoparent = loader.load();
        } catch (Exception e) {

        }

        Scene nuevaescena = new Scene(nuevoparent, 800, 800);

        //        VentanaDeDetallesController micontrolador = loader.getController();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(nuevaescena);

        window.show();

        
         
    }

}
