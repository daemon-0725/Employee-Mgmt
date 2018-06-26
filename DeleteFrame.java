import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
class DeleteFrame extends JFrame {
		static boolean option;
		Container c;
		JLabel lblParam;
		JTextField txtId;
		JButton btnDelete,btnBack,btnDeleteAll;
		JPanel p1,p2,p3;
		JRadioButton rb1,rb2;
	
	DeleteFrame() {
		c=getContentPane();
		
		p1= new JPanel();
		lblParam= new JLabel("ID: ");
		txtId = new JTextField(6);
		p1.add(lblParam);
		p1.add(txtId);
		c.add(p1);

		p3= new JPanel();
		rb1=new JRadioButton("ID",true);
		rb2=new JRadioButton("Name");
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rb1);
		bg1.add(rb2);
		p3.add(rb1);
		p3.add(rb2);
		c.add("North",p3);

		p2= new JPanel();
		btnBack = new JButton("Back");
		btnDelete = new JButton("Delete");
		btnDeleteAll = new JButton("Delete All");
		p2.add(btnBack);
		p2.add(btnDelete);
		p2.add(btnDeleteAll);
		c.add("South",p2);

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				MainFrame a = new MainFrame();
				dispose();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String a = txtId.getText();
				DatabaseHandler d = new DatabaseHandler();
				if (option) {
					d.deleteEmployee(Integer.parseInt(a));
				}
				else {
					d.deleteEmployee(a);
				}
				txtId.setText("");
				txtId.requestFocus();
			}
		});

		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rb1.isSelected()) {
					lblParam.setText("ID");
					option=true;
				}
			}
		});

		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rb2.isSelected()) {
					lblParam.setText("Name");
					option=false;
				}
			}
		});

		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int opt = JOptionPane.showConfirmDialog(new JDialog(), "Erase All Records??");
				if (opt==JOptionPane.YES_OPTION) {
					DatabaseHandler.getCon();
					String sql = "delete from Employee where id>0";
					try {
						PreparedStatement pst = DatabaseHandler.con.prepareStatement(sql);
						pst.executeUpdate();
					}
					catch(Exception e){}
					JOptionPane.showMessageDialog(new JDialog(), "All Records Erased!");
					MainFrame mo = new MainFrame();
					dispose();
				}
			}
		});

		setTitle("Delete Employee");
		setSize(500,150);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}