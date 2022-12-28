import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class GetEmployeeWithProject {
    private static final String GET_EMPLOYEE_BY_ID = "SELECT e FROM Employee e WHERE e.id = :ei";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int employeeId = Integer.parseInt(scanner.nextLine());

        List<Employee> employeeList = entityManager.createQuery(GET_EMPLOYEE_BY_ID
                        , Employee.class)
                .setMaxResults(1)
                .setParameter("ei", employeeId)
                .getResultList();

        for (Employee employee : employeeList) {
            Set<Project> projectSet = employee.getProjects();

            employeeList.stream()
                    .forEach(e -> System.out.printf("%s %s - %s\n", e.getFirstName(), e.getLastName(), e.getJobTitle()));

            projectSet.stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .forEach(project -> System.out.printf("\t%s\n", project.getName()));
        }


    }
}
