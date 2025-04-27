package gr.aueb.cf;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school7PU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        // Here transaction starts (work unit)

//        em.getTransaction().begin();









        // Here the transaction ends
//        em.getTransaction().commit();

        // Closing the resources
        emf.close();
        em.close();

    }
}
