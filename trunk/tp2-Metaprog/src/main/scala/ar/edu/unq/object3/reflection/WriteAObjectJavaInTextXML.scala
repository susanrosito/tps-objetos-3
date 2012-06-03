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
      case s: java.lang.String => <Value value={ s }> </Value>
      case i: java.lang.Number => <Value value={ i + "" }> </Value>
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
              case f => <Field name={ field.getName() } type={ field.getType().getName() }>{ toXml(f) }</Field>
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

  def setFieldObj[A](fields: List[RepObj[A]], ob: Any) {
    for (f <- fields) {
      ReflectionUtils.setFieldValue(ob, f.property, f.value)
    }
  }
  def readXMLToObject[A](path: String): A = {
    var xml = scala.xml.XML.load(Source.fromFile(path))
    var className = (xml \ "@name").toString()
    var packagaName = xml \ "@package"
    var lists = repAllFieldList(xml \ "List")
    var fields = repAllField(xml \ "Field")
    var ob: A = ReflectionUtils.createClassOfType(packagaName.toString() + "." + className)
    setFieldObj(lists, ob)
    setFieldObj(fields, ob)
    return ob
  }

  def repAllField(listfield: NodeSeq): List[RepObj[Any]] = {
    var listf = new ArrayList[RepObj[Any]]()
    for (f <- listfield) {
      listf.add(repField(f))
    }
    return listf
  }

  def repField(field: Node): RepObj[Any] = {
    return decideWay(field)
  }

  def repAllFieldList(listFieldList: NodeSeq): List[RepObj[java.util.List[Any]]] = {
    var list = new ArrayList[RepObj[java.util.List[Any]]]()
    for (lfl <- listFieldList) {
      list.add(repFieldList(lfl))
    }
    return list
  }

  def decideWay(field: Node): RepObj[Any] = {
    var name = (field \ "@name").toString()
    var ty = (field \ "@type").toString()
    var taValue = field \ "Value"
    if (isHasValue(taValue)) {
      return new RepObj(name, ReflectionUtils.convertStringToClasType((taValue \ "@value").toString(), ReflectionUtils.getClassFromName(ty)))
    } else {
      return new RepObj(name, repClassInXml((field \ "Class")))
    }
  }

  def isHasValue(value: NodeSeq): Boolean = {
    return !(value.isEmpty)
  }
  def repFieldList[B](list: NodeSeq): RepObj[java.util.List[Any]] = {
    var name = list \ "@name"
    var ty = list \ "@type"
    var values = list \ "Value"
    var classE = list \ "Class"
    var l: java.util.List[Any] = ReflectionUtils.createClassOfType(ty.toString())
    if (isHasValue(values)) {
      this.createValues(values, l)
    } else {
      this.createObjectNoInpure(classE, l)
    }
    return new RepObj[java.util.List[Any]](name.toString(), l)
  }

  def createValues(values: NodeSeq, list: java.util.List[Any]) = {
    for (v <- values) {
      list.add((v \ "@value").toString()) // esto no esta del todo bien Reparar
    }
  }
  def createObjectNoInpure(cl: NodeSeq, list: java.util.List[Any]) {
    for (c <- cl) {
      list.add(repClassInXml(c))
    }
  }
  def repClassInXml[A](field: NodeSeq): Any = {
    var className = (field \ "@name").toString()
    var packageName = field \ "@package"
    var lists = repAllFieldList(field \ "List")
    var fields = repAllField(field \ "Field")
    var ob: Any = ReflectionUtils.createClassOfType(packageName.toString() + "." + className)
    setFieldObj(lists, ob)
    setFieldObj(fields, ob)
    return ob
  }

}

