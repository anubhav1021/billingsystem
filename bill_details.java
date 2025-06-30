package billingsystempackage;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class bill_details extends JFrame{
    String meter;
    ResultSet resultSet;
    bill_details(String meter){
        this.meter=meter;

        setSize(700,650);
        // setBounds(100, 200, 20, 150);
        setLocation(400,150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        // setVisible(true);




        JTable table =new JTable();
        try {
            databaseconn c=new databaseconn();
            String query_bill = "select * from bill1 where meter_no = '"+meter+"'";
            ResultSet resultSet= c.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }


        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);

        setVisible(true);

    }
    public static void main(String[] args) {
        // System.out.println("hello world");
        new bill_details("");
    }
    
}
