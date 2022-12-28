import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMaximumSalaries {
    private static final String GIVE_MAX_SALARY = "SELECT e.department.name, MAX(e.salary)" +
            " FROM Employee AS e GROUP BY e.department.id HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager
                .createQuery(GIVE_MAX_SALARY, Object[].class)
                .getResultList()
                .forEach(d -> System.out.println(d[0] + " " + d[1]));

    }
}
