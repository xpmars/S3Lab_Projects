/*package com.rajendra.pkg2

object Lab06 {
  def apply(host: String, port: Int) = new ServerConfiguration(host, port)
  def apply() = new ServerConfiguration("localhost", 8080)

  class Marker private (val color: String) {
    override def toString(): String = color
  }
  
  private val markers = Map(
    "red" -> new Marker("red"),
    "blue" -> new Marker("blue"),
    "green" -> new Marker("green"))  

  def primaryColors = "red, green, blue"

  // TIP
  // Any object that has an apply method can be called with '.apply' omitted
  // example:
  // class Array{
  //   def get(index:Int) = { ...some code to get from the array... }  
  //   def apply(index:Int) = get(index)
  // }
  // Then the following calls are essentially equivalent:
  // a.apply(7)
  // a(7)

  // FIXME
  // provide apply method for Marker
  def apply(index: Int) = markers.get(index)
  //def apply(color: String) = if (markers.contains(color)) markers(color) else null

  

  class ServerConfiguration(val host: String, val port: Int) {
    def url = "http://" + host + ":" + port
  }
}*/