package billingsystempackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class main_class extends JFrame implements ActionListener {
    String acctype;
    String meter_pass;

    main_class(String acctype, String meter_pass) {
        this.acctype = acctype;
        this.meter_pass = meter_pass;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon imageicon = new ImageIcon(ClassLoader.getSystemResource("icons/splash_11zon.jpg"));
        Image image = imageicon.getImage().getScaledInstance(1530, 830, Image.SCALE_SMOOTH);
        ImageIcon image2 = new ImageIcon(image);
        JLabel imagelabel = new JLabel(image2);
        add(imagelabel);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        if (acctype.equals("Admin")) {
            JMenu menu = new JMenu("File");
            menu.setFont(new Font("serif", Font.ROMAN_BASELINE, 15));
            menubar.add(menu);

            JMenuItem newcustomer = createMenuItem("New Customer", "icons/new-account.png");
            menu.add(newcustomer);

            JMenuItem customerdet = createMenuItem("Customer Details", "icons/information.png");
            menu.add(customerdet);

            JMenuItem calculatebill = createMenuItem("Calculate Bill", "icons/accounting.png");
            menu.add(calculatebill);
        } else if (acctype.equals("Customer")) {
            JMenu user = new JMenu("User");
            user.setFont(new Font("serif", Font.ROMAN_BASELINE, 15));
            menubar.add(user);

            JMenuItem billdetails = createMenuItem("Billdetails", "icons/bill.png");
            user.add(billdetails);

            JMenu bill = new JMenu("Bill");
            bill.setFont(new Font("serif", Font.ROMAN_BASELINE, 15));
            menubar.add(bill);

            JMenuItem genbill = createMenuItem("Generate Bill", "icons/icons8-bill-100.png");
            bill.add(genbill);

            JMenu info = new JMenu("Other");
            info.setFont(new Font("serif", Font.ROMAN_BASELINE, 15));
            menubar.add(info);

            JMenuItem upinfo = createMenuItem("Update Info", "icons/update.png");
            info.add(upinfo);

            JMenuItem viewinfo = createMenuItem("View Info", "icons/inquiry.png");
            info.add(viewinfo);
        }

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif", Font.ROMAN_BASELINE, 15));
        menubar.add(exit);

        JMenuItem eexit = createMenuItem("Exit", "icons/logout.png");
        exit.add(eexit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    private JMenuItem createMenuItem(String title, String iconPath) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("monospaced", Font.BOLD, 14));
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        menuItem.setIcon(new ImageIcon(img));
        menuItem.addActionListener(this);
        return menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if (msg.equals("New Customer")) {
            new newCustomer();
        } else if (msg.equals("Customer Details")) {
            new customerdetails();
        } else if (msg.equals("Calculate Bill")) {
            new calculatebill();
        } else if (msg.equals("View Info")) {
            new view_information(meter_pass);
        } else if (msg.equals("Update Info")) {
            new update_information(meter_pass);
        } else if (msg.equals("Billdetails")) {
            new bill_details(meter_pass);
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new loginpage();
        } else if (msg.equals("Generate Bill")) {
            new generatebill(meter_pass);
        }
    }

    public static void main(String[] args) {
        new main_class("", "");
    }
}
