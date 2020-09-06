package sample

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream

fun main() {
    val excelFile = FileInputStream(File("sample.customers.xlsx"))
    val workbook = XSSFWorkbook(excelFile)

    val sheet = workbook.getSheet("Customers")
    val rows = sheet.iterator()
    while (rows.hasNext()) {
        val currentRow = rows.next()
        val cellsInRow = currentRow.iterator()
        while (cellsInRow.hasNext()) {
            val currentCell = cellsInRow.next()
            if (currentCell.cellTypeEnum === CellType.STRING) {
                print("${currentCell.stringCellValue}\t| ")
            } else if (currentCell.cellTypeEnum === CellType.NUMERIC) {
                print("${currentCell.numericCellValue}\t| ")
            }
        }
        println()
    }

    workbook.close()
    excelFile.close()
}
