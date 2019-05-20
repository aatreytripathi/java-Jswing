package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dbproject.Myoperation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;
public class Plans extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtplanid;
	private JTextField txtplanname;
	private JTextField txtfacilities;
	private JTextField txtcharge;
	private JTextField txtduration;
	private JButton btnAdd;
	private Connection cnn;
	private PreparedStatement psplans;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Plans frame = new Plans();
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
	public Plans() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Plans.class.getResource("/com/image/BT_binder-128.png")));
		creategui();
		cnn=Myoperation.createConnection();
	}
	public void creategui()
	{
		setTitle("Add_Plans");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblplanm = new JLabel("Plan_Name");
		lblplanm.setForeground(Color.WHITE);
		lblplanm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblplanm.setBounds(45, 84, 104, 23);
		contentPane.add(lblplanm);
		
		JLabel lblplanid = new JLabel("Plan_ID");
		lblplanid.setForeground(Color.WHITE);
		lblplanid.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblplanid.setBounds(45, 51, 104, 23);
		contentPane.add(lblplanid);
		
		JLabel lblfacilities = new JLabel("Facilities");
		lblfacilities.setForeground(Color.WHITE);
		lblfacilities.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblfacilities.setBounds(45, 118, 104, 23);
		contentPane.add(lblfacilities);
		
		JLabel lblcharge = new JLabel("Charge");
		lblcharge.setForeground(Color.WHITE);
		lblcharge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblcharge.setBounds(45, 151, 104, 23);
		contentPane.add(lblcharge);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setForeground(Color.WHITE);
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDuration.setBounds(45, 189, 104, 23);
		contentPane.add(lblDuration);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(134, 243, 89, 23);
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		txtplanid = new JTextField();
		txtplanid.setToolTipText("Enter Plan_ID");
		txtplanid.setBounds(195, 52, 157, 22);
		contentPane.add(txtplanid);
		txtplanid.setColumns(10);
		
		txtplanname = new JTextField();
		txtplanname.setToolTipText("Enter Plan_Name");
		txtplanname.setColumns(10);
		txtplanname.setBounds(195, 85, 157, 22);
		contentPane.add(txtplanname);
		
		txtfacilities = new JTextField();
		txtfacilities.setToolTipText("Enter Facilities Available");
		txtfacilities.setColumns(10);
		txtfacilities.setBounds(195, 119, 157, 22);
		contentPane.add(txtfacilities);
		
		txtcharge = new JTextField();
		txtcharge.setToolTipText("Enter Cost");
		txtcharge.setColumns(10);
		txtcharge.setBounds(195, 152, 157, 22);
		contentPane.add(txtcharge);
		
		txtduration = new JTextField();
		txtduration.setColumns(10);
		txtduration.setBounds(195, 190, 157, 22);
		contentPane.add(txtduration);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Plans.class.getResource("/com/image/window_10.jpg")));
		label.setBounds(-597, 0, 1021, 632);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
	
		String id=txtplanid.getText();
		String name=txtplanname.getText();
		String fac=txtfacilities.getText();
		String chrg=txtcharge.getText();
		String dur=txtduration.getText();
		
		if(id.isEmpty()||name.isEmpty()||chrg.isEmpty()||dur.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Fill Missing Fields");
		}
		else
		{
			try {
				
				
				String insert ="INSERT into planinfo ( planid, planname, facilities, charge, duration) values(?,?,?,?,?)";
				psplans=cnn.prepareStatement(insert);
				psplans.setString(1,id);
				psplans.setString(2, name);
				psplans.setString(3, fac);
				psplans.setString(4, chrg);
				psplans.setString(5, dur);
				
				int row=psplans.executeUpdate();
			
				if(row>0)
				{
					JOptionPane.showMessageDialog(this,"Plan Added Sucessfully");
				}
			}
			catch(SQLException se) {}
			finally
			{
				try {
					if(psplans!=null)
						psplans.close();
				}
				catch(SQLException ie)
				{
					System.out.println(ie);
				}
			}
			
			
		}
	}
}
