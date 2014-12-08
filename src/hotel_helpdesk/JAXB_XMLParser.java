package hotel_helpdesk;
import java.io.*;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
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

        
        // The Parser and load the 
	public JAXB_XMLParser() {

		try {
			jaxbContext = JAXBContext.newInstance("hotel_helpdesk");  // Package that contains all our classes																													
			unmarshaller = jaxbContext.createUnmarshaller();
                       // JOptionPane.showMessageDialog(null,jaxbContext.toString());
		}
		catch (JAXBException e) {
                    e.printStackTrace();
		}
	}
	
	// Instance objects and return a list with this objects in it
	public HotelInfo loadXML(InputStream fileinputstream) {

		try {
			Object xmltoobject = unmarshaller.unmarshal(fileinputstream);
                       // JOptionPane.showMessageDialog(null,jaxbContext.toString());	
			if (myhotel == null) {
				// generate the myhotel object that conatins all info from the xml document
				myhotel= (HotelInfo) (((JAXBElement) xmltoobject).getValue());                               		
			      return myhotel;
			}
		} // try

		catch (JAXBException e) {
			e.printStackTrace();
		}
		return myhotel; // return Hotel Object
	}
}
