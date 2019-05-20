package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class AboutClubFrame extends JFrame {

	private JPanel contentPane;
	int txtlength=0;
	Timer tm;
	int counter =0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutClubFrame frame = new AboutClubFrame();
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
	public AboutClubFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutClubFrame.class.getResource("/com/image/Cricket.png")));
//		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutClubFrame.class.getResource("/com/images/if_Cricket_71198.png")));
		setBackground(new Color(210, 180, 140));
		setTitle("About_Club");
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 561);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AboutClubFrame.class.getResource("/com/image/_41989104_hook_side_416x300.gif")));
		label.setBounds(56, 228, 446, 284);
		contentPane.add(label);
		
		JLabel lblRoyalChallegersCricket = new JLabel("  ROYAL CHALLENGERS CRICKET CLUB");
		lblRoyalChallegersCricket.setForeground(Color.BLUE);
		lblRoyalChallegersCricket.setBackground(new Color(32, 178, 170));
		lblRoyalChallegersCricket.setFont(new Font("Chiller", Font.BOLD, 20));
		lblRoyalChallegersCricket.setBounds(109, 11, 373, 41);
		contentPane.add(lblRoyalChallegersCricket);
		
		JTextArea txtrWeAreVery = new JTextArea();
		txtrWeAreVery.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtrWeAreVery.setText("We are very proud of what Royal Cricket Club has achieved over the last few \r\nyears, including buying our own ground, building a new pavilion and supporting \r\nthe local community by playing high quality and enjoyable cricket at all levels. \r\nWe are a very friendly club, and welcome new players and supporters alike.\r\n\r\nThe Club runs three Saturday League sides competing in the Home Countries \r\nPremier League and Thames Valley Cricket League, a Sunday XI, and a thriving \r\nand successful junior section for both boys and girls and a Ladies team which\r\n is becoming a real force in ladies cricket locally.");
		txtrWeAreVery.setBounds(10, 52, 546, 150);
		contentPane.add(txtrWeAreVery);
		
		
		String txt=lblRoyalChallegersCricket.getText();
		int txtlength=txt.length();
		
		tm=new Timer(150,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				counter++;
				if(counter>txtlength)
				{
					lblRoyalChallegersCricket.setText("");
					counter=0;
				}
				else
				{
					lblRoyalChallegersCricket.setText(txt.substring(0,counter));
					if(lblRoyalChallegersCricket.getForeground()==Color.blue)
					{
						lblRoyalChallegersCricket.setForeground(Color.GREEN);
					}
					else if(lblRoyalChallegersCricket.getForeground()==Color.GREEN)
					{
						lblRoyalChallegersCricket.setForeground(Color.red);
					}
					else if(lblRoyalChallegersCricket.getForeground()==Color.red)
					{
						lblRoyalChallegersCricket.setForeground(Color.BLUE);
					}
				
				}
			}
		});
		tm.start();
	}
}
