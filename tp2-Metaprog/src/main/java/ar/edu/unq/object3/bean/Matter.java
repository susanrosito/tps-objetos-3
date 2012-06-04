package ar.edu.unq.object3.bean;

import java.util.List;

public class Matter {

	private String name;
	private String description;
	private List<String> issues;
	private Integer credits;
	
	public Matter(){
		
	}
	public Matter(String name, String description, List<String> issues, Integer credits){
		this.setName(name);
		this.setDescription(description);
		this.setIssues(issues);
		this.setCredits(credits);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getIssues() {
		return issues;
	}

	public void setIssues(List<String> issues) {
		this.issues = issues;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}
}
