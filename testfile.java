package billingsystempackage;
import java.awt.event.*;
import javax.swing.*;

 class MyFrame extends JFrame implements ActionListener {
    JButton button;

    public MyFrame() {
        button = new JButton("Click Me");
        button.addActionListener(this);  // 'this' refers to the current class implementing ActionListener

        add(button);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Overriding actionPerformed method (required by ActionListener)
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button Clicked!");
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
