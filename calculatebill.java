package billingsystempackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.*;
// import javax.swing.JPanel;

public class calculatebill extends JFrame implements ActionListener{
    TextField unitText;
    JLabel nametext,addressText;
    Choice meternumCho,monthCho;
    JButton submit,cancel;
    calculatebill(){

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(188, 77, 161));
        add(panel);

        JLabel heading =new JLabel("calculate electricity bill");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20 ));
        panel.add(heading);

        JLabel meternum=new JLabel("meter number");
        meternum.setBounds(50,80,100,20);
        panel.add(meternum);

        meternumCho = new Choice();
        try {
            databaseconn c = new databaseconn();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer1");
            while (resultSet.next()){
                meternumCho.add(resultSet.getString("meter_no"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        meternumCho.setBounds(180,80,100,20);
        panel.add(meternumCho);

        JLabel name = new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);


        nametext=new JLabel("");
        nametext.setBounds(180,120,150,20);
        panel.add(nametext);


        JLabel address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180,160,150,20);
        panel.add(addressText);


        try {
            databaseconn c = new databaseconn();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer1 where meter_no = '"+meternumCho.getSelectedItem()+"' ");
            
            while (resultSet.next()){
                nametext.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));

            }
        }catch (Exception E){
            E.printStackTrace();
        }
        meternumCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    databaseconn c= new databaseconn();
                    ResultSet resultSet = c.statement.executeQuery("select * from new_customer1 where meter_no = '"+meternumCho.getSelectedItem()+"' ");
                    while (resultSet.next()){
                        nametext.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setBounds(50,200,100,20);
        panel.add(unitconsumed);

        unitText = new TextField();
        unitText.setBounds(180,200,150,20);
        panel.add(unitText);



        JLabel month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);


        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180,240,150,20);
        panel.add(monthCho);


        submit = new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);



        cancel = new JButton("Cancel");
        cancel.setBounds(220,300,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);






        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/Calculate_bill.png"));
        Image image = imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel,"East");



        





        setSize(650,400);
        setLocation(400,200);
        setVisible(true);}




        @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit){

            String smeterNo = meternumCho.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCho.getSelectedItem();

            int totalbill=0;
            int units=Integer.parseInt(sunit);
            String query_tax="select * from tax";

            try {
                databaseconn c =new databaseconn();
                ResultSet resultSet=c.statement.executeQuery(query_tax);
                while (resultSet.next()){
                    totalbill+=units*Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalbill+=Integer.parseInt(resultSet.getString("meter_rent"));
                    totalbill+=Integer.parseInt(resultSet.getString("service_charge"));
                    totalbill+=Integer.parseInt(resultSet.getString("additional_charge"));



                }
                
            } catch ( Exception w) {
                w.printStackTrace();

            }
            String query_total_bill = "insert into bill1 values('"+smeterNo+"', '"+smonth+"','"+sunit+"', '"+totalbill+"','Not Paid')";

            try{
                databaseconn c =new databaseconn();
                c.statement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null, "customer bill updated successfully");
            }
            catch(Exception r){
                r.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }

        // throw new UnsupportedOperationException("Not supported yet.");
    }

        
    
    public static void main(String[] args) {
        new calculatebill();
    }}

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }

