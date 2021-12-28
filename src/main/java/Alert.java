import java.util.List;

public class Alert {

    // setPatientName (pat.name)
    // setAlert (warning/urgent)
    // setAbnormality ([description of the symptoms])
    String name;
    String status;
    List<String> abnormalDetails;
    String patLoc;

    public Alert(String name, String status, List<String> details, String patLoc){
        this.name = name;
        this.status = status;
        this.abnormalDetails = details;
        this.patLoc = patLoc;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getAbnormalDetails() {
        return abnormalDetails;
    }

    public String getPatLoc() {
        return patLoc;
    }

    @Override
    public String toString(){
        return name;
    }
}
