/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author FranciscoHO
 */
public class EventoDeUsuario {
    
    
    private SimpleStringProperty name_events;
    private SimpleStringProperty date_events;
    private SimpleStringProperty place_events;
    
    
    public EventoDeUsuario(String name_events, String date_events, String place_events){
        
        this.name_events = new SimpleStringProperty(name_events);
        this.date_events = new SimpleStringProperty(date_events);
        this.place_events = new SimpleStringProperty(place_events);
    }

    public void setName_events(SimpleStringProperty name_events) {
        this.name_events = name_events;
    }
    
    public void setDate_events(SimpleStringProperty date_events) {
        this.date_events = date_events;
    }
    
    public void setPlace_events(SimpleStringProperty place_events) {
        this.place_events = place_events;
    }
    
    public String getName_events() {
        return name_events.get();
    }
    
    public String getDate_events() {
        return date_events.get();
    }

    public String getPlace_events() {
        return place_events.get();
    }
 
    
    
    
}
