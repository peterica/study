package com.peterica.demo.type

var a: Boolean = true     // 논리 (true, false)

// Integer types
var b: Byte = 123         // 8 트 정수 (-128 ~ 127)
var c: Short = 123        // 16비트 정수 (-32768 ~ 32767)
var d: Int = 123          // 32비트 정수 (-2 31 ~ 2 31 -1)
var e: Long = 123L        // 64비트 정수 (-2 63 ~ 2 63 -1)

// Floating-point types
var f: Float = 12.3F      // 32비트 부동 소수점
var g: Double = 12.3      // 64비트 부동 소수점

// Characters
var h: Char = 'A'         // 문자 (글자 하나)
var i: String= "ABC"      // 문자열

// 밑줄은 무시되어 밑줄을 사용하여 숫자 상수를 더 읽기 쉽게 만들 수 있습니다.
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010
