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
public class MT_users {
 
    private SimpleStringProperty id_user;
    private SimpleStringProperty nick_user;
    private SimpleStringProperty nif_user;
    
    public MT_users(){
    }
    
    public MT_users(String id_user, String nick_user, String nif_user){
        this.id_user = new SimpleStringProperty(id_user);
        this.nick_user = new SimpleStringProperty(nick_user);
        this.nif_user = new SimpleStringProperty(nif_user);
    }

    public void setId_user(SimpleStringProperty id_user) {
        this.id_user = id_user;
    }

    public void setNick_user(SimpleStringProperty nick_user) {
        this.nick_user = nick_user;
    }

    public void setNif_user(SimpleStringProperty nif_user) {
        this.nif_user = nif_user;
    }

    public String getId_user() {
        return id_user.get();
    }

    public String getNick_user() {
        return nick_user.get();
    }

    public String getNif_user() {
        return nif_user.get();
    }
    
}
