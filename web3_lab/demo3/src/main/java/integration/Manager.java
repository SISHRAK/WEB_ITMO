package integration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class Manager implements AutoCloseable {
    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("mainUnit");
            if (emf == null) throw new PersistenceException();
        } catch (PersistenceException e) {
            System.err.println("Database connection error! Check credentials.");
            System.exit(1);
        }
    }

    public static synchronized EntityManager generateEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void close() {
        if (emf != null && emf.isOpen())
            emf.close();
    }
}