import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ChangeCasing {
    private static final String UPDATE_TOWNS_UPPERCASE = "UPDATE Town t SET t.name = upper(name) WHERE length(name) <= 5";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE_TOWNS_UPPERCASE)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
