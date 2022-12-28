import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    private static final String UPDATE_SALARY = "UPDATE Employee e SET e.salary = e.salary + (e.salary * 0.12) WHERE e.department.id IN (1,2,4,11)";
    private static final String GET_EMPLOYEES = "SELECT e FROM Employee e WHERE e.department.id IN (1,2,4,11)";
    private static final String PRINT_FORMAT = "%s %s ($%.2f)\n";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager
                .createQuery(UPDATE_SALARY)
                .executeUpdate();

        entityManager.createQuery(GET_EMPLOYEES,Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT,
                        e.getFirstName(),
                        e.getLastName(),
                        e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
