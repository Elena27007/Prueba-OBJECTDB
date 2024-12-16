package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que se encarga de establecer la conexión con EntityManager
 */
public class ObjectDBUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("data.odb");
    }

    EntityManagerFactory getEmf() {
        return emf;
    }
}
