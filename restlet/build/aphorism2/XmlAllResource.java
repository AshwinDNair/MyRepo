package aphorism2;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.ext.xml.DomRepresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.*;
import java.io.IOException;
public class XmlAllResource extends ServerResource {
    private static Logger logger
        = Logger.getLogger(
            XmlAllResource.class.getName());
	public XmlAllResource() { }
	@Get
    public Representation toXml()throws SecurityException, IOException {
	
String[] list = getList();
	// Generate the XML response.
	DomRepresentation dom = null;  
        try {  
            dom = new DomRepresentation(MediaType.TEXT_XML);  
	    dom.setIndenting(true);
            Document doc = dom.getDocument();  
  
            Element root = doc.createElement("doctor");  
 for (String doctor : list) {
		Element next = doc.createElement("doctor");  
	 	next.appendChild(doc.createTextNode(doctor));  
		root.appendChild(next);
	    }
	    doc.appendChild(root);
		
	}
	catch(Exception e) { }
	return dom;
    }

	public String[] getList()throws SecurityException, IOException{
		// Create a file handler object
        ConsoleHandler handler
            = new ConsoleHandler();
  
        // Add file handler as
        // handler of logs
        logger.addHandler(handler);
  
        // Log message
        logger.info("This is Info Messsage ");
        logger.log(Level.WARNING,
                   "Warning Message");
		String [] list={};
		String output="";
     CopyOnWriteArrayList<Doctor> doctors = Doctors.getList();
    CopyOnWriteArrayList<Patient>   patients = Patients.getList();
	int i=0;
	for (Doctor a : doctors) {
        output=output+a.getName()+" -- ";
	   	  	for (Patient p : patients) {
			output=output+p.getId()+":"+p.getName()+"==>"+p.getInsuranceNumber()+"\t"; 
	
	}
	list[i]=output;
	i++;
    output="";
        
       
	}	
	return list;
	}
    // @Get
    // public Representation toXml() {
	// List<Doctor> list = Doctors.getList();
	// DomRepresentation dom = null;  
    //     try {  
    //         dom = new DomRepresentation(MediaType.TEXT_XML);  
	//     dom.setIndenting(true);
    //         Document doc = dom.getDocument();  
  
    //         Element root = doc.createElement("doctor");  
	//     for (Doctor doctor : list) {
	// 	Element next = doc.createElement("doctor");  
	// 	next.appendChild(doc.createTextNode(doctor.toString()));  
	// 	root.appendChild(next);
	//     }
	//     doc.appendChild(root);
	// }
	// catch(Exception e) { }
	// return dom;
    // }
}


