package example.model.dao;

import example.connection.Connection;
import example.model.entity.Categoria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class CategoriaDAO {

    public static void saveCategoria(Categoria categoria) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
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
    }

    public static Categoria findCategoriaByNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        Categoria categoria = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
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
        return categoria;
    }

    public static boolean deleteCategoria(Categoria categoriaToDelete) {
        if (categoriaToDelete == null) {
            return false;
        }
        Categoria categoriaFound = findCategoriaByNombre(categoriaToDelete.getNombre());
        if (categoriaFound == null) {
            return false;
        }
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean eliminado = false;
        try {
            tx = session.beginTransaction();
            session.delete(categoriaFound);
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