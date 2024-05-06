package com.peterica.demo

fun main() {
    // 변하지 않는 변수
    val x: Int = 5

    // 변할 수 있는 변수
    var y: Int = 5
    y += 1
    print(y)

    // 변수 유형 추론
    val a = 5
    var b = "five"

    // 선언 후 값 초기화
    val c: Int
    c = 3

}