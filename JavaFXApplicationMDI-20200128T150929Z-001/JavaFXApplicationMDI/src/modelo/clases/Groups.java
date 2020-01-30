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
public class Groups {


/**
 *
 * @author tarde
 */
    
//Clase usada para definir propiedades y con ella poder acceder a los datos contenidos.
//La propiedad es de tipo String.
//Modelo de datos para los Alumnos que se visualizarán en el TableView.

    private final SimpleStringProperty id_group;
    private final SimpleStringProperty name_group;
    private final SimpleStringProperty logo_group;
    private final SimpleStringProperty obs_group;
    

    public Groups(String id_group, String name_group, String logo_group, String obs_group) {
//*****************************************************
//SETTERS, usados para acceder a los atributos.
        this.id_group = new SimpleStringProperty(id_group);
        this.name_group = new SimpleStringProperty(name_group);
        this.logo_group = new SimpleStringProperty(logo_group);
        this.obs_group = new SimpleStringProperty(obs_group);
    }
//*****************************************************
//GETTERS, usados para acceder a los atributos.

    public String getid_group() {
        return id_group.get();
    }

    public String getname_group() {
        return name_group.get();
    }

    public String getlogo_group() {
        return logo_group.get();
    }

    public String getobs_group() {
        return obs_group.get();
    }

   
//*********************************************
//Devolver los datos de las propiedades

    public StringProperty id_groupProperty() {
        return id_group;
    }

    public StringProperty name_groupProperty() {
        return name_group;
    }

    public StringProperty logo_groupProperty() {
        return logo_group;
    }

    public StringProperty obs_groupProperty() {
        return obs_group;
    }
    
}
 

