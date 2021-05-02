package doctor_patient;

public class Patient {
    private String patientName;
    private String insuranceNum;
    private int patientId;
    private int doctorId;

    public Patient() { }
    
    // overrides
    @Override
    public String toString() {          //to generate string of patient details
	return String.format("%2d: ", patientId) + patientName + " has the insurance number: " + insuranceNum+"consults with docter id"+doctorId;
    }
    
    // properties
    public void setName(String name) {             // to set patient name
	this.patientName = name; 
    }
    public String getName() { return this.patientName; }        // to retrieve patient name
    
    public void setInsuranceNumber(String insuranceNum) {            //to set insurance number
        this.insuranceNum = insuranceNum;   
    }
    public String getInsuranceNumber() { return this.insuranceNum; }        //to get insurance number

    public void setId(int id) { this.patientId = id; }          //to set patient id
    public int getId() { return this.patientId; }           //to get patient id

    public void setDoctorId(int id) { this.doctorId = id; }         //to set doctor id
    public int getDoctorId() { return this.doctorId; }          //to get doctor id
    
}