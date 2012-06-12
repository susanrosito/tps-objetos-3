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
      case string: java.lang.String => <Value value={ string } type={ string.getClass().getName() } enum={ EnumV.vl.toString() }> </Value>
      case integer: java.lang.Number => <Value value={ integer + "" } type={ integer.getClass().getName() } enum={ EnumV.vl.toString() }> </Value>
      case _ => {
        var fields = ReflectionUtils.getFields(ob)
        <Class name={ ob.getClass().getSimpleName() } package={ ob.getClass().getPackage().getName() } enum={ EnumV.cl.toString() }>
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
    return this.representClassOfXml(xml)
  }

  def representField(field: Node): RepresentObject[Any] = {
    var name = (field \ "@name").toString()
    var value = field \ "Value"
    if (isHasValue(value)) {
      return new RepresentObject(name, ReflectionUtils.createObject(value))
    } else {
      return new RepresentObject(name, representClassOfXml((field \ "Class")))
    }
  }

  def representValueOrClass( valueOrClass: NodeSeq) = {
    var enum = valueOrClass \ "@enum"
    EnumV.withName(enum.toString()) match {
      case EnumV.vl => ReflectionUtils.createObject(valueOrClass)
      case EnumV.cl => representClassOfXml(valueOrClass)
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
    var elems = values ++ cl
    var l: java.util.List[Any] = ReflectionUtils.createClassOfType(ty.toString())
    l = elems map representValueOrClass
    return new RepresentObject[java.util.List[Any]](name.toString(), l)
  }

  def representClassOfXml[A](field: NodeSeq): A = {
    var className = (field \ "@name").toString()
    var packageName = field \ "@package"
    var lists = (field \ "List") map representFieldList
    var fields = (field \ "Field") map representField
    var ob: A = ReflectionUtils.createClassOfType(packageName.toString() + "." + className)
    setterFieldObject(lists, ob)
    setterFieldObject(fields, ob)
    return ob
  }
}

