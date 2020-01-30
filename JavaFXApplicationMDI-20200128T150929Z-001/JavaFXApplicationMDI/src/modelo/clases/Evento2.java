/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.clases;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Evento2 {
    private SimpleStringProperty idEvent;
    private SimpleStringProperty idUser;
    private SimpleStringProperty date;
    private SimpleStringProperty place;
    private SimpleStringProperty name;
    private SimpleStringProperty price;
    
    public Evento2(String event,String user,String date,String place,String name,String price){
        this.idEvent=new SimpleStringProperty(event);
        this.idUser=new SimpleStringProperty(user);
        this.date=new SimpleStringProperty(date);
        this.place=new SimpleStringProperty(place);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleStringProperty(price);
    }
    public String getIdEvent() {
        return idEvent.get();
    }
    public String getIdUser() {
        return idUser.get();
    }
    public String getDate() {
        return date.get();
    }
    public String getPlace() {
        return place.get();
    }
    public String getName() {
        return name.get();
    }
    public String getPrice() {
        return price.get();
    }
    
    public StringProperty propertyIdEvent() {
        return idEvent;
    }
    public StringProperty propertyIdUser() {
        return idUser;
    }
    public StringProperty propertyDate() {
        return date;
    }
    public StringProperty propertyPlace() {
        return place;
    }
    public StringProperty propertyName() {
        return name;
    }
    public StringProperty propertyPrice() {
        return price;
    }
}
