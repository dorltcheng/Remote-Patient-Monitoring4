import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public interface mainMenu {

    ArrayList<Alert> alertList = new ArrayList<Alert>();

    /*
    Should have the method to get PatientList from database class:
        public getPatientList()

    */
    /*
    static ArrayList<Alert> alertChecker(ArrayList<Patient> patientList) {
        double tempUThreshold = 40.0;
        double hrUThreshold = 130.0;
        double rrUThreshold = 25.0;

        double tempWThreshold = 38.0;
        double hrWThreshold = 110.0;
        double rrWThreshold = 20.0;

        // We are not accounting for abnormalities of ECG and BP here (wave format)

        // Assuming patient's temp, bp, hr, rr are all doubles, need further conversion later
        for (Patient pat : patientList) {

            List<String> abnormalDetails = new ArrayList<String>();

            int i = pat.length;

            for (i = 0; i < pat.length; i++) {
                if (pat.temp[i] > tempWThreshold && pat.temp[i] < tempUThreshold) {
                    String temp = "High Temperature";
                    abnormalDetails.add(temp);
                    pat.alertStatus = "Warning";
                    pat.alertHistory.add("High Temperature @time: " + i + " second \n");
                }

                if (pat.hr[i] > hrWThreshold && pat.hr[i] < hrUThreshold) {
                    String hr = "High Heart Rate";
                    abnormalDetails.add(hr);
                    pat.alertStatus = "Warning";
                    pat.alertHistory.add("High Heart Rate @time: " + i + " second \n");
                }

                if (pat.rr[i] > rrWThreshold && pat.rr[i] < rrUThreshold) {
                    String rr = "High Respiratory Rate";
                    abnormalDetails.add(rr);
                    pat.alertStatus = "Warning";
                    pat.alertHistory.add("High Respiratory Rate @time: " + i + " second \n");
                }

                if (pat.temp[i] > tempUThreshold) {
                    String temp = "Very High Temperature";
                    abnormalDetails.add(temp);
                    pat.alertStatus = "Urgent";
                    pat.alertHistory.add("Very High Temperature @time: " + i + " second \n");
                }

                if (pat.hr[i] > hrUThreshold) {
                    String hr = "Very High Heart Rate";
                    abnormalDetails.add(hr);
                    pat.alertStatus = "Urgent";
                    pat.alertHistory.add("Very High Heart Rate @time: " + i + " second \n");
                }

                if (pat.rr[i] > rrUThreshold) {
                    String rr = "Very High Respiratory Rate";
                    abnormalDetails.add(rr);
                    pat.alertStatus = "Urgent";
                    pat.alertHistory.add("Very High Respiratory Rate @time: " + i + " second \n");
                }

            }
            // Add the emergency patients into alertList by creating an Alert class object for each of them
            if (pat.alertStatus == "Warning" || pat.alertStatus == "Urgent") {
                Alert a = new Alert(pat.name, pat.alertStatus, abnormalDetails, pat.patLoc);
                alertList.add(a);
            }
            // abnormalDetails.clear();
        }
        return alertList;
    }

     */

    static ArrayList<Alert> realTimeAlertChecker(ArrayList<Patient> patientList) {
        double tempUThreshold = 40.0;
        double hrUThreshold = 130.0;
        double rrUThreshold = 25.0;

        double tempWThreshold = 38.0;
        double hrWThreshold = 110.0;
        double rrWThreshold = 20.0;

        int duration = patientList.get(0).length;       // need this cuz now the data is small, if 24h data do not need to cancel the timer cuz it will keep looping
        final int[] i = {0};

        Timer timer = new Timer();
        TimerTask alertCheck = new TimerTask(){

            @Override
            public void run() {
                
                // Assuming patient's temp, bp, hr, rr are all doubles, need further conversion later

                for (Patient pat:patientList) {

                    pat.alertStatus = "Healthy";
                    pat.abnormalDetails.clear();

                    if (pat.temp[i[0]] > tempWThreshold && pat.temp[i[0]] < tempUThreshold) {
                        String temp = "High Temperature";
                        pat.abnormalDetails.add(temp);
                        pat.alertStatus = "Warning";
                        pat.alertHistory.add("High Temperature @time: " + i[0] + " second \n");
                    }

                    if (pat.hr[i[0]] > hrWThreshold && pat.hr[i[0]] < hrUThreshold) {
                        String hr = "High Heart Rate";
                        pat.abnormalDetails.add(hr);
                        pat.alertStatus = "Warning";
                        pat.alertHistory.add("High Heart Rate @time: " + i[0] + " second \n");
                    }

                    if (pat.rr[i[0]] > rrWThreshold && pat.rr[i[0]] < rrUThreshold) {
                        String rr = "High Respiratory Rate";
                        pat.abnormalDetails.add(rr);
                        pat.alertStatus = "Warning";
                        pat.alertHistory.add("High Respiratory Rate @time: " + i[0] + " second \n");
                    }

                    if (pat.temp[i[0]] > tempUThreshold) {
                        String temp = "Very High Temperature";
                        pat.abnormalDetails.add(temp);
                        pat.alertStatus = "Urgent";
                        pat.alertHistory.add("Very High Temperature @time: " + i[0] + " second \n");
                    }

                    if (pat.hr[i[0]] > hrUThreshold) {
                        String hr = "Very High Heart Rate";
                        pat.abnormalDetails.add(hr);
                        pat.alertStatus = "Urgent";
                        pat.alertHistory.add("Very High Heart Rate @time: " + i[0] + " second \n");
                    }

                    if (pat.rr[i[0]] > rrUThreshold) {
                        String rr = "Very High Respiratory Rate";
                        pat.abnormalDetails.add(rr);
                        pat.alertStatus = "Urgent";
                        pat.alertHistory.add("Very High Respiratory Rate @time: " + i[0] + " second \n");
                    }

                    // Add the emergency patients into alertList by creating an Alert class object for each of them
                    if (pat.alertStatus == "Warning" || pat.alertStatus == "Urgent") {
                        Alert a = new Alert(pat.name, pat.alertStatus, pat.abnormalDetails, pat.patLoc);
                        alertList.add(a);
                    }
                    //abnormalDetails.clear();
                    //pat.alertStatus = "Healthy";

                }

                i[0]++;
                if (i[0] == duration){
                    timer.cancel();
                }

            }
        };

        timer.schedule(alertCheck, 0,1000);

        return alertList;
    }

}

/* notes:

I have a TimerTask, at each second: I have to take the first element (i=0) of the array and determine if it is in abnormal
i=1
repeat the second time

 */


/*
Notes:
1. Remove BP and ECG abnormalities
2. Save the date and time and recent alerts when abnormal
3. How to read data from

- need a clock in the app
- need a time indication in the data
- when the app run, match the time with the time indication in the data
    - then start looking at abnormal values
    - then save the time and date when it goes abnormal

 */


