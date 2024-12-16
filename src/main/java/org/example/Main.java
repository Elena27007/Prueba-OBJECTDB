package org.example;

import org.example.models.Comentario;
import org.example.models.Usuario;
import org.example.services.DataService;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal donde se prueban las historias de usuario
 */
public class Main {
    public static void main(String[] args) {
        DataService s = new DataService(new ObjectDBUtil().getEmf());

        Usuario u1 = new Usuario();
        u1.setNombre("Elena");
        u1.setCorreo("ehermos@gmail.com");
        List<Comentario> comentarios1 = null;
        u1.setComentarios(comentarios1);

        Usuario u2 = new Usuario();
        u2.setNombre("David");
        u2.setCorreo("david@gmail.com");
        List<Comentario> comentarios2 = null;
        u2.setComentarios(comentarios2);

        Comentario c1 = new Comentario();
        c1.setContenido("Muy buena peli");
        c1.setValoración(10);

        Comentario c2 = new Comentario();
        c2.setContenido("No me ha gustado");
        c2.setValoración(3);

        Comentario c3 = new Comentario();
        c3.setContenido("Bastante bien");
        c3.setValoración(8);

        comentarios1 = new ArrayList<Comentario>();
        comentarios1.add(c1);
        comentarios1.add(c2);

        comentarios2 = new ArrayList<Comentario>();
        comentarios2.add(c3);

        s.registrarUsuario(u1);
        s.registrarUsuario(u2);

        s.listaComentariosPorUsuario("ehermos@gmail.com");

        s.añadirComentario(u1,"Mi pelicula favorita",10);

        s.usuariosPorValoracion();
    }
}