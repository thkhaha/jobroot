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

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface PositionRepository extends Neo4jRepository<Position, Long> {

	@Query("MATCH path=(po:Position {name:'SDE-I'})-[pa:Path*1..20]->(po2:Position {name:'SDM-III'}) RETURN nodes(path), rels(path)")
	Collection<Position> findPath(@Param("startPosition") String startPosition, @Param("endPosition") String endPosition, @Param("limit") int limit);
}