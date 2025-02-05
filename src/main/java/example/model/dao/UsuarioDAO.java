package example.model.dao;

import example.connection.Connection;
import example.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UsuarioDAO {

    public static void saveUser(Usuario usuario) {
        //Session session = Connection.getInstance().getSessionFactory().openSession();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (session.save(usuario) != null) {
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public static Usuario findUser(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        Transaction tx = null;
        Usuario usuario = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            tx = session.beginTransaction();
            Query<Usuario> query = session.createQuery("FROM Usuario u WHERE u.email = :email", Usuario.class);
            query.setParameter("email", email);
            usuario = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        return usuario;
    }

    public static boolean deleteUser(Usuario usuarioToDelete) {
        Transaction tx = null;
        boolean eliminado = false;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Usuario usuarioFinded=findUser(usuarioToDelete.getEmail());
        if (usuarioFinded != null) {
            return false;
        }
        try {
            tx = session.beginTransaction();
            session.delete(usuarioFinded);
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

