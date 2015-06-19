package s2s

import org.apache.poi.ss.usermodel
import usermodel.{Row, Sheet, WorkbookFactory}

import java.nio.file.FileSystems
import java.io.FileInputStream

object Noodle {

  val path = FileSystems.getDefault().getPath("src/test/resources/simple-sum.xlsx")
  val in = new FileInputStream(path.toFile)
  val sheet = WorkbookFactory.create(in).getSheetAt(0)

  import Cells._
  val cellA3: Cell = sheet.getRow(2).getCell(0)

  in.close
}

object Main extends App {
  println(Noodle.cellA3)
}