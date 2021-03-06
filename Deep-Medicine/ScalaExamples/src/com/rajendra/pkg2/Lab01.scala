package com.rajendra.pkg2

object Lab01 {
  
  abstract class Greeter(greeting: String, msg: String) {
    def sayHello() = greeting + " " + msg
  }

// FIXME:
// add subclasses MorningGreeter, DefaultGreeter, SimpleGreeter
  
  //class MorningGreeter(greeting: String, msg: String) extends Greeter(greeting,msg)
  class MorningGreeter extends Greeter("God morning!", "Have fun with Scala.")  
  class DefaultGreeter(greeting: String, msg: String) extends Greeter(greeting,msg)
  class SimpleGreeter(greeting: String, msg: String) extends Greeter(greeting,msg)

// TIP:
// Scala does not require every public class to be declared
// in a file of the same name. Organizationally, it makes sense
// for all of these trivial classes to be contained within the same file.
}

