package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JToggleButton;

public class AdminPortal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLogout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPortal frame = new AdminPortal();
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
	public AdminPortal() {
		setBackground(SystemColor.control);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminPortal.class.getResource("/com/image/admin.png")));
		setTitle("Admin_Portal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 517);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClerk = new JMenu("Clerk_Registration");
		menuBar.add(mnClerk);
		
		JMenuItem mntmAddAccount = new JMenuItem("New Registration");
		mntmAddAccount.addActionListener(this);
		mnClerk.add(mntmAddAccount);
		
		JMenuItem mntmUpdateAccount = new JMenuItem("Update");
		mntmUpdateAccount.addActionListener(this);
		mnClerk.add(mntmUpdateAccount);
		
		JMenuItem mntmDeleteAccount = new JMenuItem("Delete");
		mntmDeleteAccount.addActionListener(this);
		mnClerk.add(mntmDeleteAccount);
		
		JMenu mnPlans = new JMenu("Plans");
		menuBar.add(mnPlans);
		
		JMenuItem mntmAddPlan = new JMenuItem("Add Plan");
		mntmAddPlan.addActionListener(this);
		mnPlans.add(mntmAddPlan);
		
		JMenuItem mntmUpdatePlan = new JMenuItem("Update Plan");
		mntmUpdatePlan.addActionListener(this);
		mnPlans.add(mntmUpdatePlan);
		
		JMenuItem mntmDeletePlan = new JMenuItem("Delete Plan");
		mntmDeletePlan.addActionListener(this);
		mnPlans.add(mntmDeletePlan);
		
		JMenuItem mntmViewplans = new JMenuItem("ViewPlans");
		mntmViewplans.addActionListener(this);
		mnPlans.add(mntmViewplans);
		
		JMenu menu = new JMenu("");
		menuBar.add(menu);
		
		JMenu mnViewMembersInfo = new JMenu("View Members Info.");
		menuBar.add(mnViewMembersInfo);
		
		JMenuItem mntmPlanwise = new JMenuItem("Plan_Wise Members");
		mntmPlanwise.addActionListener(this);
		mnViewMembersInfo.add(mntmPlanwise);
		
		JMenuItem mntmDatewiseMembers = new JMenuItem("Date_Wise Members");
		mntmDatewiseMembers.addActionListener(this);
		mnViewMembersInfo.add(mntmDatewiseMembers);
		
		 btnLogout = new JButton("Logout");
		 menuBar.add(btnLogout);
		 btnLogout.setBorderPainted(false);
		 btnLogout.setBackground(SystemColor.menu);
		 btnLogout.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdminPortal.class.getResource("/com/image/download-cricket-wallpaper-hd-background-9377668 (1).jpg")));
		label.setBounds(0, 0, 1366, 696);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
			
		if(e.getActionCommand().equalsIgnoreCase("Add Plan"))
		{
			Plans p=new Plans();
			p.setVisible(true);
		
		}
			if(e.getActionCommand().equalsIgnoreCase("update Plan"))
			{
				UpdatePlanFrame up=new UpdatePlanFrame();
				up.setVisible(true);
			}
				if(e.getActionCommand().equalsIgnoreCase("delete Plan"))
				{
					DeletePlanFrame dp=new DeletePlanFrame();
					dp.setVisible(true);
				}
		if(e.getActionCommand().equalsIgnoreCase("New Registration"))
				{
					AddClerkFrame ac=new AddClerkFrame();
					ac.setVisible(true);
				}
				
		if(e.getActionCommand().equalsIgnoreCase("Update"))
		{
			UpdateClerkFrame uc=new UpdateClerkFrame();
			uc.setVisible(true);
		}
		if(e.getActionCommand().equalsIgnoreCase("Delete"))
		{
			DeleteClerkFrame dc=new DeleteClerkFrame();
			dc.setVisible(true);
		}
		
		if(e.getActionCommand().equalsIgnoreCase("Logout"))
		{
			this.dispose();
			Home h=new Home();
			h.setVisible(true);
		
		}
		if(e.getActionCommand().equalsIgnoreCase("Plan_Wise Members"))
		{
			ViewAllMember vi=new ViewAllMember();
			vi.setVisible(true);
		}
		if(e.getActionCommand().equalsIgnoreCase("Date_Wise Members"))
		{
			DateWiseMember dm=new DateWiseMember();
			dm.setVisible(true);
			
		}
		if(e.getActionCommand().equalsIgnoreCase("ViewPlans"))
		{
			ViewAllPlans vp=new ViewAllPlans();
			vp.setVisible(true);
			
		}
	}
}
