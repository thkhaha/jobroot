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
	private int id;
	private String name;
	private String longName;
	private long avgSalary;
	private int count;
	
	//@JsonIgnoreProperties("position")
	//@Relationship(type = "WORKED", direction = Relationship.INCOMING)
	//private List<Worked> worked;

	@JsonIgnoreProperties("position")
	@Relationship(type = "Path")
	private List<Path> paths;

	public Position() {
	}

	public Position(int id, String name, String longName, long avgSalary, int count) {
		this.id = id;
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

	public List<Path> getPaths() {
		return paths;
	}

	public void addPath(Path path) {
		if (this.paths == null) {
			this.paths = new ArrayList<>();
		}
		this.paths.add(path);
	}
}