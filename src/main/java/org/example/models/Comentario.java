package org.example.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Clase que representa la entidad del comentario
 */
@Entity
@Data
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String contenido;
    private Integer valoración;

    @ManyToOne
    private Usuario usuario;

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", valoración=" + valoración +
                ", usuario=" + usuario.getNombre() +
                '}';
    }
}
