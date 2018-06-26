import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UpdateFrame extends JFrame {
	Container c;
	
	JLabel lblId,lblName;
	JTextField txtId,txtName;
	JButton btnUpdate,btnBack;
	JPanel p1,p2;
	UpdateFrame(){
		c=getContentPane();
		p1=new JPanel();
		lblId=new JLabel("ID:");
		txtId=new JTextField(5);
		lblName=new JLabel("Name");
		txtName= new JTextField(10);
		p1.add(lblId);
		p1.add(txtId);
		p1.add(lblName);
		p1.add(txtName);
		c.add(p1);

		p2=new JPanel();
		btnUpdate = new JButton("Update");
		btnBack = new JButton("Back");
		p2.add(btnBack);
		p2.add(btnUpdate);
		c.add("South",p2);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				MainFrame a = new MainFrame();
				dispose();
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent ae) {
				String id = txtId.getText();
				String name = txtName.getText();
				DatabaseHandler d = new DatabaseHandler();
				d.updateEmployee(Integer.parseInt(id),name);
				txtId.setText("");
				txtName.setText("");
				txtId.requestFocus();
			}
		});

		setTitle("Update Employee");
		setSize(500,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}