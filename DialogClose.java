import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.*;

public class DialogClose extends JDialog {

    public DialogClose() {
        this.setLayout(new GridLayout(2, 2));
        
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0,2);
        layout.setHgap(10);
        
        this.add(new JLabel("Are you sure you want to exit?", JLabel.CENTER));
        panel.add(new JButton(new AbstractAction("Yes") {

            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            	
                DialogClose.this.setVisible(false);
                DialogClose.this.dispatchEvent(new WindowEvent(
                    DialogClose.this, WindowEvent.WINDOW_CLOSING));
            }
  
        }));
        
        panel.add(new JButton(new AbstractAction("No") {

            @Override
            public void actionPerformed(ActionEvent e) {
                DialogClose.this.setVisible(false);
                DialogClose.this.dispatchEvent(new WindowEvent(
                    DialogClose.this, WindowEvent.WINDOW_CLOSING));
            }
  
        }));
        
        this.add(panel);
    }

    void display() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    
}