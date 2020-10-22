package gelatinous
package site

import java.nio.file.Files
import scala.jdk.CollectionConverters._

object DemoCollection extends ArticleCollection[Demo] {
  val sourceDir: String = Site.sourceDir ++ "demos/"
  val baseRoute: String = "demos/"
  val indexPage: Index = DemoIndex
  val demos: Iterator[Demo] = Files
    .list(sourcePath)
    .iterator
    .asScala
    .map(file => createArticle(Files.readAllLines(file).asScala.toList))
  def createArticle(lines: List[String]): Demo = {
    val (metadata, _, summary) = new MarkdownParser().parse(lines)
    Demo(metadata, summary)
  }
}
