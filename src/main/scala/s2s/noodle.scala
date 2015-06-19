package s2s

import org.apache.poi.ss.usermodel
import usermodel.{Row, Sheet, WorkbookFactory}

import java.nio.file.FileSystems
import java.io.FileInputStream

import Cells._

object Noodle {

  val path = FileSystems.getDefault().getPath("src/test/resources/simple-sum.xlsx")
  val in = new FileInputStream(path.toFile)
  val sheet = WorkbookFactory.create(in).getSheetAt(0)

  val cellA3: Cell = sheet.getRow(2).getCell(0).asCell
  in.close
}

object Main extends App {
  println(Noodle.cellA3)

  Noodle.cellA3 match {
    case FormulaCell(f, v) =>
      println(s"Parsing $f ...")
      val tree = new FormulaParser(f).FORMULA.run()
      println(tree)
    case _ => println("That wasn't a formula")
  }
}