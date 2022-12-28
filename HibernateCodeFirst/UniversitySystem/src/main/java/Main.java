import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("university_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.createQuery("SELECT s FROM Student s", Student.class)
                .setMaxResults(1)
                .getResultList()
                .forEach(s -> System.out.println(s.getPhoneNumber()));
    }
}
