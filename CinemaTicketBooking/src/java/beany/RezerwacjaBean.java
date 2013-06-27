/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import klasy.Rezerwacja;
import org.hibernate.Session;
import javax.faces.bean.SessionScoped;
import klasy.Film;
import klasy.HibernateUtil;
import klasy.Seans;

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
    private int nrSiedzenia; //nr w rzedzie
    private int idSiedzenia; // unikalny numer na cala sale
    private String nazwisko;
    private Boolean zatwierdzona;
    private Film film;
    private Seans seans;
    //private SeansBean seansBean = new SeansBean();

    /**
     * Creates a new instance of RezerwacjaBean
     */
    public RezerwacjaBean() {
    }

    public List<Rezerwacja> getRezerwacje() {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Rezerwacja> rezerwacje = session.createQuery("from Rezerwacja").list();
        session.getTransaction().commit();
        return rezerwacje;
    }

    public String addRezerwacja() {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Rezerwacja rez = new Rezerwacja(idSeansu, rzad, nrSiedzenia, nazwisko, false);
        session.save(rez);

        session.getTransaction().commit();
        return "rezerwacje";
        //return "admin_rezerwacje";
    }

    public String deleteRezerwacja(int id) {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Rezerwacja rez = (Rezerwacja) session.get(Rezerwacja.class, id);
        session.delete(rez);

        session.getTransaction().commit();
        return "admin_rezerwacje";
    }

    public String zatwierdz(int id) {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Rezerwacja rez = (Rezerwacja) session.get(Rezerwacja.class, id);
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

    public Seans getSeans() {
        return seans;
    }

    public void setSeans(Seans seans) {
        this.seans = seans;
    }

    public Map<String, Integer> getFreeSeats() {
        Map<String, Integer> availableSeats = new TreeMap<String, Integer>();
        List<Rezerwacja> rezerwacje = HibernateUtil.executeHQLQuery("from Rezerwacja r where r.idSeansu=" + this.idSeansu);

        for (int i = 1; i <= this.seans.getSala().getWysokosc(); ++i) {
            for (int j = 1; j <= this.seans.getSala().getSzerokosc(); ++j) {
                boolean available = true;
                for (Rezerwacja r : rezerwacje) {
                    if (i==r.getRzad() && j==r.getNrSiedzenia()) {
                        available = false;
                        break;
                    }
                }
                
                if (available) {
                    List<Integer> poz = new ArrayList<Integer>();
                    poz.add(i);
                    poz.add(j);
                    int seat = this.pozycjaToId(poz);
                    availableSeats.put("Rzad " + i + ", Siedzenie " + j, seat);
                }
            }
        }

        return availableSeats;
    }

    public int getIdSiedzenia() {
        return idSiedzenia;
    }

    public void setIdSiedzenia(int idSiedzenia) {
        this.idSiedzenia = idSiedzenia;
    }

    public int pozycjaToId(List<Integer> p) {
        int rzad = p.get(0);
        int nr = p.get(1);
        int szer = this.seans.getSala().getSzerokosc();
        int wys = this.seans.getSala().getWysokosc();
        int id = (rzad - 1) * szer + nr;
        return id;
    }
    
    public List<Integer> idToPozycja(int id) {
        int szer = this.seans.getSala().getSzerokosc();
        int wys = this.seans.getSala().getWysokosc();
        int rzad = ((id - 1) / szer) + 1;
        int nrMiejsca = ((id - 1) % szer) + 1;
        List<Integer> pozycja = new ArrayList<Integer>();
        pozycja.add(rzad);
        pozycja.add(nrMiejsca);
        return pozycja;
    }

}
