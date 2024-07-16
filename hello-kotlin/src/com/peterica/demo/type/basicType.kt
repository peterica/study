package com.peterica.demo.type
/*
    [Kotlin] 데이터 타입
    https://peterica.tistory.com/652
 */

// 기본 데이터 타입
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


// Numbers
123          // 10진수 (Int)
123L         // 10진수 (Long)
0x0F         // 16진수
0b10111100   // 2진수
123.4        // 실수 (Double)
1.23e2       // 실수 (Double)
123.4f       // 실수 (Float)
1.23e2       // 실수 (Float)
12_345_678   // 자리 구분

// 밑줄은 무시되어 밑줄을 사용하여 숫자 상수를 더 읽기 쉽게 만들 수 있습니다.
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

// Booleans
val myTrue: Boolean = true
val myFalse: Boolean = false
val boolNull: Boolean? = null

println(myTrue || myFalse)
// true
println(myTrue && myFalse)
// false
println(!myTrue)
// false
println(boolNull)
// null


// Characters
'A'

// 이스케이프 시퀀스
//\t 		// 탭
//\b 		// 백스페이스
//\n 		// 새 줄(LF)
//\r 		// 캐리지 리턴(CR)
//\' 		// 작은따옴표
//\" 		// 큰따옴표
//\\ 		// 백슬래시
//\$ 		// 달러 표시
//\uFF00  	// Unicode 문자
//
//println('\n') // 새 줄이 출력된다.
//println('\uFF00') // ＀


// 형변환
//123.toString()        // Int에서 String으로 변환
//"123".toInt()         // String을 Int로 변환
//"3.14".toInt()        // String을 Int로 변환 (변환 할 수 없는 경우는 null)
//1.23.toInt()          // Double에서 Int로 변환 (잘라 내기)
//1.23.roundToInt()     // Double에서 Int로 변환 (반올림)


// String templates
var a = 1
// simple name in template:
val s1 = "a is $a"

a = 2
// arbitrary expression in template:
val s2 = "${s1.replace("is", "was")}, but now is $a"


//String formatiting
// 0추가
println(String.format("%07d", 31416))
// 0031416

// 소수점 자리
println(String.format("%+.4f", 3.141592))
// +3.1416

// 대문자 출력
println(String.format("%S %S", "hello", "world"))
// HELLO WORLD


// 여러 줄 String
val text = """
    for (c in "foo")
        print(c)
"""


// Range
// Iterate
for (x in 1..5) print(x)		//12345
for (x in 1.rangeTo(5)) print(x)	//12345
for (x in 1..10 step 2) print(x)	//13579
for (x in 9 downTo 0 step 3) print(x)	//9630


