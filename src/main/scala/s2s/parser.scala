package s2s

import org.parboiled2._
import CharPredicate.AlphaNum

sealed trait Expr extends Product with Serializable
final case class CellRef(get: String) extends Expr
final case class Func(name: String, left: Expr, right: Expr) extends Expr

// This is going to need:
// - better understanding of grammar and format (e.g., of cell references)
// - a ton of little unit tests
class FormulaParser(val input: ParserInput) extends Parser {

  def FORMULA = rule { EXPR ~ EOI }

  def EXPR: Rule1[Expr] = rule {
    FUNC_APPLY | CELL_REF | INFIX_OP
  }

  def CELL_REF = rule {
    capture(oneOrMore(AlphaNum)) ~> CellRef
  }

  def FUNC_APPLY = rule {
    (FUNC_NAME ~ '(' ~ CELL_REF ~ ',' ~ CELL_REF ~ ')') ~> Func
  }

  def INFIX_OP = rule {
    (EXPR ~ OP ~ EXPR) ~> ( (l,o,r) => Func(o,l,r) )
  }

  def FUNC_NAME = rule {
    capture(oneOrMore(AlphaNum))
  }

  def OP = rule { capture("+") }

}