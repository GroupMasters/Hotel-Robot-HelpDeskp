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
   private JAXBContext jaxbContext = null;  											   
	private Unmarshaller unmarshaller = null;  											
	
	

	public JAXB_XMLParser() {

		try {
			jaxbContext = JAXBContext.newInstance("hotel_helpdesk");  // Package that contains ouer classes																													
			unmarshaller = jaxbContext.createUnmarshaller();
		}
		catch (JAXBException e) {
		}
	}
	
	
}
