package Garage
import scala.collection.mutable.Buffer

class Garage {
	
	var carsInRepair = List[{def repair}]()
	var dirtyCars = List[{def clean}]()
	var readyCars = List[{def repair; def clean}]()
	
//	var dirtyCarsB = Buffer [{def clean; def repair}]()
//	var carsAndMotoBikerInRepairB = Buffer[{def repair; def clean}]()
//	var readyCarsB = Buffer [{def clean}]()
	
	/**Repairs all the cars in the garage, and after that the
	 * list is empthy. The cars are now in the list of dirty cars 
	 **/
	
	def repairAllCars() = {
		var moreDirtyCars :  List[{def clean}] = this.dirtyCars
		this.carsInRepair.foreach(r => 
		{r.repair 
		 r::moreDirtyCars})
		 
		 this.carsInRepair = List[{def repair}]()
		 this.dirtyCars= moreDirtyCars;
	}
	
	
//	def repairAllCarsAndMotoBikerB() = {
//	  this.carsAndMotoBikerInRepairB.foreach(c => {c.repair
//	  this.dirtyCarsB.append(c)})
//	  this.carsAndMotoBikerInRepairB.--(this.carsAndMotoBikerInRepairB) 
//		 println(this.carsAndMotoBikerInRepairB == List[{def repair}]())
//	}
	
	/**Wash all the cars in the garage, and after that the
	 * list is empthy. The cars are now in the list of ready cars 
	 **/
	def cleanAllCars() = {
		var moreReadyCars : List[{def repair; def clean}] = this.readyCars
		this.dirtyCars.foreach(
		{d => d.clean
			 d::moreReadyCars })
			 this.dirtyCars = List[{def clean}]()
			 this.readyCars = moreReadyCars}
	
//	def cleanAllCarsAndMotoBiker() = {
//	  this.dirtyCarsB.map(x => x.clean) //_.clean)
//	  this.dirtyCarsB.--(this.dirtyCarsB) // no se si esto funciona
//	}
	
	/**This method adds a new car in the garage:
	 * if it's broken, is added in the list of broken cars
	 * otherwise, is added in the list of dirty cars
	 **/
	def addCar(car : {def repair; def clean; def isBroken(): Boolean}) = {
	if (car.isBroken())
	{ this.carsInRepair= car::this.carsInRepair }
	
	else { this.dirtyCars = car::this.dirtyCars }}
	
//	def addCarB(car: {def repair ; def clean ; def isBroken() : Boolean }) = {
//	  if(car.isBroken()){
//	    this.carsAndMotoBikerInRepairB.append(car)
//	  }
//	  else{
//	    this.dirtyCarsB.append(car)
//	  }
//	}
	
	/**This method returns a boolean that indicates if the parameter car
	 * is in the list of ready cars.	 
	 **/
	def isReady(car : Car) : Boolean = { this.readyCars.contains(car)}
	/**
	 * This method deletes the car of a list.
	 * @param car
	 * @param l
	 * @return
	 */
	
	def deleteCar (car : Car  , l : List [{def repair; def clean}]) : List [{def repair; def clean}] = 
	{
		if(l.isEmpty) 
		{ List [{def repair; def clean}]() }
		else{ 
			var actual : {def repair} = l.head
			var resto :  List [{def repair; def clean}] = l.tail
			if(car == actual)
			{ resto }
			else 
			{ this.deleteCar(car,resto) }
		}
	}
	
//	def deleteCarB (car: {def repair ; def clean } , l : Buffer[{def repair ; def clean}]) : Buffer[{def repair ; def clean}] = {
//	  if(l.isEmpty)
//		 Buffer[{def repair ; def clean}]()
//	  else 
//	     l.-(car)
//	}
	
	/**This method "returns" the car to his owner.
	 * It only deletes it from the list.
	 **/
	
	def returnCar(car : Car ) = {
	this.readyCars = this.deleteCar(car, this.readyCars) 
	}
}

object Main {
  
  def main(args: Array[String]){
    var garage = new Garage
    
    var car: Car = new Car(123, new Persona("susu",11))
    var car2: Car = new Car(356, new Persona("tita",14))
    var car3: Car = new Car(178, new Persona("buut",161))
    
    //Nota: Este es nuestro dominio. Tenemos un inconveniente al querer agregar autos al garage.
    // Es por eso que no, podemos hacer funcionar nuestro tp. 
     
  
  }
}