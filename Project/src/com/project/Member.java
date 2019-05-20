package com.project;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbproject.Myoperation;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.JTextArea;
public class Member extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private JTextField txtmemid,txtmemname,txtemail,txtphone,txtoccupation;
	private JRadioButton rdbtnOther, rdbtnMale , rdbtnFemale;
	private JDateChooser jd;
	private Connection cnn;
	private PreparedStatement psmember,psread;
	private JDateChooser datec1,datec2;
	private JTextField txtdoexp;
	private JComboBox cmbplanid;
	private JTextArea txtaddress;
	private ResultSet rs;
	private JButton btnsubmit, btnReset;
	public int dayy;
	private ButtonGroup bg;
	java.util .Date db=null;
	java.util .Date dm=null;
	int flag=0;
	private String email,phone;
	boolean setmail,setphone;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member frame = new Member();
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
	public Member() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Member.class.getResource("/com/image/79224.png")));
		setTitle("Add Members");
		cnn=Myoperation.createConnection();
//		System.out.println("created");
		creategui();
	}
	public void creategui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 752, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		 cmbplanid = new JComboBox();
		 cmbplanid.setModel(new DefaultComboBoxModel(new String[] {"Select PlanID"}));
		 cmbplanid.setBounds(345, 417, 201, 20);
		 fillcombo();
		 cmbplanid.addActionListener(this);
		 
		  txtaddress = new JTextArea();
		 txtaddress.setBounds(345, 228, 201, 81);
		 contentPane.add(txtaddress);
		 
		 btnReset = new JButton("RESET");
		 btnReset.setBounds(409, 535, 104, 23);
		 contentPane.add(btnReset);
		 btnReset.addActionListener(this);
		 contentPane.add(cmbplanid);
		 
		 txtdoexp = new JTextField();
		 txtdoexp.setBounds(345, 479, 201, 20);
		 contentPane.add(txtdoexp);
		 txtdoexp.setColumns(10);
		
		 datec2 = new JDateChooser();
		datec2.setBounds(345, 448, 201, 20);
		contentPane.add(datec2);
		
		datec1 = new JDateChooser();
		datec1.setBounds(345, 351, 201, 20);
		contentPane.add(datec1);
		
		JLabel lblmemid = new JLabel("Member_ID");
		lblmemid.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblmemid.setBounds(152, 96, 87, 21);
		contentPane.add(lblmemid);
		
		JLabel lblmemname = new JLabel("Member_Name");
		lblmemname.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblmemname.setBounds(152, 133, 123, 21);
		contentPane.add(lblmemname);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblemail.setBounds(152, 165, 70, 21);
		contentPane.add(lblemail);
		
		JLabel lblgender = new JLabel("Gender");
		lblgender.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblgender.setBounds(152, 197, 70, 21);
		contentPane.add(lblgender);
		
		JLabel lbladdress = new JLabel("Address");
		lbladdress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbladdress.setBounds(152, 229, 87, 21);
		contentPane.add(lbladdress);
		
		JLabel lblphone = new JLabel("Phone_No.");
		lblphone.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblphone.setBounds(152, 319, 87, 20);
		contentPane.add(lblphone);
		
		JLabel lbldob = new JLabel("D.O.B");
		lbldob.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbldob.setBounds(151, 350, 46, 21);
		contentPane.add(lbldob);
		
		JLabel lbloccupation = new JLabel("Occupation");
		lbloccupation.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbloccupation.setBounds(152, 382, 87, 21);
		contentPane.add(lbloccupation);
		
		JLabel lblplanid = new JLabel("Plan_ID");
		lblplanid.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblplanid.setBounds(152, 414, 87, 21);
		contentPane.add(lblplanid);
		
		JLabel lbldomem = new JLabel("Date Of Membership");
		lbldomem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbldomem.setBounds(152, 446, 168, 21);
		contentPane.add(lbldomem);
		
		JLabel lbldoexp = new JLabel("Date Of Expiry");
		lbldoexp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lbldoexp.setBounds(152, 478, 104, 21);
		contentPane.add(lbldoexp);
		
		txtmemid = new JTextField();
		txtmemid.setBounds(345, 97, 201, 20);
		contentPane.add(txtmemid);
		txtmemid.setColumns(10);
		
		txtmemname = new JTextField();
		txtmemname.setBounds(345, 134, 201, 20);
		contentPane.add(txtmemname);
		txtmemname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(345, 166, 201, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		 rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBackground(Color.WHITE);
		rdbtnMale.setBounds(345, 197, 62, 23);
		rdbtnMale.addActionListener(this);
		contentPane.add(rdbtnMale);
		
		 rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBackground(Color.WHITE);
		rdbtnFemale.setBounds(403, 197, 75, 23);
		rdbtnFemale.addActionListener(this);
		contentPane.add(rdbtnFemale);
		
		 rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBackground(Color.WHITE);
		rdbtnOther.setBounds(475, 197, 70, 23);
		rdbtnOther.addActionListener(this);
		contentPane.add(rdbtnOther);
		
		 bg=new ButtonGroup();
		bg.add(rdbtnMale);
		bg.add(rdbtnFemale);
		bg.add(rdbtnOther);
		
		txtphone = new JTextField();
		txtphone.setBounds(345, 320, 201, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtoccupation = new JTextField();
		txtoccupation.setBounds(345, 386, 201, 20);
		contentPane.add(txtoccupation);
		txtoccupation.setColumns(10);
		
		btnsubmit = new JButton("SUBMIT");
		btnsubmit.setSelectedIcon(null);
		btnsubmit.setBounds(251, 535, 117, 23);
		btnsubmit.addActionListener(this);
		contentPane.add(btnsubmit);
		
		JLabel lblCricketClubMembership = new JLabel("Cricket Club Membership Form");
		lblCricketClubMembership.setFont(new Font("Old English Text MT", Font.PLAIN, 40));
		lblCricketClubMembership.setBounds(93, 22, 548, 46);
		contentPane.add(lblCricketClubMembership);
		
		JLabel lblNewLabel = new JLabel("Select_PlanID");
		lblNewLabel.setIcon(new ImageIcon(Member.class.getResource("/com/image/2722587-cricket-wallpapers.jpg")));
		lblNewLabel.setBounds(0, 0, 736, 600);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Member.class.getResource("/com/image/download.jpg")));
		label.setBounds(418, 478, 46, 14);
		contentPane.add(label);
	}
	
	public void fillcombo()
	{
		try {
			String selectsql="select planid from planinfo";
			psmember=cnn.prepareStatement(selectsql);
			rs=psmember.executeQuery();
			while(rs.next())
			{
				String uid=rs.getString("planid");
				cmbplanid.addItem(uid);
			}
		}
		catch(SQLException ie)
		{
			System.out.println(ie);
		}
	}
	public boolean chkname(String nm,int j)
	{
		String s=nm.substring(j);
		if(s.trim().equals("@gmail.com")||s.trim().equals("@yahoo.com")||s.trim().equals("@rediffmail.com"))
			return true;
		else
			return false;
	}
	public boolean chkphone(String ph)
	{
		String no=ph.trim();
	if(no.length()==13)
			return true ;
		else 
			return false ;
	}
	
	public static String getDate(Calendar cal){
	      return "" + cal.get(Calendar.DATE) +"/" +
	              (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
	  }
	@Override
	public void actionPerformed(ActionEvent e)

	{	
		
		String cid=(String)cmbplanid.getSelectedItem();
		//		int day=20;//plan duration
			
		
						
	if(e.getSource()==cmbplanid)
		
	{
	try 
	{
		
		String strsql="select * from planinfo where planid=?";
		psread=cnn.prepareStatement(strsql);
		psread.setString(1, cid);
		rs=psread.executeQuery();
		if(rs.next())
		{
			String dur=rs.getString("duration");
			dayy=Integer.parseInt(dur);
//			System.out.println("duration "+dayy);
																
		}
						}
		catch(SQLException se)
		{
			
			System.out.println(se);
		}
					
	}
	
		if(e.getSource()==btnsubmit)
		{
	SimpleDateFormat sdf=new SimpleDateFormat();
	String id=txtmemid.getText();
	String rdbtn = null;
	String name=txtmemname.getText();
	 email=txtemail.getText();
	 phone="+91"+txtphone.getText();
	String address=txtaddress.getText();
	String occupation=txtoccupation.getText();
	
	if(rdbtnMale.isSelected())
	{
		rdbtn=rdbtnMale.getActionCommand();
	}
	else if(rdbtnFemale.isSelected())
	{
		rdbtn=rdbtnFemale.getActionCommand();
	}
	else if(rdbtnOther.isSelected())
	{
		rdbtn=rdbtnOther.getActionCommand();
	}
	
	
		if(id.isEmpty()||name.isEmpty()||email.isEmpty()||phone.isEmpty()||address.isEmpty()||occupation.isEmpty()||datec1.getDate()==null||datec2.getDate()==null||cid.equalsIgnoreCase("Select PlanID"))
		{
			JOptionPane.showMessageDialog(this, "Fill Missing Fields");
		}
		else
		{	db=datec1.getDate();
			Calendar current =Calendar.getInstance();
			current.add(Calendar.YEAR,-20);
//			JOptionPane.showMessageDialog(this,current);
			Calendar cdob=Calendar.getInstance();
			cdob.setTime(db);
			
			java.sql.Date chkdate=new java.sql.Date(current.getTimeInMillis());
			
			if(getDate(cdob).compareTo(getDate(current))<0)
			{
				flag=1;
//			System.out.println(chkdate);
			}
			else 
			{
				flag=0;
//			System.out.println("Not a valid Date");
			}
			
			java.sql.Date dbsql=new java.sql.Date(db.getTime());
			 
			dm=datec2.getDate();
			
			 java.sql.Date dmsql=new java.sql.Date(dm.getTime());
			 
//			JOptionPane.showMessageDialog(this, dm);
			Calendar cmem=Calendar.getInstance();
			cmem.setTime(dm);
//			int day=20;

			cmem.add(Calendar.DATE,dayy);
			System.out.println(getDate(cmem));//date of expiry
			java.sql.Date expdate=new java.sql.Date(cmem.getTimeInMillis());
//			System.out.println(expdate);
			txtdoexp.setText(expdate.toString());
			int j;
			
			for(int i=0;i<email.length();i++)
			{
				if(email.charAt(i)=='@')
					{ j=i;
					setmail=chkname(email,j);
					break;
					}
				else
				{
					setmail=false;
				}
				}
			setphone=chkphone(phone);
			
			try {
				String strsql="select * from memberinfo where memberid=?";
				psread=cnn.prepareStatement(strsql);
				psread.setString(1, id);
				rs=psread.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this, " MemberID Already Exists");
					txtmemid.setText("");													
				}
				else
				{
					if(flag==1&&setmail==true&&setphone==true)
					{
				String mid=(String)cmbplanid.getSelectedItem();
//				memberid, membername, email, gender, address, phoneno, Dob, Occupation, planid, dateofmembership, dateofexpiry
				String insert ="INSERT into memberinfo ( memberid, membername, email, address, phoneno,  Occupation, planid, Dob,dateofmembership,dateofexpiry,gender) values(?,?,?,?,?,?,?,?,?,?,?)";
				psmember=cnn.prepareStatement(insert);
				psmember.setString(1,id);
				psmember.setString(2, name);
				psmember.setString(3, email);
				psmember.setString(4, address);
				psmember.setString(5, phone);
				psmember.setString(6, occupation);
				psmember.setString(7,mid);
				psmember.setDate(8,dbsql);
				psmember.setDate(9, dmsql);
				psmember.setDate(10, expdate);
				psmember.setString(11,rdbtn);
				int row=psmember.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Member Registered Sucessfully");
					txtmemid.setText("");
					txtaddress.setText("");
					txtdoexp.setText("");
					txtemail.setText("");
					txtmemname.setText("");
					txtoccupation.setText("");
					txtphone.setText("");
					cmbplanid.setSelectedItem("Select PlanID");
					datec1.setDate(null);
					datec2.setDate(null);
					bg.clearSelection();

				}
				}
				else if(flag==0&&setmail==false&&setphone==false)
				{
					JOptionPane.showMessageDialog(this,"Enter Correct Email , Phone No and Check Member Age Must Be Above 20 Years");
					
				}
				else if(flag==0)
				{
					JOptionPane.showMessageDialog(this,"Member Age Must Be Above 20 Years");
					
				}
				else if(setmail==false)
				{
					JOptionPane.showMessageDialog(this,"Enter Correct Email");
					
				}
				else if(setphone==false)
				{
					JOptionPane.showMessageDialog(this,"Enter Valid Phone No.");
					
				}
					
											
			}
			}
			catch(SQLException se) 
			{
				System.out.println(se);
			}
		}
					
}
		if(e.getSource()==btnReset)
		{
			txtmemid.setText("");
			txtaddress.setText("");
			txtdoexp.setText("");
			txtemail.setText("");
			txtmemname.setText("");
			txtoccupation.setText("");
			txtphone.setText("");
			cmbplanid.setSelectedItem("Select PlanID");
			datec1.setDate(null);
			datec2.setDate(null);
			bg.clearSelection();
		}
	}
}
