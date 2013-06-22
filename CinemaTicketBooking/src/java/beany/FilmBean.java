/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import klasy.Film;
import org.hibernate.Session;

/**
 *
 * @author mateusz
 */
@ManagedBean
@RequestScoped
public class FilmBean {

    /**
     * Creates a new instance of addFilm
     */
    
    private String nazwa;
    private int wiek;
    private int czas;
    private int id;
    String opis; 
   boolean modifying = false;
    
    public FilmBean() 
    {
        
    }
    
    public String addFilm()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Film film = new Film(nazwa, wiek, czas, opis);
        session.save(film);        
        session.getTransaction().commit();
        return "admin_filmy";
    }
    public String deleteFilm(int filmId)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Film film = (Film)session.get(Film.class, filmId);
        session.delete(film);
        session.getTransaction().commit();
        return "admin_filmy";
    }
    public String toModifyFilm(int id, String naz,int wie,int cza, String op)
    {
        this.id = id;
        nazwa = naz;
        wiek = wie;
        czas = cza;
        modifying = true;
        opis = op;
        return "admin_modify_film";
    }
    public String modifyFilm()
    {
        System.out.println("jest");
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("jest");
        Film film = (Film)session.get(Film.class, this.id);
        
        film.setCzasTrwania(czas);
        film.setKategoriaWiekowa(wiek);
        film.setNazwa(nazwa);
        film.setOpis(opis);
        session.saveOrUpdate(film);
        session.getTransaction().commit();
        modifying = false;
        return "admin_filmy";
    }
    public List<Film> getFilmy()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Film> filmy = session.createQuery("from Film").list();
        session.getTransaction().commit();
        return filmy;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public int getCzas() {
        return czas;
    }

    public void setCzas(int czas) {
        this.czas = czas;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isModifying() {
        return modifying;
    }

    public void setModifying(boolean modifying) {
        this.modifying = modifying;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
