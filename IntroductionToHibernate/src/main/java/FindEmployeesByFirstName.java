import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    private static final String SELECT_EMPLOYEE_BY_PATTERN = "SELECT e FROM Employee e WHERE e.firstName LIKE :regex";
    private static final String PRINT_FORMAT = "%s %s - %s - ($%.2f)\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String pattern = scanner.nextLine();

        entityManager.createQuery(SELECT_EMPLOYEE_BY_PATTERN, Employee.class)
                .setParameter("regex", pattern + "%")
                .getResultList()
                .forEach(employee -> System.out.printf(PRINT_FORMAT,
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getJobTitle(),
                        employee.getSalary()));
    }
}
