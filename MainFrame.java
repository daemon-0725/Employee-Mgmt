import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame {
	Container c;
	JButton btnAdd, btnView,btnDelete,btnUpdate;
	JPanel p1,p2;

	MainFrame() {
		c=getContentPane();
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnDelete = new JButton("Delete");
		btnUpdate = new JButton("Update");
		p1=new JPanel();
		p2=new JPanel();
		p1.add(btnAdd);
		p1.add(btnView);
		p1.add(btnUpdate);
		p1.add(btnDelete);
		c.add("North",p2);
		c.add(p1);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				AddFrame a = new AddFrame();
				dispose();
			}
		});

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				ViewFrame a = new ViewFrame();
				dispose();
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent ae) {
				UpdateFrame a = new UpdateFrame();
				dispose();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				DeleteFrame d = new DeleteFrame();
				dispose();
			}
		});

		setTitle("Employee Management System");
		setSize(500,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame m = new MainFrame();
	}
}

class DatabaseHandler {
	private static final int EMPLOYEE_NAME_LENGTH = 20;
	static Connection con;
	static void getCon() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","123456");
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(),"Connection failed  <3<3");
		}
	}

	public void addEmployee(int id, String name) {
		getCon();
		try {
			String sql = "insert into Employee values(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,name);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),r+" records inserted.");

		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(),"Cannot Insert :(");
		}
	}

	public void addFromFile(String[] s) {
		getCon();
		try {
			String sql = "select * from Employee";
			PreparedStatement pst = con.prepareStatement(sql);
			int j =pst.executeUpdate()+1;
			int i=0;
			sql = "insert into Employee values(?,?)";
			while(i<s.length) {
				PreparedStatement pst1 = con.prepareStatement(sql);
				pst1.setInt(1,(i+j));
				pst1.setString(2,s[i]);
				pst1.executeUpdate();
				i++;
			}
			JOptionPane.showMessageDialog(new JDialog(),s.length+" records inserted!");
		}
		catch(SQLException e) {
			System.out.println("addfmfile");
		}
	}

	public String viewEmployee() {
		StringBuffer sb  = new StringBuffer();
		getCon();

		try {
			String sql = "select * from Employee order by id";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int r = rs.getInt(1);
				String n = rs.getString(2);
				sb.append("ID: "+r+" Name: "+n+"\n");
			}
		}

		catch(SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Some Issue");
		}
		return sb.toString();
	}

	public void deleteEmployee(int a) {
		getCon();
		try {
			String sql= "delete from Employee where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,a);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r+" records deleted.");
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), e);
		}
	}

	public void deleteEmployee(String a) {
		getCon();
		try {
			while(a.length()!=EMPLOYEE_NAME_LENGTH) {
				a=a.concat(" ");
			}
			String sql= "delete from Employee where name=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,a);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r+" records deleted.");
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), e);
		}
	}

	public void updateEmployee(int id, String new_name) {
		getCon();
		try {
			String sql = "Update Employee set name=? where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,new_name);
			pst.setInt(2,id);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r+ " records updated.");
		}

		catch(SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(),e);
		}
	}
}