package doctor_patient;

public class Doctor {
    private String doctorName;
    private int patientsCount;
    private int doctorId;

    public Doctor() { }
    
    // overrides
    @Override
    public String toString() {
	return String.format("%2d: ", doctorId) + doctorName +" consults on "  + patientsCount +" patients";
    }
    
    // properties
    public void setName(String name) { 
	this.doctorName = name; 
    }
    public String getName() { return this.doctorName; }
    
    public void setPatientCount(int patientsCount) { 
        this.patientsCount = patientsCount; 
    }
    public int getPatientCount() { return this.patientsCount; }

    public void setId(int id) { this.doctorId = id; }
    public int getId() { return this.doctorId; }
}