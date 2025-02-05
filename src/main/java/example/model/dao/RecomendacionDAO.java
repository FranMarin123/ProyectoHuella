package example.model.dao;

import example.model.entity.Recomendacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class RecomendacionDAO {

    public static void saveRecomendacion(Recomendacion recomendacion) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(recomendacion);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static Recomendacion findRecomendacionById(Integer id) {
        if (id == null) {
            return null;
        }
        Recomendacion recomendacion = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            recomendacion = session.get(Recomendacion.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return recomendacion;
    }

    public static List<Recomendacion> findRecomendacionesByCategoria(Integer idCategoria) {
        if (idCategoria == null) {
            return null;
        }
        List<Recomendacion> recomendaciones = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Recomendacion> query = session.createQuery("FROM Recomendacion r WHERE r.idCategoria.id = :idCategoria", Recomendacion.class);
            query.setParameter("idCategoria", idCategoria);
            recomendaciones = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return recomendaciones;
    }

    public static boolean deleteRecomendacion(Integer id) {
        if (id == null) {
            return false;
        }
        Recomendacion recomendacion = findRecomendacionById(id);
        if (recomendacion == null) {
            return false;
        }
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean eliminado = false;
        try {
            tx = session.beginTransaction();
            session.delete(recomendacion);
            tx.commit();
            eliminado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return eliminado;
    }
}
