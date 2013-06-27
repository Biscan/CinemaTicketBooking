/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beany;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import klasy.Film;
import klasy.HibernateUtil;
import klasy.Rezerwacja;
import klasy.Sala;
import klasy.Seans;
import org.hibernate.Session;

/**
 *
 * @author mateusz
 */
@ManagedBean
@SessionScoped
public class SeansBean {

    /**
     * Creates a new instance of SeansBean
     */
    private int id;
    private int idFilmu;
    private int idSali;
    private int idSeansu;
    private Date data;
    private String toDate;
    
    @ManagedProperty(value="#{rezerwacjaBean}")  
    private RezerwacjaBean rezerwacjaBean;
    
    public SeansBean() 
    {
        this.rezerwacjaBean = new RezerwacjaBean();
    }
    public String addSeans()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Seans seans = new Seans(idFilmu, idSali, data);
        try{
            session.save(seans);        
            session.getTransaction().commit();
        }
        catch(Exception ex)
        {
            session.getTransaction().rollback();
        }
        return "admin_seanse";
    }
    public String deleteSeans(int seansId)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Seans seans = (Seans)session.get(Seans.class, seansId);
        session.delete(seans);
        session.getTransaction().commit();
        return "admin_seanse";
    }
    public String toModifySeans(int id, int idFilmu, int idSali, Date data)
    {
        this.id = id;
        this.idFilmu = idFilmu;
        this.idSali = idSali;
        this.data = data;
       
        return "admin_modify_seans";
    }
    public String modifySeans()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Seans seans = (Seans)session.get(Seans.class, this.id);
        
        seans.setIdFilmu(idFilmu);
        seans.setIdSali(idSali);
        seans.setData(data);
        session.update(seans);
        session.getTransaction().commit();

        return "admin_seanse";
    }
    public List<Seans> getSeanse()
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Seans> seanse = session.createQuery("from Seans").list();
        session.getTransaction().commit();
        return seanse;
    }
    
    public String getFilmName(int filmId)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Film film = (Film)session.get(Film.class, filmId);
        session.getTransaction().commit();
        return film.getNazwa();
    }
    
    public int getSalaNumber(int salaId)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sala sala = (Sala)session.get(Sala.class, salaId);
        session.getTransaction().commit();
        return sala.getNumer();
    }
    public Map<String,Integer> getFilmy()
    {
        Map<String,Integer> filmy = new HashMap<String, Integer>();
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Film> filmyList = session.createQuery("from Film").list();
        session.getTransaction().commit();
        for(Film film:filmyList)
        {
            filmy.put(film.getNazwa(), film.getId());
        }
        return filmy;
    }
    public Map<Integer,Integer> getSale()
    {
        Map<Integer,Integer> sale = new HashMap<Integer, Integer>();
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Sala> saleList = session.createQuery("from Sala").list();
        session.getTransaction().commit();
        for(Sala sala:saleList)
        {
            sale.put(sala.getNumer(), sala.getId());
        }
        return sale;
    }
    public Map<String,Date> getDistinctDate()
    {
        Map<String,Date> dates = new HashMap<String, Date>();
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Seans> seanse = session.createQuery("from Seans").list();
        session.getTransaction().commit();
        //Calendar kal = new GregorianCalendar();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for(Seans s:seanse)
        {
            String dat = dateFormat.format(s.getData());
            //kal.setTime(s.getData());
            //String dat = ((Integer)kal.get(Calendar.DATE)).toString() + "." + ((Integer)kal.get(Calendar.MONTH) + 1) + "." + ((Integer)kal.get(Calendar.YEAR));
            dates.put(dat, s.getData());
        }
        
        return dates;
    }
    
    public Map<String,Date> getDistinctTimeBySeansId(int idFilmu)
    {
        Map<String,Date> dates = new HashMap<String, Date>();
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Seans> seanse;
        if (idFilmu == 0)
            seanse = session.createQuery("from Seans").list();
        else 
            seanse = session.createQuery("from Seans s where s.idFilmu =" + idFilmu).list();
        session.getTransaction().commit();
        //Calendar kal = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
        for(Seans s:seanse)
        {
            String dat = dateFormat.format(s.getData());
            //kal.setTime(s.getData());
            //String dat = ((Integer)kal.get(Calendar.HOUR)).toString() + ":" + ((Integer)kal.get(Calendar.MINUTE));
            dates.put(dat, s.getData());
        }
        
        return dates;
    }
    
    public List<Film> getSeanseByDate()
    {
            
        List<Seans> seanse;
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if(data == null)
        {
            List<Seans> seanse2 = session.createQuery("from Seans").list();
            data = seanse2.get(0).getData();
        }
       
        //Calendar dat = new GregorianCalendar();
        //dat.setTime(data);
        //Integer month = dat.get(Calendar.MONTH) + 1;
        //String selection = (((Integer)dat.get(Calendar.YEAR) + "." + month + "." + (Integer)dat.get(Calendar.DATE)).toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String selection = dateFormat.format(data);
        String query = "from Seans where DATE(data) = DATE('" + selection + "')";
        seanse = session.createQuery(query).list();
        query = "from Film where id in(";
        for(int i=0;i<seanse.size();++i)
        {
            String d = ((Integer)seanse.get(i).getIdFilmu()).toString(); 
            query += d;
            if(i != seanse.size() -1)
                query += ",";
        }
        query += ")";
        List<Film> filmy = session.createQuery(query).list();
        session.getTransaction().commit();
        return filmy;
    }
    
    public Seans getSeansByDate() {
        List<Seans> seanse;
        if (this.data == null)
            seanse = HibernateUtil.executeHQLQuery("from Seans s where s.idFilmu = " + this.idFilmu); 
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
            String t  = dateFormat.format(this.data);
            seanse = HibernateUtil.executeHQLQuery("from Seans s where s.idFilmu = " + this.idFilmu + " and s.data like '" + dateFormat.format(this.data) + "%'"); 
            //seanse = HibernateUtil.executeHQLQuery("from Seans s where s.idFilmu = " + this.idFilmu + " and s.data = '" + this.data + "'"); 
        }
        return seanse.get(0);
    }
    
    public String getTimeById(int id_filmu)
    {
        Session session = klasy.HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Seans> seanse;
        if (this.data != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            seanse = session.createQuery("from Seans s where s.idFilmu = " + id_filmu + " and DATE(s.data) = '" + dateFormat.format(this.data) + "'").list();
        } else {
            seanse = session.createQuery("from Seans where id_filmu = " + id_filmu).list();
        }
        String time = "";
        for(int i=0;i<seanse.size();++i)
        {
            //Calendar kal = new GregorianCalendar();
            //kal.setTime(seanse.get(i).getData());
            //int hour = kal.get(Calendar.HOUR_OF_DAY);
            //int minute = kal.get(Calendar.MINUTE);
            //int hour = seanse.get(i).getData().getHours() - 7;
            SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
            time += dateFormat.format(seanse.get(i).getData());
            if(i != seanse.size() - 1)
                time += ";    ";
        }
        session.getTransaction().commit();
        return time;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public int getIdFilmu() {
    return idFilmu;
    }
    public void setIdFilmu(int idFilmu) {
    this.idFilmu = idFilmu;
    }
    public int getIdSali() {
    return idSali;
    }
    public void setIdSali(int idSali) {
    this.idSali = idSali;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public RezerwacjaBean getRezerwacjaBean() {
        return this.rezerwacjaBean;
    }
    
    public void setRezerwacjaBean(RezerwacjaBean rezerwacja) {
        this.rezerwacjaBean = rezerwacja;
    }
    
    public String rezerwuj(Film f) {
        System.out.println(f.getId());
        this.idFilmu = f.getId();
        this.rezerwacjaBean.setIdFilmu(f.getId());
        this.rezerwacjaBean.setFilm(f);
        this.rezerwacjaBean.setZatwierdzona(Boolean.FALSE);
        return "rezerwacja";
    }
    
    public String wybierzSale() {
        Seans s = this.getSeansByDate();
        this.idSeansu = s.getId();
        this.rezerwacjaBean.setIdSeansu(this.idSeansu);
        this.rezerwacjaBean.setSeans(s);
        return "wybor_miejsca";
    }
    
    public String rezerwujMiejsce() {
        Integer idSiedzenia = this.rezerwacjaBean.getIdSiedzenia();
        List<Integer> poz = this.rezerwacjaBean.idToPozycja(idSiedzenia);
        this.rezerwacjaBean.setRzad(poz.get(0));
        this.rezerwacjaBean.setNrSiedzenia(poz.get(1));
        return "podsumowanie";
    }
    
    public String potwierdzRezerwacje() {
        RezerwacjaBean rb = this.rezerwacjaBean;
        Rezerwacja r = new Rezerwacja();
        r.setIdSeansu(rb.getIdSeansu());
        r.setNazwisko(rb.getNazwisko());
        r.setNrSiedzenia(rb.getNrSiedzenia());
        r.setRzad(rb.getRzad());
        r.setZatwierdzona(Boolean.FALSE);
        
        if (HibernateUtil.addEntity(r))
            return "potwierdzenie";
        else
            return "niepowodzenie";
    }

    public int getIdSeansu() {
        return idSeansu;
    }

    public void setIdSeansu(int idSeansu) {
        this.idSeansu = idSeansu;
    }
    
    
}
