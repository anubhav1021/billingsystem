package billingsystempackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class meterinfo extends JFrame implements ActionListener{
    Choice phaseCodeCho,billtypCho;
    JButton submit;
    String meternumber;

    meterinfo(String meternumber){
        this.meternumber=meternumber;
        // getContentPane().setBackground(new Color(106, 143, 213));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(106, 143, 213));
        // panel.setBackground(new Color(106, 143, 213));
        panel.revalidate();
        panel.repaint();

        add(panel);


        JLabel heading  =new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahome",Font.BOLD,20));
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50,80,100,20);
        panel.add(meterNumber);

        JLabel meterNumberText = new JLabel(meternumber);
         meterNumberText.setBounds(180,80,150,20);
         panel.add(meterNumberText);

        

        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,150,100,20);
        panel.add(phaseCode);

        phaseCodeCho =new Choice();
        phaseCodeCho.add("011");
        phaseCodeCho.add("022");
        phaseCodeCho.add("033");
        phaseCodeCho.add("044");
        phaseCodeCho.add("055");
        phaseCodeCho.add("066");
        phaseCodeCho.add("077");
        phaseCodeCho.add("088");
        phaseCodeCho.add("099");
        phaseCodeCho.setBounds(180,150,150,20);
        panel.add(phaseCodeCho);



        JLabel billtyp = new JLabel("Bill Type");
        billtyp.setBounds(50,200,100,20);
        panel.add(billtyp);

        billtypCho =new Choice();
        billtypCho.add("Normal");
        billtypCho.add("Industrial");
        billtypCho.setBounds(180,200,150,20);
        panel.add(billtypCho);


        

        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50,280,150,20);
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);

        JLabel note1 = new JLabel("By default bill is calculated for 30 days only");
        note1.setBounds(50,340,300,20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        // ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sarso.jpg"));
        // Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_SMOOTH);
        // ImageIcon i3 = new ImageIcon(i2);
        // JLabel imgLabel = new JLabel(i3);
        // add(imgLabel,"East");




        
        setSize(700,500);
        setLocation(400,200);
        setVisible(true);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit){
            String smeterNum = meternumber;
            String sphaseCode = phaseCodeCho.getSelectedItem();
            String sbillTyp = billtypCho.getSelectedItem();
            String sday ="30";


            String query_meterInfo = "INSERT INTO meter_info (meter_number, phase_code, bill_type, days) VALUES ('"+smeterNum+"','"+sphaseCode+"','"+sbillTyp+"','30')";
            try{
                databaseconn c= new databaseconn();
                c.statement.executeUpdate(query_meterInfo);

                JOptionPane.showMessageDialog(null,"Meter Information Submited Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }



    }
    


    public static void main(String[] args) {
        new meterinfo("");

        
    }}