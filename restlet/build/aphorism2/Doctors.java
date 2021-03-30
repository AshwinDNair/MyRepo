package aphorism2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.beans.XMLEncoder; // simple and effective
import javax.servlet.ServletContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Doctors {
    private static CopyOnWriteArrayList<Doctor> doctors;
    private static AtomicInteger id;
 private ServletContext sctx;  
	 private static final Logger LOGGER = Logger.getLogger(Doctors.class.getName());
    static {
		
	doctors = new CopyOnWriteArrayList<Doctor>();
	id = new AtomicInteger();
	try{
	populate();		
		}
	catch( Exception err){
		System.out.println(err);
			 sctx.log("	ERROOORRRRRRRR!!!!!!!");
		 //sctx.log(err);
	}
	// add("Dr.Harry",1);
	// add("Dr.Ron",2);
	// add("Dr.Hermione",3);
	// add("Dr.Neville",4);
	// add("Dr.Giny",5);
	// add("Dr.Draco",6);
	
    }

 //** utilities
  private void populate() {
	// plist = new PlacesList();

	String filename = "/WEB-INF/data/drs.db";
	 InputStream in = sctx.getResourceAsStream(filename);
	
	// // Read the data into the array of Predictions. 
	 if (in != null) {
	     try {
	 	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		int i = 0;
		String record = null;
	 	while ((record = reader.readLine()) != null) {
	 	    String[] parts = record.split("!");
			 	    Doctor p = new Doctor();
	 	    p.setName(parts[0]);
		    p.setPatientCount(Integer.parseInt(parts[1]));
			doctors.add(p);
		   
		}
	     }
	    catch (Exception e) { 
		throw new RuntimeException("I/O failed!"); 
	    }
	}
    }
     // public static void populate() {
	 //String filename = "drs.db";
	  // LOGGER.info("Logger Name!!!!!!");
         
       // LOGGER.config("W");
         
        
         
	// InputStream in = sctx.getResourceAsStream(filename);

	// // Read the data into the array of Predictions. 
	// if (in != null) {
	  //   try {
	 	//InputStreamReader isr = new InputStreamReader(in);
	//	BufferedReader reader = new BufferedReader(isr);

	// //	doctors = new Doctor[n];
		int i = 0;
	 	String record = null;
	//	while ((record = reader.readLine()) != null) {
	//	    String[] parts = record.split("!");
	//	    Doctor p = new Doctor();
	 	//    p.setName(parts[0]);
		  //  p.setPatientCount(Integer.parseInt(parts[1]));
			//doctors.add(p);
	//	}
	  //   }
	    //catch (IOException e) { }
	 //}
    //}
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
    public static void add(String name,int patientsCount) {
	int localId = id.incrementAndGet();
	Doctor doctor = new Doctor();
	doctor.setName(name);
	doctor.setPatientCount(patientsCount);
	doctor.setId(localId);
	doctors.add(doctor);
    }
}
