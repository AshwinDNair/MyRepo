package doctor_patient;

public class Doctor {
    private String doctorName;
    private int patientsCount;
    private int doctorId;

    public Doctor() { }
    
    // overrides
    @Override
    public String toString() {                      //to generate string of doctor details
	return String.format("%2d: ", doctorId) + doctorName +" consults on "  + patientsCount +" patients";
    }
    
    // properties
    public void setName(String name) {                  // to set doctor name
	this.doctorName = name; 
    }
    public String getName() { return this.doctorName; }    // to retrieve doctor name
    
    public void setPatientCount(int patientsCount) {        //to set number of patients
        this.patientsCount = patientsCount; 
    }
    public int getPatientCount() { return this.patientsCount; } //to get number of patients

    public void setId(int id) { this.doctorId = id; }   //to set doctor id
    public int getId() { return this.doctorId; }   // to retrieve doctor id
}