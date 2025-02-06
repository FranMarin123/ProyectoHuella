package example.model.dao;

import example.connection.Connection;
import example.model.entity.Categoria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CategoriaDAO {

    public void saveCategoria(Categoria categoria) {
        Session session = Connection.getInstance().getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (session.save(categoria) != null) {
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
    }

    public Categoria findCategoriaById(Integer id) {
        Transaction tx = null;
        Categoria categoria = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query<Categoria> query = session.createQuery("FROM Categoria c WHERE c.id = :id", Categoria.class);
            query.setParameter("id", id);
            categoria = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
        return categoria;
    }

    public Categoria findCategoriaByNombre(String nombre) {
        Transaction tx = null;
        Categoria categoria = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query<Categoria> query = session.createQuery("FROM Categoria c WHERE c.nombre = :nombre", Categoria.class);
            query.setParameter("nombre", nombre);
            categoria = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
        return categoria;
    }
}