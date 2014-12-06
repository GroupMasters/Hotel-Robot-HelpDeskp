package hotel_helpdesk;
import java.io.*;
import javax.xml.bind.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author obaro
 */




public class JAXB_XMLParser {

	private JAXBContext jaxbContext = null;     // generate a context to work in with JAXB											   
	private Unmarshaller unmarshaller = null;   // unmarshall = genrate objects from an xml file												
	
	// This is a candidate for a name change because you wont deal with a library any more in your conversion
	private HotelInfo myhotel = null;            // the main object containing all data

	public JAXB_XMLParser() {

		try {
			jaxbContext = JAXBContext.newInstance("hotel_helpdesk");  // Package that contains ouer classes																													
			unmarshaller = jaxbContext.createUnmarshaller();
		}
		catch (JAXBException e) {
		}
	}
	
	// Instance objects and return a list with this objects in it
	public HotelInfo loadXML(InputStream fileinputstream) {

		try {
			Object xmltoobject = unmarshaller.unmarshal(fileinputstream);

			if (myhotel == null) {

				// generate the myhotel object that conatins all info from the xml document
				myhotel= (HotelInfo) (((JAXBElement) xmltoobject).getValue());
				
				
				
			}
		} // try

		catch (JAXBException e) {
			e.printStackTrace();
		}
		return myhotel; // return Hotel Object
	}
}
