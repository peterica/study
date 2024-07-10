package com.peterica.demo

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream


fun main() {
    val codeFileMap = mapOf(
        "CODE1" to listOf("file1.txt", "file2.txt"),
        "CODE2" to listOf("file3.txt", "file4.txt"),
        "CODE3" to listOf("file5.txt", "file6.txt")
    )

    var randomPath = "temp"+(0..999999).random().toString()
    val baseDir = File(randomPath)
    baseDir.mkdir()

    // 코드명으로 폴더 생성 및 파일 저장
    codeFileMap.forEach { (code, files) ->
        val codeDir = File(baseDir, code)
        codeDir.mkdir()
        files.forEach { fileName ->
            File(codeDir, fileName).createNewFile()
        }
    }

    // 폴더들을 압축
    val zipFile = File(baseDir, "compressed_folders.zip")
    ZipOutputStream(FileOutputStream(zipFile)).use { zipOut ->
        baseDir.listFiles()?.filter { it.isDirectory }?.forEach { folder ->
            addFolderToZip(folder, folder.name, zipOut)
        }
    }

    println("폴더 생성 및 압축이 완료되었습니다.")

    // 월별 저장 공간
    // 파일 정리를 위해 월별 폴더를 분리
    var monthPath = "zipDirRoot"+ "yyyyMM".getTimeNow()
    var monthDir = File(monthPath)
    if (!monthDir.exists()) monthDir.mkdir()

    zipFile.copyTo(File(monthPath,"compressed_folders.zip"))
    println("압축파일 이동 완료")

    // temp 폴더 삭제
    if (deleteFolder(baseDir))
        println("temp 삭제 성공")
    else
        println("temp 삭제 실패")

    println("압축파일 이동 및 temp 삭제")

}

fun addFolderToZip(folder: File, folderName: String, zipOut: ZipOutputStream) {
    folder.listFiles()?.forEach { file ->
        if (file.isDirectory) {
            addFolderToZip(file, "$folderName/${file.name}", zipOut)
        } else {
            val entry = ZipEntry("$folderName/${file.name}")
            zipOut.putNextEntry(entry)
            FileInputStream(file).use { fileIn ->
                fileIn.copyTo(zipOut)
            }
            zipOut.closeEntry()
        }
    }
}

// Kotlin Extension을 이용한 날짜 포맷
fun String.getTimeNow(): String {
    return try {
        val date = Date(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat(this)
        simpleDateFormat.format(date)
    } catch (e: Exception) {
        println(e.message)
        ""
    }
}

// File 객체를 재쥐저긍로 삭제한다.
fun deleteFolder(folder: File): Boolean {
    if (folder.exists()) {
        val files = folder.listFiles()
        if (files != null) {
            for (file in files) {
                if (file.isDirectory) {
                    deleteFolder(file)
                } else {
                    file.delete()
                }
            }
        }
    }
    return folder.delete()
}
