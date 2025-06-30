package billingsystempackage;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class generatebill extends JFrame implements ActionListener {
    String meter;
    Choice searchmonthcho;
     JTextArea area;
    JButton bill;
    generatebill(String meter){
        this.meter=meter;
        setSize(500,700);
        setLocation(500,30);
        setLayout( new BorderLayout());
        JPanel panel = new JPanel();



        JLabel heading = new JLabel("Generate Bill");

        JLabel meter_no = new JLabel(meter);

        searchmonthcho = new Choice();
        searchmonthcho.add("January");
        searchmonthcho.add("February");
        searchmonthcho.add("March");
        searchmonthcho.add("April");
        searchmonthcho.add("May");
        searchmonthcho.add("June");
        searchmonthcho.add("July");
        searchmonthcho.add("August");
        searchmonthcho.add("September");
        searchmonthcho.add("October");
        searchmonthcho.add("November");
        searchmonthcho.add("December");



         area= new JTextArea(50,15);
        area.setText("\n \n \t ------------------- Click on the ---------------\n \t ------------------- Generate Bill");
        area.setFont(new Font("Senserif",Font.ITALIC,15));
        JScrollPane pane = new JScrollPane(area);
        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

        add(pane);

        panel.add(heading);
        panel.add(meter_no);
        panel.add(searchmonthcho);
        add(panel,"North");
        add(bill,"South");

        setVisible(true);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{  

          databaseconn c = new databaseconn();
            String smonth =  searchmonthcho.getSelectedItem();
            area.setText("\n Power Limited \n Electricity Bill For Month of "+smonth+",2025\n\n\n");
            ResultSet customerResult = c.statement.executeQuery("SELECT * FROM new_customer1 WHERE meter_no ='" + meter + "'");
if (customerResult.next()) {
    area.append("\n    Customer Name        : " + customerResult.getString("name"));
    area.append("\n    Customer Meter Number: " + customerResult.getString("meter_no"));
    area.append("\n    Customer Address     : " + customerResult.getString("address"));
    area.append("\n    Customer City        : " + customerResult.getString("city"));
    area.append("\n    Customer State       : " + customerResult.getString("state"));
    area.append("\n    Customer Email       : " + customerResult.getString("email"));
    area.append("\n    Customer Phone Number: " + customerResult.getString("phone_no"));
}

ResultSet meterResult = c.statement.executeQuery("SELECT * FROM meter_info WHERE meter_number ='"+ meter +"'");
if (meterResult.next()) {

    area.append("\n    Customer Phase Code: " + meterResult.getString("phase_code"));
    area.append("\n    Customer Bill Type: " + meterResult.getString("bill_type"));
    area.append("\n    Customer Days: " + meterResult.getString("Days"));
}


ResultSet taxResult = c.statement.executeQuery("SELECT * FROM tax");
if (taxResult.next()) {
    area.append("\n    Cost Per Unit: " + taxResult.getString("cost_per_unit"));
    area.append("\n    Meter Rent: " + taxResult.getString("meter_rent"));
    area.append("\n    Service Charge: " + taxResult.getString("service_charge"));
    area.append("\n    Service Tax: " + taxResult.getString("additional_charge"));
   
}


ResultSet billResult = c.statement.executeQuery("SELECT * FROM bill1 WHERE meter_no = '" + meter + "' AND month = '" + searchmonthcho.getSelectedItem() + "'");
if (billResult.next()) {
    area.append("\n    Current Month: " + billResult.getString("month"));
    area.append("\n    Units Consumed: " + billResult.getString("unit"));
    area.append("\n    Total Charges: " + billResult.getString("total_bill"));
    area.append("\n    Total Payable: " + billResult.getString("total_bill"));
}


        
        }
        catch(Exception q){
            q.printStackTrace();

        }
        
        
        
        
        
        
        
        
    }
    public static void main(String[] args) {
        new generatebill("");
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
}
