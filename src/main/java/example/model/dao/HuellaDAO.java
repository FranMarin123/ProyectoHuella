package example.model.dao;

import example.connection.Connection;
import example.model.entity.Huella;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HuellaDAO {

    public static void saveHuella(Huella huella) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(huella);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public static Huella findHuellaById(Integer id) {
        if (id == null) {
            return null;
        }
        Huella huella = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            huella = session.get(Huella.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return huella;
    }

    public static List<Huella> findHuellasByUsuario(Integer idUsuario) {
        if (idUsuario == null) {
            return null;
        }
        List<Huella> huellas = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Huella> query = session.createQuery("FROM Huella h WHERE h.idUsuario.id = :idUsuario", Huella.class);
            query.setParameter("idUsuario", idUsuario);
            huellas = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return huellas;
    }

    public static boolean deleteHuella(Integer id) {
        if (id == null) {
            return false;
        }
        Huella huella = findHuellaById(id);
        if (huella == null) {
            return false;
        }
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean eliminado = false;
        try {
            tx = session.beginTransaction();
            session.delete(huella);
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