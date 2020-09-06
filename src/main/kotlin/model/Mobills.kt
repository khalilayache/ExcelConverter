package model

import extensions.printErr
import java.nio.file.Path

class Mobills(private val filesPath: List<Path>) {

    companion object {
        val NAME = "Mobills"
    }

    private val headers: List<Header> = listOf(
        HeaderDict.DATE(),
        HeaderDict.DESCRIPTION(),
        HeaderDict.VALUE(),
        HeaderDict.ACCOUNT(),
        HeaderDict.CATEGORY()
    )

    private sealed class HeaderDict(override val text: String) : Header(text) {
        class DATE(override val text: String = "Data") : HeaderDict(text)
        class DESCRIPTION(override val text: String = "Descrição") : HeaderDict(text)
        class CATEGORY(override val text: String = "Categoria") : HeaderDict(text)
        class VALUE(override val text: String = "Valor") : HeaderDict(text)
        class ACCOUNT(override val text: String = "Conta") : HeaderDict(text)
    }

    fun process() {
        printErr("Platform not supported yet!")
    }
}


