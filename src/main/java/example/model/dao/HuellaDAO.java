package example.model.dao;

import example.connection.Connection;
import example.model.entity.Huella;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class HuellaDAO {

    public void saveHuella(Huella huella) {
        Session session = Connection.getInstance().getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (session.save(huella) != null) {
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
    }

    public Huella findHuellaById(Integer id) {
        Transaction tx = null;
        Huella huella = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query<Huella> query = session.createQuery("FROM Huella h WHERE h.id = :id", Huella.class);
            query.setParameter("id", id);
            huella = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
        return huella;
    }

    public boolean deleteHuella(Huella huellaToDelete) {
        Transaction tx = null;
        boolean eliminado = false;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(huellaToDelete);
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

    public List<Huella> findHuellasByUsuarioAndFecha(int userId, LocalDate fechaInicio, LocalDate fechaFin) {
        Transaction tx = null;
        List<Huella> huellas = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();

            Query<Huella> query = session.createQuery(
                    "FROM Huella h WHERE h.idUsuario.id = :userId AND h.fecha BETWEEN :fechaInicio AND :fechaFin",
                    Huella.class
            );
            query.setParameter("userId", userId);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);

            huellas = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return huellas;
    }

    public List<Huella> findHuellasByUsuario(int userId) {
        Transaction tx = null;
        List<Huella> huellas = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();

            Query<Huella> query = session.createQuery(
                    "FROM Huella h WHERE h.idUsuario.id = :userId",
                    Huella.class
            );
            query.setParameter("userId", userId);

            huellas = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return huellas;
    }
}
