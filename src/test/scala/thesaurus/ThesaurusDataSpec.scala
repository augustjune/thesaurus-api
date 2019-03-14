package thesaurus

import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.WordSpec
import pureconfig.ConfigReader.Result

import scala.io.Source
import pureconfig.generic.auto.exportReader

class ThesaurusDataSpec extends WordSpec {

  def resourceContent(path: String): String =
    Source.fromFile(getClass.getResource(path).toURI).mkString

  def config(path: String): Config = ConfigFactory.parseResources(path)

  "A Thesaurus data" when {
    "is being created" should {
      "handle any string" in {
        assert(ThesaurusData.fromHttpResponse("").isLeft)
        assert(ThesaurusData.fromHttpResponse("asdasd").isLeft)
      }

      "handle misspelled redirection" in {
        val expected: Result[LookupError] =
          pureconfig.loadConfig[Misspelling](config("words/misspelled.conf"))
        val parsed = ThesaurusData.fromHttpResponse(resourceContent("/cached/misspelled"))
        assert(expected.swap == parsed)
      }

      "parse proper response" in {
        assert(ThesaurusData.fromHttpResponse(resourceContent("/cached/taken")).isRight)
      }
    }

    "is read" should {
      val parsedData = ThesaurusData.fromHttpResponse(resourceContent("/cached/taken"))

      "extract word data" in {
        val parsed = parsedData.flatMap(_.extractWord)
        val expected = pureconfig.loadConfig[ThesaurusWord](config("words/taken.conf"))
        assert(expected == parsed)
      }
    }
  }
}
