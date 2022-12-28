import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    private static final String GET_EMPLOYEE_BY_DEPARTMENTS
            = "SELECT e FROM Employee e WHERE e.department.id = 6 ORDER BY e.salary, e.id";
    private static final String PRINT_FORMAT = "%s %s from %s - $%.2f\n";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.createQuery(GET_EMPLOYEE_BY_DEPARTMENTS, Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT,
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()));
    }
}
