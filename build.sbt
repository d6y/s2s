name := "s2s"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Xlint",
  "-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

libraryDependencies ++= cats ++ logging ++ excel ++ parboiled //++ testing

lazy val parboiled = Seq("org.parboiled" %% "parboiled" % "2.1.0")

lazy val logging = Seq( "ch.qos.logback" %  "logback-classic" % "1.1.3")

lazy val excel = Seq(
  "org.apache.poi" %  "poi"       % "3.12",
  "org.apache.poi" %  "poi-ooxml" % "3.12"
)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

val algebraVersion = "0.2.0-SNAPSHOT"
val catsVersion    = "0.1.0-SNAPSHOT"

lazy val cats = Seq(
  "org.spire-math" %% "algebra"     % algebraVersion,
  "org.spire-math" %% "algebra-std" % algebraVersion,
  "org.spire-math" %% "cats-core"   % catsVersion,
  "org.spire-math" %% "cats-std"    % catsVersion
)
