package billingsystempackage;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class customerdetails extends JFrame implements ActionListener {
    Choice searchMeterCho, searchNameCho;
    JTable table;
    JButton search, print, close;

    customerdetails() {
        super("Customer Details");
        getContentPane().setBackground(new Color(79, 108, 102));
        setSize(700, 500);
        setLocation(400, 200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180, 20, 150, 20);
        add(searchMeterCho);

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(400, 20, 150, 20);
        add(searchName);

        searchNameCho = new Choice();
        searchNameCho.setBounds(550, 20, 150, 20);
        add(searchNameCho);

        // Fetching database records
        try {
            databaseconn c = new databaseconn();
            ResultSet resultset = c.statement.executeQuery("SELECT * FROM new_customer1");
            while (resultset.next()) {
                searchMeterCho.add(resultset.getString("meter_no"));
                searchNameCho.add(resultset.getString("name"));
            }
            c.statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buttons
        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20, 70, 80, 25);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120, 70, 80, 25);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600, 70, 80, 25);
        close.addActionListener(this);
        add(close);

        // Table & Scroll Pane
        table = new JTable();
        try {
            databaseconn c = new databaseconn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM new_customer1");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception w) {
            w.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 110, 700, 350); // Moved down so buttons are visible
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==search){
            String query_search = "select * from new_customer1 where meter_no = '"+searchMeterCho.getSelectedItem()+"' or name = '"+searchNameCho.getSelectedItem()+"'" ;
            try{
                databaseconn c = new databaseconn();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==print)
        {
            try {
                table.print();
            } catch (Exception E) {
                E.printStackTrace();
            }

        }else {
            setVisible(false);
        }}
    

    public static void main(String[] args) {
        new customerdetails();
    }}
