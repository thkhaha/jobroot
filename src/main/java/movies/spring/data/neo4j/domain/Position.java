package movies.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alex Yoon
 * @author Tae Kim
 */
@NodeEntity
public class Position {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String longName;
	private long avgSalary;
	private int count;
	
	@JsonIgnoreProperties("position")
	@Relationship(type = "WORKED", direction = Relationship.INCOMING)
	private List<Worked> worked;

	@JsonIgnoreProperties("position")
	@Relationship(type = "PATH", direction = Relationship.UNDIRECTED)
	private List<Path> path;

	public Position() {
	}

	public Position(String name, String longName, long avgSalary, int count) {
		this.name = name;
		this.longName = longName;
		this.avgSalary = avgSalary;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLongName() {
		return longName;
	}

	public long getAvgSalary() {
		return avgSalary;
	}

	public int getCount() {
		return count;
	}

	public void addPath(Path path) {
		if (this.path == null) {
			this.path = new ArrayList<>();
		}
		this.path.add(path);
	}
}