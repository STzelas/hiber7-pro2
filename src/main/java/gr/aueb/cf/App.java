package gr.aueb.cf;


import gr.aueb.cf.core.GenderType;
import gr.aueb.cf.model.Region;
import gr.aueb.cf.model.Teacher;
import gr.aueb.cf.model.TeacherMoreInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class App {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school7PU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        // Here transaction starts (work unit)

        em.getTransaction().begin();

        // Φτιάχνω τον Teacher με τον builder (που δημιουργήθηκε απο το Lombok)
        Teacher teacher = Teacher.builder()
                .firstname("Κώστας")
                .lastname("Γιαννούτσος")
                .isActive(true)
                .build();

        // Φτιάχνω το TeacherMoreInfo με τον builder (που δημιουργήθηκε απο το Lombok)
        TeacherMoreInfo teacherMoreInfo = TeacherMoreInfo.builder()
                .dateOfBirth(LocalDateTime.of(2000, 2, 1, 10, 10))
                .gender(GenderType.MALE)
                .build();


        // Βάζω στον teacher που έφτιαξα το teacherMoreInfo, Region κλπ
        teacher.setTeacherMoreInfo(teacherMoreInfo);
        Region region = em.find(Region.class, 1);
        teacher.addRegion(region);

        em.persist(teacher); // Θα γίνει persist και το teacherMoreInfo

        // Here the transaction ends
        em.getTransaction().commit();

        // Closing the resources
        emf.close();
        em.close();

    }
}
