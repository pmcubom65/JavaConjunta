
// SQL=> select t_purchases.name_purchase_ev, t_purchases.nif_purchase_ev, t_purchases.email_purchase_ev, t_purchases.adress_purchase_ev, t_event.price 
//from t_purchases join t_event on t_purchases.id_event_purchase_ev = t_event.id_events;

package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import modelo.clases.ImprimirOrdenCompra;
import modelo.clases.Evento;

public class FXMLPurchasesController implements Initializable {
    
    ObservableList listaPagos = FXCollections.observableArrayList();
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtDireccion;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtNIF;
    @FXML
    public TextField txtCP;
    @FXML
    public ChoiceBox<String> cbMetodos;
    @FXML
    private Button btnConfirmar;
    
    private Evento evento;
    
    public void loadData(){
        listaPagos.removeAll(listaPagos);
        String a = "PayPal";
        String b = "Transferencia bancaria";
        String c = "Tarjeta de credito";
        String d = "Contrarrembolso";
        listaPagos.addAll(a, b, c, d);
        
        cbMetodos.getItems().addAll(listaPagos);
        cbMetodos.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void imprimirInforme(ActionEvent event) {
        try{
            
           new ImprimirOrdenCompra().abrirVentana(Integer.parseInt(evento.getid_events()),txtNombre.getText(), txtDireccion.getText(), txtNIF.getText(), txtCP.getText(), txtEmail.getText(), cbMetodos.getValue().toString());
            
        }
        catch(Exception ex){
        //JOptionPane.showMessageDialog(null,ex,"Error en el Informe.",JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        //JOptionPane.showMessageDialog(null, evento.getid_events(), "Le HOC", 0);
    }    
    
    
    public void verDetalles(Evento evento){
        
        
        this.evento=evento;
        
       
    }
    
    
}
