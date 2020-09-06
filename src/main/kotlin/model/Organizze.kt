package model

import extensions.asDate
import program.ExcelReader
import program.ExcelWriter
import util.ENVIRONMENT.CONVERTED_FILES_PATH
import java.io.File
import java.nio.file.Path
import kotlin.math.roundToInt

class Organizze(private val filesPath: List<Path>) {

    companion object {
        const val NAME = "Organizze"
    }

    private val accountsMerge: HashMap<String, Account> = hashMapOf()

    private val headers: List<Header> = listOf(
        HeadersDict.DATE(),
        HeadersDict.DESCRIPTION(),
        HeadersDict.CATEGORY(),
        HeadersDict.VALUE(),
        HeadersDict.SITUATION()
    )

    private sealed class HeadersDict(override val text: String) : Header(text) {
        class DATE(override val text: String = "Data") : HeadersDict(text)
        class DESCRIPTION(override val text: String = "Descrição") : HeadersDict(text)
        class CATEGORY(override val text: String = "Categoria") : HeadersDict(text)
        class VALUE(override val text: String = "Valor") : HeadersDict(text)
        class SITUATION(override val text: String = "Situação") : HeadersDict(text)
    }

    fun process() {
        println(System.currentTimeMillis())
        filesPath.forEach { filePath ->
            val accountsFromFile = ExcelReader().readExcelFrom(filePath)
            accountsFromFile.forEach { (accountName, account) ->
                if (accountsMerge.containsKey(accountName)) {
                    accountsMerge[accountName]?.transactions?.addAll(account.transactions)
                } else {
                    accountsMerge[accountName] = account
                }
            }
        }
        if (accountsMerge.isNotEmpty()) {
            createConvertedFolder()
        }
        accountsMerge.forEach { (accountName, account) ->
            writeExcel(account)
        }
        println(System.currentTimeMillis())
    }

    private fun createConvertedFolder() {
        File(CONVERTED_FILES_PATH).mkdirs()
    }

    private fun writeExcel(account: Account) {
        val excelWriter = ExcelWriter()
            .setHeaderStyle()
            .setHeaders(headers)

        fillCells(excelWriter, account)
            .save(Platform.ORGANIZZE.getName(), account.name)
    }

    private fun fillCells(excelWriter: ExcelWriter, account: Account): ExcelWriter {
        account.transactions.forEachIndexed { index, transaction ->
            val rowInfo: HashMap<Int, Any> = hashMapOf()
            headers.forEachIndexed { headerIndex, header ->
                when (header.text) {
                    HeadersDict.DATE().text -> rowInfo[headerIndex] = transaction.date.asDate()
                    HeadersDict.DESCRIPTION().text -> rowInfo[headerIndex] = transaction.description
                    HeadersDict.VALUE().text -> rowInfo[headerIndex] = transaction.value
                    HeadersDict.CATEGORY().text -> rowInfo[headerIndex] = transaction.category
                }
            }

            excelWriter.writeCells(index + 1, rowInfo)
        }
        return excelWriter
    }
}
