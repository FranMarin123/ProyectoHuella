package example.model.dao;

import example.connection.Connection;
import example.model.entity.Recomendacion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class RecomendacionDAO {

    public void saveRecomendacion(Recomendacion recomendacion) {
        Session session = Connection.getInstance().getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (session.save(recomendacion) != null) {
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
    }

    public Recomendacion findRecomendacionById(Integer id) {
        Transaction tx = null;
        Recomendacion recomendacion = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query<Recomendacion> query = session.createQuery("FROM Recomendacion r WHERE r.id = :id", Recomendacion.class);
            query.setParameter("id", id);
            recomendacion = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
        return recomendacion;
    }

    public boolean deleteRecomendacion(Recomendacion recomendacionToDelete) {
        Transaction tx = null;
        boolean eliminado = false;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(recomendacionToDelete);
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