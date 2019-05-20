package com.project;
import java.awt.event.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbproject.Myoperation;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class DeleteClerkFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btndelete;
	private JComboBox cmbid;
	private Connection cn;
	private PreparedStatement pssports;
	private ResultSet rs;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteClerkFrame frame = new DeleteClerkFrame();
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
	public DeleteClerkFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteClerkFrame.class.getResource("/com/image/login.jpg")));
		setTitle("Delete_Clerk");
		cn=Myoperation.createConnection();
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79)));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbid = new JComboBox();
		cmbid.addActionListener(this);
		cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select Username"}));
		cmbid.setToolTipText("");
		cmbid.setBounds(227, 78, 152, 29);
		fillcombo();
		contentPane.add(cmbid);
		
		 btndelete = new JButton("DELETE");
		 btndelete.addActionListener(this);
		btndelete.setBounds(177, 187, 89, 23);
		contentPane.add(btndelete);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(DeleteClerkFrame.class.getResource("/com/image/75976023-checkered-race-flag-waveing-vector-background-layout-design.jpg")));
		label.setBounds(0, 0, 434, 262);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cid;
		String uid=(String)cmbid.getSelectedItem();
		if(uid.equalsIgnoreCase("Select Username"))
		{
			JOptionPane.showMessageDialog(this,"Select Username");
		}
		else
		{
			cid=uid;
		
		if(e.getSource()==btndelete)
		{
			int confirm=JOptionPane.showConfirmDialog(this, "Are you Sure you want to Delete it ?");
			if(confirm==0)
			{
				
				try 
				{
					
				String strdelete="delete from accountinfo where Userid=?";
				pssports=cn.prepareStatement(strdelete);
				pssports.setString(1, cid);
				int row=pssports.executeUpdate();
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Record Deleted Suceessfully");
				}
					
					
				}
				catch(SQLException c)
				{
					System.out.println(c);
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
