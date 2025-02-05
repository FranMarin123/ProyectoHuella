package example.model.dao;

import example.connection.Connection;
import example.model.entity.Actividad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ActividadDAO {

    public static void saveActividad(Actividad actividad) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (session.save(actividad) != null) {
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static Actividad findActividadByNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        Actividad actividad = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Actividad> query = session.createQuery("FROM Actividad a WHERE a.nombre = :nombre", Actividad.class);
            query.setParameter("nombre", nombre);
            actividad = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return actividad;
    }

    public static boolean deleteActividad(Actividad actividadToDelete) {
        if (actividadToDelete == null) {
            return false;
        }
        Actividad actividadFound = findActividadByNombre(actividadToDelete.getNombre());
        if (actividadFound == null) {
            return false;
        }
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean eliminado = false;
        try {
            tx = session.beginTransaction();
            session.delete(actividadFound);
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