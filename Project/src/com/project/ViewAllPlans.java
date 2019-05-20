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

public class ViewAllPlans extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String[][] Data;
	String[] columnames={"PlanID ", "PlanName", "Facilities", "Charge", "Duration"};
	private PreparedStatement pscount,psdata,psmember,psselect,psupdate;
	private ResultSet rscount,rsdata, rs,rsselect;
	private Connection cn;
	String pid;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllPlans frame = new ViewAllPlans();
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
	public ViewAllPlans() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAllPlans.class.getResource("/com/image/images (5).png")));
		setTitle("View_All_Plans");
		cn=Myoperation.createConnection();
		creategui();
		
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
		jspp.setBounds(10, 24, 738, 349);
		
		contentPane.add(jspp);
		
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		fillData();
		table.setModel(new DefaultTableModel(
			Data,columnames 
		));
		
		
		jspp.setViewportView(table);
		jspp.setViewportView(table);
		jspp.setOpaque(false);
		jspp.getViewport().setOpaque(false);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ViewAllPlans.class.getResource("/com/image/IMG_109415.jpg")));
		label.setBounds(-199, -136, 957, 551);
		contentPane.add(label);
	}

public	void fillData()
	{
	
String strcount="select count(*) from planinfo";
//String strcount="select count(*) from course where courseduration=?";

try{
	
	DatabaseMetaData ds=cn.getMetaData();
	pscount=cn.prepareStatement(strcount);
	rscount=pscount.executeQuery();
	rscount.next();
	int cnt=rscount.getInt(1);
//	System.out.println("counting ="+cnt);
	Data=new String[cnt][5];
	
String strdata="select * from planinfo ";
psdata=cn.prepareStatement(strdata);

//System.out.println(psdata);
rsdata=psdata.executeQuery();
int row=0;
int flag=0;
while(rsdata.next())
{	
	flag=1;
	
//	memberid, membername, email, gender, address, phoneno, Dob, Occupation, planid, dateofmembership, dateofexpiry
	Data[row][0]=rsdata.getString("planid");
	Data[row][1]=rsdata.getString("planname");
	Data[row][2]=rsdata.getString("facilities");
	Data[row][3]=rsdata.getString("charge");
	Data[row][4]=rsdata.getString("duration");
	
	
	
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


}
