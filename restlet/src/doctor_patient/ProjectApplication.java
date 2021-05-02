package doctor_patient;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.routing.Router;
import org.restlet.data.Status;
import org.restlet.data.MediaType;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProjectApplication extends Application {
	@Override
	public synchronized Restlet createInboundRoot() {
		
		Restlet janitor = new Restlet(getContext()) {
			public void handle(Request request, Response response) {
				String msg = null;
				String output = null;
				String sid = (String) request.getAttributes().get("id");
				if (sid == null)
					msg = badRequest("No ID given.\n");

				Integer id = null;
				try {
					id = Integer.parseInt(sid.trim());
				} catch (Exception e) {
					msg = badRequest("Ill-formed ID.\n");
				}

				Doctor doctor = Doctors.find(id);				// find the doctor whose id has been passed as parameter
				CopyOnWriteArrayList<Patient> patients = Patients.getList();
				for (Patient patient : patients) {
					if (patient.getDoctorId() == (doctor.getId())) {
						Patients.getList().remove(patient);
					}
				}

				if (doctor == null)
					msg = badRequest("No doctor with ID " + id + "\n");
				else {
					Doctors.getList().remove(doctor);		//removing the doctor object from the Doctor Array List
					//DoctorPatientUtil.writeDoctorFile();  updating the drs.db file has been disabled
					//DoctorPatientUtil.writePatientFile();  updating the patients.db file has been disabled
					msg = "Doctor with Id:" + id + " removed.\n";
				}

				// Generate HTTP respoxnse.
				response.setEntity(msg, MediaType.TEXT_PLAIN);
			}
		};

		// Create the routing table for various apis in DoctorPatient Information Service.
		Router router = new Router(getContext());
		router.attach("/", PlainResource.class);
		router.attach("/doctors", PlainResourceDoctor.class);
		router.attach("/patients", PlainResourcePatient.class);
		router.attach("/doctors/{id}", PlainOneResourceDoctor.class);
		router.attach("/patients/{id}", PlainOneResourcePatient.class);
		router.attach("/xml", XmlAllResource.class);
		router.attach("/xml/doctors", XmlAllResourceDoctor.class);
		router.attach("/xml/doctors/{id}", XmlOneResourceDoctor.class);
		router.attach("/xml/patients", XmlAllResourcePatient.class);
		router.attach("/xml/patients/{id}", XmlOneResourcePatient.class);
		router.attach("/xml/{id}", XmlOneResource.class);
		router.attach("/create", CreateResource.class);
		router.attach("/update", UpdateResource.class);
		router.attach("/delete/{id}", janitor); // instance of Doctor class
		router.attach("/{id}", PlainOneResource.class);
		return router;
	}

	private String badRequest(String msg) {
		Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
		return error.toString();
	}

}
