package ar.edu.unq.object3.bean;

import java.util.ArrayList;

import ar.edu.unq.object3.reflection.WriteAObjectJavaInTextXML;

public class ExampleCreateXmlAObject {

	public static void main(String[] args) {

		// create students with class java
		Student susana = new Student("Susana", "Rosito",
				"rosito.susana@gmail.com", 22, 2345);
		Student ronny = new Student("Ronny", "DeJesus", "nnydjesus@gmail.com",
				22, 2675);
		Student rocio = new Student("Rocio", "Amaya", "rocio.amaya@gmail.com",
				25, 1246);
		Student natalia = new Student("Natalia", "Rodriguez",
				"naty.Stefy@gmail.com", 22, 4545);
		Student pablo = new Student("Pablo", "Alegre",
				"pablo.alegre@gmail.com", 22, 8976);
		Student marilu = new Student("Marilu", "Medici",
				"mari.medici@gmail.com", 22, 8964);

		// issues of the matter
		ArrayList<String> issues = new ArrayList<String>();
		issues.add("Tipos");
		issues.add("Scala");
		issues.add("trait/Mixin");
		issues.add("Multimethod");
		issues.add("MetaProgramacion/Reflection");
		issues.add("Aspectos");

		// the matter
		Matter objects3 = new Matter(
				"objetos3",
				"Esta es una materia de tipo electiva, de la carrera tec. en programacion informatica. Vamos a utilizar distintas tecnologias y herramientas y aprender varios conceptos importantes",
				issues, 6);

		// list of students
		ArrayList<Student> studentsOfObjects3 = new ArrayList<Student>();
		studentsOfObjects3.add(susana);
		studentsOfObjects3.add(ronny);
		studentsOfObjects3.add(rocio);
		studentsOfObjects3.add(natalia);
		studentsOfObjects3.add(marilu);
		studentsOfObjects3.add(pablo);

		// creo un Curso
		Course course = new Course("Curso de: ",
				studentsOfObjects3, objects3);

//		WriteAObjectJavaInTextXML.writeToFile("/home/usuario/Escritorio/materia.xml", WriteAObjectJavaInTextXML.toXmlSimple(objects3).toString());
//		WriteAObjectJavaInTextXML.writeToFile("/home/nny/Escritorio/curso.xml", WriteAObjectJavaInTextXML.toXml(course).toString());
//		Object c = WriteAObjectJavaInTextXML.readXMLToObject("/home/nny/Escritorio/curso.xml");
		Course c = WriteAObjectJavaInTextXML.readXMLToObject("/home/nny/Escritorio/curso.xml");
		System.out.println("Objecto curso: " + c );
		System.out.println("Materia del curso: " + c.getMatter());
		System.out.println("Nombre de la materia: " + c.getMatter().getName());
		System.out.println("Descripcion de la materia: "+ c.getMatter().getDescription());
		System.out.println("Creditos de la materia: " +c.getMatter().getCredits());
		System.out.println("Temas de la materia: " +c.getMatter().getIssues());
		System.out.println("Estudiantes del Curso: " +c.getStudents());
		System.out.println("nombre del first Estudiantes : " +c.getStudents().get(0).getName());
		System.out.println("apellido del first Estudiantes : " +c.getStudents().get(0).getLastName());
		System.out.println("edad del first Estudiantes : " +c.getStudents().get(0).getAge());
		System.out.println("legajo del firts Estudiantes : " +c.getStudents().get(0).getDocket());
//		System.out.println(m.getDescription());
	}
}
