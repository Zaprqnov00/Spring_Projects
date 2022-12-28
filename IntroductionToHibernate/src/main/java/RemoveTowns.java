import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String townForRemove = scanner.nextLine();

        Town town = entityManager.createQuery("SELECT t FROM Town t WHERE t.name = :townName",Town.class)
                .setParameter("townName", townForRemove)
                .getSingleResult();

        List<Address> addressList = entityManager.createQuery("SELECT a FROM Address a").getResultList();

        int countDeletedAddresses = 0;
        for (Address address :addressList) {
            if (address.getTown().getId() == town.getId()){
                addressList.remove(address.getId());
                countDeletedAddresses++;
            }
            entityManager.persist(address);
        }

        System.out.printf("%d address in %s deleted",countDeletedAddresses,town.getName());


        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
