package example.model.dao;

import example.connection.Connection;
import example.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UsuarioDAO {

    public void saveUser(Usuario usuario) {
        Session session = Connection.getInstance().getSessionFactory().openSession();
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
        session.close();
    }

    public Usuario findUser(String email) {
        Transaction tx = null;
        Usuario usuario = null;
        Session session = Connection.getInstance().getSessionFactory().openSession();
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
        session.close();
        return usuario;
    }

    public boolean deleteUser(Usuario usuarioToDelete) {
        Transaction tx = null;
        boolean eliminado = false;
        Session session = Connection.getInstance().getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(usuarioToDelete);
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

