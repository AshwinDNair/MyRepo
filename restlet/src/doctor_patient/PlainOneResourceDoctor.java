package doctor_patient;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.concurrent.CopyOnWriteArrayList;


public class PlainOneResourceDoctor extends ServerResource { 
    //to represent an individual Doctor details based on id in plain text format(without patient details)

CopyOnWriteArrayList<Doctor> doctors;
CopyOnWriteArrayList<Patient> patients;
int id;
    public PlainOneResourceDoctor() { }
    @Get
    public Representation toIndividualPlain() {
   // Extract the friend's id.
	String sid = (String) getRequest().getAttributes().get("id");
	if (sid == null) 
    return badRequest("No ID provided\n");

	int id=0;
	try {
	    id = Integer.parseInt(sid.trim());
	}
	catch(Exception e) { 

        return badRequest("No such ID\n"); 
        }

        String output="";
      doctors = Doctors.getList();
	for (Doctor a : doctors) {
        if(a.getId()==id){
            output=output+a.getId()+" : "+a.getName()+"==> Patient Count : "+a.getPatientCount()+"\n";
        }
       
	}	
    
	setStatus(Status.SUCCESS_OK);
	return new StringRepresentation(output, MediaType.TEXT_PLAIN);
    }
      private StringRepresentation badRequest(String msg) {
	Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
	return new StringRepresentation(error.toString());
    }
}
