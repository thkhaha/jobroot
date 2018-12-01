package movies.spring.data.neo4j.domain;

import org.neo4j.ogm.annotation.*;

/**
 * @author Alex Yoon
 * @author Tae Kim
 */
@RelationshipEntity(type = "PATH")
public class Path {
    @Id
    @GeneratedValue
	private long id;
	private int avgYear;
	private int avgSalaryBump;
	
	@StartNode
	private Position startPosition;

	@EndNode
	private Position endPosition;

	public Path() {
	}

	public Path(Position startPosition, Position endPosition) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}

	public Long getId() {
	    return id;
	}

	public int getAvgYear() {
	    return avgYear;
	}

	public int getAvgSalaryBump() {
	    return avgSalaryBump;
	}

	public Position getStartPosition() {
	    return startPosition;
	}

	public Position getEndPosition() {
	    return endPosition;
	}
}