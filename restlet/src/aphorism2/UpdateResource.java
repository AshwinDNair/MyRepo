package aphorism2;

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
    public Representation update(Representation data) {
	Status status = null;
	String msg = null;

	// Extract the data from the POST body.
	Form form = new Form(data);
	String sid = form.getFirstValue("id");
	String name = form.getFirstValue("name");
	if (sid == null || name == null) {
	    msg = "An ID and new name  must be provided.\n";
	    status = Status.CLIENT_ERROR_BAD_REQUEST;
	}
	else {
	    int id = Integer.parseInt(sid.trim());
	    Doctor doctor = Doctors.find(id);
	    if (doctor == null) {
		msg = "There is no doctor with ID " + id + "\n";
		status = Status.CLIENT_ERROR_BAD_REQUEST;
	    }
	    else {
			int patientsCount = Integer.parseInt(form.getFirstValue("patientsCount"));
			doctor.setName(name);
		msg = "Id: " + id + " has been updated to '" + name + "'.\n";
		status = Status.SUCCESS_OK;
	    }
	}

	setStatus(status);
	return new StringRepresentation(msg, MediaType.TEXT_PLAIN);
    }
}


