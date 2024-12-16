package org.example.services;

import org.example.models.Comentario;
import org.example.models.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene todos los métodos necesarios para llevar a cabo las historias de usuario
 */
public class DataService {
    private static EntityManagerFactory emf;

    /**
     * Constructor de la clase
     * @param emf
     */
    public DataService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Método que regustra un nuevo usuario en la plataforma
     * @param usuario
     */
    public void registrarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (!usuarioExiste(usuario.getCorreo(), em)) {
                em.persist(usuario);
                em.getTransaction().commit();
            } else {
                System.out.println("Usuario con correo " + usuario.getCorreo() + " ya existe.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    private boolean usuarioExiste(String correo, EntityManager em) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class);
        query.setParameter("correo", correo);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    /**
     * Método que lista los comentarios de un usuario específico
     * @param correo
     * @return
     */
    public List<Comentario> listaComentariosPorUsuario(String correo){
        List<Comentario> salida = new ArrayList<Comentario>();
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Comentario> q = em.createQuery("select c from Comentario c join c.usuario u where u.correo=:correo", Comentario.class);
            q.setParameter("correo",correo);
            salida = q.getResultList();
            em.close();
            return salida;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Método qur añade un comentario a la plataforma
     * @param usuario
     * @param contenido
     * @param valoracion
     */
    public void añadirComentario(Usuario usuario, String contenido, int valoracion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        usuario = em.merge(usuario); // Ensure the entity is managed by the current EntityManager
        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setValoración(valoracion);
        comentario.setUsuario(usuario);
        em.persist(comentario);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Método que lista los correos de los usuarios que han realizado comentarios con un 10 en su valoración
     * @return
     */
    public List<String> usuariosPorValoracion() {
        List<String> salida = new ArrayList<String>();
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<String> q = em.createQuery("select u.correo from Usuario u join u.comentarios c where c.valoración=10", String.class);
            salida = q.getResultList();
            em.close();
            return salida;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
