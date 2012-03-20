package only.hunam.jqdyn

import xml.NodeSeq
import net.liftweb.http.js.JsCmd

trait JsExpEval {
  protected def wrapArg(arg: Any): String = arg match {
    case s: String => "'" + s + "'"
    case ns: NodeSeq => "'" + ns + "'"
    case _ => arg.toString
  }

  protected def eval(cmd: String)(args: Any*): String =
    cmd + "(" + args.map(wrapArg).reduceLeftOption(_+", "+_).getOrElse("") + ")"
}

class JqDyn private[JqDyn] (statement: String) extends Dynamic with JsCmd with JsExpEval {
  def applyDynamic(cmd: String)(args: Any*) =
    new JqDyn(statement + "." + eval(cmd)(args: _*))

  def #>(ns: NodeSeq) = applyDynamic("replaceWith")(ns)
  def #>(s: String) = applyDynamic("val")(s)

  override def toJsCmd = statement
}

object JqDyn extends Dynamic with JsExpEval {
  def applyDynamic(cmd: String)(args: Any*) =
    new JqDyn("$." + eval(cmd)(args: _*))

  def apply(q: String): JqDyn = new JqDyn("$('"+q+"')")
}
