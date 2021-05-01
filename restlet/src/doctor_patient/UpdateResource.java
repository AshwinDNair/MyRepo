package doctor_patient;

import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import org.restlet.data.Form;

public class UpdateResource extends ServerResource {
    public UpdateResource() { }

    @Put
    public Representation update(Representation data) throws Exception {
	Status status = null;
	String msg = null;

	// Extract the data from the POST body.
	Form form = new Form(data);
	String sid = form.getFirstValue("id");
	String name = form.getFirstValue("name");
	
		if(sid == null || name == null){
			if (sid == null) 
			msg = "An id must be provided.\n";
			if(name == null)
			msg = "An new name  must be provided.\n";
			if(sid == null && name == null)
			msg = "An id and new name  must be provided.\n";

			status = Status.CLIENT_ERROR_BAD_REQUEST;
		}
		
		
		else {
			try{
				int id = Integer.parseInt(sid.trim());
			Doctor doctor = Doctors.find(id);
			if (doctor == null) {
			msg = "There is no doctor with ID " + id + "\n";
			status = Status.CLIENT_ERROR_BAD_REQUEST;
			}
			else {
				doctor.setName(name);
				DoctorPatientUtil.writeDoctorFile();
			msg = "Id: " + id + " has been updated to '" + name + "'.\n";
			status = Status.SUCCESS_OK;
			}

			}
			catch (Exception e) {
				throw e;
			}
			
		}
	
	
	

	setStatus(status);
	return new StringRepresentation(msg, MediaType.TEXT_PLAIN);
    }
}


