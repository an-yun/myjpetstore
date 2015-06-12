package myjpetstore.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by zuo on 2015/6/6.
 */
public class test {
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        ThreadLocal session = new ThreadLocal();
        Session s = (Session)session.get();
        if(s==null)
            s=sessionFactory.openSession();
        Log log = new Log("zuo","test");
        s.beginTransaction();
        s.save(log);
        s.getTransaction().commit();
    }
}
