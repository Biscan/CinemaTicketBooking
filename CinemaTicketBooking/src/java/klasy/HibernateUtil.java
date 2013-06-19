/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package klasy;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author mateusz
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static List executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            session.getTransaction().commit();
            return resultList;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return null;
    }

    public static boolean addEntity(Object o) {
        Session session =
                HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return false;
    }
    
    public static boolean removeEntity(Object o) {
        Session session =
                HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Nie ma takiego obiektu");
        } finally {
            session.close();
        }
        
        return false;
    }
    
    public static boolean removeEntity(int id, Object classType) {
        Session session =
                HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Object o = session.get(classType.getClass(), id);
            session.delete(o);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Nie ma takiego obiektu");
        } finally {
            session.close();
        }
        
        return false;
    }
    
    public static int removeAll(String dbName) {
        Session session =
                HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = String.format("delete from %s", dbName);
            Query query = session.createQuery(hql);
            int ret = query.executeUpdate();
            session.getTransaction().commit();
            
            return ret;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return 0;
    }
    
    public static Object getEntity(int id, Object classType) {
        Session session =
                HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Object o = session.get(classType.getClass(), id);
            session.getTransaction().commit();
            
            return o;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (java.lang.IllegalArgumentException e) {
            System.out.println("Nie ma takiego obiektu");
        } finally {
            session.close();
        }
        
        return null;
    }
}
