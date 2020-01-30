package controlador;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Activity;
import com.google.api.services.youtube.model.ActivityContentDetails;
import com.google.api.services.youtube.model.ActivitySnippet;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ResourceId;

import com.google.api.services.youtube.*;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.text.View;
import modelo.MT_videos;
import modelo.Conexion;

public class VideosController implements Initializable {
    @FXML
    private Pane contenedor;
    @FXML
    private Label label;
    @FXML
    private Button btnCargar;
    @FXML
    private MenuButton listaGrupos;
    @FXML
    private ListView listaVideos;
    @FXML
    private WebView webVideo;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarGrupos();
        //cargarVideos();
        //contenedor.getChildren();
    }
    public void cargarGrupos(){
        ArrayList<String> rs=MT_videos.cargarGrupos();
            for(String res: rs){
                MenuItem elemento=new MenuItem();
                elemento.setText(res);
                elemento.setOnAction(a->{
                    listaGrupos.setText(elemento.getText());
     });
                listaGrupos.getItems().add(elemento);
            }        
    }
    @FXML
    public void cargarVideos(){
        Properties properties = new Properties();
        //HAY QUE MIRAR LAS PROPIEDADES PARA ESTABLECER EL ID DEL USUARIO CON SU NOMBRE Y CONTRASEÑA
        YouTube youtube;
        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            })
            .setApplicationName("youtube-cmdline-search-sample")
            .build();
    try{
        properties.setProperty("youtube.apikey", "AIzaSyBIET98axxuwac0s6m3sQ6SIrr2FF8npEA");
        String apiKey = properties.getProperty("youtube.apikey");
        YouTube.Search.List query = youtube.search().list("id,snippet");
      query.setMaxResults((long)20);
      query.setOrder("date");
      query.setKey(apiKey);
      query.setQ(listaGrupos.getText()+" group live");//aqui va el termino que quieres buscar
      query.setType("video");
      query.setFields("items(id/videoId,snippet/title,snippet/thumbnails/default/url,snippet/publishedAt)");
      query.setMaxResults(new Long(5));
      SearchListResponse searchResponse = query.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {
                List<Pane> list = new ArrayList<Pane>();
                
                for(SearchResult s: searchResultList){
                    /*System.out.println(s.getSnippet());
                    System.out.println(s.getId());*/
                    Pane c=new Pane();
                    //c.setMinSize((int)listaVideos.getWidth(), 500);
                    //s.getSnippet().getPublishedAt().toString();
                    ZonedDateTime dateTime = ZonedDateTime.parse(s.getSnippet().getPublishedAt().toString(), DateTimeFormatter.ISO_DATE_TIME);
                    
                    ImageView imagen=new ImageView(s.getSnippet().getThumbnails().getDefault().getUrl());
                    imagen.setX(0);
                    imagen.setY(0);
                    Label etTitulo=new Label(s.getSnippet().getTitle());
                    Label etFecha=new Label("Fecha de publicación: "+dateTime.getDayOfMonth()+"/"+dateTime.getMonthValue()+"/"+dateTime.getYear());
                    etTitulo.setPadding(new Insets(imagen.getBoundsInLocal().getHeight()/2.3,0,0,imagen.getBoundsInLocal().getWidth()+10));
                    etFecha.setPadding(new Insets(imagen.getBoundsInLocal().getHeight()/1.6,0,0,imagen.getBoundsInLocal().getWidth()+10));
                    //etTitulo.setMinWidth(imagen.getBoundsInLocal().getWidth());
                    //etTitulo.setWrapText(true);
                    etTitulo.setAlignment(Pos.BASELINE_RIGHT);
                    etTitulo.setTextAlignment(TextAlignment.RIGHT);
                    c.getChildren().add(etTitulo);
                    c.getChildren().add(etFecha);
                    c.getChildren().add(imagen);
                    c.setOnMouseClicked((e) -> {
			cargarVideo(s.getId().getVideoId().toString());
		});
                    list.add(c);
                }
                ObservableList<Pane> items = FXCollections.observableList(list);
                listaVideos.setItems(items);
                
            }
    }   catch (IOException ex) {
            Logger.getLogger(VideosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void cargarVideo(String id){
        webVideo.setVisible(true);
        WebEngine motor=webVideo.getEngine();
        motor.load("https://www.youtube.com/embed/"+id);
    }
}
