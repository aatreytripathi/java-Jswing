package com.project;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import com.dbproject.Myoperation;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
public class UpdatePlanFrame extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtplanname;
	private Connection cn;
	private PreparedStatement pssports;
	private ResultSet rs;
	private JComboBox cmbplanid;
	private JLabel lblfacilities;
	private JLabel lblplanname;
	private JButton btnupdate;
	private JTextField txtfacilities;
	private JTextField txtcharge;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePlanFrame frame = new UpdatePlanFrame();
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
	public UpdatePlanFrame()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdatePlanFrame.class.getResource("/com/image/56-128.png")));
		setTitle("Update_Plan");
		cn=Myoperation.createConnection();
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 352);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(219, 112, 147));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbplanid = new JComboBox();
		 cmbplanid.addActionListener(this);
		cmbplanid.setModel(new DefaultComboBoxModel(new String[] {"Select PlanID"}));
		cmbplanid.setBounds(156, 51, 155, 20);
		fillcombo();
		contentPane.add(cmbplanid);
		
		 lblfacilities = new JLabel("Facilities");
		 lblfacilities.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblfacilities.setBounds(42, 148, 101, 14);
		contentPane.add(lblfacilities);
		
		lblplanname = new JLabel("Plan Name");
		lblplanname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblplanname.setBounds(42, 108, 101, 14);
		contentPane.add(lblplanname);
		
		txtplanname = new JTextField();
		txtplanname.setBounds(181, 105, 130, 20);
		contentPane.add(txtplanname);
		txtplanname.setColumns(10);
		
		 btnupdate = new JButton("UPDATE");
		 btnupdate.addActionListener(this);
		btnupdate.setBounds(110, 266, 89, 23);
		contentPane.add(btnupdate);
		
		JLabel lblcharge = new JLabel("Charge");
		lblcharge.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblcharge.setBounds(42, 187, 101, 14);
		contentPane.add(lblcharge);
		
		txtfacilities = new JTextField();
		txtfacilities.setColumns(10);
		txtfacilities.setBounds(181, 145, 130, 20);
		contentPane.add(txtfacilities);
		
		txtcharge = new JTextField();
		txtcharge.setColumns(10);
		txtcharge.setBounds(181, 184, 130, 20);
		contentPane.add(txtcharge);
		
		JLabel lblduration = new JLabel("Duration");
		lblduration.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblduration.setBounds(42, 222, 101, 14);
		contentPane.add(lblduration);
		
		txtduration = new JTextField();
		txtduration.setColumns(10);
		txtduration.setBounds(181, 219, 130, 20);
		contentPane.add(txtduration);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UpdatePlanFrame.class.getResource("/com/image/IMG_217161.jpg")));
		label.setBounds(-312, -332, 746, 646);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cid;
		String uid=(String)cmbplanid.getSelectedItem();
		if(uid.equalsIgnoreCase("Select PlanID"))
		{
			JOptionPane.showMessageDialog(this, " Select Plan_ID");
			
		}

		else {
			cid=uid;
				
			
		if(e.getSource()==cmbplanid)
			
		{
			try {
				
			String strsql="select * from planinfo where planid=?";
			pssports=cn.prepareStatement(strsql);
			pssports.setString(1, uid);
			rs=pssports.executeQuery();
			if(rs.next())
			{
				String name=rs.getString("planname");
				String fac=rs.getString("facilities");
				String chr=rs.getString("charge");
				String dur=rs.getString("duration");
				
				txtplanname.setText(name);
				txtfacilities.setText(fac);
				txtcharge.setText(chr);
				txtduration.setText(dur);
	
				
			}
				
				
				
				
			}
			catch(SQLException se)
			{
				
				System.out.println(se);
			}
			
			
			
			
			
		}

		if(e.getSource()==btnupdate)
				{
			if(txtplanname.getText().toString().isEmpty()||txtcharge.getText().toString().isEmpty()||txtduration.getText().toString().isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Fill Missing Fields");
			}
				
					
			else if(uid.equalsIgnoreCase("Select PlanID"))
					{
						JOptionPane.showMessageDialog(this, "Select Plan_ID");
						
					}
					
					else
					{
						
						try
						{
								String strupdate="update planinfo set planname=?,facilities=?,charge=?,duration=? where planid=?";
								pssports=cn.prepareStatement(strupdate);
								
								String pname=txtplanname.getText();
								String pfac=txtfacilities.getText();
								String pchr=txtcharge.getText();
								String pdur=txtduration.getText();
								pssports.setString(1, pname);
								pssports.setString(2, pfac);
								pssports.setString(3,pchr);
								pssports.setString(4,pdur);
								pssports.setString(5,uid);
								int row1=pssports.executeUpdate();
								if(row1>0)
								{
				
									JOptionPane.showMessageDialog(this, "Plan Details Updated", "updation", JOptionPane.INFORMATION_MESSAGE);
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
			String selectsql="select planid from planinfo";
			pssports=cn.prepareStatement(selectsql);
			rs=pssports.executeQuery();
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
