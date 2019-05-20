package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JMenuItem;

public class Home extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel lblSomeVideos;
	private JButton btnabout,btncontact,btnlogin,btnMedia;
	JMenuBar  menuBar;
	private JLabel lblRoyalChallengersCricket;
	private JLabel label_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/com/image/images.jpg")));
		setTitle("HomePage");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 898, 553);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Home.class.getResource("/com/image/77586f80d1887e6.jpg")));
		label_3.setBounds(449, 92, 621, 288);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Home.class.getResource("/com/image/wwww.png")));
		label_4.setBounds(882, 92, 549, 288);
		contentPane.add(label_4);
		
		lblRoyalChallengersCricket = new JLabel("\t\t\t\t\t\t\t\tRoyal  Challengers  Cricket  Club");
		lblRoyalChallengersCricket.setForeground(new Color(230, 230, 250));
		lblRoyalChallengersCricket.setFont(new Font("Lucida Handwriting", Font.BOLD, 42));
		lblRoyalChallengersCricket.setBounds(221, 11, 1142, 70);
		contentPane.add(lblRoyalChallengersCricket);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Home.class.getResource("/com/image/H7ye.gif")));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(401, 441, 536, 254);
		contentPane.add(label_1);
		
		JLabel lblKjkj = new JLabel("");
		lblKjkj.setIcon(new ImageIcon(Home.class.getResource("/com/image/abdrunout_1426772308.gif")));
		lblKjkj.setBounds(0, 441, 527, 250);
		contentPane.add(lblKjkj);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Home.class.getResource("/com/image/f1d45b58316406b3541ba925109ec836.gif")));
		label.setBounds(882, 441, 481, 255);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Home.class.getResource("/com/image/colorful-cricket-tournament-banner-illustration_csp43435626.jpg")));
		label_2.setBounds(0, 92, 450, 288);
		contentPane.add(label_2);
		
		  menuBar= new JMenuBar();
			 
			menuBar.setBackground(SystemColor.menu);
			setJMenuBar(menuBar);
			
			 btnabout = new JButton("About");
			 btnabout.setBackground(SystemColor.menu);
			 btnabout.addActionListener(this);
			menuBar.add(btnabout);
			
			 btncontact = new JButton("Contact Us");
			 btncontact.setBackground(SystemColor.menu);
			 btncontact.addActionListener(this);
			menuBar.add(btncontact);
			
			 btnMedia = new JButton("Media");
			btnMedia.setBackground(SystemColor.menu);
			btnMedia.addActionListener(this);
			menuBar.add(btnMedia);
			
			 btnlogin = new JButton("Login");
			 btnlogin.setBackground(SystemColor.menu);
			 btnlogin.addActionListener(this);
			 menuBar.add(btnlogin);
			
			lblSomeVideos = new JLabel("Some Videos");
			lblSomeVideos.setForeground(new Color(255, 255, 255));
			lblSomeVideos.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
			lblSomeVideos.setBackground(new Color(51, 0, 153));
			lblSomeVideos.setBounds(10, 391, 123, 39);
			contentPane.add(lblSomeVideos);
			
			label_5 = new JLabel("");
			label_5.setIcon(new ImageIcon(Home.class.getResource("/com/image/IMG_217127.jpg")));
			label_5.setBounds(0, -26, 1372, 721);
			contentPane.add(label_5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
		
		if(e.getSource()==btnlogin)
		{
			login lc=new login();
			lc.setVisible(true);
		}
		if(e.getSource()==btnabout)
		{
			AboutClubFrame ac=new AboutClubFrame();
			ac.setVisible(true);
		}
		if(e.getSource()==btncontact)
		{
			Contact_Frame cf=new Contact_Frame();
			cf.setVisible(true);
		}
		if(e.getSource()==btnMedia)
		{
			MediaFrame mf=new MediaFrame();
			mf.setVisible(true);
		}
				
	}
}
