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
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class DoctorPatientUtil {
    private static CopyOnWriteArrayList<Doctor> doctors = Doctors.getList();
    private static CopyOnWriteArrayList<Patient> patients = Patients.getList();
    private static Element root1;


    public static Element getPatientXml(Patient p,Document doc){
        Element rootNewPatient = doc.createElement("patient");
        Element rootPatientName = doc.createElement("patient");
        Element rootPatientId = doc.createElement("id");
        Element rootInsuranceNo = doc.createElement("insuranceNo");
        Element rootDocterId = doc.createElement("docterId");
        String id=Integer.toString(p.getDoctorId());
        String doctorId=Integer.toString(p.getDoctorId());
        rootPatientId.appendChild(doc.createTextNode(id));
        rootPatientName.appendChild(doc.createTextNode(p.getName()));
        rootInsuranceNo.appendChild(doc.createTextNode(p.getInsuranceNumber()));
        rootDocterId.appendChild(doc.createTextNode(doctorId));
        rootNewPatient.appendChild(rootPatientId);
        rootNewPatient.appendChild(rootPatientName);
        rootNewPatient.appendChild(rootInsuranceNo);
        rootNewPatient.appendChild(rootDocterId);
        return rootNewPatient;
    }
    public static Element getPatientsXml(Doctor d,Document doc){
        Element rootPatients = doc.createElement("patients");
        
        for (Patient p : patients) {
            if(d==null){
                rootPatients.appendChild(getPatientXml(p, doc));  
            }
            else
              if (d.getId() == p.getDoctorId()) {
                rootPatients.appendChild(getPatientXml(p, doc)); 
            }

        }
        return rootPatients;
    }
    public static Element getOneXml(Doctor d, Document doc,boolean isDoctorOnly) {
        try {
            Element root = doc.createElement("doctor");
            Element rootDocId = doc.createElement("id");
            Element rootDocName = doc.createElement("name");
            Element rootDocPatientNum = doc.createElement("patientNumber");
            String doctorId=Integer.toString(d.getId());
            String patientNumberStr=Integer.toString(d.getPatientCount());
            rootDocId.appendChild(doc.createTextNode(doctorId));
            rootDocName.appendChild(doc.createTextNode(d.getName()));
            rootDocPatientNum.appendChild(doc.createTextNode(patientNumberStr));
            root.appendChild(rootDocId);
            root.appendChild(rootDocName);
            root.appendChild(rootDocPatientNum);
            if(isDoctorOnly==false)
            root.appendChild(getPatientsXml(d,doc));
            root1 = root;
            return root;

        } catch (Exception e) {
        }
        return root1;
    }

    public static Element getAllXml(Document doc,boolean isDoctorOnly) {
        try {
            Element doctorsRoot = doc.createElement("doctors");
            for (Doctor d : doctors) {

                doctorsRoot.appendChild(getOneXml(d, doc,isDoctorOnly));
            }

            root1 = doctorsRoot;
            return doctorsRoot;

        } catch (Exception e) {
        }
        return root1;
    }

    public static void writeDoctorFile() {
        String output = null;
        File file = new File(
                "../webapps/drpatient/WEB-INF/data/drs.db");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Doctor d : Doctors.getList()) {
                output = d.getId() + "!" + d.getName() + "!" + d.getPatientCount() + "\n";
                bw.write(output);
            }
            bw.close();
        } catch (Exception e) {

        }
    }
        public static void writePatientFile() {
            String output = null;
            File file = new File(
                    "../webapps/drpatient/WEB-INF/data/patients.db");
            try {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                for (Patient p : Patients.getList()) {

                    output = p.getId() + "!" + p.getName() + "!" + p.getInsuranceNumber() + "!"+p.getDoctorId()+"\n";
                    bw.write(output);
                }
                bw.close();
            } catch (Exception e) {
    
            }
    }

}
