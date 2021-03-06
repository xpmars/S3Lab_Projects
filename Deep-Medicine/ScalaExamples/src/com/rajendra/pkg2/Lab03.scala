package com.rajendra.pkg2

object Lab03 {
  abstract class Duck(val fly: () => String, val quack: () => String) {
    def display(): String

    def swim() {
      println("All ducks float, even decoys!")
    }
  }

  // FIXME
  // extend Duck and use FlyBehaviors.fly and a function literal returning "quack quack"
  // in the call of the super constructor
  class MallardDuck extends Duck(FlyBehaviors.fly,FlyBehaviors.flyNoWay){ // FIXME...
    override def display() = {
      "I'm a real Mallard duck"
    }
  }

  // FIXME
  // define subclass ModelDuck that takes fly and quack functions in it's
  // primary constructor.
  class ModelDuck(fly:()=>String,quack:()=>String) {}
}

object FlyBehaviors {
  def flyNoWay() = {
    "I can't fly"
  }

  def fly() = {
    "I'm flying"
  }

  def flyRocketPowered() = {
    "I'm flying with a rocket";
  }
}