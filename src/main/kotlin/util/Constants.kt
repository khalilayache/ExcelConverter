package util

import java.nio.file.Path
import java.nio.file.Paths

object ENVIRONMENT{
    private const val PATH_TO_FILES_TO_CONVERT = "/to-convert/"
    private const val PATH_TO_FILES_CONVERTED = "/converted/"
    private val PROJECT_PATH = Paths.get("").toAbsolutePath().toString()

    val RESOURCE_OF_FILES_TO_CONVERT: Path = Paths.get(PROJECT_PATH, PATH_TO_FILES_TO_CONVERT)
    val RESOURCE_OF_CONVERTED_FILES: Path = Paths.get(PROJECT_PATH, PATH_TO_FILES_CONVERTED)
    val CONVERTED_FILES_PATH = "${PROJECT_PATH}/$PATH_TO_FILES_CONVERTED"
}