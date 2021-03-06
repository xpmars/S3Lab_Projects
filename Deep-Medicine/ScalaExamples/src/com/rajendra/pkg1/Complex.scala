package com.rajendra.pkg1

object Complex {
  def apply(re:Double, im:Double)={
    new Complex(re,im)
  }
  def add(a:Complex, b:Complex)={
    a+b
  }
  
  def main(args: Array[String]) {
    val a_ = Complex(1,2)
    val b_ = Complex(2,3)
    println(a_ + b_)
  }
}

class Complex(val re:Double, val im:Double){
  def +(b:Complex):Complex={
    new Complex(re + b.re, im+b.im)
  }
  def -(b:Complex):Complex={
    new Complex(re-b.re, im-b.im)
  }
  
  override def toString:String={
    println(re+"--"+im)
    (re, im) match{
      case(0,0)=> "0"
      case(0,im)=> im+"i"
      case(re,0)=> re+""
      case(re,im)=>re+"+"+im+"i"
    }
  }  
}










