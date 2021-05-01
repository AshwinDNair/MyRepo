package doctor_patient;

public class Patient {
    private String patientName;
    private String insuranceNum;
    private int patientId;
    private int doctorId;

    public Patient() { }
    
    // overrides
    @Override
    public String toString() {
	return String.format("%2d: ", patientId) + patientName + " has the insurance number: " + insuranceNum+"consults with docter id"+doctorId;
    }
    
    // properties
    public void setName(String name) { 
	this.patientName = name; 
    }
    public String getName() { return this.patientName; }
    
    public void setInsuranceNumber(String insuranceNum) { 
        this.insuranceNum = insuranceNum; 
    }
    public String getInsuranceNumber() { return this.insuranceNum; }

    public void setId(int id) { this.patientId = id; }
    public int getId() { return this.patientId; }

    public void setDoctorId(int id) { this.doctorId = id; }
    public int getDoctorId() { return this.doctorId; }
    
}