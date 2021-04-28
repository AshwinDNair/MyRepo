package aphorism2;

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
		// To illlustrate the different API possibilities, implement the
		// DELETE operation as an anonymous Restlet class. For the
		// remaining operations, follow Restlet best practices and
		// implement each as a Java class.

		// DELETE Doctor handler
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

				Doctor doctor = Doctors.find(id);
				CopyOnWriteArrayList<Patient> patients = Patients.getList();
				for (Patient patient : patients) {
					if (patient.getDoctorId() == (doctor.getId())) {
						// found it!
						Patients.getList().remove(patient);
					}
				}

				if (doctor == null)
					msg = badRequest("No doctor with ID " + id + "\n");
				else {
					Doctors.getList().remove(doctor);
					DoctorPatientUtil.writeDoctorFile();
					DoctorPatientUtil.writePatientFile();
					msg = "Doctor with Id:" + id + " removed.\n";
				}

				// Generate HTTP respoxnse.
				response.setEntity(msg, MediaType.TEXT_PLAIN);
			}
		};

		// Create the routing table.
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
