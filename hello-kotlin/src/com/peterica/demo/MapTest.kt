package com.peterica.demo

/**
 * [Kotlin] Map 다양한 사용법
 * https://peterica.tistory.com/391
 */

fun map생성(){

    // 불변 Map
    val immutableMap = mapOf("key1" to "value1", "key2" to "value2")

    // 가변 Map
    val mutableMap = mutableMapOf("key1" to "value1", "key2" to "value2")

    // 빈 Map
    val emptyMap = emptyMap<String, String>()
}

fun map제거(){
    // 생성
    val map = mutableMapOf("Chocolate" to 14, "Strawberry" to 9)

    // remove
    map.remove("Strawberry")

    // -=
    map -= "Chocolate"
}

fun map추가(){

    val mutableMap = mutableMapOf<String, Int>()
    // 단건 추가
    mutableMap["key1"] = 1
    mutableMap.put("key2", 2)
    mutableMap += "key3" to 3

    // 복수 추가
    mutableMap.putAll(setOf("Strawberry" to 3, "Rocky Road" to 2))
    mutableMap += mapOf("Maple Walnut" to 1, "Mint Chocolate" to 4)
}

fun map값가져오기(){

    val map = mapOf("key1" to "value1", "key2" to "value2")
    val defaultValue = "defaultValue"

    // 대괄호
    val value = map["key"]

    // getValue 함수
    val value1 = map.getValue("key") // 키가 없으면 예외 발생

    // getOrElse 함수
    val value2 = map.getOrElse("key") { defaultValue }

    // getOrDefault 함수
    val value3 = map.getOrDefault("key", defaultValue)

    // for
    for ((key, value) in map) {
        println("$key = $value")
    }

    // forEach
    map.forEach { (key, value) ->
        println("$key = $value")
    }
}

fun map루프문(){

    val map = mapOf("key1" to "value1", "key2" to "value2")

    // for
    for ((key, value) in map) {
        println("$key = $value")
    }

    // forEach
    map.forEach { (key, value) ->
        println("$key = $value")
    }
}

fun map필터링(){
    val map = mapOf("key1" to 1, "key2" to 2)

    // filter
    val filteredMap = map.filter { (key, value) -> key.length > 3 && value > 10 }

    // filterKeys와 filterValues
    val filteredByKeys = map.filterKeys { it.length > 3 }
    val filteredByValues = map.filterValues { it > 10 }
}

fun map변환(){
    val map = mapOf("key1" to 1, "key2" to 2)

    // map
    val transformedMap = map.map { (key, value) -> key to value.toString() }.toMap()

    // mapKeys와 mapValues
    val transformedKeys = map.mapKeys { it.key.toUpperCase() }
    val transformedValues = map.mapValues { it.value * 2 }
}

fun map데이터가공() {

    class shipment(
        var flavor: String = "",
        var quantity: Int = 0
    )

    // kotlin API 방법
    val shipments = listOf(
        shipment("Chocolate", 3),
        shipment("Strawberry", 7),
        shipment("Vanilla", 5),
        shipment("Chocolate", 5),
        shipment("Vanilla", 1),
        shipment("Rocky Road", 10),
    )

    val shipmentInventory = mutableMapOf<String, Int>()

    // for문을 이용하는 방식
    for (shipment in shipments){
        val currentQuantity = shipmentInventory[shipment.flavor] ?: 0
        shipmentInventory[shipment.flavor] = currentQuantity + shipment.quantity
    }

    // groupBy 이용하는 방식 비교
    val shipmenInventory2 = shipments
        .groupBy({ it.flavor }, { it.quantity }) // 데이터 가공할 수 있다.
        .mapValues { it.value.sum() }  // 목록을 단일 합계로 줄인다.
}

fun main(){
    map데이터가공()
}
