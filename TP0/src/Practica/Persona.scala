package Practica

class Persona (var nombre : String , var telefono : Int){
	
	
	
	
	 def == (p: Persona) : Boolean = this.nombre == p.nombre
	 def > (p: Persona) : Boolean = {
		var resultado=  this.nombre .compareTo(p.nombre)
		resultado match {
		case 1 => true;
		case _ => false;}
	 }
	 def < (p: Persona) : Boolean = !this.<(p)
	 
	 def cambiarTelefonoPor(tel: Int) = this.telefono=tel
	 
	
  
	
}

