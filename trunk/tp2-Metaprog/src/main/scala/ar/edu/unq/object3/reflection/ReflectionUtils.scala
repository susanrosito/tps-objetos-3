package ar.edu.unq.object3.reflection
import java.lang.Class
import scala.collection.JavaConversions._
import java.lang.reflect.Field
import java.io.PrintWriter
import scala.xml.NodeSeq

object ReflectionUtils {

  def getFields(ob: Any): Array[Field] = {
    var fields = getFieldsClass(ob.getClass())
    for (field <- fields) {
      field.setAccessible(true)
    }
    return fields
  }

  def getFieldsClass[A](ob: java.lang.Class[A]): Array[Field] = {
    if (ob == null) {
      return Array[Field]()
    } else {
      return ob.getDeclaredFields() ++ getFieldsClass(ob.getSuperclass())
    }
  }

  def getClassFromName[A](pathClass: String): Class[A] = {
    Class.forName(pathClass) match {
      case c: Class[A] => c
    }
  }

  def createClassOfType[A](pathClass: String): A = {
    getClassFromName(pathClass).getConstructor().newInstance()
  }

  def setFieldValue[A](ob: A, name: String, value: Any) {
    var field = searchField(ob, name)
    field.setAccessible(true)
    field.set(ob, value)
  }

  def searchField[A](ob: A, name: String): Field = {
    getFields(ob).foreach(f => {
      if (f.getName() == name) {
        return f
      }
    })
    return null
  }

  def createObject(field: NodeSeq): Any = {
    var value: String = (field \ "@value").toString()
    var nameType: String = (field \ "@type").toString()
    convertStringToClasType(value, getClassFromName(nameType))
  }

  def convertStringToClasType[A](value: String, classType: Class[A]) = {
    if (classType == classOf[Integer]) {
      Integer.parseInt(value)
    } else if (classType == classOf[String]) {
      value
    } else {
      null.asInstanceOf[A]
    }
  }
}