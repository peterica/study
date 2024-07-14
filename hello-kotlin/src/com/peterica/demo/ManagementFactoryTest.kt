package com.peterica.demo

import java.lang.management.ManagementFactory

// [Kotlin] ManagementFactory을 이용한 JVM 모니터링 방법
// https://peterica.tistory.com/707

// 메모리 사용량 확인
fun getMemoryUsage() {
    val runtime = Runtime.getRuntime()
    val memoryMXBean = ManagementFactory.getMemoryMXBean()
    val heapMemoryUsage = memoryMXBean.heapMemoryUsage
    val nonHeapMemoryUsage = memoryMXBean.nonHeapMemoryUsage

    println("== 전체 메모리 사용량 ==")
    println("총 메모리: ${runtime.totalMemory() / 1024 / 1024} MB")
    println("사용 가능한 메모리: ${runtime.freeMemory() / 1024 / 1024} MB")
    println("사죵 중인 메모리: ${(runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024} MB")
    println("최대 메모리: ${runtime.maxMemory() / 1024 / 1024} MB")

    println("\n== 힙 메모리 사용량 ==")
    println("사용 중인 힙 메모리: ${heapMemoryUsage.used / 1024 / 1024} MB")
    println("최대 힙 메모리: ${heapMemoryUsage.max / 1024 / 1024} MB")

    println("\n== 논힙 메모리 사용량 ==")
    println("사용 중인 Non-Heap 메모리: ${nonHeapMemoryUsage.used / 1024 / 1024} MB")
    println("최대 Non-Heap 메모리: ${nonHeapMemoryUsage.max / 1024 / 1024} MB")
}

// 스레드 정보 확인
fun getThreadInfo() {
    val threadMXBean = ManagementFactory.getThreadMXBean()
    println("Total thread count: ${threadMXBean.threadCount}")
    println("Peak thread count: ${threadMXBean.peakThreadCount}")
    println("total Started thread count: ${threadMXBean.totalStartedThreadCount}")

    val threadIds = threadMXBean.allThreadIds.toList()
    println("all Thread Ids: ${threadIds.toString()}")
    println("get Thread Info: ${threadMXBean.getThreadInfo(threadIds.get(0))}")
}

//운영체제 정보 확인
fun getOSInfo() {
    val operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean()
    println("OS Name: ${operatingSystemMXBean.name}")
    println("OS Version: ${operatingSystemMXBean.version}")
    println("Available Processors: ${operatingSystemMXBean.availableProcessors}")
    println("system Load Average: ${operatingSystemMXBean.systemLoadAverage} minutes")
}

// 클래스 로딩 정보
fun getClassLoadingInfo() {
    val classLoadingMXBean = ManagementFactory.getClassLoadingMXBean()
    println("Total loaded class count: ${classLoadingMXBean.totalLoadedClassCount}")
    println("Loaded class count: ${classLoadingMXBean.loadedClassCount}")
    println("Unloaded class count: ${classLoadingMXBean.unloadedClassCount}")
}

// 가비지 컬렉션 정보
fun getGCInfo() {
    val gcMXBeans = ManagementFactory.getGarbageCollectorMXBeans()
    for (gcMXBean in gcMXBeans) {
        println("GC name: ${gcMXBean.name}")
        println("Collection count: ${gcMXBean.collectionCount}")
        println("Collection time: ${gcMXBean.collectionTime} ms")
    }
}

fun main() {
    println("\n######################")
    println("# 메모리 사용량 확인\n")
    getMemoryUsage()

    println("\n######################")
    println("# 스레드 정보 확인\n")
    getThreadInfo()

    println("\n######################")
    println("# 운영체제 정보 확인\n")
    getOSInfo()

    println("\n######################")
    println("# 클래스 로딩 정보\n")
    getClassLoadingInfo()

    println("\n######################")
    println("# 가비지 컬렉션 정보\n")
    getGCInfo()
}