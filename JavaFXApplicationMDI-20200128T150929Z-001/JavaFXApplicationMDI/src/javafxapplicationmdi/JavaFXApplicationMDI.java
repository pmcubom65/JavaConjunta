package javafxapplicationmdi;

import br.com.supremeforever.suprememdiwindow.MDICanvas;
import br.com.supremeforever.suprememdiwindow.MDIWindow;
import controlador.FXMLPurchasesController;
import controlador.variableGlobal;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import modelo.Metodos;
import modelo.clases.Evento;




public class JavaFXApplicationMDI extends Application {
    public static FXMLPurchasesController vista;
    public static Evento evento;
    
    int i=1;
    String url="http://laweb.com/imagen1.jpg";
    public static MDIWindow mDIWindow;
    AnchorPane root;
    MDICanvas canvas;
    public Menu menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu90;
            
    @Override
    public void start(Stage stage) throws Exception {
        canvas=new MDICanvas();
        canvas.setPrefSize(1000, 650);
          
        MenuBar menuBar = new MenuBar();
        
        // Login/Register
        menu1 = new Menu("Login");
        MenuItem menuItem10 = new MenuItem("Registrarse...");
        menu1.getItems().add(menuItem10);
        MenuItem menuItem11 = new MenuItem("Iniciar sesión...");
        menu1.getItems().add(menuItem11);
        menuBar.getMenus().add(menu1);

        
        //Eventos
        
        menu2 = new Menu("Eventos");
        MenuItem menuItem20 = new MenuItem("Propios");
        menu2.getItems().add(menuItem20);
        MenuItem menuItem21 = new MenuItem("Generales");
        menu2.getItems().add(menuItem21);
        menuBar.getMenus().add(menu2);
        menu2.setDisable(true);
        
        //Grupos
        menu3 = new Menu("Grupos");
        MenuItem menuItem30 = new MenuItem("Ver datos...");
        menu3.getItems().add(menuItem30);
        menuBar.getMenus().add(menu3);
        menu3.setDisable(true);
        
        //Eventos grupos
        menu4 = new Menu("eventosGrupos");
        MenuItem menuItem40 = new MenuItem("Ver datos...");
        menu4.getItems().add(menuItem40);
        menuBar.getMenus().add(menu4);
        menu4.setDisable(true);
        
        menu5 = new Menu("Lista de videos");
        MenuItem menuItem50 = new MenuItem("Ver datos...");
        menu5.getItems().add(menuItem50);
        menuBar.getMenus().add(menu5);
        menu5.setDisable(true);     
       
        menu6 = new Menu("Usuarios");
        MenuItem menuItem60 = new MenuItem("Buscar Usuario");
        menu6.getItems().add(menuItem60);
        menuBar.getMenus().add(menu6);
        menu6.setDisable(true);
        
        menu7 = new Menu("Favoritos");
        MenuItem menuItem70 = new MenuItem("Favoritos");
        menu7.getItems().add(menuItem70);
        menuBar.getMenus().add(menu7);
        menu7.setDisable(true);
        
        menu90 = new Menu("Cerrar Sesión");
        MenuItem menuItem90 = new MenuItem("Desconectar...");
        menu90.getItems().add(menuItem90);
        menuBar.getMenus().add(menu90);
        menu90.setDisable(true);
        

        
        //EVENTOS EN LAS OPCIONES DE MENÚ.
        //Opción 1 de Login
         menuItem11.setOnAction(e -> { formularioFXML("FXMLDocumentLoginMDI.fxml","INICIAR_SESIÓN"); });
        
        //Opcion 2 de Register
         menuItem10.setOnAction(e -> { formularioFXML("FXMLDocumentRegisterMDI.fxml","REGISTRARSE"); });
    
        //Opción 1 de Proveedores
        
         menuItem20.setOnAction(e ->{formularioFXML("EventosPropios.fxml","EVENTOS PROPIOS");});
         menuItem21.setOnAction(e ->{formularioFXML("Todoseventos.fxml","EVENTOS");});
       
         //Opción 1 de Grupos
         menuItem30.setOnAction(e -> { formularioFXML("FXMLDocumentGroupsMDI.fxml","GRUPOS"); });
         
         menuItem40.setOnAction(e -> { formularioFXML("FXMLDocumentEvents_groupMDI.fxml","eventosGrupos"); });
         
         menuItem50.setOnAction(e -> { formularioFXML("Videos.fxml","Lista de videos"); });
        
         menuItem60.setOnAction(e -> { formularioFXML("Usuarios.fxml","EVENTOS DE USUARIOS"); });
         
         menuItem70.setOnAction(e -> { formularioFXML("FXMLPantallaFavoritos.fxml","FAVORITOS"); });

         menu90.setOnAction(e -> desconectar());

        //Idem para el resto de opciones de los menús.
        
        
        
        ////***********************************////////
        canvas.centerMdiWindow(mDIWindow);
        VBox box=new VBox(menuBar, canvas);
        AnchorPane.setBottomAnchor(box, 0d);
        AnchorPane.setTopAnchor(box, 0d);
        AnchorPane.setLeftAnchor(box, 0d);
        AnchorPane.setRightAnchor(box, 0d);

        AnchorPane pane=new AnchorPane(box);
        Scene scene = new Scene(pane);
                
        stage.setScene(scene);
        stage.show();
    }
        
    public void formularioFXML(String formulariofxml, String nombre)
    {
        try {
            new variableGlobal(1);
            new Metodos().cargarLoader(this);
            root = FXMLLoader.load(getClass().getResource("/vista/"+formulariofxml));  
            mDIWindow=new MDIWindow("myId"+i,new ImageView(url),nombre,root);
            canvas.addMDIWindow(mDIWindow);
            i++;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex,"Error.",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void desconectar(){
        variableGlobal.id_active=0;
        resetearMenusAdmin();  
        cerrarVentanas();
    }
    
    public void cerrarVentanas(){
        try{
        for(int i=1;i<999;i++){
           // if(mDIWindow.getId().equals("myId"+i))
        canvas.removeMDIWindow("myId"+i);
        
        }}catch(Exception e){e.printStackTrace();}
    }
    
    public void resetearMenusAdmin(){
        this.menu1.setDisable(false);
        this.menu2.setDisable(true);
        this.menu3.setDisable(true);
        this.menu4.setDisable(true);
        this.menu5.setDisable(true);
        this.menu6.setDisable(true);
        this.menu7.setDisable(true);
        this.menu90.setDisable(true);
    }
    
    public void activarMenusAdmin(){
        this.menu1.setDisable(true);
        this.menu2.setDisable(false);
        this.menu3.setDisable(false);
        this.menu4.setDisable(false);
        this.menu5.setDisable(false);
        this.menu6.setDisable(false);
        this.menu7.setDisable(false);
        this.menu90.setDisable(false);
    }
    
    public void activarMenusUser(){
        this.menu1.setDisable(true);
        this.menu2.setDisable(false);
        this.menu5.setDisable(false);
        this.menu7.setDisable(false);
        this.menu90.setDisable(false);
        
       // this.menu3.setDisable(false);
       // this.menu4.setDisable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void iniciar(){
         try {
             
            FXMLLoader loader= new FXMLLoader(controlador.FXMLPurchasesController.class.getResource("/vista/FXMLPurchases.fxml"));
            Parent root = loader.load();
            vista=loader.getController();
            vista.verDetalles(evento);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    

public static void verDetalles(Evento evento){
        
        
        evento=evento;
        
       
    }
    
}
