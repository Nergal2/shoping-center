
package wsdlsaved;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wsdl package. 
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

    private final static QName _GetAllItems_QNAME = new QName("http://soapservermaven.mycompany.com/", "getAllItems");
    private final static QName _Item_QNAME = new QName("http://soapservermaven.mycompany.com/", "item");
    private final static QName _TestServerreturn_QNAME = new QName("http://soapservermaven.mycompany.com/", "testServerreturn");
    private final static QName _TestServerreturnResponse_QNAME = new QName("http://soapservermaven.mycompany.com/", "testServerreturnResponse");
    private final static QName _GetAllItemsResponse_QNAME = new QName("http://soapservermaven.mycompany.com/", "getAllItemsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllItemsResponse }
     * 
     */
    public GetAllItemsResponse createGetAllItemsResponse() {
        return new GetAllItemsResponse();
    }

    /**
     * Create an instance of {@link GetAllItems }
     * 
     */
    public GetAllItems createGetAllItems() {
        return new GetAllItems();
    }

    /**
     * Create an instance of {@link TestServerreturn }
     * 
     */
    public TestServerreturn createTestServerreturn() {
        return new TestServerreturn();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link TestServerreturnResponse }
     * 
     */
    public TestServerreturnResponse createTestServerreturnResponse() {
        return new TestServerreturnResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapservermaven.mycompany.com/", name = "getAllItems")
    public JAXBElement<GetAllItems> createGetAllItems(GetAllItems value) {
        return new JAXBElement<GetAllItems>(_GetAllItems_QNAME, GetAllItems.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Item }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapservermaven.mycompany.com/", name = "item")
    public JAXBElement<Item> createItem(Item value) {
        return new JAXBElement<Item>(_Item_QNAME, Item.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestServerreturn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapservermaven.mycompany.com/", name = "testServerreturn")
    public JAXBElement<TestServerreturn> createTestServerreturn(TestServerreturn value) {
        return new JAXBElement<TestServerreturn>(_TestServerreturn_QNAME, TestServerreturn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestServerreturnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapservermaven.mycompany.com/", name = "testServerreturnResponse")
    public JAXBElement<TestServerreturnResponse> createTestServerreturnResponse(TestServerreturnResponse value) {
        return new JAXBElement<TestServerreturnResponse>(_TestServerreturnResponse_QNAME, TestServerreturnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllItemsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapservermaven.mycompany.com/", name = "getAllItemsResponse")
    public JAXBElement<GetAllItemsResponse> createGetAllItemsResponse(GetAllItemsResponse value) {
        return new JAXBElement<GetAllItemsResponse>(_GetAllItemsResponse_QNAME, GetAllItemsResponse.class, null, value);
    }

}
