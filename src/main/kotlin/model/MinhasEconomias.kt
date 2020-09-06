package model

import extensions.getText
import model.MinhasEconomias.HeadersDict.*
import org.apache.poi.ss.usermodel.Cell

class MinhasEconomias {

    companion object {
        const val NAME = "Minhas Economias"
    }

    var headers: List<Any> = listOf()

    private sealed class HeadersDict(override val text: String) : Header(text) {
        class DATE(override val text: String = "Data Ocorrência") : HeadersDict(text)
        class DESCRIPTION(override val text: String = "Descrição") : HeadersDict(text)
        class VALUE(override val text: String = "Valor") : HeadersDict(text)
        class CATEGORY(override val text: String = "Categoria") : HeadersDict(text)
        class ACCOUNT(override val text: String = "Conta") : HeadersDict(text)
    }

    fun toAccount(cells: MutableIterator<Cell>): Account {
        var date = 0.0
        var description = ""
        var value = 0.0
        var category = ""
        var account = ""

        cells.asSequence().forEachIndexed { index, cell ->
            when (index) {
                headers.indexOf(DATE().text) -> {
                    date = cell.getText() as Double
                }
                headers.indexOf(DESCRIPTION().text) -> {
                    description = cell.getText() as String
                }
                headers.indexOf(VALUE().text) -> {
                    value = cell.getText() as Double
                }
                headers.indexOf(CATEGORY().text) -> {
                    category = cell.getText() as String
                }
                headers.indexOf(ACCOUNT().text) -> {
                    account = cell.getText() as String
                }
            }
        }

        return Account(account, mutableListOf(Transaction(date, description, value, category)))
    }
}