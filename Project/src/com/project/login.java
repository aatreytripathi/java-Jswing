package com.project;
import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.dbproject.Myoperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.UIManager;
public class login extends JFrame  implements ActionListener 
{

	private JPanel contentPane;
	private JTextField txtuserid;
	private JPasswordField txtuserpass;
	private JButton btnsubmit;
	private Connection cn;
	private PreparedStatement pslogin;
	private ResultSet rs;
	private JCheckBox chckbxShowPassword;
	private JButton btnReset, btnForgottenPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/com/image/login.jpg")));
		setBackground(Color.WHITE);
		setTitle("Login");
		cn=Myoperation.createConnection();
		
		createGui();
		
	}
	
	public void createGui()
	{
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 546, 405);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setOpaque(false);
		chckbxShowPassword.setForeground(Color.WHITE);
		chckbxShowPassword.setBounds(268, 273, 121, 23);
		chckbxShowPassword.addActionListener(this);
		
		btnReset = new JButton("RESET");
		btnReset.setBounds(268, 309, 89, 29);
		btnReset.addActionListener(this);
		
		btnForgottenPassword = new JButton("Forgot Password");
		btnForgottenPassword.addActionListener(this);
		btnForgottenPassword.setBorder(null);
		btnForgottenPassword.setBackground(SystemColor.windowBorder);
		btnForgottenPassword.setOpaque(false);
		btnForgottenPassword.setForeground(Color.WHITE);
		btnForgottenPassword.setBounds(95, 273, 158, 23);
		contentPane.add(btnForgottenPassword);
		contentPane.add(btnReset);
		contentPane.add(chckbxShowPassword);
		
		JLabel lblname = new JLabel("Enter  UserId");
		lblname.setForeground(Color.WHITE);
		lblname.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblname.setBounds(73, 168, 120, 35);
		contentPane.add(lblname);
		
		JLabel lblicon = new JLabel("");
		lblicon.setIcon(new ImageIcon(login.class.getResource("/com/image/if_Rounded-31_2024644.png")));
		lblicon.setBounds(203, 11, 134, 134);
		contentPane.add(lblicon);
		
		txtuserid = new JTextField();
		txtuserid.setToolTipText("Enter UserID");
		txtuserid.setForeground(Color.WHITE);
		txtuserid.setOpaque(false);
		txtuserid.setBounds(203, 176, 186, 20);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		JLabel lblUserpass = new JLabel("Password");
		lblUserpass.setForeground(Color.WHITE);
		lblUserpass.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		lblUserpass.setBounds(73, 227, 86, 29);
		contentPane.add(lblUserpass);
		
		txtuserpass = new JPasswordField();
		txtuserpass.setToolTipText("Enter Password\r\n");
		txtuserpass.setForeground(Color.WHITE);
		txtuserpass.setOpaque(false);
		txtuserpass.setBounds(203, 232, 186, 21);
		contentPane.add(txtuserpass);
		
		btnsubmit = new JButton("LOGIN");
		btnsubmit.addActionListener(this);
		
		btnsubmit.setBounds(117, 309, 99, 29);
		
		contentPane.add(btnsubmit);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(login.class.getResource("/com/image/013-128.png")));
		label.setBounds(399, 98, 120, 215);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(login.class.getResource("/com/image/black.jpg")));
		label_1.setBounds(0, 0, 530, 367);
		contentPane.add(label_1);
	}
	public String checkUserDetails(String userid,String userpass)
	{
		try {
	String strsql="SELECT * FROM accountinfo  where Userid=? and Userpass=?";
	
		pslogin=cn.prepareStatement(strsql);
		
		pslogin.setString(1,userid);
		pslogin.setString(2, userpass);
		rs=pslogin.executeQuery();
		if(rs.next())
		{
			String type=rs.getString("Usertype");
			return type;
		}
			
		}
		catch(SQLException se)
		{
			System.out.println(se);
			
		}
		return " Invalid UserID/Password ";
		
		
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String ui=txtuserid.getText();
		char[]arr=txtuserpass.getPassword();
		String upass=new String(arr);
		
		if(chckbxShowPassword.isSelected())
		{
			txtuserpass.setEchoChar((char)0);
		}
		else
		{
			txtuserpass.setEchoChar('*');
		}
		
		
		if(e.getSource()==btnReset)
		{
			txtuserid.setText("");
			txtuserpass.setText("");
		}
		if(e.getSource()==btnsubmit)
		{
	if(ui.trim().isEmpty()||upass.isEmpty())
		{
			
		JOptionPane.showMessageDialog(this,"UserID/Password Needed");		
			
		}
	else {
			String info=checkUserDetails(ui, upass);
			if(info.equalsIgnoreCase("admin"))
			{
			AdminPortal ad=new AdminPortal();
				ad.setVisible(true);
				this.dispose();
			}
			
			else if(info.equalsIgnoreCase("clerk"))
			{
				Clerk_Portal ck=new Clerk_Portal();
				ck.setVisible(true);
				this.dispose();
			}
			
			
		else {
			JOptionPane.showMessageDialog(this,info);
			
		}
	}
		}
		if(e.getSource()==btnForgottenPassword)
		{
			this.dispose();
			ForgotPassword fp=new ForgotPassword();
			fp.setVisible(true);
		}
		
		
	}
}
