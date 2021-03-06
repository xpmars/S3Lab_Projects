package com.rajendra.pkg2

object Lab04 {
  import scala.collection.mutable.ArrayBuffer
  abstract class IntQueue {
    def get(): Int
    def put(x: Int)
  }

  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) { buf += x }
    def underlyingBuffer = buf
  }

  trait Doubling extends IntQueue{
    abstract override def put(x:Int)= {super.put(2*x)}
  }
  
  trait Incrementing extends IntQueue{
    abstract override def put(x:Int)= {super.put(x+1)}
  }
  
  //FIXME:
  // define stackable traits Doubling and Incrementing as subclasses of IntQueue
  // you have to mark your put method with 'abstract override' to 
  // make the stackable modifications work

  case class Person(val name: String, val age: Int, val country: String, email: String)

  abstract class Validator {
    def validate(p: Person): Boolean
  }

  class BasicValidator extends Validator {
    override def validate(p: Person): Boolean = !p.name.isEmpty && !p.country.isEmpty && !p.email.isEmpty
  }

  //FIXME:
  // define stackable traits AgeValidator and CountryValidator as subclasses of Validator
  // you have to mark your put method with 'abstract override' to 
  // make the stackable modifications work
  // AgeValidator: age >= 18 
  // CountryValidator: country == "SE"
  trait AgeValidator extends Validator{
    override def validate(p: Person): Boolean = p.age >= 18
  }
  trait CountryValidator extends Validator{
    override def validate(p: Person): Boolean = p.country == "SE"
  }
}




