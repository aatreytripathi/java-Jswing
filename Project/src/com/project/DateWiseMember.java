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
import java.awt.Color;
import java.awt.Toolkit;

public class DateWiseMember extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnView;
	private JTable table;
	String[][] Data;
	String[] columnames={"Name ", "Email", "DOB", "Address", "Occupation", "Phone_No.","Subscription-ExpiryDate"};
	private PreparedStatement pscount,psdata,psmember,psselect,psupdate;
	private ResultSet rscount,rsdata, rs,rsselect;
	private Connection cn;
	String pid;
	JDateChooser dateofmem;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateWiseMember frame = new DateWiseMember();
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
	public DateWiseMember() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DateWiseMember.class.getResource("/com/image/images (5).png")));
		setTitle("Date-Wise Members");
		cn=Myoperation.createConnection();
		creategui();
		
	}
	
	public void creategui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 789, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane jspp = new JScrollPane();
		jspp.setBounds(26, 131, 737, 287);
		
		contentPane.add(jspp);
		
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.ITALIC, 15));
			
		jspp.setViewportView(table);
		jspp.setOpaque(false);
		jspp.getViewport().setOpaque(false);
		
		dateofmem = new JDateChooser();
		dateofmem.setToolTipText("choose Date Of Membership\r\n");
		dateofmem.setBounds(246, 67, 190, 20);
		contentPane.add(dateofmem);
		
		JLabel lblDateofmembership = new JLabel("Date_Of_Membership");
		lblDateofmembership.setForeground(Color.WHITE);
		lblDateofmembership.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDateofmembership.setBounds(40, 67, 165, 20);
		contentPane.add(lblDateofmembership);
		
		btnView = new JButton("VIEW");
		btnView.setBounds(493, 64, 89, 23);
		btnView.addActionListener(this);
		contentPane.add(btnView);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(DateWiseMember.class.getResource("/com/image/windows_10_mobile.jpg")));
		label.setBounds(0, 0, 773, 449);
		contentPane.add(label);
	}

public	void fillData()
	{
	java.util.Date db=dateofmem.getDate();
	java.sql.Date dbsql=new java.sql.Date(db.getTime());
	
String strcount="select count(*) from memberinfo where dateofmembership=?";
//String strcount="select count(*) from course where courseduration=?";

try{
	
	DatabaseMetaData ds=cn.getMetaData();
	pscount=cn.prepareStatement(strcount);
	pscount.setDate(1,dbsql);
	rscount=pscount.executeQuery();
	rscount.next();
	int cnt=rscount.getInt(1);
//	System.out.println("counting ="+cnt);
	Data=new String[cnt][7];
	
String strdata="select * from memberinfo where dateofmembership=? ";
psdata=cn.prepareStatement(strdata);
psdata.setDate(1,dbsql);
//System.out.println(psdata);
rsdata=psdata.executeQuery();
int row=0;
int flag=0;
while(rsdata.next())
{	flag=1;
	
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
	if(dateofmem.getDate()==null)
	{
		JOptionPane.showMessageDialog(this,"Select Any Date");
	}
	else
	{
	fillData();
	table.setModel(new DefaultTableModel(
		Data,columnames 
	));
	}
}
}
