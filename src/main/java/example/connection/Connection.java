package example.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {
    private static Connection _instance;
    private SessionFactory sessionFactory;

    private Connection() {
        try {
            sessionFactory=new Configuration().configure().buildSessionFactory();
        }catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static Connection getInstance() {
        if (_instance == null) {
            _instance = new Connection();
        }
        return _instance;
    }

    public void close(){
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
