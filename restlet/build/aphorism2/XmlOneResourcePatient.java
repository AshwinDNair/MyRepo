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
public class XmlOneResourcePatient extends ServerResource {
	public XmlOneResourcePatient() {
	}

	@Get
	public Representation toIndividualXml() throws Exception {
		String sid = (String) getRequest().getAttributes().get("id");
		CopyOnWriteArrayList<Patient> patients;
		String output = "";
		patients = Patients.getList();
		if (sid == null)
			return badRequest("No ID provided\n");

		int id;
		id = Integer.parseInt(sid.trim());
		Patient p = Patients.find(id);
		try {
			if (p == null) {
				throw new Exception("Invalid Id");
			}
		} catch (Exception e) {
			return badRequest("No such ID\n");
		}

		DomRepresentation dom = null;
		try {
			dom = new DomRepresentation(MediaType.TEXT_XML);
			dom.setIndenting(true);
			Document doc = dom.getDocument();
			Element root = DoctorPatientUtil.getPatientXml(p,doc);
			// root.appendChild(root1);
			doc.appendChild(root);
		} catch (Exception e) {
			throw e;
		}
		return dom;
	}

	private StringRepresentation badRequest(String msg) {
		Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
		return new StringRepresentation(error.toString());
	}
}
