package aphorism2;


import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class DoctorPatient {
    private static CopyOnWriteArrayList<Doctor> doctors = Doctors.getList();
    private static CopyOnWriteArrayList<Patient>   patients = Patients.getList();
    private static Element root1;
    public DoctorPatient() { }

    public void setDocterPatientList() { 

    }
    public static Element getOneXml(Doctor d){
        DomRepresentation dom = null; 
                try {  
            dom = new DomRepresentation(MediaType.TEXT_XML);  
	    dom.setIndenting(true);
            Document doc = dom.getDocument();  
			Element root = doc.createElement("doctor"); 
			Element rootDocName = doc.createElement("name"); 
            rootDocName.appendChild(doc.createTextNode(d.getName()));
            root.appendChild(rootDocName);
            Element rootPatients = doc.createElement("patients"); 
	for (Patient p : patients) {
        if(d.getId()==p.getDoctorId()){
         Element rootNewPatient = doc.createElement("patient"); 
         Element rootPatientName = doc.createElement("name"); 
         Element rootInsuranceNo = doc.createElement("insuranceNo"); 
         rootPatientName.appendChild(doc.createTextNode(p.getName()));
         rootInsuranceNo.appendChild(doc.createTextNode(p.getInsuranceNumber()));
         rootNewPatient.appendChild(rootPatientName);
         rootNewPatient.appendChild(rootInsuranceNo);
         rootPatients.appendChild(rootNewPatient);
        
		}
       
	}
    root.appendChild(rootPatients);
    root1=root;
    return root;
        
	}
	catch(Exception e) { 

  
    }
	//return dom;
    return root1;
    }
    }
    // public Element getAllXml(){

    // }
