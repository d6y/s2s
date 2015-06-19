package s2s

import org.apache.poi.ss.usermodel
import usermodel.DateUtil.isCellDateFormatted
import java.time.{ZoneId, LocalDateTime}

object Cells {
  sealed trait Cell
  final case class BlankCell() extends Cell
  final case class BooleanCell(get: Boolean) extends Cell
  final case class ErrorCell(errorCode: Byte) extends Cell
  final case class FormulaCell(get: String, cachedResult: Cell) extends Cell
  final case class NumericCell(get: Double) extends Cell
  final case class StringCell (get: String) extends Cell
  final case class DateCell(get: LocalDateTime) extends Cell

  implicit class CellOps(c: usermodel.Cell) {
    def asCell: Cell = c.getCellType match {
      case _ if isCellDateFormatted(c)      => DateCell(LocalDateTime.ofInstant(c.getDateCellValue.toInstant(), ZoneId.systemDefault))
      case usermodel.Cell.CELL_TYPE_BLANK   => BlankCell()
      case usermodel.Cell.CELL_TYPE_BOOLEAN => BooleanCell(c.getBooleanCellValue)
      case usermodel.Cell.CELL_TYPE_ERROR   => ErrorCell(c.getErrorCellValue)
      case usermodel.Cell.CELL_TYPE_NUMERIC => NumericCell(c.getNumericCellValue)
      case usermodel.Cell.CELL_TYPE_STRING  => StringCell(c.getStringCellValue)
      case usermodel.Cell.CELL_TYPE_FORMULA =>
        if (c.getCachedFormulaResultType == usermodel.Cell.CELL_TYPE_NUMERIC)
          FormulaCell(c.getCellFormula, NumericCell(c.getNumericCellValue))
        else
          FormulaCell(c.getCellFormula, StringCell(c.getStringCellValue))
      case n => throw new IllegalArgumentException(s"Unrecognized cell type: $n")
      // getCellComment could be useful
    }
  }

  implicit def wrapCell(c: usermodel.Cell): Cell = CellOps(c).asCell
}