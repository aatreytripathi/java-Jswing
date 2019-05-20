package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbproject.Myoperation;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.sql.*;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Update_info extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox cmbmemid;
	private JButton btnupdate;
	private Connection cn;
	private PreparedStatement psmember,psselect,psupdate;
	private ResultSet rs,rsselect;
	private JLabel label;
	private JTextField txtemail;
	private JTextField txtoccupation;
	private JLabel lblPhoneNo;
	private JTextField txtph;
	private JTextArea txtaddress;
	private String email,pho;
	boolean setmail,setphone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_info frame = new Update_info();
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
	public Update_info() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Update_info.class.getResource("/com/image/if_Rules of Fight Club_27667.png")));
		cn=Myoperation.createConnection();
		creategui();
	}
	
	public void fillcombo()
	{
		
		try {
			String strsql ="SELECT memberid FROM memberinfo";
			psmember=cn.prepareStatement(strsql);
			rs=psmember.executeQuery();
			while(rs.next())
			{
				String cid=rs.getString("memberid");
				cmbmemid.addItem(cid);
			}
		}
		catch(SQLException se) {}
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
	public void creategui()
	{
		setTitle("Update_Memberinfo.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 426, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbmemid = new JComboBox();
		 cmbmemid.setOpaque(false);
		 cmbmemid.addActionListener(this);
		cmbmemid.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 13));
		cmbmemid.setModel(new DefaultComboBoxModel(new String[] {"Select Member_ID"}));
		cmbmemid.setBounds(164, 32, 157, 23);
		fillcombo();
		
		 txtaddress = new JTextArea();
		 txtaddress.setBorder(new LineBorder(UIManager.getColor("activeCaption")));
		txtaddress.setBounds(164, 131, 217, 94);
		contentPane.add(txtaddress);
		
		txtph = new JTextField();
		txtph.setOpaque(false);
		txtph.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtph.setColumns(10);
		txtph.setBounds(164, 269, 217, 20);
		contentPane.add(txtph);
		
		lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhoneNo.setBounds(23, 268, 149, 20);
		contentPane.add(lblPhoneNo);
		
		txtoccupation = new JTextField();
		txtoccupation.setOpaque(false);
		txtoccupation.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtoccupation.setColumns(10);
		txtoccupation.setBounds(164, 236, 217, 20);
		contentPane.add(txtoccupation);
		
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOccupation.setBounds(23, 234, 89, 23);
		contentPane.add(lblOccupation);
		
		txtemail = new JTextField();
		txtemail.setOpaque(false);
		txtemail.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtemail.setColumns(10);
		txtemail.setBounds(164, 83, 217, 20);
		contentPane.add(txtemail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddress.setBounds(23, 131, 89, 23);
		contentPane.add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email\r\n");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(23, 81, 89, 23);
		contentPane.add(lblEmail);
		
		contentPane.add(cmbmemid);
		
		 btnupdate = new JButton("UPDATE");
		 btnupdate.setOpaque(false);
		btnupdate.setBounds(139, 317, 100, 23);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Update_info.class.getResource("/com/image/Curve_2.jpg")));
		label.setBounds(0, 0, 411, 387);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String mid;
		String memid=(String)cmbmemid.getSelectedItem();
		mid=memid;
		
	
if(e.getSource()==cmbmemid)
		
	{
		try {
			
		String strsql="select * from memberinfo where memberid=?";
		psselect=cn.prepareStatement(strsql);
		psselect.setString(1, mid);
//		System.out.println(psselect);
		rsselect=psselect.executeQuery();
		if(rsselect.next())
		{
						
			String addr=rsselect.getString("address");
			txtaddress.setText(addr);
			String occ=rsselect.getString("occupation");
			txtoccupation.setText(occ);
			String em=rsselect.getString("email");
			txtemail.setText(em);
			String ph=rsselect.getString("phoneno");
			txtph.setText(ph);
				
		}
	}
		catch(SQLException |NumberFormatException se)
		{
			
			System.out.println(se);
		}
	
	}

if(e.getSource()==btnupdate)
{
	
	if(mid.equalsIgnoreCase("Select Member_Id"))
	{
		JOptionPane.showMessageDialog(this, "Select MemberID");
		
	}
	
	else
	{
		
		try
		{
//			memberid, membername, email, gender, address, phoneno, Dob, Occupation, planid, dateofmembership, dateofexpiry
				String strupdate="update memberinfo set email=?,phoneno=?,Occupation=?,address=? where memberid=?";
				psupdate=cn.prepareStatement(strupdate);
				 email=txtemail.getText();
				 pho=txtph.getText();
				String oc=txtoccupation.getText();
				String ad=txtaddress.getText();
				
				if(email.isEmpty()||pho.isEmpty()||oc.isEmpty()||ad.isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Fill Missing Fields");
				}
				else
				{
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
					setphone=chkphone(pho);
					
					if(setmail==true&&setphone==true)
					{
				psupdate.setString(1, email);
				psupdate.setString(2,pho);
				psupdate.setString(3,oc);
				psupdate.setString(4,ad);
				
				psupdate.setString(5,mid);
				int row1=psupdate.executeUpdate();
				if(row1>0)
				{

					JOptionPane.showMessageDialog(this, "Details Updated", "updation", JOptionPane.INFORMATION_MESSAGE);
					txtaddress.setText("");
					txtemail.setText("");
					txtph.setText("");
					txtoccupation.setText("");
				}
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
		catch(SQLException  se)
		{
			
			System.out.println(se);
		}
		
	}
	}
}
}
