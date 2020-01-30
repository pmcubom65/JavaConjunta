/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.clases;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tarde
 */
public class Evento {

    private final SimpleStringProperty id_events;
    private final SimpleStringProperty id_users_events;
    private final SimpleStringProperty date_events;
    private final SimpleStringProperty place_events;
    private final SimpleStringProperty name_events;
    private final SimpleStringProperty price;

    public Evento(String idevent, String iduser_event, String date, String place, String nameevent, String precio) {

        this.id_events = new SimpleStringProperty(idevent);
        this.id_users_events = new SimpleStringProperty(iduser_event);
        this.date_events = new SimpleStringProperty(date);
        this.place_events = new SimpleStringProperty(place);
        this.name_events = new SimpleStringProperty(nameevent);
        this.price = new SimpleStringProperty(precio);

    }

   /* public Evento(Evento e) {

        this.id_events = new SimpleStringProperty(e.getid_events());
        this.id_users_events = new SimpleStringProperty(e.getid_users_events());
        this.date_events = new SimpleStringProperty(e.getdate_events());
        this.place_events = new SimpleStringProperty(e.getplace_events());
        this.name_events = new SimpleStringProperty(e.getname_events());
        this.price = new SimpleStringProperty(e.getprice());

    }*/
    
    public Evento(String nameevent, String place, String date, String precio,String evento) {

        this.id_events = new SimpleStringProperty(evento);
        this.id_users_events = new SimpleStringProperty();
        this.date_events = new SimpleStringProperty(date);
        this.place_events = new SimpleStringProperty(place);
        this.name_events = new SimpleStringProperty(nameevent);
        this.price = new SimpleStringProperty(precio);

    }

    public String getid_events() {
        return id_events.get();
    }

    public String getid_users_events() {
        return id_users_events.get();
    }

    public String getdate_events() {
        return date_events.get();
    }

    public String getplace_events() {
        return place_events.get();
    }

    public String getname_events() {
        return name_events.get();
    }

    public String getprice() {
        return price.get();
    }
//*********************************************
//Devolver los datos de las propiedades

    public StringProperty id_eventsProperty() {
        return id_events;
    }

    public StringProperty id_users_eventsProperty() {
        return id_users_events;
    }

    public StringProperty date_eventsProperty() {
        return date_events;
    }

    public StringProperty place_eventsProperty() {
        return place_events;
    }

    public StringProperty name_eventsProperty() {
        return name_events;
    }

    public StringProperty priceProperty() {
        return price;
    }
}
