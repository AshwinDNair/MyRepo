package doctor_patient;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
//import java.nio.file.Paths;


public class Patients {
    private static CopyOnWriteArrayList<Patient> patients;
    private static AtomicInteger id;

    static {
	patients = new CopyOnWriteArrayList<Patient>();
	try{

	populate();		
		}
	catch( Exception err){
		System.out.println(err);
		
	}
    }

  public static void populate() {         // to initialize the Patients ArrayList with data retrieved from the patients.db
    try {
  
      File myObj = new File("../webapps/drpatient/WEB-INF/data/patients.db");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String record = myReader.nextLine();
        String[] parts = record.split("!");
		add(Integer.parseInt(parts[0]),parts[1],parts[2],Integer.parseInt(parts[3]));
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
     
    public static String toPlain() {      //to represent all the  patients in the Patients ArrayList in plain text format
	String retval = "";
	int i = 1;
	for (Patient patient : patients) retval += patient.toString() + "\n";
	return retval;
    }
    
    public static CopyOnWriteArrayList<Patient> getList() { return patients; }    //to list all the  patients  in the Patients ArrayList

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
    public static void add(int Id,String name,String insuranceNum,int docterId) {     //to add patient details to the Patients ArrayList
	Patient patient = new Patient();
	patient.setName(name);
	patient.setInsuranceNumber(insuranceNum);
	patient.setId(Id);
	patient.setDoctorId(docterId);
	patients.add(patient);
    }
}

