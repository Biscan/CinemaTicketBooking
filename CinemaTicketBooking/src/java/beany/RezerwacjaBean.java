/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import klasy.Rezerwacja;
import org.hibernate.Session;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import klasy.Film;


/**
 *
 * @author babel
 */
@ManagedBean
@SessionScoped
public class RezerwacjaBean {

    private Integer id;
    private int idSeansu;
    private int idFilmu;
    private int rzad;
    private int nrSiedzenia;
    private String nazwisko;
    private Boolean zatwierdzona;
    private Film film;
    //private SeansBean seansBean = new SeansBean();
     
    /**
     * Creates a new instance of RezerwacjaBean
     */
    
    
    
    public RezerwacjaBean() {
    }
    
    public List<Rezerwacja> getRezerwacje()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Rezerwacja> rezerwacje = session.createQuery("from Rezerwacja").list();
        session.getTransaction().commit();
        return rezerwacje;
    }
    
    public String addRezerwacja()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Rezerwacja rez = new Rezerwacja(idSeansu, rzad, nrSiedzenia, nazwisko, false);
        session.save(rez);
        
        session.getTransaction().commit();
        return "rezerwacje";
        //return "admin_rezerwacje";
    }
    public String deleteRezerwacja(int id)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Rezerwacja rez = (Rezerwacja)session.get(Rezerwacja.class,id);
        session.delete(rez);
        
        session.getTransaction().commit();
        return "admin_rezerwacje";
    }
    
    public String zatwierdz(int id)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
        Rezerwacja rez = (Rezerwacja)session.get(Rezerwacja.class,id);
        rez.setZatwierdzona(!rez.getZatwierdzona());
        session.update(rez);
        
        session.getTransaction().commit();
        return "admin_rezerwacje";
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
    public int getIdFilmu() {
        return this.idFilmu;
    }
    
    public void setIdFilmu(int idFilmu) {
        this.idFilmu = idFilmu;
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
    
    /*public SeansBean getSeansBean() {
        return this.seansBean;
    }
    
    public void setSeansBean(SeansBean seansBean) {
        this.seansBean = seansBean;
    }*/

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
    
}
