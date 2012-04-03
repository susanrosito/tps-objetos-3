package Practica

class Agenda {
	
	var agendados = List[Persona]() 
	
	def acomodarPersona (p : Persona, l: List [Persona]) : List [Persona] = 
	{
		if(this.agendados.isEmpty) 
		{ p::this.agendados }
		else{ 
			var actual : Persona = l.head
			var resto : List [Persona] = l.tail
			if(p > actual)
			{ p::actual::resto }
			else 
			{ actual:: this.acomodarPersona(p, resto) }
		}
	}
	
	def agendarPersona(p : Persona) = this.agendados= this.acomodarPersona(p, this.agendados)
	
	def buscarPersona (p : Persona, l : List [Persona]) : Persona = 
	{
		 
		if(l.isEmpty) 
		{ null }
		else{ 
			var actual : Persona = l.head
			var resto :  List [Persona] = l.tail
			if(p == actual)
			{ actual }
			else 
			{ this.buscarPersona(p,l) }
		}
	}
	
	def obtenerTel (p: Persona) : Int = 
		this.buscarPersona(p, this.agendados ).telefono
		
	def cambiarTel (p: Persona, tel : Int) = 
		this.buscarPersona(p, this.agendados ).telefono = tel

}