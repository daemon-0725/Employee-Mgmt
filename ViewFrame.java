import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame {
	Container c;
	JTextArea taData;
	JButton btnBack;
	JScrollPane spData;
	JPanel p1,p2;
	ViewFrame(){
		c=getContentPane();
		//c.setLayout(new FlowLayout());
		p1=new JPanel();
		taData = new JTextArea(3,25);
		taData.setEditable(false);
		spData = new JScrollPane(taData);
		p1.add(spData);
		c.add(p1);

		DatabaseHandler d = new DatabaseHandler();
		String s = d.viewEmployee();
		taData.setText(s);

		p2=new JPanel();
		btnBack = new JButton("Back");
		p2.add(btnBack);
		c.add("South",p2);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				MainFrame a = new MainFrame();
				dispose();
			}
		});

		setTitle("View Employees");
		setSize(500,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}