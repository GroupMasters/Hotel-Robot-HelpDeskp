//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.14 at 11:02:05 AM GMT 
//


package hotel_helpdesk;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
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

    private final static QName _HotelInfo_QNAME = new QName("", "hotelInfo");
    private final static QName _Rate_QNAME = new QName("", "rate");
    private final static QName _BookingInfo_QNAME = new QName("", "bookingInfo");
    private final static QName _Supporter_QNAME = new QName("", "supporter");
    private final static QName _Room_QNAME = new QName("", "room");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HotelInfo }
     * 
     */
    public HotelInfo createHotelInfo() {
        return new HotelInfo();
    }

    /**
     * Create an instance of {@link BookingInfo }
     * 
     */
    public BookingInfo createBookingInfo() {
        return new BookingInfo();
    }

    /**
     * Create an instance of {@link Supporter }
     * 
     */
    public Supporter createSupporter() {
        return new Supporter();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HotelInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "hotelInfo")
    public JAXBElement<HotelInfo> createHotelInfo(HotelInfo value) {
        return new JAXBElement<HotelInfo>(_HotelInfo_QNAME, HotelInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rate")
    public JAXBElement<Integer> createRate(Integer value) {
        return new JAXBElement<Integer>(_Rate_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookingInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bookingInfo")
    public JAXBElement<BookingInfo> createBookingInfo(BookingInfo value) {
        return new JAXBElement<BookingInfo>(_BookingInfo_QNAME, BookingInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Supporter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "supporter")
    public JAXBElement<Supporter> createSupporter(Supporter value) {
        return new JAXBElement<Supporter>(_Supporter_QNAME, Supporter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Room }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "room")
    public JAXBElement<Room> createRoom(Room value) {
        return new JAXBElement<Room>(_Room_QNAME, Room.class, null, value);
    }

}
