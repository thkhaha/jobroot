package movies.spring.data.neo4j.domain;

import org.neo4j.ogm.annotation.*;

/**
 * @author Alex Yoon
 * @author Tae Kim
 */
@RelationshipEntity(type = "WORKED")
public class Worked {
	private Long id;
	private int yearsOfExp;
	private int jobOrder;
	
	@StartNode
	private Person person;

	@EndNode
	private Position position;

	public Worked() {
	}

	public Worked(Long id, Position position, Person person) {
		this.id = id;
		this.position = position;
		this.person = person;
	}

	public Long getId() {
	    return id;
	}

	public int getJobOrder() {
	    return jobOrder;
	}

	public int getYearsOfExp() {
	    return yearsOfExp;
	}

	public Person getPerson() {
	    return person;
	}

	public Position getPosition() {
	    return position;
	}
}