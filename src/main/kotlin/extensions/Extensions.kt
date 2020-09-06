package extensions

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import java.util.*
import kotlin.math.roundToInt

fun printErr(message: Any) = System.err.print(message)

fun Cell.getText(): Any {
    return when (this.cellTypeEnum) {
        CellType.STRING -> this.stringCellValue
        CellType.NUMERIC -> this.numericCellValue
        else -> ""
    }
}

fun Double.asDate(): Date {
    return DateUtil.getJavaDate(this)
}