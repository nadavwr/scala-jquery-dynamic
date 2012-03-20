package only.hunam.jqdyn

import org.specs2._

import only.hunam.jqdyn.{JqDyn => $}

object JqDynSpec extends mutable.Specification {
  
  "A plain query" should {
    "translate literally (except for single quotes)" in {
      $("test").toJsCmd must be equalTo("$('test')")
    }
  }
  
  "An extention function" should {
    "evaluate literally" in {
      $.foo("test").toJsCmd must be equalTo("$.foo('test')")
    }
  }

  "A query followed by a non-empty method call" should {
    "evaluate literally" in {
      $("test").foo("bar").toJsCmd must be equalTo("$('test').foo('bar')")
    }
  }

  "A query followed by a member w/o parens" should {
    "output parens" in {
      ($("test").foo).toJsCmd must be equalTo("$('test').foo()")
    }
  }

  "A query followed by a member w/ empty parens" should {
    "output parens" in {
      ($("test").foo()).toJsCmd must be equalTo("$('test').foo()")
    }
  }
  
  "A query followed by a member w/ underscore" should {
    "not output parens" in {
      ($("test").foo_).toJsCmd must be equalTo("$('test').foo")
      ($("test").foo_.bar).toJsCmd must be equalTo("$('test').foo.bar()")
    }
  }
}