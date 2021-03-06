
package lavcraft.test.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloWorld", targetNamespace = "http://ws.test.lavcraft")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloWorld {


    /**
     * 
     * @param helloWorld
     * @return
     *     returns lavcraft.test.ws.HelloWorldResponse
     */
    @WebMethod
    @WebResult(name = "HelloWorldResponse", targetNamespace = "http://ws.test.lavcraft", partName = "return")
    public HelloWorldResponse getHelloWorldAsString(
        @WebParam(name = "HelloWorldRequest", targetNamespace = "http://ws.test.lavcraft", partName = "helloWorld")
        HelloWorldRequest helloWorld);

}
