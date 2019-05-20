package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;

public class Clerk_Portal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mntmPlanwiseMembers,mntmDatewiseMembers;
	private JButton btnLogout,btnCheckMemberValidity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clerk_Portal frame = new Clerk_Portal();
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
	public Clerk_Portal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clerk_Portal.class.getResource("/com/image/office-worker-working-008-128.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Clerk_Portal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMemberinfo = new JMenu("Member_Info");
		menuBar.add(mnMemberinfo);
		
		JMenuItem mntmAddInfo = new JMenuItem("Add Info.");
		mntmAddInfo.addActionListener(this);
		mnMemberinfo.add(mntmAddInfo);
		
		JMenuItem mntmUpdateInfo = new JMenuItem("Update Info.");
		mntmUpdateInfo.addActionListener(this);
		mnMemberinfo.add(mntmUpdateInfo);
		
		JMenuItem mntmDeleteInfo = new JMenuItem("Delete Info.");
		mntmDeleteInfo.addActionListener(this);
		mnMemberinfo.add(mntmDeleteInfo);
		
		JMenu mnViewMembersInfo = new JMenu("View Members info.");
		menuBar.add(mnViewMembersInfo);
		
		mntmPlanwiseMembers = new JMenuItem("Plan_Wise Members");
		mntmPlanwiseMembers.addActionListener(this);
		mnViewMembersInfo.add(mntmPlanwiseMembers);
		
		 mntmDatewiseMembers = new JMenuItem("Date_Wise Members");
		mntmDatewiseMembers.addActionListener(this);
		mnViewMembersInfo.add(mntmDatewiseMembers);
		
		btnCheckMemberValidity = new JButton("Check Member Validity");
		btnCheckMemberValidity.setBackground(SystemColor.menu);
		menuBar.add(btnCheckMemberValidity);
		btnCheckMemberValidity.addActionListener(this);
		
		 btnLogout = new JButton("Logout");
		menuBar.add(btnLogout);
		btnLogout.setBackground(SystemColor.menu);
		btnLogout.setBorderPainted(false);
		btnLogout.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Clerk_Portal.class.getResource("/com/image/2722742-cricket-wallpapers.jpg")));
		label.setBounds(-94, 0, 1460, 697);
		contentPane.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equalsIgnoreCase("Add info."))
		{
			Member mb=new Member();
			mb.setVisible(true);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Update info."))
		{
			Update_info ui=new Update_info();
			ui.setVisible(true);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Delete Info."))
		{
			DeleteInfo di=new DeleteInfo();
			di.setVisible(true);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Plan_Wise Members"))
		{
			ViewAllMember vi=new ViewAllMember();
			vi.setVisible(true);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Date_Wise Members"))
		{
			DateWiseMember dm=new DateWiseMember();
			dm.setVisible(true);
		}
		else if(e.getSource()==btnLogout)
		{
			this.dispose();
			Home h=new Home();
			h.setVisible(true);
		
		}
		else if(e.getSource()==btnCheckMemberValidity)
		{
			ValidUserFrame dm=new ValidUserFrame();
			dm.setVisible(true);
		}
	}
}
