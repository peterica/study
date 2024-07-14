package example.com

/**
 * [Kotlin] MutableList 기능 설명
 * https://peterica.tistory.com/701
 */

fun mutableList생성(){
    val list1 = mutableListOf(1, 2, 3)
    val list2 = mutableListOf<String>()
    val list3 = MutableList(5) { it * 2 } // [0,2,4,6,8]
    val list4 = (1..50).toMutableList()
    val list5 = MutableList<Int>(5, { i -> i}) // [0,1,2,3,4]
}

fun mutableList추가(){
    val list = mutableListOf(1, 2, 3)
    list.add(4) // [1, 2, 3, 4]
    list.add(0, 0) // [0, 1, 2, 3, 4]
    list.addAll(listOf(5, 6)) // [0, 1, 2, 3, 4, 5, 6]
}

fun mutableList제거(){
    val list = mutableListOf(1, 2, 3, 4, 5)
    list.remove(3) // [1, 2, 4, 5]
    list.removeAt(0) // [2, 4, 5]
    list.clear() // []
}

fun mutableList수정(){
    val list = mutableListOf(1, 2, 3)
    list[1] = 20 // [1, 20, 3]
}

fun mutableList루프(){
    val list = mutableListOf(1, 2, 3)
    for (item in list) {
        println(item)
    }

    list.forEach { println(it) }
}

fun mutableListUtil(){
    val list = mutableListOf(1, 2, 3, 4, 5)
    list.retainAll(listOf(1, 2, 3)) // [1, 2, 3]
    val list2 = mutableListOf(1, 2, 3, 4, 5)
    list2.removeAll(listOf(1, 2)) // [3, 4, 5]
    val subList = list.subList(0, 2) // [1, 2]
}

fun test(){
    val list1 = mutableListOf(1, 2, 3)
    list1.add(4) // 가능
    // list1 = mutableListOf(5, 6) // 컴파일 에러

    var list2 = mutableListOf(1, 2, 3)
    list2.add(4) // 가능
    list2 = mutableListOf(5, 6) // 가능
}

fun main(){
    test()
}


