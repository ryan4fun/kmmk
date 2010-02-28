package com.gps.orm;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

 
public class HibernateUtil { 
 
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml"; 
 
    /**//** Holds a single instance of Session */ 
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>(); 
 
    private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>(); 
 
    /**//** The single instance of hibernate configuration */ 
    private static final Configuration cfg = new Configuration(); 
 
    /**//** The single instance of hibernate SessionFactory */ 
    private static SessionFactory sessionFactory; 

    /**//** 
     * Returns the ThreadLocal Session instance. Lazy initialize the 
     * <code>SessionFactory</code> if needed. 
     * 
     * @return Session 
     * @throws HibernateException 
     */ 
    public synchronized static Session getSession() throws HibernateException { 
        Session session = threadLocal.get(); 
 
        if (session == null) {
            if (sessionFactory == null) { 
                try { 
                    cfg.configure(CONFIG_FILE_LOCATION); 
                    sessionFactory = cfg.buildSessionFactory(); 
                } catch (Exception e) { 
                    System.err.println("%%%% Error Creating SessionFactory %%%%"); 
                    e.printStackTrace(); 
                }
            }
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session; 
    } 
 
    /**//** 
     * Close the single hibernate session instance. 
     * 
     * @throws HibernateException 
     */ 
    public synchronized static void closeSession() throws HibernateException { 
        Session session = threadLocal.get(); 
//        threadLocal.set(null);
        threadLocal.remove();
        if (session != null) { 
            session.close(); 
        } 
    } 
 
    /**//** 
     * Default constructor. 
     */ 
    private HibernateUtil() { 
    } 
 
    public static void beginTransaction() throws HibernateException {
		Transaction tx = threadTransaction.get(); 
        try { 
            if (tx == null) { 
                tx = getSession().beginTransaction(); 
                threadTransaction.set(tx); 
            } 
        } catch (HibernateException ex) { 
            throw new HibernateException(ex.toString()); 
        }

    } 
 
    public static void commitTransaction() throws HibernateException {
		Transaction tx = threadTransaction.get(); 
        try { 
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) 
                tx.commit();
            threadTransaction.set(null); 
        } catch (HibernateException ex) { 
            rollbackTransaction(); 
            throw new HibernateException(ex.toString()); 
        } 
    } 
 
    public static void rollbackTransaction() throws HibernateException {
		Transaction tx = threadTransaction.get(); 
        try { 
            threadTransaction.set(null); 
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) { 
                tx.rollback(); 
            } 
        } catch (HibernateException ex) { 
            throw new HibernateException(ex.toString()); 
        } finally { 
            closeSession(); 
        }    	
    }
} 
