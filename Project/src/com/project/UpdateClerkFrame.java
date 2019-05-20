package com.project;
import java.awt.event.*;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbproject.Myoperation;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
public class UpdateClerkFrame extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private Connection cn;
	private PreparedStatement pssports;
	private ResultSet rs;
	private JComboBox cmbid;
	private JLabel lblOldPassword;
	private JLabel lblAccountUsername;
	private JButton btnupdate,btnReset;
	private JLabel label;
	private JTextField txtoldpass;
	private JCheckBox chckbxShowPassword;
	private JPasswordField txtnewpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClerkFrame frame = new UpdateClerkFrame();
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
	public UpdateClerkFrame()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateClerkFrame.class.getResource("/com/image/56-128.png")));
		setTitle("Update_Clerk");
		cn=Myoperation.createConnection();
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnReset = new JButton("RESET");
		btnReset.setBounds(244, 264, 89, 23);
		btnReset.addActionListener(this);
		contentPane.add(btnReset);
		
		chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setOpaque(false);
		chckbxShowPassword.setForeground(Color.WHITE);
		chckbxShowPassword.setBounds(275, 228, 121, 23);
		chckbxShowPassword.addActionListener(this);
		
		txtnewpass = new JPasswordField();
		txtnewpass.setBounds(224, 191, 172, 20);
		contentPane.add(txtnewpass);
		contentPane.add(chckbxShowPassword);
		
		
		 cmbid = new JComboBox();
		 cmbid.addActionListener(this);
		cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select UserID"}));
		cmbid.setBounds(156, 45, 178, 26);
		fillcombo();
		
		JLabel lblEnterNewPassword = new JLabel("Enter New Password");
		lblEnterNewPassword.setForeground(Color.WHITE);
		lblEnterNewPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterNewPassword.setBounds(42, 190, 143, 20);
		contentPane.add(lblEnterNewPassword);
		
		txtoldpass = new JTextField();
		txtoldpass.setBounds(224, 146, 172, 20);
		contentPane.add(txtoldpass);
		txtoldpass.setColumns(10);
		contentPane.add(cmbid);
		
		 lblOldPassword = new JLabel("Enter Old Password");
		 lblOldPassword.setForeground(new Color(255, 255, 255));
		 lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOldPassword.setBounds(42, 145, 130, 20);
		contentPane.add(lblOldPassword);
		
		lblAccountUsername = new JLabel("Account Username");
		lblAccountUsername.setForeground(new Color(255, 255, 255));
		lblAccountUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAccountUsername.setBounds(42, 108, 130, 26);
		contentPane.add(lblAccountUsername);
		
		txtid = new JTextField();
		txtid.setBounds(224, 108, 172, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		 btnupdate = new JButton("UPDATE");
		 btnupdate.addActionListener(this);
		btnupdate.setBounds(103, 264, 89, 23);
		contentPane.add(btnupdate);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(UpdateClerkFrame.class.getResource("/com/image/window_10.jpg")));
		label.setBounds(-326, -59, 801, 431);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cid;
		String uid=(String)cmbid.getSelectedItem();
		
		if(chckbxShowPassword.isSelected())
		{
			txtnewpass.setEchoChar((char)0);
		}
		else
		{
			txtnewpass.setEchoChar('*');
		}
		if(uid.equalsIgnoreCase("Select UserID"))
		{
			JOptionPane.showMessageDialog(this, "Select UserID");
			
		}

		else {
			cid=uid;
					
		if(e.getSource()==cmbid)
			
		{
			try {
				
			String strsql="select * from accountinfo where Userid=?";
			pssports=cn.prepareStatement(strsql);
			pssports.setString(1, cid);
			
			rs=pssports.executeQuery();
			if(rs.next())
			{
				String name=rs.getString("Userid");
				
				txtid.setText(name);
			
	
				
			}
				
				
				
				
			}
			catch(SQLException se)
			{
				
				System.out.println(se);
			}
			
			
			
			
			
		}
		
		
		if(e.getSource()==btnReset)
		{
			txtid.setText("");
			txtoldpass.setText("");
			txtnewpass.setText("");
			cmbid.setSelectedItem("Select UserID");
		}
			
		
		
		if(e.getSource()==btnupdate)
				{
			if(txtid.getText().toString().isEmpty()||txtoldpass.getText().toString().isEmpty()||txtnewpass.toString().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Fill Missing Fields");
			}
				
					
			else if(uid.equalsIgnoreCase("Select UserID"))
					{
						JOptionPane.showMessageDialog(this, "Select UserID");
						
					}
					
					else 
						{
						try
						{
							String strsql="select * from accountinfo where Userid=?";
							pssports=cn.prepareStatement(strsql);
							pssports.setString(1, cid);
							rs=pssports.executeQuery();
							if(rs.next())
							{
								
								
								String password=rs.getString("Userpass");
								
								if(password.equals(txtoldpass.getText()))
								{
								String strupdate="update accountinfo set Userid=?,Userpass=? where Userid=?";
								pssports=cn.prepareStatement(strupdate);
								String uname=txtid.getText();
								char[]arr=txtnewpass.getPassword();
								String upass=new String(arr);
								if(upass.isEmpty())
								{
									JOptionPane.showMessageDialog(this,"Enter New Password");
								}
								else
								{
								pssports.setString(1, uname);
								pssports.setString(2, upass);
								pssports.setString(3,cid);
								int row1=pssports.executeUpdate();
								if(row1>0)
								{
				
									JOptionPane.showMessageDialog(this, "Details Updated");
								}
			

								}
						}
								else
								{
									JOptionPane.showMessageDialog(this,"Old Password is Incorrect !");
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
		
		
	
	public void fillcombo()
	{
		try {
			String selectsql="select Userid from accountinfo where Usertype =?";
			pssports=cn.prepareStatement(selectsql);
			pssports.setString(1, "clerk");
			rs=pssports.executeQuery();
			while(rs.next())
			{
				String uid=rs.getString("Userid");
				cmbid.addItem(uid);
			}
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
