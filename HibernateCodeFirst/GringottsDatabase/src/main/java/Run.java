import entities.Wizard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Run {
    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gringotts_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


    }
}
