package Garage

class Car (var patente : Int, var dueño : Persona) {
	
	var broken : Boolean = false
	var dirty : Boolean = false
	
	def repair() = this.broken= true
	def clean() = this.dirty = true
	
	def isDirty() : Boolean = this.dirty 
	def isBroken(): Boolean = this.broken
	
	def == (car : Car) : Boolean = this.patente == car.patente
	

}