// Where everything runs here


        import java.time.Clock;
        import java.time.Instant;
        import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Making an array for the temp, hr and rr
        double[] pat1_temp = new double[] {36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 39, 39, 39, 42, 42, 42, 42, 42};
        double[] pat2_temp = new double[] {36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36};
        double[] pat3_temp = new double[] {36, 36, 36, 41, 41, 41, 41, 41, 41, 41, 39, 39, 39, 39, 39, 36, 36, 36, 36, 36};

        double[] pat1_hr = new double[] {100, 100, 100, 100, 200, 200, 200, 200, 200, 100, 100, 100, 100, 100, 200, 200, 200, 100, 100, 100};
        double[] pat2_hr = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        double[] pat3_hr = new double[] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

        double[] pat1_rr = new double[] {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18};
        double[] pat2_rr = new double[] {18, 22, 22, 22, 22, 18, 18, 18, 18, 18, 18, 18, 18, 26, 26, 26, 26, 26, 26, 26};
        double[] pat3_rr = new double[] {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18};

        // instantiate the patient list here:
        Patient pat1 = new Patient("Anson", "healthy", "Ward 62", 20, pat1_temp, pat1_hr, pat1_rr);
        //
        Patient pat2 = new Patient("Billy", "healthy", "Ward 88", 20, pat2_temp, pat2_hr, pat2_rr);
        Patient pat3 = new Patient("Carina", "healthy", "Ward 100", 20, pat3_temp, pat3_hr, pat3_rr);

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(pat1);
        patientList.add(pat2);
        patientList.add(pat3);

        mainMenu.realTimeAlertChecker(patientList);
        //ArrayList<Alert> alertList = mainMenu.alertChecker(patientList);

        EmergencyUIController emUIController = new EmergencyUIController(patientList);

        System.out.println(pat1.alertStatus);


    }



}


/*
Notes:
- AlertList is not used now (Alert class is not used now)
- AlertDisplay is not used

To do:
- Implement clock and save the exact time and date instead of seconds
- turn alertHistroy into ArrayList with size 9
    - array[0]: temp_flag (0: normal, 1: abnormal)
    - array[1]: temp_start time
    - array[2]: temp_end time
    - array[3]: hr_flag
    - array[4]: hr_start time
    - array[5]: hr_end time
    - array[6]: rr_flag
    - array[7]: rr_start time
    - array[8]: rr_end time


 */

