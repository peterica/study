import java.io.BufferedReader
import java.io.InputStreamReader

fun runCommand(command: String) {

    try {
        val process = Runtime.getRuntime().exec(command)

        // 명령어 실행 결과 읽기
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }

        // 에러 스트림 읽기
        val errorReader = BufferedReader(InputStreamReader(process.errorStream))
        while (errorReader.readLine().also { line = it } != null) {
            System.err.println(line)
        }

        // 프로세스 종료 대기 및 종료 코드 확인
        val exitCode = process.waitFor()
        println("명령어 실행 완료. 종료 코드: $exitCode")

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main() {
//    println("\n######################")
//    println("# 정상 shell 명령어\n")
//    runCommand("ls -l") // 실행할 shell 명령어

//    println("\n######################")
//    println("# error shell 명령어\n")
//    runCommand("error") // 잘못된 shell 명령어)

    println("\n######################")
    println("# fileZipTest\n")
    runCommand("sh fileZipTest.sh temp test.zip") // 잘못된 shell 명령어

    println("\n######################")
    println("# fileZipTest error\n")
    runCommand("sh fileZipTest.sh wrong_temp test.zip") // 압축할 폴더와 압축명을 인자로 전달
}
