package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.mysql.jdbc.ResultSetMetaData;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dbproject.Myoperation;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ViewAllMember extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	String[][] Data;
	String[] columnames={"Name ", "Email", "DOB", "Address", "Occupation", "Phone_No.","Subscription-ExpiryDate"};
	private PreparedStatement pscount,psdata,psmember,psselect,psupdate;
	private ResultSet rscount,rsdata, rs,rsselect;
	private Connection cn;
	JComboBox cmbplanid;
	String pid;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllMember frame = new ViewAllMember();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewAllMember() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAllMember.class.getResource("/com/image/images (5).png")));
		setTitle("View_All_Info.");
		cn=Myoperation.createConnection();
		creategui();
		
	}
	public void fillcomboplan()
	{
		
		try {
			String strsql ="SELECT planid FROM planinfo";
			psmember=cn.prepareStatement(strsql);
			rs=psmember.executeQuery();
			while(rs.next())
			{
				String cid=rs.getString("planid");
				cmbplanid.addItem(cid);
			}
		}
		catch(SQLException se) {}
	}
	public void creategui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 774, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane jspp = new JScrollPane();
		jspp.setOpaque(false);
		jspp.setBounds(10, 76, 738, 297);
		
		contentPane.add(jspp);
		
		
		
		cmbplanid = new JComboBox();
		cmbplanid.setModel(new DefaultComboBoxModel(new String[] {"Select Plan_ID"}));
		cmbplanid.setBounds(246, 25, 190, 20);
		cmbplanid.addActionListener(this);
		fillcomboplan();
		contentPane.add(cmbplanid);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		
		
		
		jspp.setViewportView(table);
		jspp.setViewportView(table);
		jspp.setOpaque(false);
		jspp.getViewport().setOpaque(false);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ViewAllMember.class.getResource("/com/image/IMG_109415.jpg")));
		label.setBounds(-199, -136, 957, 551);
		contentPane.add(label);
	}

public	void fillData()
	{
	
String strcount="select count(*) from memberinfo where planid=?";
//String strcount="select count(*) from course where courseduration=?";

try{
	
	DatabaseMetaData ds=cn.getMetaData();
	pscount=cn.prepareStatement(strcount);
	pscount.setString(1, (String)cmbplanid.getSelectedItem());
	rscount=pscount.executeQuery();
	rscount.next();
	int cnt=rscount.getInt(1);
//	System.out.println("counting ="+cnt);
	Data=new String[cnt][7];
	
String strdata="select * from memberinfo where planid=? ";
psdata=cn.prepareStatement(strdata);
psdata.setString(1, (String)cmbplanid.getSelectedItem());
//System.out.println(psdata);
rsdata=psdata.executeQuery();
int row=0;
int flag=0;
while(rsdata.next())
{	
	flag=1;
	
//	memberid, membername, email, gender, address, phoneno, Dob, Occupation, planid, dateofmembership, dateofexpiry
	Data[row][0]=rsdata.getString("membername");
	Data[row][1]=rsdata.getString("email");
	Data[row][2]=rsdata.getString("Dob");
	Data[row][3]=rsdata.getString("address");
	Data[row][4]=rsdata.getString("Occupation");
	Data[row][5]=rsdata.getString("phoneno");
	Data[row][6]=rsdata.getString("dateofexpiry");
	
	row++;
	
}
if(flag==0)
{
	JOptionPane.showMessageDialog(this, "No Data Present");
	
}
	}
catch(SQLException se)
{
	System.out.println(se);
}
}

@Override
public void actionPerformed(ActionEvent e) 
{
	fillData();
	table.setModel(new DefaultTableModel(
		Data,columnames 
	));
	
}
}
