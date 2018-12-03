package movies.spring.data.neo4j.services;

import static org.neo4j.helpers.collection.MapUtil.map;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import movies.spring.data.neo4j.executor.BoltCypherExecutor;
import movies.spring.data.neo4j.executor.CypherExecutor;
import movies.spring.data.neo4j.util.Util;

import org.neo4j.driver.internal.InternalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PositionService {

    private final static Logger LOG = LoggerFactory.getLogger(PositionService.class);
    private final CypherExecutor cypher;

    public PositionService() {
        cypher = createCypherExecutor(Util.DEFAULT_URL);
    }

    private CypherExecutor createCypherExecutor(String uri) {
        try {
            String auth = new URL(uri.replace("bolt","http")).getUserInfo();
            if (auth != null) {
                String[] parts = auth.split(":");
                return new BoltCypherExecutor(uri,parts[0],parts[1]);
            }
            return new BoltCypherExecutor(uri);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Neo4j-ServerURL " + uri);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Map<String, Object> findPath(String startPosition, String endPosition, int limit) {
        Iterator<Map<String,Object>> result = cypher.query(
        		"MATCH p=(po:Position {name:'" + startPosition + "'})-[pa:Path*1..20]->(po2:Position {name:'" + endPosition + "'}) " + 
        		"RETURN DISTINCT [n in nodes(p) | n.name] as Positions", null);
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
       // List nodes = new ArrayList();
       // List rels= new ArrayList();
		
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            List<String> positionPath = new ArrayList<String>((Collection<String>)row.get("Positions"));
            int i=0;
            int source=i;
            for(String position : positionPath) {
                i++;
                nodes.add(map("Position", position, "label", "position"));
                if(positionPath.size() > i) {
                    rels.add(linkMap("source", source,"target", i, "weight", 1));
                }
                source=i;
            }
        }
        return map("nodes", nodes, "links", rels);
    } 

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}
	
	private Map<String, Object> linkMap(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		result.put(key3, value3);
		return result;
	}
}

/*
 * private final PositionRepository positionRepository;
public PositionService(PositionRepository positionRepository) {
	this.positionRepository = positionRepository;
}

private Map<String, Object> toD3Format(Collection<Position> positions) {
	List<Map<String, Object>> nodes = new ArrayList<>();
	List<Map<String, Object>> rels = new ArrayList<>();
	int i = 0;
	Iterator<Position> result = positions.iterator();
	while (result.hasNext()) {
		Position position = result.next();
		nodes.add(map("name", position.getName(), 
					  "longName", position.getLongName()));
		nodes.add(map("avgSalary", Long.valueOf(position.getAvgSalary()), 
					  "count", String.valueOf(position.getCount())));
		int target = i;
		i++;
		for (Path path : position.getPaths()) {
			Map<String, Object> pathObject = map("avgYear", path.getAvgYear(), 
												 "avgSalaryBump", path.getAvgSalaryBump());
			int source = nodes.indexOf(pathObject);
			if (source == -1) {
				nodes.add(pathObject);
				source = i++;
			}
			rels.add(map("source", source, "target", target));
		}
	}
	return map("nodes", nodes, "links", rels);
}

private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
	Map<String, Object> result = new HashMap<String, Object>(2);
	result.put(key1, value1);
	result.put(key2, value2);
	return result;
}

@Transactional(readOnly = true)
public Map<String, Object>  findPath(String startPosition, String endPosition, int limit) {
	Collection<Position> result = positionRepository.findPath(startPosition, endPosition, limit);
	return toD3Format(result);
}*/



