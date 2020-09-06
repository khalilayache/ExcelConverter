package program

import model.Header
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFFont
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.IndexedColors
import util.ENVIRONMENT.RESOURCE_OF_CONVERTED_FILES
import java.io.File
import java.io.FileOutputStream
import java.util.*

class ExcelWriter {
    private val workbook: HSSFWorkbook by lazy { HSSFWorkbook() }
    private val headerCellStyle: HSSFCellStyle by lazy { workbook.createCellStyle() }

    private val sheet: HSSFSheet by lazy { workbook.createSheet("Transactions") }

    fun setHeaderStyle(): ExcelWriter {
        val headerFont: HSSFFont = workbook.createFont()
        headerFont.bold = true
        headerFont.color = IndexedColors.BLACK.getIndex()
        headerCellStyle.setFont(headerFont)

        return this
    }

    fun setHeaders(headers: List<Header>): ExcelWriter {
        val headerRow = sheet.createRow(0)

        headers.forEachIndexed { index, header ->
            val cell = headerRow.createCell(index)
            cell.setCellValue(header.text)
            cell.setCellStyle(headerCellStyle)
        }

        return this
    }

    fun writeCells(rowNumber: Int, rowInfo: HashMap<Int, Any>): ExcelWriter {
        val row = sheet.createRow(rowNumber)
        rowInfo.forEach { (index, value) ->
            val cell = row.createCell(index)
            when (value) {
                is String -> cell.setCellValue(value)
                is Double -> cell.setCellValue(value)
                is Date -> {
                    val cellStyle = workbook.createCellStyle()
                    cellStyle.dataFormat = workbook.creationHelper.createDataFormat().getFormat("mm/dd/yy")
                    cell.setCellValue(value)
                    cell.setCellStyle(cellStyle)
                }
            }
        }

        return this
    }

    fun save(platform: String, accountName: String) {
        val fileOut =
            FileOutputStream(File(RESOURCE_OF_CONVERTED_FILES.toString() + "/${platform}_${accountName}_.xls"))
        workbook.write(fileOut)
        fileOut.close()
        workbook.close()
    }
}