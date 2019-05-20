package com.project;
import java.sql.*;
import com.dbproject.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.text.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Date;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ValidUserFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtdoex;
	private JButton btnCheckValidity;
	private JButton btnRenew;
//	String doe;
	private Connection cn;
	private PreparedStatement pscount,psdata,psmember,psselect,psupdate,pssports;
	private ResultSet rscount,rsdata, rs,rsselect;
	String[][] Data;
	String[] columnames={"Name ", "DOB", "DateOfMembership", "DateOfExpiry", "Message"};
	public ArrayList<String>memberlist=new ArrayList<>();
	private JTable table_1;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValidUserFrame frame = new ValidUserFrame();
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
	public ValidUserFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ValidUserFrame.class.getResource("/com/image/images (5).png")));
		setTitle("Valid_User");
		cn=Myoperation.createConnection();
	createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 911, 456);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
	/*	JLabel lblDateOfExpiry = new JLabel("Date Of Expiry");
		lblDateOfExpiry.setBounds(81, 122, 79, 14);
		contentPane.add(lblDateOfExpiry);*/
		
	/*	txtdoex = new JTextField();
		txtdoex.setBounds(183, 119, 121, 20);
		contentPane.add(txtdoex);
		txtdoex.setColumns(10);*/
		
		btnCheckValidity = new JButton("CHECK VALIDITY");
		btnCheckValidity.addActionListener(this);
		btnCheckValidity.setBounds(75, 44, 128, 23);
		contentPane.add(btnCheckValidity);
		
		btnRenew = new JButton("RENEW");
		btnRenew.addActionListener(this);
		btnRenew.setBounds(313, 44, 89, 23);
		contentPane.add(btnRenew);
		
		JScrollPane jspp = new JScrollPane();
		jspp.setBounds(10, 133, 875, 274);
		contentPane.add(jspp);
		
		table_1 = new JTable();
		jspp.setViewportView(table_1);
		jspp.setViewportView(table_1);
		jspp.setViewportView(table_1);
		jspp.setOpaque(false);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ValidUserFrame.class.getResource("/com/image/sachin-sachin-tendulkar-fan-club-25740060-1024-768.jpg")));
		label.setBounds(-13, 0, 946, 603);
		contentPane.add(label);
		jspp.getViewport().setOpaque(false);
		
		
	}
	public static String getDate(Calendar cal){
	      return "" + cal.get(Calendar.DATE) +"/" +
	              (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
	  }
	
	public	void fillData()
	{
	
try{
	
	String nm="Membership is Expired or going to expire in within 7 Days";
	int cnt=memberlist.size();
//	System.out.println("counting ="+cnt);
	Data=new String[cnt][5];
	for(int i=0;i<cnt;i++)
	{
	
		String strdata="select * from memberinfo where membername=?";
		psdata=cn.prepareStatement(strdata);
		psdata.setString(1, memberlist.get(i));
//		System.out.println(memberlist.get(i));
		rsdata=psdata.executeQuery();
	while(rsdata.next())
	{
//	memberid, membername, email, gender, address, phoneno, Dob, Occupation, planid, dateofmembership, dateofexpiry
	Data[i][0]=rsdata.getString("membername");
	Data[i][1]=rsdata.getString("Dob");
	Data[i][2]=rsdata.getString("dateofmembership");
	Data[i][3]=rsdata.getString("dateofexpiry");
	Data[i][4]=nm;
	}
}
}
catch(SQLException se) {}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
			
			
				if(e.getSource()==btnCheckValidity)
				{
					java.sql.Date dexp=null;
					try
					{
						
						String selectsql="select * from memberinfo";
						pssports=cn.prepareStatement(selectsql);
						rs=pssports.executeQuery();
						while(rs.next())
						{
//							 doe=rs.getString("dateofexpiry");
							 dexp=rs.getDate("dateofexpiry");
//							txtdoex.setText(doe.toString());
							 Calendar current=Calendar.getInstance();
								
								Calendar cdxp=Calendar.getInstance();
								cdxp.setTime(dexp);
//								System.out.println("Original date of expiry"+getDate(cdxp));
								cdxp.add(Calendar.DATE,-7);
//								System.out.println("Checking date of expiry"+getDate(cdxp));
								if(current.compareTo(cdxp)>0)
								{
									memberlist.add(rs.getString("membername"));
									//System.out.println("Comparing Date"+getDate(current));
									
								}
								
								}
//						
					}
		
			catch(SQLException ie)
			{
				System.out.println(ie);
			}
					fillData();
					table_1.setModel(new DefaultTableModel(
						Data,columnames 
					));
					
				}
			else if(e.getSource()==btnRenew)
			{
				Renew_Membership rm=new Renew_Membership();
				rm.setVisible(true);
			}
	}
}
