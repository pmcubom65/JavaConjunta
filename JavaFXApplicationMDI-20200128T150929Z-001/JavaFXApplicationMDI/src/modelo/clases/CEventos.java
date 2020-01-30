
package modelo;


public class CEventos {
    
    String id_ev;
    String id_ev_fav;
    String id_user_ev_fav;
    String date_events;
    String name_events;
    String place_events;

    public CEventos(String id_ev, String id_ev_fav, String id_user_ev_fav, String date_events, String name_events, String place_events) {
        this.id_ev = id_ev;
        this.id_ev_fav = id_ev_fav;
        this.id_user_ev_fav = id_user_ev_fav;
        this.date_events = date_events;
        this.name_events = name_events;
        this.place_events = place_events;
    }

    public void setDate_events(String date_events) {
        this.date_events = date_events;
    }

    public void setName_events(String name_events) {
        this.name_events = name_events;
    }

    public void setPlace_events(String place_events) {
        this.place_events = place_events;
    }

    
    
    
    public String getDate_events() {
        return date_events;
    }

    public String getName_events() {
        return name_events;
    }

    public String getPlace_events() {
        return place_events;
    }
    
    

   

    public String getId_ev() {
        return id_ev;
    }

    public void setId_ev(String id_ev) {
        this.id_ev = id_ev;
    }

    public String getId_ev_fav() {
        return id_ev_fav;
    }

    public void setId_ev_fav(String id_ev_fav) {
        this.id_ev_fav = id_ev_fav;
    }

    public String getId_user_ev_fav() {
        return id_user_ev_fav;
    }

    public void setId_user_ev_fav(String id_user_ev_fav) {
        this.id_user_ev_fav = id_user_ev_fav;
    }
    
    
    


    
    
    
    
}
