import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class AddingANewAddressAndUpdatingEmployee {
    private static final String UPDATE_TOWNS = "UPDATE Employee e SET e.address = :na WHERE e.lastName = :ln";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String lastNameScanner = scanner.nextLine();

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");

        entityManager.persist(newAddress);

        entityManager.createQuery(UPDATE_TOWNS)
                .setParameter("na", newAddress)
                .setParameter("ln", lastNameScanner)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
