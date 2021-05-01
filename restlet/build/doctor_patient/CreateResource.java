package doctor_patient;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class CreateResource extends ServerResource {
    public CreateResource() { }

    @Post
    public Representation create(Representation data) {
	Status status = null;
	String msg = null;

	// Extract the data from the POST body.
	Form form = new Form(data);
	String name = form.getFirstValue("name");
	int id=0;
	int patientNum = 0;
	if (name == null) {
	    msg = "No name was given.\n";
	    status = Status.CLIENT_ERROR_BAD_REQUEST;
	}
		else if (form.getFirstValue("patientNum") == null) {
	    msg = "No number of patients was given.\n";
	    status = Status.CLIENT_ERROR_BAD_REQUEST;
	
	}
		else if (form.getFirstValue("patientNum") == null) {
	    msg = "No docter id was given.\n";
	    status = Status.CLIENT_ERROR_BAD_REQUEST;
		}
	else {
		int patientId=0;
		String patientName="";
		String insuranceNumber="";
		patientNum=Integer.parseInt(form.getFirstValue("patientNum"));
		id=Integer.parseInt(form.getFirstValue("id"));
		Doctors.add(id,name,patientNum);
		DoctorPatientUtil.writeDoctorFile();
		for(int i=0;i<patientNum;i++){
			 patientId = Integer.parseInt(form.getFirstValue("patientid"+(i+1)));
			 patientName = form.getFirstValue("patientname"+(i+1));
			 insuranceNumber = form.getFirstValue("insurancenumber"+(i+1));
			 Patients.add(patientId, patientName, insuranceNumber, id);
		}
		DoctorPatientUtil.writePatientFile();
	    msg = name + " has been added.\n";
	    status = Status.SUCCESS_OK;
	}

	setStatus(status);
	return new StringRepresentation(msg, MediaType.TEXT_PLAIN);
    }
}


