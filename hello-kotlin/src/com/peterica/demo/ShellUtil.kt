package com.peterica.demo

object ShellUtil {

    /**
     * cp 명령을 사용하여 파일을 복사한다.
     * 원본 파일 경로와 대상 경로를 인자로 받는다.
     * -r: 디렉토리 복사(없으면 생성)
      */
    fun copyFile(source: String, destination: String): Boolean {
        val command = "cp  $source $destination"
        println(command)
        return executeCommand(command)
    }

    /**
     * zip 명령을 사용하여 파일을 압축한다.
     * 압축할 파일 경로와 압축 결과 파일 경로를 인자로 받는다.
     */
    fun compressFile(source: String, destination: String): Boolean {
        val command = "zip -r $destination $source"
        println(command)
        return executeCommand(command)
    }

    /**
     * 압축 파일 무결성 검사를 진행한다.
     * 압축된 파일 경로를 인자로 받는다.
     * $ unzip -t file.zip
     * Archive:  file.zip
     *     testing: source/CODE1/test1.png   OK
     * No errors detected in compressed data of file.zip.
     */
    fun checkZipFile(destination: String): Boolean {
        val command = "unzip -t $destination"
        println(command)
        return executeCommand(command)
    }

    /**
     * 실제 shell 명령을 실행하는 private 함수이다.
     * Runtime.getRuntime().exec()를 사용하여 명령을 실행한다.
     * 명령 실행 결과를 boolean 값으로 반환한다 (성공: true, 실패: false).
     */
    private fun executeCommand(command: String): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(command)
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (e: Exception) {
            println("Error executing command: ${e.message}")
            false
        }
    }
}

fun main() {
    val sourceFile = "source/CODE1/test1.png"
    val destinationFile = "source/CODE1/test3.png"
    val compressedFile = "compressed/file.zip"

    // 파일 복사
    if (ShellUtil.copyFile(sourceFile, destinationFile)) {
        println("파일 복사 성공")
    } else {
        println("파일 복사 실패")
    }

    // 파일 압축
    if (ShellUtil.compressFile(sourceFile, compressedFile)) {
        println("파일 압축 성공")
    } else {
        println("파일 압축 실패")
    }

    // 압축 파일 무결성 검사
    if (ShellUtil.checkZipFile(compressedFile)) {
        println("압축 무결성 검사 성공")
    } else {
        println("압축 무결성 검사 실패")
    }
}