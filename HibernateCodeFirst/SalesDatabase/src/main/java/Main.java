import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sales_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList().forEach(product -> System.out.println(product.getQuantity()));

    }
}
