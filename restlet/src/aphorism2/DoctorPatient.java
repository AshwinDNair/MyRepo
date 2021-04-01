package aphorism2;

public class DoctorPatient {
     CopyOnWriteArrayList<Doctor> doctors = Doctors.getList();
    CopyOnWriteArrayList<Patient>   patients = Patients.getList();

    public Doctor() { }
    
    // overrides
    @Override
    public String toString() {
	return String.format("%2d: ", doctorId) + doctorName +" consults on "  + patientsCount +" patients";
    }
    
    public void setDocterPatientList() { 
	this.doctorName = name; 
    }
    public String getName() { return this.doctorName; }
}