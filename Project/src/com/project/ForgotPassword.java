package com.project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbproject.Myoperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class ForgotPassword extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtemail;
	private JButton btnOk ;
	private Connection cn;
	private PreparedStatement pslogin,pssports;
	private ResultSet rs;
	private JLabel lblEnterUserid;
	private JTextField txtid;
	private String msg;
	private JButton btnSubmit;
	private JLabel label;
	private JLabel label_1;
	private JTextField txtpass1;
	private JTextField txtpass2;
	int flag=0;
	private JLabel label_2;
		/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	public ForgotPassword() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ForgotPassword.class.getResource("/com/image/App-02-128.png")));
		cn=Myoperation.createConnection();
		creategui();
		
	}

	public void creategui()
	{
		setTitle("Forgot Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterRegisteredEmail = new JLabel("Enter your Registered Email");
		lblEnterRegisteredEmail.setForeground(Color.WHITE);
		lblEnterRegisteredEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterRegisteredEmail.setBounds(20, 102, 201, 22);
		contentPane.add(lblEnterRegisteredEmail);
		
		txtemail = new JTextField();
		txtemail.setBounds(137, 137, 274, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(121, 190, 89, 23);
		btnOk.addActionListener(this);
		contentPane.add(btnOk);
		
		lblEnterUserid = new JLabel("Enter Userid");
		lblEnterUserid.setForeground(Color.WHITE);
		lblEnterUserid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterUserid.setBounds(20, 60, 95, 22);
		contentPane.add(lblEnterUserid);
		
		txtid = new JTextField();
		txtid.setBounds(137, 62, 160, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(137, 375, 89, 23);
		btnSubmit.addActionListener(this);
		btnSubmit.setEnabled(false);
		contentPane.add(btnSubmit);
		
		label = new JLabel("Enter New Password");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(20, 255, 147, 24);
		contentPane.add(label);
		
		label_1 = new JLabel("Re-Enter New Password");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(20, 307, 171, 24);
		contentPane.add(label_1);
		
		txtpass1 = new JTextField();
		txtpass1.setColumns(10);
		txtpass1.setBounds(233, 255, 177, 20);
		contentPane.add(txtpass1);
		
		txtpass2 = new JTextField();
		txtpass2.setColumns(10);
		txtpass2.setBounds(233, 310, 177, 20);
		contentPane.add(txtpass2);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(ForgotPassword.class.getResource("/com/image/IMG_109415.jpg")));
		label_2.setBounds(-448, -11, 974, 435);
		contentPane.add(label_2);
	}
	
	public String checkUserDetails(String ui)
	{
		 msg="UserID Does'nt Exist";
		try {
	String strsql="SELECT * FROM accountinfo  where Userid=?";
		pslogin=cn.prepareStatement(strsql);
		pslogin.setString(1,ui);
		rs=pslogin.executeQuery();
		if(rs.next())
		{
			String mail=rs.getString("Useremail");
			return mail;
		}
		}
		catch(SQLException se)
		{
			System.out.println(se);
			
		}
		return msg;
				
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String ui=txtid.getText();
		String email=txtemail.getText();
		btnSubmit.setEnabled(false);
		
	
	if(e.getSource()==btnOk)
	{
		if(ui.trim().isEmpty()||email.trim().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Fill Missing Fields");
		}
		else {
			
			String info=checkUserDetails(ui);
			if(info.equals(msg))
			{
				JOptionPane.showMessageDialog(this, msg);
				btnSubmit.setEnabled(false);
			}
			else
			{
				if(info.equalsIgnoreCase(email))
				{
					JOptionPane.showMessageDialog(this, " Email is Verified " );
					btnSubmit.setEnabled(true);
					
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Incorrect Email-id");
				btnSubmit.setEnabled(false);
				}
			}
	}
	}
	if(e.getSource()==btnSubmit)
	{
		String pass1=txtpass1.getText();
		String pass2=txtpass2.getText();
	
		if(pass1.isEmpty()||pass2.isEmpty()||ui.trim().isEmpty()||email.trim().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Fill Missing Fields");
		}
		else
		{
			if(pass2.equals(pass1))
				{
				btnSubmit.setEnabled(true);
				try
				{
					String strsql="select * from accountinfo where Userid=?";
					pssports=cn.prepareStatement(strsql);
					pssports.setString(1, ui);
					rs=pssports.executeQuery();
					if(rs.next())
					{
						
						String strupdate="update accountinfo set Userpass=? where Userid=?";
						pssports=cn.prepareStatement(strupdate);
						pssports.setString(1, pass2);
						pssports.setString(2, ui);
						int row1=pssports.executeUpdate();
						if(row1>0)
						{
		
							JOptionPane.showMessageDialog(this, "New Password is Saved");
							btnSubmit.setEnabled(false);
							this.dispose();
							login lf=new login();
							lf.setVisible(true);
							
						}
				}
				}
				catch(SQLException  se)
				{
					
					System.out.println(se);
				}
				
			
				}
		else
		{	
			JOptionPane.showMessageDialog(this, "Enter Same Password in Both Fields");
			btnSubmit.setEnabled(true);
		}
	}
	
		
	}
	
	}
	
}
