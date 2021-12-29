import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
Useful tutorial:
About JList: https://www.youtube.com/watch?v=lRupi3iJmzk&list=PLM-syYolLbszU7ZATekAlWqiQx9O3XSC1&index=5&ab_channel=PaulBaumgarten
About displaying on split pane: https://www.youtube.com/watch?v=KOI1WbkKUpQ&ab_channel=LazicB.
 */

public class EmergencyUIController extends JFrame {
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JButton emergencyButton;
    private JButton reportButton;
    private JButton wardButton;
    private JPanel contentPanel;
    private JLabel urgentLabel;
    private JLabel warningLabel;
    private JPanel urgentDetailsPanel;

    private JList UrgentPatName;
    private DefaultListModel<String> UModel;
    // later need to combine with alertList<Alert>, check out 1st video

    private JLabel urgentDetailsLabel;
    private JPanel warningDetailsPanel;
    private JList WarningPatName;
    private DefaultListModel<String> WModel;

    private JLabel warningDetailsLabel;
    private JList urgentDetailsList;
    private JList warningDetailsList;

    public EmergencyUIController(ArrayList<Patient> patientList){

        final int[] counter = {0};
        Timer timer = new Timer();
        TimerTask displayAlert = new TimerTask() {
            @Override
            public void run() {

                System.out.println((counter[0]+1));

                UModel = new DefaultListModel();
                urgentDetailsList.setModel(UModel);

                WModel = new DefaultListModel();
                warningDetailsList.setModel(WModel);

                for (Patient aPat:patientList){
                    if (aPat.alertStatus == "Urgent"){
                        UModel.addElement(aPat.name + " " + aPat.abnormalDetails + " " + aPat.patLoc);
                    }
                    if (aPat.alertStatus == "Warning"){
                        WModel.addElement(aPat.name + " " + aPat.abnormalDetails + " " + aPat.patLoc);
                    }

                    System.out.print(aPat.name + " " + aPat.alertStatus+" "+ aPat.abnormalDetails+ "\n" + aPat.alertHistoryTemp+"\n" + aPat.alertHistoryHR+"\n"+aPat.alertHistoryRR+"\n");

                }

                counter[0]++;
                if (counter[0]==20){        // hard code the time to stop
                    timer.cancel();
                }

            }
        };
        timer.schedule(displayAlert, 0, 1000);
/*
No need now:

        UrgentPatName.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Alert pat = (Alert) UrgentPatName.getSelectedValue();
                urgentDetailsLabel.setText(pat.status + "\n" + pat.abnormalDetails + "\n Locate at " + pat.patLoc);
            }
        });

        WarningPatName.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Alert pat = (Alert) WarningPatName.getSelectedValue();
                warningDetailsPanel.setText(pat.status + "\n" + pat.abnormalDetails + "\n Locate at " + pat.patLoc);
            }
        });

 */

        ActionListener switchWard = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to Ward UI, for now:
                System.out.println("Switching to Ward page...");
            }
        };
        wardButton.addActionListener(switchWard);

        ActionListener switchReport = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to Ward UI, for now:
                System.out.println("Switching to Report page...");
            }
        };
        reportButton.addActionListener(switchReport);

        ActionListener switchEmergency = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to Ward UI, for now:
                System.out.println("Already on emergency page...");
            }
        };
        emergencyButton.addActionListener(switchEmergency);


        setContentPane(mainPanel);
        setTitle("PatientMed");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }


}
