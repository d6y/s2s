# Spreadsheet to Scala

## Outline

Read a spreadsheet, and for some specified set of cells...

- convert cell formulas to functions.
- convert cells without formulas to constants.
- convert the current value of a formula in the spreadsheet to a unit test.

 For example:

 ```
       A                     B              C
 1          11
 2          13
 3  =SUM(A1,A2)
 ```

 Should be converted to something along the lines of...

 ```
 lazy val cellA1 = 11
 lazy val cellA2 = 13
 def cellA3(x: Int, y: Int) = x + y

 asssert( cellA3(cellA1,cellA2) === 24 )
```

## Approach

 - Parse a formula into a tree
 - Chase references and include them
 - Generate code

## Challenges

- Excel formula syntax not uniform
- Functions take values, arrays, ranges...
- Differences between Scala and Excel formula behaviour
- Cyclic references˜
- Infering/converting types
- References across worksheets
- How does Excel treat dates and timezones?
- What are array formula?
- Spreadsheets other than Excel
- ...

 We'll do some simple things, and see what cases present themselves.

# Current Status

Does almost nothing:

```
s2s$ sbt run
[info] Loading global plugins from /Users/richard/.sbt/0.13/plugins
[info] Set current project to s2s (in build file:/Users/richard/Developer/s2s/)
[info] Compiling 1 Scala source to /Users/richard/Developer/s2s/target/scala-2.11/classes...
[info] Running s2s.Main
FormulaCell(SUM(A1,A2),NumericCell(24.0))
[success] Total time: 3 s, completed 19-Jun-2015 15:46:16
```