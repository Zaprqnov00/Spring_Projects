import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    private static final String SELECT_EMPLOYEE_BY_SALARY = "SELECT e FROM Employee e WHERE e.salary > 50000";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager
                .createQuery(SELECT_EMPLOYEE_BY_SALARY, Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf("%s\n",e.getFirstName()));


    }
}
