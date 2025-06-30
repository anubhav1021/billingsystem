package billingsystempackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class loginpage extends JFrame implements ActionListener {
    JTextField usertext,userpass;
    Choice loginChoice;
    JButton loginbutton,cancelbutton,signupbutton;
    loginpage(){
        super("LOGIN PAGE");
        getContentPane().setBackground(new Color(106, 143, 213));


        JLabel username = new JLabel("Username");
        username.setFont(new Font("SansSerif", Font.BOLD, 16)); 
        
        username.setBounds(300, 60, 100, 20);
        add(username);
        

        usertext=new JTextField();
        usertext.setBounds(400,60,150,20);
        add(usertext);


        JLabel password=new JLabel("Password");
        password.setBounds(300,100,100,20);
        password.setFont(new Font("SansSerif", Font.BOLD, 16)); // Set font family, style, and size

        add(password);

        userpass=new JTextField();
        userpass.setBounds(400,100,150,20);
        add(userpass);


        JLabel login=new JLabel("Login as-");
        login.setBounds(300,140,100,20);
        login.setFont(new Font("SansSerif", Font.BOLD, 16)); // Set font family, style, and size

        add(login);

        loginChoice=new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,140,150,20);
        add(loginChoice);


        loginbutton=new JButton("Login");
        loginbutton.setBounds(350,190,100,20);
        loginbutton.addActionListener(this);
        add(loginbutton);

        cancelbutton=new JButton("Cancel");
        cancelbutton.setBounds(470,190,100,20);
        cancelbutton.addActionListener(this);
        add(cancelbutton);

        signupbutton=new JButton("Signup");
        signupbutton.setBounds(400,230,100,20);
        signupbutton.addActionListener(this);
        add(signupbutton);

        ImageIcon profile=new ImageIcon("src/icons/login123.png");
        Image profile2=profile.getImage().getScaledInstance(250,250,Image.SCALE_SMOOTH);
        ImageIcon fprofile=new ImageIcon(profile2);
        JLabel profileLabel =new JLabel(fprofile);
        profileLabel.setBounds(0,5 ,250,250);
        add(profileLabel);



        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
        // setBackground(new Color(456,234,234));
            
        }
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==loginbutton){
                
                String susername =usertext.getText();
                String spassword=userpass.getText();
                String user=loginChoice.getSelectedItem();

                try {
                    databaseconn c=new databaseconn();
                    String query = "select * from Signup where username = '"+susername+"' and password = '"+spassword+"' and usertype ='"+user+"'";          
                    ResultSet resultset=c.statement.executeQuery(query);
                    

                    if (resultset.next()){
                        String meter = resultset.getString("meter_no");
                     setVisible(false);
                     new main_class(user,meter);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Invalid login");
                    }
                } catch (Exception q) {
                    q.printStackTrace();

                }

            }
        
        else if(e.getSource()==cancelbutton){
            setVisible(false);
        }
        else if (e.getSource()==signupbutton){
            setVisible(false);
            new signuppage();
        }
    }

    public static void main(String[] args) {
        
        new loginpage();
    }
    
}
