package aphorism2;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class Doctors {
    private static CopyOnWriteArrayList<Doctor> doctors;
    private static AtomicInteger id;

    static {
	doctors = new CopyOnWriteArrayList<Doctor>();
	try{
	populate();		
		}
	catch( Exception err){
		System.out.println(err);
		
	}
    }

  public static void populate() { 
    try {
      File myObj = new File("/Users/ashwinnair/Desktop/University Of Illinois Springfield/Web Services/TermProject/RestletDrPatientRepo/restlet/src/aphorism2/drs.db");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String record = myReader.nextLine();
        String[] parts = record.split("!");
		add(Integer.parseInt(parts[0]),parts[1],Integer.parseInt(parts[2]));
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
     
    public static String toPlain() {
	String retval = "";
	int i = 1;
	for (Doctor doctor : doctors) retval += doctor.toString() + "\n";
	return retval;
    }
    
    public static CopyOnWriteArrayList<Doctor> getList() { return doctors; }

    // Support GET one operation	.
    public static Doctor find(int id) {
	Doctor doctor = null;
	for (Doctor a : doctors) {
	    if (a.getId() == id) {
		doctor = a;
		break;
	    }
	}	
	return doctor;
    }

    // Support POST operation.
    public static void add(int Id,String name,int patientsCount) {
	Doctor doctor = new Doctor();
	doctor.setName(name);
	doctor.setPatientCount(patientsCount);
	doctor.setId(Id);
	doctors.add(doctor);
    }
}
