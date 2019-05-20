package com.project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Contact_Frame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact_Frame frame = new Contact_Frame();
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
	public Contact_Frame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Contact_Frame.class.getResource("/com/image/images.png")));
//		setIconImage(Toolkit.getDefaultToolkit().getImage(Contact_Frame.class.getResource("/com/images/images (5).png")));
		setTitle("Contact Us");
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 321);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Contact_Frame.class.getResource("/com/image/images.png")));
		lblNewLabel.setBounds(0, 0, 524, 282);
		contentPane.add(lblNewLabel);
		
		JLabel lblPhoneNo = new JLabel("CALL US ON : 9792043115 , 9125234989.");
		lblPhoneNo.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblPhoneNo.setToolTipText("");
		lblPhoneNo.setBounds(235, 74, 299, 42);
		contentPane.add(lblPhoneNo);
		
		JLabel lblEmailRoyalclubnicgmailcom = new JLabel("E-MAIL : royalclub.nic@gmail.com");
		lblEmailRoyalclubnicgmailcom.setFont(new Font("Rockwell", Font.BOLD, 14));
		lblEmailRoyalclubnicgmailcom.setBounds(239, 139, 273, 33);
		contentPane.add(lblEmailRoyalclubnicgmailcom);
	}
}
