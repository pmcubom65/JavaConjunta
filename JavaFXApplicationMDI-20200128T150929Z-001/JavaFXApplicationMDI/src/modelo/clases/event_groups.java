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
 * @author javier
 */
public class event_groups {
    
    private final SimpleStringProperty id_ev_group;
    private final SimpleStringProperty name_event;
    private final SimpleStringProperty name_group;
    private final SimpleStringProperty hour_event_group;
    
      public event_groups(String id_ev_group, String name_event, String name_group, String hour_event_group) {

        this.id_ev_group = new SimpleStringProperty(id_ev_group);
        this.name_event = new SimpleStringProperty(name_event);
        this.name_group = new SimpleStringProperty(name_group);
        this.hour_event_group = new SimpleStringProperty(hour_event_group);
        
    }

    public String getId_ev_group() {
        return id_ev_group.get();
    }

    public String getName_event() {
        return name_event.get();
    }

    public String getName_group() {
        return name_group.get();
    }

    public String getHour_event_group() {
        return hour_event_group.get();
    }
   
     public StringProperty Id_ev_groupProperty() {
        return id_ev_group;
    }

    public StringProperty Name_eventProperty() {
        return name_event;
    }

    public StringProperty Name_groupProperty() {
        return name_group;
    }

     public StringProperty Hour_event_groupProperty() {
        return hour_event_group;
    }
   
}
