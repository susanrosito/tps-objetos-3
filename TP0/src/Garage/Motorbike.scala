package Garage

class Motorbike {
	
	var dirty: Boolean = true 
	var goodStateWheels : Boolean = true
   
	def repair() = this.goodStateWheels = false
    def clean() = this.dirty = false
}