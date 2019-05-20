package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.Toolkit;

public class MediaFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MediaFrame frame = new MediaFrame();
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
	public MediaFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MediaFrame.class.getResource("/com/image/marketplace_banner.png")));
		setTitle("Media_Frame");
		createGui();
	}
	public void createGui()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSoial = new JLabel("Social Media");
		lblSoial.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblSoial.setBackground(new Color(15, 128, 15));
		lblSoial.setForeground(new Color(0, 0, 0));
		lblSoial.setBounds(10, 11, 591, 34);
		contentPane.add(lblSoial);
		
		JLabel lblTwitter = new JLabel("Twitter");
		lblTwitter.setIcon(new ImageIcon(MediaFrame.class.getResource("/com/image/icons8-twitter-50.png")));
		lblTwitter.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblTwitter.setBounds(10, 72, 601, 52);
		contentPane.add(lblTwitter);
		
		JLabel lblFacebook = new JLabel("Facebook");
		lblFacebook.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblFacebook.setIcon(new ImageIcon(MediaFrame.class.getResource("/com/image/icons8-facebook-26.png")));
		lblFacebook.setBounds(10, 360, 601, 52);
		contentPane.add(lblFacebook);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MediaFrame.class.getResource("/com/image/safe_image.jpg")));
		lblNewLabel.setBounds(10, 413, 130, 43);
		contentPane.add(lblNewLabel);
		
		JTextArea txtrWeAreLooking = new JTextArea();
		txtrWeAreLooking.setText("We are looking for players this Sunday (29th). Please contact the Andrew \r\nChappell andrewchappell@live.co.uk if you are interested\r\n2 days, 22 hours ago\r\n\r\nRT @BerksCricket: Richard Morris brings up his century with a 6. 104 off \r\n149 balls. 2x6 10x4. @BerksCricket 312-4 off 68 overs\r\n3 days, 2 hours ago\r\n\r\nIt\u2019s our annual #hccsummerball this weekend and we have a fantastic \r\n#silentauction. Feel free to take a look https://t.co/fbzQqEz8YY\r\n1 week ago");
		txtrWeAreLooking.setBounds(10, 135, 591, 214);
		contentPane.add(txtrWeAreLooking);
		
		JTextArea txtrPostedByHenley = new JTextArea();
		txtrPostedByHenley.setText("POSTED BY: HENLEY CRICKET CLUB SUMMER BALL 2018 AUCTION OF PROMISES \r\n1 WEEK AGO\r\nIt\u2019s our annual #hccsummerball this weekend and we have a fantastic\r\n #silentauction. Feel free to take a look\r\nhttps://auctionofpromises.com/HenleyCCSummerBallAuction\r\nFacebook likes(2)  Facebook shares(0)\r\nVIEW ON FACEBOOK");
		txtrPostedByHenley.setBounds(10, 456, 591, 161);
		contentPane.add(txtrPostedByHenley);
	}
}
