import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


class AddFrame extends JFrame {
	Container c;
	
	JLabel lblId,lblName;
	JTextField txtId,txtName;
	JButton btnAdd,btnBack,btnImport;
	JPanel p1,p2;
	AddFrame(){
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
		btnAdd = new JButton("Add");
		btnBack = new JButton("Back");
		btnImport = new JButton("Import from File");
		p2.add(btnBack);
		p2.add(btnImport);
		p2.add(btnAdd);
		c.add("South",p2);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				MainFrame a = new MainFrame();
				dispose();
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				String i=txtId.getText();
				String n=txtName.getText();

				if(i.length()==0) {
					JOptionPane.showMessageDialog(new JDialog(), "ID is Empty");
					txtId.requestFocus();
					return;
				}

				if (n.length()==0) {
					JOptionPane.showMessageDialog(new JDialog(), "Name is Empty");
					return;
				}

				DatabaseHandler d = new DatabaseHandler();
				try {
					d.addEmployee(Integer.parseInt(i),n);
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(new JDialog(),"Invalid Input :(");
				}
				txtId.setText("");
				txtName.setText("");
				txtId.requestFocus();
			}
		});

		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				File ip = new File("Employee.txt");
				StringBuilder adds = new StringBuilder(); 
				String[] str = new String[50];
				try(FileReader fr = new FileReader(ip);
				BufferedReader br = new BufferedReader(fr);) {
		
					String line;
					int i=0;
					while((line=br.readLine())!=null) {
						adds.append(line);
						adds.append(",,");
						i++;
					}
					String v=adds.toString();
					str=v.split(",,");
				}
				catch(IOException e) {
					JOptionPane.showMessageDialog(new JDialog(),"An Error Occurred :(\n Check If File is corrupt");
				}
				new DatabaseHandler().addFromFile(str);
				}
		});

		setTitle("Add Employee");
		setSize(500,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}