package org.example.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la entidad del usuario
 */
@Entity
@Data
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String correo;

    private String nombre;

    @OneToMany
    private List<Comentario> comentarios = new ArrayList<>();
}
