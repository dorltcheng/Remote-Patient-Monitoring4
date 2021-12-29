import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public interface mainMenu {

    ArrayList<Alert> alertList = new ArrayList<Alert>();

    static void realTimeAlertChecker(ArrayList<Patient> patientList) {
        double tempUThreshold = 40.0;
        double hrUThreshold = 130.0;
        double rrUThreshold = 25.0;

        double tempWThreshold = 38.0;
        double hrWThreshold = 110.0;
        double rrWThreshold = 20.0;

        int duration = patientList.get(0).length;       // need this cuz now the data is small, if 24h data do not need to cancel the timer cuz it will keep looping
        final int[] i = {0};

        for (Patient pat:patientList){
            pat.tempFlag = "H";

            pat.hrFlag = "H";

            pat.rrFlag = "H";
        }

        Clock clock = Clock.systemDefaultZone();

        Timer timer = new Timer();
        TimerTask alertCheck = new TimerTask(){

            @Override
            public void run() {

                /* About alertHistory
                    - alertHistory.add()
                 */


                for (Patient pat:patientList) {

                    pat.alertStatus = "Healthy";
                    pat.abnormalDetails.clear();

                    // TEMPERATURE
                    if (pat.tempFlag == "H"){
                        if (pat.temp[i[0]] > tempWThreshold && pat.temp[i[0]] < tempUThreshold) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.alertStatus = "Warning";
                            pat.tempFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning Start: "+ instant.toString());
                        }
                        if (pat.temp[i[0]] > tempUThreshold) {
                            String temp = "Very High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.alertStatus = "Urgent";
                            pat.tempFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent Start: "+instant.toString());
                        }
                    }
                    else if (pat.tempFlag == "W"){
                        if (pat.temp[i[0]] > tempWThreshold && pat.temp[i[0]] < tempUThreshold) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.alertStatus = "Warning";
                        }
                        if (pat.temp[i[0]] > tempUThreshold) {
                            String temp = "Very High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.alertStatus = "Urgent";
                            pat.tempFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning ends: "+ instant.toString());
                            pat.alertHistoryTemp.add("Urgent starts: "+ instant.toString());
                        }
                        if (pat.temp[i[0]] < tempWThreshold) {
                            pat.alertStatus = "Healthy";
                            pat.tempFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Warning ends: " +instant.toString());
                        }
                    }
                    else if (pat.tempFlag == "U"){
                        if (pat.temp[i[0]] > tempWThreshold && pat.temp[i[0]] < tempUThreshold) {
                            String temp = "High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.alertStatus = "Warning";
                            pat.tempFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent ends: "+ instant.toString());
                            pat.alertHistoryTemp.add("Warning starts: "+ instant.toString());

                        }
                        if (pat.temp[i[0]] > tempUThreshold) {
                            String temp = "Very High Temperature";
                            pat.abnormalDetails.add(temp);
                            pat.alertStatus = "Urgent";
                        }
                        if (pat.temp[i[0]] < tempWThreshold) {
                            pat.alertStatus = "Healthy";
                            pat.tempFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryTemp.add("Urgent ends: " +instant.toString());
                        }
                    }

                    // HEART RATE
                    if (pat.hrFlag == "H"){
                        if (pat.hr[i[0]] > hrWThreshold && pat.hr[i[0]] < hrUThreshold) {
                            String hr = "High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.alertStatus = "Warning";
                            pat.hrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning Start: "+ instant.toString());
                        }
                        if (pat.hr[i[0]] > hrUThreshold) {
                            String hr = "Very High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.alertStatus = "Urgent";
                            pat.hrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent Start: "+instant.toString());
                        }
                    }
                    else if (pat.hrFlag == "W"){
                        if (pat.hr[i[0]] > hrWThreshold && pat.hr[i[0]] < hrUThreshold) {
                            String hr = "High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.alertStatus = "Warning";
                        }
                        if (pat.hr[i[0]] > hrUThreshold) {
                            String hr = "Very High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.alertStatus = "Urgent";
                            pat.hrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning ends: "+ instant.toString());
                            pat.alertHistoryHR.add("Urgent starts: "+ instant.toString());
                        }
                        if (pat.hr[i[0]] < hrWThreshold) {
                            pat.alertStatus = "Healthy";
                            pat.hrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Warning ends: " +instant.toString());
                        }
                    }
                    else if (pat.hrFlag == "U"){
                        if (pat.hr[i[0]] > hrWThreshold && pat.hr[i[0]] < hrUThreshold) {
                            String hr = "High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.alertStatus = "Warning";
                            pat.hrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent ends: "+ instant.toString());
                            pat.alertHistoryHR.add("Warning starts: "+ instant.toString());

                        }
                        if (pat.hr[i[0]] > hrUThreshold) {
                            String hr = "Very High Heart Rate";
                            pat.abnormalDetails.add(hr);
                            pat.alertStatus = "Urgent";
                        }
                        if (pat.hr[i[0]] < hrWThreshold) {
                            pat.alertStatus = "Healthy";
                            pat.hrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryHR.add("Urgent ends: " +instant.toString());
                        }
                    }

                    // RESPIRATORY RATE
                    if (pat.rrFlag == "H"){
                        if (pat.rr[i[0]] > rrWThreshold && pat.rr[i[0]] < rrUThreshold) {
                            String rr = "High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Warning";
                            pat.rrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning Start: "+ instant.toString());
                        }
                        if (pat.rr[i[0]] > rrUThreshold) {
                            String rr = "Very High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Urgent";
                            pat.rrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent Start: "+instant.toString());
                        }
                    }
                    else if (pat.rrFlag == "W"){
                        if (pat.rr[i[0]] > rrWThreshold && pat.rr[i[0]] < rrUThreshold) {
                            String rr = "High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Warning";
                        }
                        if (pat.rr[i[0]] > rrUThreshold) {
                            String rr = "Very High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Urgent";
                            pat.rrFlag = "U";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning ends: "+ instant.toString());
                            pat.alertHistoryRR.add("Urgent starts: "+ instant.toString());
                        }
                        if (pat.rr[i[0]] < rrWThreshold) {
                            pat.alertStatus = "Healthy";
                            pat.rrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Warning ends: " +instant.toString());
                        }
                    }
                    else if (pat.rrFlag == "U"){
                        if (pat.rr[i[0]] > rrWThreshold && pat.rr[i[0]] < rrUThreshold) {
                            String rr = "High Respiratory Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Warning";
                            pat.rrFlag = "W";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent ends: "+ instant.toString());
                            pat.alertHistoryRR.add("Warning starts: "+ instant.toString());

                        }
                        if (pat.rr[i[0]] > rrUThreshold) {
                            String rr = "Very High Heart Rate";
                            pat.abnormalDetails.add(rr);
                            pat.alertStatus = "Urgent";
                        }
                        if (pat.rr[i[0]] < rrWThreshold) {
                            pat.alertStatus = "Healthy";
                            pat.rrFlag = "H";
                            Instant instant = clock.instant();
                            pat.alertHistoryRR.add("Urgent ends: " +instant.toString());
                        }
                    }

                }

                i[0]++;
                if (i[0] == duration){
                    timer.cancel();
                }

            }
        };
        timer.schedule(alertCheck, 0,1000);

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


