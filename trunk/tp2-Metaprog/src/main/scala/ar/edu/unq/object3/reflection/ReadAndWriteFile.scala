package ar.edu.unq.object3.reflection
import scala.io.Source
import java.io.PrintWriter
import java.io.File

trait ReadAndWriteFile extends File {
  
  def text = Source.fromFile( this ).mkString

  def text_=( s: String ) {
    val out = new PrintWriter( this )
    try{ out.print( s ) }
    finally{ out.close }
  }
}