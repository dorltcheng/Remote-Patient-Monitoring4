import java.util.ArrayList;
import java.util.List;

public class Patient {

    // which has variables to store temp, BP, HR, RR, ECG, alertStatus, report, other patient data

    String name;
    String alertStatus;
    String patLoc;
    // should not be double - how?
    int length;
    double[] temp = new double[length];
    double[] bp = new double[length];
    double[] hr = new double[length];
    double[] rr = new double[length];
    double[] ecg = new double[length];
    List<String> abnormalDetails = new ArrayList<String>();

    ArrayList<String> alertHistory = new ArrayList<>();

    public Patient(String name, String alertStatus, String patLoc, int length, double[] temp, double[] hr, double[] rr){
        this.name = name;
        this.alertStatus = alertStatus;
        this.patLoc = patLoc;
        this.length = length;
        this.temp = temp;
        //this.bp = bp;
        this.hr = hr;
        this.rr = rr;
    }

}
