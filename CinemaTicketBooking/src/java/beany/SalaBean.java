/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.exception.ConstraintViolationException;
import klasy.Sala;
import org.hibernate.Session;

/**
 *
 * @author mateusz
 */
@ManagedBean
@SessionScoped
public class SalaBean {

    /**
     * Creates a new instance of SalaBean
     */
    int id;
    int wysokosc;
    int szerokosc;
    int numer;
    String errMessage;
    public SalaBean() 
    {
        
    }
    public String addSala()
    {
        errMessage = "";
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sala sala = new Sala(wysokosc, szerokosc, numer);
        try
        {
            session.save(sala);
            session.getTransaction().commit();
            wysokosc = 0;
            szerokosc = 0;
            numer = 0;
        }
        catch(Exception ex)
        {
            errMessage = "Istnieje już sala o takim numerze!";
            session.getTransaction().rollback();
            return "admin_add_sala";
        }            
        
        return "admin_sale";
    }
    public String deleteSala(int salaId)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sala sala = (Sala)session.get(Sala.class, salaId);
        session.delete(sala);
        session.getTransaction().commit();
        return "admin_sale";
    }
    public String toModifySala(int id, int wysokosc,int szerokosc,int numer)
    {
        this.id = id;
        this.wysokosc = wysokosc;
        this.szerokosc = szerokosc;
        this.numer = numer;
       
        return "admin_modify_sale";
    }
    public String modifySala()
    {
        errMessage = "";
        System.out.println("jest");
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("jest");
        Sala sala = (Sala)session.get(Sala.class, this.id);
        
        sala.setWysokosc(wysokosc);
        sala.setSzerokosc(szerokosc);
        sala.setNumer(numer);
        try
        {
            session.update(sala);
            session.getTransaction().commit();
            wysokosc = 0;
            szerokosc = 0;
            numer = 0;
        }
        catch(ConstraintViolationException ex)
        {
            errMessage = "Istnieje już sala o takim numerze!";
            session.getTransaction().rollback();
            return "admin_modify_sale";
        }

        return "admin_sale";
    }
    public List<Sala> getSale()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Sala> sale = session.createQuery("from Sala").list();
        session.getTransaction().commit();
        return sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWysokosc() {
        return wysokosc;
    }

    public void setWysokosc(int wysokosc) {
        this.wysokosc = wysokosc;
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public void setSzerokosc(int szerokosc) {
        this.szerokosc = szerokosc;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
    
}
