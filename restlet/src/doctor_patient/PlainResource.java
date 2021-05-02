package doctor_patient;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.concurrent.CopyOnWriteArrayList;
public class PlainResource extends ServerResource {
    //to represent all doctors and his assigned patients in plain text format

CopyOnWriteArrayList<Doctor> doctors;
CopyOnWriteArrayList<Patient> patients;

    public PlainResource() { }
    @Get
    public Representation toPlain() {
        String output="";
      doctors = Doctors.getList();
      patients = Patients.getList();
	for (Doctor a : doctors) {
        output=output+a.getName()+" -- ";
	   	for (Patient p : patients) {
               if(a.getId()==p.getDoctorId()){
          
                       output=output+p.getId()+":"+p.getName()+"==>"+p.getInsuranceNumber()+"\t";
                       
  
                     
               }
	  
	}
    output=output+"\n";
	}	
    
	setStatus(Status.SUCCESS_OK);
	return new StringRepresentation(output, MediaType.TEXT_PLAIN);
    }

   
}


