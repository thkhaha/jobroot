package movies.spring.data.neo4j.domain;


import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Alex Yoon
 * @author Tae Kim
 */
@NodeEntity
public class Person {

	private int id;
	private String name;

	@Relationship(type = "WORKED", direction = Relationship.OUTGOING)
	private List<Worked> worked = new ArrayList<>();

	public Person() {
	}

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void addWorked(Worked worked) {
		if (this.worked == null) {
			this.worked = new ArrayList<>();
		}
		this.worked.add(worked);
	}
}