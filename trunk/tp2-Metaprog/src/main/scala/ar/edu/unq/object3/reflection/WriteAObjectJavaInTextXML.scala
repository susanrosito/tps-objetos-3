package ar.edu.unq.object3.reflection
import scala.xml._
import org.specs.specification.Example
import ar.edu.unq.object3.bean.Student
import java.util.ArrayList
import scala.collection.JavaConversions._
import ar.edu.unq.object3.bean.Course
import java.io.File
import java.util.List
import ar.edu.unq.object3.bean.Matter

object WriteAObjectJavaInTextXML {

  def toXml[A](ob: A): Node = {
    ob match {
      case string: java.lang.String => <Value value={ string } type={ string.getClass().getName() }> </Value>
      case integer: java.lang.Number => <Value value={ integer + "" } type={ integer.getClass().getName() }> </Value>
      case _ => {
        var fields = ReflectionUtils.getFields(ob)
        <Class name={ ob.getClass().getSimpleName() } package={ ob.getClass().getPackage().getName() }>
          {
            for (field <- fields) yield field.get(ob) match {
              case list: java.lang.Iterable[_] => {
                <List name={ field.getName() } type={ list.getClass().getName() }>
                  { for (value <- list) yield toXml(value) }
                </List>
              }
              case f => <Field name={ field.getName() }>{ toXml(f) }</Field>
            }
          }
        </Class>
      }
    }
  }

  def writeToFile(path: String, data: String) {
    var file = new File(path) with ReadAndWriteFile
    file.text = data
  }

  def setterFieldObject[A](fields: List[RepresentObject[A]], ob: Any) {
    for (field <- fields) {
      ReflectionUtils.setFieldValue(ob, field.property, field.value)
    }
  }

  def readXMLToObject[A](path: String): A = {
    var xml = scala.xml.XML.load(Source.fromFile(path))
    var className = (xml \ "@name").toString()
    var packagaName = xml \ "@package"
    var lists = (xml \ "List") map representFieldList
    var fields = (xml \ "Field") map representField
    var ob: A = ReflectionUtils.createClassOfType(packagaName.toString() + "." + className)
    setterFieldObject(lists, ob)
    setterFieldObject(fields, ob)
    return ob
  }

  def representField(field: Node): RepresentObject[Any] = {
    var name = (field \ "@name").toString()
    var value = field \ "Value"
    if (isHasValue(value)) {
      return new RepresentObject(name, ReflectionUtils.convertStringToClasType((value \ "@value").toString(), ReflectionUtils.getClassFromName((value \ "@type").toString())))
    } else {
      return new RepresentObject(name, representClassOfXml((field \ "Class")))
    }
  }

  def isHasValue(value: NodeSeq): Boolean = {
    return !(value.isEmpty)
  }
  def representFieldList[B](list: NodeSeq): RepresentObject[java.util.List[Any]] = {
    var name = list \ "@name"
    var ty = list \ "@type"
    var values = list \ "Value"
    var cl = list \ "Class"
    var l: java.util.List[Any] = ReflectionUtils.createClassOfType(ty.toString())
    if (isHasValue(values)) {
      this.createValues(values, l)
    } else {
      this.createObject(cl, l)
    }
    return new RepresentObject[java.util.List[Any]](name.toString(), l)
  }

  def createValues(values: NodeSeq, list: java.util.List[Any]) = {
    for (v <- values) {
      list.add(ReflectionUtils.convertStringToClasType((v \ "@value").toString(), ReflectionUtils.getClassFromName((v \ "@type").toString())))
    }
  }
  def createObject(cl: NodeSeq, list: java.util.List[Any]) {
    for (c <- cl) {
      list.add(representClassOfXml(c))
    }
  }

  def representClassOfXml[A](field: NodeSeq): Any = {
    var className = (field \ "@name").toString()
    var packageName = field \ "@package"
    var lists = (field \ "List") map representFieldList
    var fields = (field \ "Field") map representField
    var ob: Any = ReflectionUtils.createClassOfType(packageName.toString() + "." + className)
    setterFieldObject(lists, ob)
    setterFieldObject(fields, ob)
    return ob
  }
}

