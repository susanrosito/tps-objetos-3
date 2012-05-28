package metaprogramacion;

import java.io.PrintWriter;
import java.lang.reflect.Field;

public class InspectClass {
	
	private static void introspectInstance(Object anObject, PrintWriter printer) throws IllegalArgumentException,  IllegalAccessException {

		 Class objectType = anObject.getClass();
		 printer.println("Un " + objectType.getSimpleName() + "");
		 for (Field field : objectType.getDeclaredFields()) {
		 field.setAccessible(true);
		 printer.print("\tcon ");
		 printer.print(field.getName());
		 printer.println(" = " + toString(field.get(anObject)));
		 }
		 printer.flush();
		}
	
	
	private static String toString(Object object) {
		if(object != null){
		return object.toString();
		}else{
			return "null";
		}
	}


	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
	    introspectInstance(new Example("Orson Scott Card", "El Juego de Ender"), new PrintWriter(System.out));
	}

//	private static void introspect(Class aClass, PrintWriter printer) {
//		printer.println("Un '" + aClass.getSimpleName() + "'");
//		
//		for (Field field : aClass.getDeclaredFields()) {
//			printer.print("\ttiene ");
//			printer.print(field.getName());
//			printer.println(" de tipo " + field.getType().getSimpleName());
//		}
//		printer.flush();
//	}
}
