package com.project;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Calendar;

import com.dbproject.Myoperation;
import java.awt.Font;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
public class Renew_Membership extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtplanname;
	private int dayy;
	private Connection cn;
	private PreparedStatement pssports,psread,psmember,psupdate;
	private ResultSet rs;
	private JComboBox cmbplanid;
	private JLabel lblplanname;
	private JButton btnupdate;
	private JTextField txtdoexp;
	private JDateChooser datec2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Renew_Membership frame = new Renew_Membership();
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
	public Renew_Membership()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Renew_Membership.class.getResource("/com/image/Noun_Project_100Icon_10px_grid-07-128.png")));
		setTitle("Renew_Plan");
		cn=Myoperation.createConnection();
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 352);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(219, 112, 147));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbplanid = new JComboBox();
		 cmbplanid.addActionListener(this);
		cmbplanid.setModel(new DefaultComboBoxModel(new String[] {"Select MemberID"}));
		cmbplanid.setBounds(156, 51, 155, 20);
		fillcombo();
		
		txtdoexp = new JTextField();
		txtdoexp.setBounds(231, 196, 172, 20);
		contentPane.add(txtdoexp);
		txtdoexp.setColumns(10);
		
		 datec2 = new JDateChooser();
		datec2.setBounds(231, 153, 172, 20);
		contentPane.add(datec2);
		contentPane.add(cmbplanid);
		
		lblplanname = new JLabel("Plan Name");
		lblplanname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblplanname.setBounds(42, 108, 101, 14);
		contentPane.add(lblplanname);
		
		txtplanname = new JTextField();
		txtplanname.setBounds(231, 106, 172, 20);
		contentPane.add(txtplanname);
		txtplanname.setColumns(10);
		
		 btnupdate = new JButton("UPDATE");
		 btnupdate.addActionListener(this);
		btnupdate.setBounds(110, 266, 89, 23);
		contentPane.add(btnupdate);
		
		JLabel lblcharge = new JLabel("Date Of Membership");
		lblcharge.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcharge.setBounds(44, 153, 138, 14);
		contentPane.add(lblcharge);
		
		JLabel lblduration = new JLabel("Date Of Expiry");
		lblduration.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblduration.setBounds(42, 198, 101, 14);
		contentPane.add(lblduration);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Renew_Membership.class.getResource("/com/image/IMG_217161.jpg")));
		label.setBounds(-312, -332, 746, 646);
		contentPane.add(label);
	}
	
	public boolean checkid(String id)
	{
	String strsql="select * from planinfo where planid =?";
	try {
		
		psread=cn.prepareStatement(strsql);
		psread.setString(1,id);
		ResultSet rs=psread.executeQuery();
		if(rs.next())
		{
			return true;
		}
	}
	catch(SQLException se) {}
	return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cid;
		String uid=(String)cmbplanid.getSelectedItem();
		if(uid.equalsIgnoreCase("Select MemberID"))
		{
			JOptionPane.showMessageDialog(this, " Select Member_ID");
			
		}

		else {
			cid=uid;
						
		if(e.getSource()==cmbplanid)
			
		{
			try {
				
			String strsql="select * from memberinfo where memberid=?";
			pssports=cn.prepareStatement(strsql);
			pssports.setString(1, uid);
			rs=pssports.executeQuery();
			if(rs.next())
			{
				String name=rs.getString("planid");
				txtplanname.setText(name);
							
			}
						
				
			}
			catch(SQLException se)
			{
				
				System.out.println(se);
			}
					
		}

		
		if(e.getSource()==btnupdate)
				{
			
			String id=txtplanname.getText();
			
			if(id.isEmpty()||datec2.getDate()==null)
			{
				JOptionPane.showMessageDialog(this, "Fill Missing Fields");
			}
			else
			{
				
				if(checkid(id)==true)
				
				{
					try 
					{
						
						String strsql="select * from planinfo where planid=?";
						psread=cn.prepareStatement(strsql);
						psread.setString(1, id);
						rs=psread.executeQuery();
						if(rs.next())
						{
							String dur=rs.getString("duration");
							dayy=Integer.parseInt(dur);
//							System.out.println("duration "+dayy);
															
						}
												
				
					java.util.Date dm= datec2.getDate();
					
					java.sql.Date dmsql=new java.sql.Date(dm.getTime());
					
//					JOptionPane.showMessageDialog(this, dm);
					Calendar cmem=Calendar.getInstance();
					
					
					cmem.setTime(dm);
//					int day=20;

					cmem.add(Calendar.DATE, dayy);//date of expiry
					java.sql.Date expdate=new java.sql.Date(cmem.getTimeInMillis());
//					System.out.println(expdate);
					txtdoexp.setText(expdate.toString());
					
					String strupdate="update memberinfo set planid=?,dateofMembership=?,dateofexpiry=? where memberid=?";
					String strinsert="insert into renew(memberid,planid,dateofmembership,dateofexpiry) values(?,?,?,?)";
					psupdate=cn.prepareStatement(strupdate);
					psmember=cn.prepareStatement(strinsert);
						
						String pname=txtplanname.getText();
						psupdate.setString(1,pname);
						psupdate.setDate(2, dmsql);
						psupdate.setDate(3, expdate);
						psupdate.setString(4, uid);
						psmember.setString(1, cid);
						psmember.setString(2, id);
						psmember.setDate(3,dmsql);
						psmember.setDate(4,expdate);
						int row=psmember.executeUpdate();
						int row1=psupdate.executeUpdate();
						if(row1>0&&row>0)
						{
		
							JOptionPane.showMessageDialog(this, "Data Updated");
							
						}
										
				}
				catch(SQLException  se)
				{
					
					System.out.println(se);
				}
			}
				else
				{
					JOptionPane.showMessageDialog(this, "Sorry ! No Such Plan Exist ");
				}
		}
				}
		}
					}
					
				
	
	public void fillcombo()
	{
		java.sql.Date dexp=null;
		try {
			String selectsql="select * from memberinfo";
			pssports=cn.prepareStatement(selectsql);
			rs=pssports.executeQuery();
			while(rs.next())
			{
//				 doe=rs.getString("dateofexpiry");
				 dexp=rs.getDate("dateofexpiry");
//				txtdoex.setText(doe.toString());
				 Calendar current=Calendar.getInstance();
					
					Calendar cdxp=Calendar.getInstance();
					cdxp.setTime(dexp);
//					cdxp.set(Calendar.DATE, -7);
					if(current.compareTo(cdxp)>0)
					{
						cmbplanid.addItem(rs.getString("memberid"));
					}
					}
//		
					}
		catch(SQLException ie)
		{
			System.out.println(ie);
		}
		finally
		{
			try {
				if(pssports!=null)
					pssports.close();
				if(rs!=null)
					rs.close();
			}
			catch(SQLException ie)
			{
				System.out.println(ie);
			}
		}
	}
}
