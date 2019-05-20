package com.project;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import com.dbproject.Myoperation;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Font;

public class AddClerkFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpassword;
	private Connection cn;
	private PreparedStatement pssports;
	private JCheckBox chckbxShowPassword;
	private JButton btncreate;
	private JLabel lblAccountId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddClerkFrame frame = new AddClerkFrame();
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
	public AddClerkFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddClerkFrame.class.getResource("/com/image/download.jpg")));
		cn=Myoperation.createConnection();
		setTitle("Add_Clerk");
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 296);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(128, 0, 0), new Color(128, 0, 0), new Color(128, 0, 0), new Color(128, 0, 0)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblAccountId = new JLabel("Account ID");
		lblAccountId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAccountId.setBounds(39, 65, 108, 14);
		contentPane.add(lblAccountId);
		
		txtid = new JTextField();
		txtid.setBounds(196, 63, 125, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.setOpaque(false);
		chckbxShowPassword.setForeground(Color.GRAY);
		chckbxShowPassword.setBounds(243, 161, 121, 23);
		chckbxShowPassword.addActionListener(this);
		contentPane.add(chckbxShowPassword);
		
		JLabel lblAccountPassword = new JLabel("Account Password");
		lblAccountPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAccountPassword.setBounds(39, 125, 137, 14);
		contentPane.add(lblAccountPassword);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(196, 122, 125, 20);
		contentPane.add(txtpassword);
		
		 btncreate = new JButton("CREATE");
		btncreate.addActionListener(this);
		btncreate.setBounds(138, 202, 89, 23);
		contentPane.add(btncreate);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AddClerkFrame.class.getResource("/com/image/Curve_2.jpg")));
		label.setBounds(0, 0, 434, 263);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String userid=txtid.getText();
		char[] ar=txtpassword.getPassword();
		String userpass=new String(ar);
		if(chckbxShowPassword.isSelected())
		{
			txtpassword.setEchoChar((char)0);
		}
		else
		{
			txtpassword.setEchoChar('*');
		}
		
		if(e.getSource()==btncreate)
		{
		if(userid.isEmpty()||userpass.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Account ID/Password Required");
		}
		else
		{
			try 
			{	
							
				
				String strinsert="insert into accountinfo(Userid, Userpass, Usertype) values(?,?,?)";
				pssports=cn.prepareStatement(strinsert);
				pssports.setString(1, userid);
				pssports.setString(2, userpass);
				pssports.setString(3, "clerk");
				int row=pssports.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this, "Clerk Added Succesfully");
					txtid.setText("");
					txtpassword.setText("");
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
					{
						pssports.close();
					}
					}
				catch(SQLException ie)
				{
					System.out.println(ie);
				}
			}
		}
		
	}
	}
}
