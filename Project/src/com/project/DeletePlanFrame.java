package com.project;
import java.awt.event.*;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import com.dbproject.Myoperation;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.sql.*;
import javax.swing.ImageIcon;

public class DeletePlanFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection cn;
	private PreparedStatement pssports;
	private JComboBox cmbid;
	private JLabel lblname;
	private JButton btndeleteplan;
	private ResultSet rs;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePlanFrame frame = new DeletePlanFrame();
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
	public DeletePlanFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeletePlanFrame.class.getResource("/com/image/edit-delete-128.png")));
//		setIconImage(Toolkit.getDefaultToolkit().getImage(DeletePlanFrame.class.getResource("/com/images/013-128.png")));
		cn=Myoperation.createConnection();
		setTitle("Delete_plan");
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbid = new JComboBox();
		cmbid.addActionListener(this);
		cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select Plan ID"}));
		cmbid.setBounds(195, 61, 125, 20);
		fillcombo();
		contentPane.add(cmbid);
		
		lblname = new JLabel("     Plan Name");
		lblname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblname.setBounds(140, 109, 252, 37);
		contentPane.add(lblname);
		
		 btndeleteplan = new JButton("DELETE PLAN");
		 btndeleteplan.addActionListener(this);
		btndeleteplan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btndeleteplan.setBounds(129, 174, 114, 23);
		contentPane.add(btndeleteplan);
		
		JLabel lblSelectId = new JLabel("Select ID");
		lblSelectId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectId.setBounds(76, 60, 86, 20);
		contentPane.add(lblSelectId);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(DeletePlanFrame.class.getResource("/com/image/windows_10_mobile.jpg")));
		label.setBounds(-96, -967, 691, 1229);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String pid;
		String uid=(String)cmbid.getSelectedItem();
		if(uid.equalsIgnoreCase("Select Plan ID"))
		{
			JOptionPane.showMessageDialog(this,"Select Plan ID");
		}
		else
		{
			pid=uid;
		if(e.getSource()==cmbid)
			
		{
			try 
			{
				
				String strsql="select * from planinfo where planid=?";
				pssports=cn.prepareStatement(strsql);
				pssports.setString(1, pid);
				rs=pssports.executeQuery();
				if(rs.next())
				{
					String name=rs.getString("planname");
					lblname.setText(name);
				}
			}
			catch(SQLException se)
			{
				
				System.out.println(se);
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
		if(e.getSource()==btndeleteplan)
		{
			int confirm=JOptionPane.showConfirmDialog(this, "Are you Sure you want to Delete it ?");
			if(confirm==0)
			{
				
				try 
				{
					
				String strdelete="delete from planinfo where planid=?";
				pssports=cn.prepareStatement(strdelete);
				pssports.setString(1, pid);
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
			String selectsql="select planid from planinfo";
			pssports=cn.prepareStatement(selectsql);
			rs=pssports.executeQuery();
			while(rs.next())
			{
				String uid=rs.getString("planid");
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
