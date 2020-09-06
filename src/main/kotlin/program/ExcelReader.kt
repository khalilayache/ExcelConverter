package program

import extensions.getText
import model.Account
import model.MinhasEconomias
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import java.io.File
import java.io.FileInputStream
import java.nio.file.Path

class ExcelReader {

    private val accounts: HashMap<String, Account> = hashMapOf()

    fun readExcelFrom(filePath: Path): HashMap<String, Account> {
        val excelFile = FileInputStream(File(filePath.toString()))
        val workbook = HSSFWorkbook(excelFile)
        val sheet = workbook.getSheetAt(0)
        val rows = sheet.iterator()

        val minhasEconomias = MinhasEconomias()

        rows.asSequence().forEachIndexed { index, row ->
            val cells = row.iterator()
            if (index == 0) {
                minhasEconomias.headers = getHeaders(cells)
                return@forEachIndexed
            }
            val account = minhasEconomias.toAccount(cells)

            if (!accounts.containsKey(account.name)) {
                accounts[account.name] = account
            } else {
                accounts[account.name]?.transactions?.add(account.transactions.first())
            }
        }

        return accounts
    }

    private fun getHeaders(cells: MutableIterator<Cell>): List<Any> {
        val headers = arrayListOf<Any>()
        cells.forEach { cell ->
            headers.add(cell.getText())
        }
        return headers
    }
}
