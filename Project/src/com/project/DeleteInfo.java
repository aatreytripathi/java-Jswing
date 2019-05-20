package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbproject.Myoperation;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class DeleteInfo extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JComboBox cmbmemid;
	private Connection cn;
	private PreparedStatement psmember,psselect;
	private ResultSet rs, rsselect ;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteInfo frame = new DeleteInfo();
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
	public DeleteInfo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteInfo.class.getResource("/com/image/edit-delete-128.png")));
		
		setTitle("Delete Info");
		cn=Myoperation.createConnection();
		creategui();
	}
	public void creategui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbmemid = new JComboBox();
		cmbmemid.setModel(new DefaultComboBoxModel(new String[] {"Select Member_ID"}));
		cmbmemid.setBounds(223, 70, 155, 30);
		fillcombo();
		contentPane.add(cmbmemid);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(164, 177, 89, 23);
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(DeleteInfo.class.getResource("/com/image/75976023-checkered-race-flag-waveing-vector-background-layout-design.jpg")));
		label.setBounds(-31, -78, 532, 400);
		contentPane.add(label);
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
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String mid;
		String memid=(String)cmbmemid.getSelectedItem();
		if(memid.equalsIgnoreCase("Select Member_ID"))
		{
			JOptionPane.showMessageDialog(this, "select");
			
		}
		else
		{
			mid=memid;
			try {
				
				String strsql="select * from memberinfo where memberid=?";
				psselect=cn.prepareStatement(strsql);
				psselect.setString(1, mid);
//				System.out.println(psselect);
				rsselect=psselect.executeQuery();
				
				{
					
					int confirm=JOptionPane.showConfirmDialog(this,"Are you Sure you want to Delete it ?");
//					System.out.println(confirm);
					if(confirm==0)
					{
						try
						{
							String strdelete="delete from memberinfo where memberid =?";
							psmember=cn.prepareStatement(strdelete);
							psmember.setString(1, mid);
							int row = psmember.executeUpdate();
							if(row>0)
							{
								JOptionPane.showMessageDialog(this,"Record Deleted");
							}
						}
						catch(SQLException se)
						{
							System.out.println(se);
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Record is not deleted");
					}
				}
			}
			catch(SQLException se) {}
			
			
			
		}
		
	}
}
