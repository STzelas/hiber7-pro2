package gr.aueb.cf;


import gr.aueb.cf.core.GenderType;
import gr.aueb.cf.model.Region;
import gr.aueb.cf.model.Teacher;
import gr.aueb.cf.model.TeacherMoreInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("school7PU");
    private final static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        // Here transaction starts (work unit)

        em.getTransaction().begin();

        // Φτιάχνω τον Teacher με τον builder (που δημιουργήθηκε απο το Lombok)
//        Teacher teacher = Teacher.builder()
//                .firstname("Κώστας")
//                .lastname("Γιαννούτσος")
//                .isActive(true)
//                .build();

        // Φτιάχνω το TeacherMoreInfo με τον builder (που δημιουργήθηκε απο το Lombok)
//        TeacherMoreInfo teacherMoreInfo = TeacherMoreInfo.builder()
//                .dateOfBirth(LocalDateTime.of(2000, 2, 1, 10, 10))
//                .gender(GenderType.MALE)
//                .build();


        // Βάζω στον teacher που έφτιαξα το teacherMoreInfo, Region κλπ
//        teacher.setTeacherMoreInfo(teacherMoreInfo);
//        Region region = em.find(Region.class, 1);
//        teacher.addRegion(region);
//
//        em.persist(teacher); // Θα γίνει persist και το teacherMoreInfo

        // merge - update
//        String sql = "SELECT t FROM Teacher t WHERE t.lastname = :lastname"; // Το On στο JOIN το κάνει αυτόματα το hibernate επειδή κάναμε τα models
//        TypedQuery<Teacher> query = em.createQuery(sql, Teacher.class);
//        query.setParameter("lastname", "Ανδρούτσος").getResultList();
//        Teacher teacher = query.getSingleResult();  // Μόνο αν υπάρχει ένας
//        teacher.setFirstname("Athanasios");
        // merge - isActive == false για soft delete
        //  teacher.setIsActive(false);
        // Select τους active καθηγητές
//        String sql = "SELECT t FROM Teacher t WHERE t.isActive = true AND t.region.title = :regionTitle";
//        TypedQuery<Teacher> query = em.createQuery(sql, Teacher.class);
//        List<Teacher> teachers = query.setParameter("regionTitle", "Θεσσαλονίκη").getResultList();
//
//        teachers.forEach(System.out::println);

        // Πόσοι teachers είναι ανα περιοχή

        String sql = "SELECT r.title, COUNT(t) FROM Region r LEFT JOIN r.teachers t " +
                "WHERE t.isActive = false OR t.isActive IS NULL GROUP BY r.title";

        TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
        List<Object[]> teachersPerRegion = query.getResultList();

        for (Object[] el : teachersPerRegion) {
            for (Object item : el) {
                System.out.print(item + " ");
            }
            System.out.println();
        }






        // Here the transaction ends
        em.getTransaction().commit();

        // Closing the resources
        emf.close();
        em.close();

    }
}
