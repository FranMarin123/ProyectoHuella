package example.model.dao;

import example.connection.Connection;
import example.model.entity.Actividad;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ActividadDAO {

    public void saveActividad(Actividad actividad) {
        Session session = Connection.getInstance().getSessionFactory().openSession();
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
        session.close();
    }

    public Actividad findActividadById(Integer id) {
        Transaction tx = null;
        Actividad actividad = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query<Actividad> query = session.createQuery("FROM Actividad a WHERE a.id = :id", Actividad.class);
            query.setParameter("id", id);
            actividad = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
        return actividad;
    }

    public Actividad findActividadByNombre(String nombre) {
        Transaction tx = null;
        Actividad actividad = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();
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
        session.close();
        return actividad;
    }

    public boolean deleteActividad(Actividad actividadToDelete) {
        Transaction tx = null;
        boolean eliminado = false;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(actividadToDelete);
            tx.commit();
            eliminado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        session.close();
        return eliminado;
    }
}