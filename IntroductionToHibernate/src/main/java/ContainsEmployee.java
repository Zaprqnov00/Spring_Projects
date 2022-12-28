import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    private static final String GET_EMPLOYEE_BY_NAMES = "SELECT e FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String[] peopleInfo = scanner.nextLine().split(" ");
        String firstName = peopleInfo[0];
        String lastName = peopleInfo[1];

        List<Employee> employeeList = entityManager.createQuery(GET_EMPLOYEE_BY_NAMES)
                .setParameter("fn",firstName)
                .setParameter("ln", lastName)
                .getResultList();

        printResult(employeeList);

    }

    private static void printResult(List<Employee> employeeList) {
        if (employeeList.isEmpty()){
            System.out.println("No");
        }else {
            System.out.println("Yes");
        }
    }
}
