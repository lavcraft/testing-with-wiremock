import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%-4relative %logger [%thread] - %msg%n"
  }
}

logger('org.mortbay.log',OFF)
logger('org.apache.cxf.phase.PhaseInterceptorChain',OFF)
logger('org.apache.cxf',OFF)
logger('org.apache.cxf.services',DEBUG)

root(DEBUG, ["STDOUT"])