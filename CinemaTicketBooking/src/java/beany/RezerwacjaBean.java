/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author babel
 */
@ManagedBean
@RequestScoped
public class RezerwacjaBean {

    private Integer id;
    private int idSeansu;
    private int rzad;
    private int nrSiedzenia;
    private String nazwisko;
    private Boolean zatwierdzona;
    private SeansBean seansBean = new SeansBean();
     
    /**
     * Creates a new instance of RezerwacjaBean
     */
    public RezerwacjaBean() {
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getIdSeansu() {
        return this.idSeansu;
    }
    
    public void setIdSeansu(int idSeansu) {
        this.idSeansu = idSeansu;
    }
    public int getRzad() {
        return this.rzad;
    }
    
    public void setRzad(int rzad) {
        this.rzad = rzad;
    }
    public int getNrSiedzenia() {
        return this.nrSiedzenia;
    }
    
    public void setNrSiedzenia(int nrSiedzenia) {
        this.nrSiedzenia = nrSiedzenia;
    }
    public String getNazwisko() {
        return this.nazwisko;
    }
    
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public Boolean getZatwierdzona() {
        return this.zatwierdzona;
    }
    
    public void setZatwierdzona(Boolean zatwierdzona) {
        this.zatwierdzona = zatwierdzona;
    }
    
    public SeansBean getSeansBean() {
        return this.seansBean;
    }
    
    public void setSeansBean(SeansBean seansBean) {
        this.seansBean = seansBean;
    }
    
}
