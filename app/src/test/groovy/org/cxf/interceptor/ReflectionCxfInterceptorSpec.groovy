package org.cxf.interceptor
import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import groovy.util.logging.Slf4j
import lavcraft.test.ws.HelloWorld
import lavcraft.test.ws.HelloWorldRequest
import lavcraft.test.ws.HelloWorldResponse
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.apache.cxf.phase.Phase
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.runner.RunWith
import org.spockframework.runtime.Sputnik
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*
/**
 * @author tolkv
 * @since 15/08/15
 */
@Slf4j
@RunWith(Sputnik)
class ReflectionCxfInterceptorSpec extends Specification {
  public static final int PORT = 8089

  @Rule
  public static WireMockClassRule wireMockRule = new WireMockClassRule(PORT)

  @Rule
  public WireMockClassRule wsService = wireMockRule

  @Rule
  public SystemOutRule outRule = new SystemOutRule().enableLog()

  def 'should invoke remote service and get response'() {
    when:
    stubFor(post(urlMatching('/api/.*')).atPriority(5)
        .willReturn(aResponse()
        .withBody(
        '<?xml version="1.0" encoding="utf-8" ?>\n' +
            '<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">\n' +
            ' <S:Body>\n' +
            '  <ns2:HelloWorldResponse xmlns:ns2="http://ws.test.lavcraft">\n' +
            '   <message>Hello friend!</message>\n' +
            '   <valid>true</valid>\n' +
            '  </ns2:HelloWorldResponse>\n' +
            ' </S:Body>\n' +
            '</S:Envelope>')
        .withHeader('Transfer-encoding', 'chunked')
        .withHeader('Content-type', 'text/xml; charset=utf-8')
        .withStatus(200)));

    log.info invokeWs() as String

    then:
    def separator = outRule.getLogWithNormalizedLineSeparator()
    separator.contains 'message'
    separator.contains 'Hello friend!'
    separator.contains 'valid'
    separator.contains 'true'
  }

  private static HelloWorldResponse invokeWs() {
    return getService().getHelloWorldAsString(
        new HelloWorldRequest(name: 'fdssfd', valid: true)
    )
  }

  public static HelloWorld getService() {
    JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean()
    initInterceptors factory
    factory.serviceClass = HelloWorld
    factory.address = "http://localhost:${PORT}/api/"

    HelloWorld srv = factory.create() as HelloWorld
    srv
  }

  private static void initInterceptors(JaxWsProxyFactoryBean factory) {
    factory.getInInterceptors() add new ReflectionCxfInterceptor(Phase.POST_UNMARSHAL)
    factory.getOutInterceptors() add new ReflectionCxfInterceptor(Phase.SETUP)
  }
}
