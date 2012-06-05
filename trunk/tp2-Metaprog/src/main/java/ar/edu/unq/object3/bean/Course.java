package ar.edu.unq.object3.bean;

import java.util.List;

public class Course {

	private String name;
	private List<Student> students;
	private Matter matter;
	
	public Course() {
	}
	
	public Course(String name, List<Student> students, Matter matter){
		this.setName(name);
		this.setStudents(students);
		this.setMatter(matter);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Matter getMatter() {
		return matter;
	}

	public void setMatter(Matter matter) {
		this.matter = matter;
	}
	
	@Override
	public String toString() {
		return this.name + this.getMatter().getName();   
	}
}
