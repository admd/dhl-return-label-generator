package org.helpers.dhl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBElement;
import org.apache.commons.codec.binary.Base64;
import org.helpers.dhl.generated.BookLabelRequestType;
import org.helpers.dhl.generated.BookLabelResponseType;
import org.helpers.dhl.generated.ObjectFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component("DHLServiceClient")
public class DHLServiceClient {

    private static final ObjectFactory WS_CLIENT_FACTORY = new ObjectFactory();

    private WebServiceTemplate webServiceTemplate;
    public DHLServiceClient() {
       
    }
    public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }
    public void generateReturnLabel(String paramOne) {
    	BookLabelRequestType request = WS_CLIENT_FACTORY
                .createBookLabelRequestType();
    	BookLabelResponseType response = WS_CLIENT_FACTORY
                .createBookLabelResponseType();
    	request.setPortalId("OnlineRetoure");
    	request.setDeliveryName("Spanien_Var3");
    	request.setShipmentReference("ShipRef Nextt");
    	request.setCustomerReference("1.Feld via Webservice");
    	request.setLabelFormat("PDF");
    	request.setSenderName1("Willi Webservice");
    	request.setSenderName2("via Webservice");
    	request.setSenderCareOfName("careOfName");
    	request.setSenderContactPhone("0800 123456");
    	request.setSenderStreet("Webservice Street");
    	request.setSenderStreetNumber("8080");
    	request.setSenderBoxNumber("12");
    	request.setSenderPostalCode("28010");
    	request.setSenderCity("Madrid");
    	
    	 JAXBElement<BookLabelResponseType> jaxBElem = WS_CLIENT_FACTORY.createBookLabelResponse(response);
    	
        
    	 jaxBElem = (JAXBElement<BookLabelResponseType>)webServiceTemplate
                 .marshalSendAndReceive(WS_CLIENT_FACTORY.createBookLabelRequest(request));

    	getPdf(jaxBElem.getValue().getLabel());
        //return response.getParamONE();
    }
	private void getPdf(String label) {
		byte[] decodedBytes = Base64.decodeBase64(label);
		
		File file = new File("d:/newfile2.pdf");
		FileOutputStream fop;
		try {
			fop = new FileOutputStream(file);
			fop.write(decodedBytes);
			fop.flush();
			fop.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}