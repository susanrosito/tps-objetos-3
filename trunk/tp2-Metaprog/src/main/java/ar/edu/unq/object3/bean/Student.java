package ar.edu.unq.object3.bean;

public class Student {

	private String name;
	private String lastName;
	private String email;
	private Integer docket;
	private Integer age;
	
	public Student(){
	}
	
	public Student(String name, String lastName , String email, Integer docket, Integer age){
		this.setName(name);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setDocket(docket);
		this.setAge(age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getDocket() {
		return docket;
	}

	public void setDocket(Integer docket) {
		this.docket = docket;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
