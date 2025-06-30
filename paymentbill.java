package billingsystempackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class paymentbill extends JFrame implements ActionListener {
    JButton back;
    String meter;
    paymentbill(String meter){
        this.meter = meter;
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try{
            j.setPage("https://paytm.com/online-payments");
            j.setBounds(400,150,800,600);
        }catch (Exception E){
            E.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html>Errorr! Errorr!  Errorr!  Errorr!  Errorr!  Errorr! </html>");
        }

        JScrollPane pane = new JScrollPane(j);
        add(pane);

        back = new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);


        setSize(800,600);
        setLocation(400,150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new paybill(meter);
    }

    public static void main(String[] args) {
        new paymentbill("");
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    }
