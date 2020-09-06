package program

import exceptions.InvalidPlatformException
import extensions.printErr
import model.Mobills
import model.Organizze
import model.Platform
import util.ENVIRONMENT
import util.ENVIRONMENT.RESOURCE_OF_FILES_TO_CONVERT
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    try {
        when (initPlatform()) {
            Platform.ORGANIZZE -> {
                convertToOrganizze()
            }
            Platform.MOBILLS -> {
                convertToMobills()
            }
        }
    } catch (e: InvalidPlatformException) {
        println(e.message)
    }
}

fun convertToMobills() {
    val filesPath = getPathOfFilesToConvert()
    if (filesPath.isNotEmpty()) {
        Mobills(filesPath).process()
    } else {
        noSheetsToConvertError()
    }
}

fun convertToOrganizze() {
    val filesPath = getPathOfFilesToConvert()
    if (filesPath.isNotEmpty()) {
        Organizze(filesPath).process()
    } else {
        noSheetsToConvertError()
    }
}

private fun getPathOfFilesToConvert(): List<Path> {
    val filesPath = arrayListOf<Path>()
    Files.walk(RESOURCE_OF_FILES_TO_CONVERT)
        .filter { item -> Files.isRegularFile(item) }
        .filter { item -> item.toString().endsWith(".xls") }
        .forEach { item -> filesPath.add(item) }

    return filesPath
}

private fun initPlatform(): Platform {
    println("From which platform do you want convert the sheets?")
    Platform.printAllIdsAndNames()

    readLine()?.toInt()?.let { platformId ->
        val selectedPlatform = Platform.getPlatformById(platformId)
        selectedPlatform?.let { platform ->
            return platform
        }
    }

    throw InvalidPlatformException()
}

private fun noSheetsToConvertError() {
    printErr("No sheets to convert found")
}