package billingsystempackage;


import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class signuppage extends JFrame implements ActionListener{
    Choice loginascho;
    TextField metertext,employeridtext,usernametext,nametext,passtext;
    JButton create,back;
    signuppage(){
        
        // data here
        super("signup page");
        getContentPane().setBackground(Color.lightGray);


        JLabel createas=new JLabel("Create Account As: ");
        createas.setBounds(30,50,125,20);
        add(createas);

        loginascho=new Choice();
        loginascho.add("Admin");
        loginascho.add("Customer");
        loginascho.setBounds(170,50,120,20);
        add(loginascho);
        

        JLabel meterno =new JLabel("Meter Number:");
        meterno.setBounds(30,90,125,20);
        meterno.setVisible(false);
        add(meterno);

        metertext=new TextField();
        metertext.setBounds(170,90,125,20);
        metertext.setVisible(false);
        add(metertext);
        
        JLabel employer =new JLabel("Enter Emp ID:");
        employer.setBounds(30,90,125,20);
        employer.setVisible(true);
        add(employer);

        employeridtext=new TextField();
        employeridtext.setBounds(170,90,125,20);
        employeridtext.setVisible(true);
        add(employeridtext);



        JLabel username=new JLabel("Username:");
        username.setBounds(30,130,125,20);
        add(username);

        usernametext=new TextField("");
        usernametext.setBounds(170,130,125,20);
        add(usernametext);


        JLabel name=new JLabel("Name");
        name.setBounds(30,170,125,20);
        add(name);

        nametext = new TextField();
        nametext.setBounds(170,170,125,20);
        add(nametext);

        metertext.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    databaseconn c = new databaseconn();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup  where meter_no = '"+metertext.getText()+"'");
                    if (resultSet.next()){
                        nametext.setText(resultSet.getString("name"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        JLabel password=new JLabel("Password");
        password.setBounds(30,210,125,20);
        add(password);

        passtext=new TextField();
        passtext.setBounds(170,210,125,20);
        add(passtext);

        loginascho.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                String user=loginascho.getSelectedItem();
                if (user.equals("Customer")){
                    employer.setVisible(false);
                    nametext.setEditable(false);
                    employeridtext.setVisible(false);
                    meterno.setVisible(true);
                    metertext.setVisible(true);

                }else{
                    employer.setVisible(true);
                    employeridtext.setVisible(true);
                    // boolean flase;
                    meterno.setVisible(false);
                    metertext.setVisible(false);
                }
                
            }
        });

        create =new JButton("create");
        create.setBackground(Color.pink);
        create.setForeground(Color.red);
        create.setVisible(true);
        create.setBounds(40,250,100,25);
        create.addActionListener(this);
        add(create);

        back =new JButton("back");
        back.setBackground(Color.pink);
        back.setForeground(Color.red);
        back.setVisible(true);
        back.setBounds(160,250,100,25);
        back.addActionListener(this);

        add(back);

        ImageIcon bgicon=new ImageIcon("src/icons/s.png");
        Image backicon=bgicon.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
        ImageIcon backicon2=new ImageIcon(backicon);
        JLabel boylabel=new JLabel(backicon2);
        boylabel.setBounds(320,30,250,250);
        add(boylabel);





        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==create){
            String sloginas=loginascho.getSelectedItem();
            String susername=usernametext.getText();
            String sname=nametext.getText();
            String spass=passtext.getText();
            String smeter=metertext.getText();
            // code here
            try{
                databaseconn c=new databaseconn();
                String query=null;
                if(loginascho.equals("Admin")){
                query = "insert into Signup value('" + smeter + "', '" + susername + "', '" + sname + "','" + spass + "','" + sloginas + "')";
            }else{ 
                query = "update Signup set username = '"+susername+"', password = '"+spass+"', usertype = '"+sloginas+"' where meter_no = '"+smeter+"'";
            }

                 c.statement.executeUpdate(query);
                 JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(false);
                new loginpage();

                // c.state
            }
            catch(Exception a){
                System.out.println("Database Insertion Failed: " + a.getMessage());

                a.printStackTrace();

            }

        }
        else if(e.getSource()==back){
            setVisible(false);
            new loginpage();

        }

        

    }

    
    public static void main(String[] args) {
        new signuppage();
    }}
