package com.rajendra.pkg1

object DuckTyping {
  class Duck{
    def quack = "Quack!"
  }
  class Frog{
    def quack="Quaack Quaack!"
  }
  def doQuack(duck:{def quack:String})={
    duck.quack
  }
}


