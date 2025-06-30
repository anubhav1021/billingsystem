package billingsystempackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class newCustomer extends JFrame implements ActionListener {
    JLabel heading, custname, meterNum, address, city, state, email, phone,meternumText;
    TextField nametext,  addressText, cityText, stateText, emailText, phoneText;
    JButton next, cancel;

    newCustomer() {
        super("New Customer");

        // Set layout first
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(215, 137, 131));
        add(panel, "Center");

        heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        custname = new JLabel("Enter Name:");
        custname.setBounds(50, 80, 100, 20);
        panel.add(custname);

        nametext = new TextField();
        nametext.setBounds(180, 80, 150, 20);
        panel.add(nametext);

        meterNum = new JLabel("Meter Number:");
        meterNum.setBounds(50, 120, 100, 20);
        panel.add(meterNum);

        meternumText = new JLabel("");
        meternumText.setBounds(180, 120, 150, 20);
        // meternumText.setEditable(false); // Make meter number read-only
        panel.add(meternumText);

        Random ran = new Random();
        long number = ran.nextInt(1000000);
        meternumText.setText("" + number);

        address = new JLabel("Address:");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City:");
        city.setBounds(50, 200, 100, 20);
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State:");
        state.setBounds(50, 240, 100, 20);
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email:");
        email.setBounds(50, 280, 100, 20);
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);

        phone = new JLabel("Phone:");
        phone.setBounds(50, 320, 100, 20);
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230, 390, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/new_customer.png"));
        Image i2 = i1.getImage().getScaledInstance(230, 200, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel = new JLabel(i3);
        add(imgLabel, "West");

        setSize(700, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==next){
            String sname = nametext.getText();
            String smeter = meternumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();


            String query_customer = "insert into new_customer1 values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+eemail+"','"+sphone+"')";
            String query_signup = "insert into Signup values('"+smeter+"','','"+sname+"','','')";
            try{
                databaseconn c = new databaseconn();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                setVisible(false);
                new meterinfo(smeter);
            }catch (Exception E){
                E.printStackTrace();
            }
        }
            

else  {
            // JOptionPane.showMessageDialog(null, "Save to database (to be implemented)");
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}
