package com.peterica.demo.basicGrammar

/*
    [Kotlin] 기본 문법
    https://peterica.tistory.com/651
 */

// 패키지 정의 및 Import
import kotlin.text.*

// 프로그램 Init
fun main() {
    println("Hello, world!!!")
}

// Print & Println
fun print() {
    // 표준출력
    print("Hello ")
    print("world!!\n")
    // 줄바꿈 출력
    println("Hello, world!!!")
}

// 주석
// 한줄 주석
/* 주석의 시작과
~~~
끝*/

// function
fun sum(a: Int, b: Int): Int {
    return a+b
}

fun main2() {
    print("sum of 3 and 5 is ")
    println(sum(3, 5))
}

fun sum2(a: Int, b: Int) = a + b

fun main3() {
    println("sum of 19 and 23 is ${sum2(19, 23)}")
}

// 문자열 템플릿
class Test {
    fun printStr():String{
        return "test"
    }
}

fun main4(args:Array<String>) {
    val num1 = 34
    println("num is $num1")
    val test = Test() // new는 생략가능
    println("test function ${test.printStr()}")
}

// 문자열과 숫자 선언
fun stringInit(){
    // 변하지 않는 수는 val
    val x: Int = 5

    // 변할 수 있는 수는 var
    var y: Int = 5
    y += 1
    print(y)

    // 변수의 유형을 추론하여 유형 생략가능
    val a = 5
    var b = "five"

    // 선언 후 값 초기화
    val c: Int
    c = 3
}

