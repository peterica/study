package com.peterica.demo

class Test {
    fun printStr():String{
        return "test"
    }
}

fun main(args:Array<String>) {
    val num1 = 34
    println("num is $num1")
    val test = Test()
    println("test function ${test.printStr()}")
}