
package lavcraft.test.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the lavcraft.test.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HelloWorldRequest_QNAME = new QName("http://ws.test.lavcraft", "HelloWorldRequest");
    private final static QName _HelloWorldResponse_QNAME = new QName("http://ws.test.lavcraft", "HelloWorldResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lavcraft.test.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloWorldRequest }
     * 
     */
    public HelloWorldRequest createHelloWorldRequest() {
        return new HelloWorldRequest();
    }

    /**
     * Create an instance of {@link HelloWorldResponse }
     * 
     */
    public HelloWorldResponse createHelloWorldResponse() {
        return new HelloWorldResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorldRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.lavcraft", name = "HelloWorldRequest")
    public JAXBElement<HelloWorldRequest> createHelloWorldRequest(HelloWorldRequest value) {
        return new JAXBElement<HelloWorldRequest>(_HelloWorldRequest_QNAME, HelloWorldRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWorldResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.test.lavcraft", name = "HelloWorldResponse")
    public JAXBElement<HelloWorldResponse> createHelloWorldResponse(HelloWorldResponse value) {
        return new JAXBElement<HelloWorldResponse>(_HelloWorldResponse_QNAME, HelloWorldResponse.class, null, value);
    }

}
