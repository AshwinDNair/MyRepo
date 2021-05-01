package doctor_patient;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.concurrent.CopyOnWriteArrayList;


public class PlainResourcePatient extends ServerResource {
CopyOnWriteArrayList<Patient> patients;

    public PlainResourcePatient() { }
    @Get
    public Representation toPlain() {
        String output="";
        patients = Patients.getList();
	for (Patient a : patients) {
        output=output+a.getId()+" : "+a.getName()+"==>"+a.getInsuranceNumber()+"\n";
	}	
    
	setStatus(Status.SUCCESS_OK);
	return new StringRepresentation(output, MediaType.TEXT_PLAIN);
    }

   
}