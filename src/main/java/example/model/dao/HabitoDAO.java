package example.model.dao;

import example.connection.Connection;
import example.model.entity.Habito;
import example.model.entity.HabitoId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HabitoDAO {

    public static void saveHabito(Habito habito) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(habito);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public static Habito findHabitoById(HabitoId id) {
        if (id == null) {
            return null;
        }
        Habito habito = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            habito = session.get(Habito.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return habito;
    }

    public static boolean deleteHabito(HabitoId id) {
        if (id == null) {
            return false;
        }
        Habito habito = findHabitoById(id);
        if (habito == null) {
            return false;
        }
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean eliminado = false;
        try {
            tx = session.beginTransaction();
            session.delete(habito);
            tx.commit();
            eliminado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return eliminado;
    }
}