package movies.spring.data.neo4j.repositories;

import movies.spring.data.neo4j.domain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author pdtyreus
 * @author Mark Angrish
 */
public interface PersonRepository extends Neo4jRepository<Person, Long> {

}