package sample

import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

private val COLUMNS_NAME = listOf(HEAD.ID, HEAD.NAME, HEAD.ADDRESS, HEAD.AGE)

private val customers = listOf(
    Customer("1", "Jack Smith", "Massachusetts", 23),
    Customer("2", "Adam Johnson", "New York", 27),
    Customer("3", "Katherin Carter", "Washington DC", 26),
    Customer("4", "Jack London", "Nevada", 33),
    Customer("5", "Jason Bourne", "California", 36)
)

fun main() {
    val workbook = XSSFWorkbook()
    val createHelper = workbook.creationHelper
    val sheet = workbook.createSheet("Customers")

    val headerFont = workbook.createFont()
    headerFont.bold = true
    headerFont.color = IndexedColors.BLUE.getIndex()

    val headerCellStyle = workbook.createCellStyle()
    headerCellStyle.setFont(headerFont)

    // Row for Header
    val headerRow = sheet.createRow(0)

    // Header
    for (index in COLUMNS_NAME.indices) {
        val cell = headerRow.createCell(index)
        cell.setCellValue(COLUMNS_NAME[index].text)
        cell.cellStyle = headerCellStyle
    }

    // CellStyle for Age
    val ageCellStyle = workbook.createCellStyle()
    ageCellStyle.dataFormat = createHelper.createDataFormat().getFormat("#")

    var rowIdx = 1
    for (customer in customers) {
        val row = sheet.createRow(rowIdx++)
        row.createCell(HEAD.ID.index).setCellValue(customer.id)
        row.createCell(HEAD.NAME.index).setCellValue(customer.name)
        row.createCell(HEAD.ADDRESS.index).setCellValue(customer.address)
        val ageCell = row.createCell(3)
        ageCell.setCellValue(customer.age.toDouble())
        ageCell.cellStyle = ageCellStyle
    }

    val fileOut = FileOutputStream("customers_test_2.xlsx")
    workbook.write(fileOut)
    fileOut.close()
    workbook.close()
}