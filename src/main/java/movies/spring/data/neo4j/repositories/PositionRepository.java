package movies.spring.data.neo4j.repositories;

import java.util.Collection;

import movies.spring.data.neo4j.domain.Position;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Alex Yoon
 * @author Tae Kim
 */
@RepositoryRestResource(collectionResourceRel = "positions", path = "positions")
public interface PositionRepository extends Neo4jRepository<Position, Long> {

	Position findByName(@Param("name") String name);

	Collection<Position> findByLongName(@Param("longName") String longName);

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
	Collection<Position> graph(@Param("limit") int limit);
}