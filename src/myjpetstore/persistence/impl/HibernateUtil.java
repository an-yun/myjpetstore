package myjpetstore.persistence.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by zuo on 2015/6/13.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
    public static final ThreadLocal threadLocal = new ThreadLocal();
    public static Session getSession()
    {
        Session session = (Session)threadLocal.get();
        if (session==null)
        {
            session= sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }
}
