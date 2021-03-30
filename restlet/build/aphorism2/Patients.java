package aphorism2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Patients {
    private static CopyOnWriteArrayList<Patient> patients;
    private static AtomicInteger id;

    static {
		
	patients = new CopyOnWriteArrayList<Patient>();
	id = new AtomicInteger();
	add("Patient1","CCS1");
	add("Patient2","CCS2");
	add("Patient3","CCS3");
	add("Patient4","CCS4");
	add("Patient5","CCS5");
	add("Patient6","CCS6");
	
    }

    public static String toPlain() {
	String retval = "";
	int i = 1;
	for (Patient patient : patients) retval += patient.toString() + "\n";
	return retval;
    }
    
    public static CopyOnWriteArrayList<Patient> getList() { return patients; }

    // Support GET one operation	.
    public static Patient find(int id) {
	Patient patient = null;
	for (Patient a : patients) {
	    if (a.getId() == id) {
		patient = a;
		break;
	    }
	}	
	return patient;
    }

    // Support POST operation.
    public static void add(String name,String insuranceNum) {
	int localId = id.incrementAndGet();
	Patient patient = new Patient();
	patient.setName(name);
	patient.setInsuranceNumber(insuranceNum);
	patient.setId(localId);
	patients.add(patient);
    }
}
