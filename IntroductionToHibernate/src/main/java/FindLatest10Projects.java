import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class FindLatest10Projects {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Project> projectList =
                entityManager.createQuery("SELECT p FROM Project p").getResultList();

        Collections.reverse(projectList);

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));

        projectList.stream()
               .sorted(Comparator.comparing(Project::getName))
               .limit(10)
               .forEach(project ->
                       System.out.printf("Project name: %s\n\tProject Description: %s\n\tProject Start Date:%s\n\tProject End Date: %s\n",
                       project.getName(),
                       project.getDescription(),
                       project.getStartDate(),
                               project.getEndDate()));


    }
}
