package movies.spring.data.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Tae Kim
 * @author Alex Yoon
 */
@SpringBootApplication
@EnableNeo4jRepositories("movies.spring.data.neo4j.repositories")
public class JobrootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobrootApplication.class, args);
    }
}