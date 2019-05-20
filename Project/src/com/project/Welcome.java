package com.project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class Welcome extends JFrame {

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
					Welcome frame = new Welcome();
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
	public Welcome() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/com/image/2722849-cricket-wallpapers.jpg")));
		setTitle("Welcome");
		creategui();
		new Thread(new Runnable() {
			
			@Override
			public void run() 
			{
				try {
					Thread.sleep(4000);
					Home h=new Home();
					h.setVisible(true);
					Welcome.this.dispose();
				}
				
				catch(InterruptedException ie) {
					System.out.println(ie);
				}
				
			}
		}).start();
	}

	public void creategui()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Welcome.class.getResource("/com/image/cricket_club.gif")));
		label_1.setBounds(54, 64, 270, 211);
		contentPane.add(label_1);
		
		JLabel lblmsg = new JLabel("Royal Challengers Club \r\n\tWelcomes You !!! ");
		lblmsg.setForeground(Color.WHITE);
		lblmsg.setFont(new Font("Monotype Corsiva", Font.ITALIC, 50));
		lblmsg.setBounds(30, 255, 733, 231);
		contentPane.add(lblmsg);
		
		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setIcon(new ImageIcon(Welcome.class.getResource("/com/image/2723084-cricket-wallpapers.jpg")));
		label.setBounds(-268, 0, 1144, 687);
		contentPane.add(label);
		
		String txt=lblmsg.getText();
		int txtlength=txt.length();
		
		tm=new Timer(100,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				counter++;
				if(counter>txtlength)
				{
					lblmsg.setText("");
					counter=0;
				}
				else
				{
				lblmsg.setText(txt.substring(0,counter));
				}
			}
		});
		tm.start();
		
		
	}
}
