package ui.main;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;

import Client.Application;
import Client.Config;
import Client.Resources;
import models.*;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.ImageIcon;
/**
 * public class ClientUI ,the menu window of Ghealth 
 * @author Bolous Abo Jaber 
 *
 */
public class ClientUI {

	private JFrame frmGhealth;
	private final JButton btnUsers = new JButton("Users");
	private final JLabel lblNewLabel = new JLabel("GHealth System");
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JButton btnMonthlyReport = new JButton("\u05DE\u05E2\u05D1\u05D3\u05D5\u05EA ");
	private final JButton btnCreateInvoice = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05D4\u05E2\u05E8\u05D5\u05EA ");
	private final JLabel lblNewLabel_3 = new JLabel("Connected to server : ");
	private final JButton btnExaminations = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05DE\u05E2\u05D1\u05D3\u05D4 ");
	private final JButton btnResults = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05EA\u05DB\u05E0\u05D0\u05D9");
	private final JLabel lblMedical = new JLabel("\u05DE\u05E2\u05D1\u05D3\u05D5\u05EA");
	private final JLabel label = new JLabel("\u05D4\u05E2\u05E8\u05D5\u05EA");
	private final JButton button = new JButton("\u05D4\u05E2\u05E8\u05D5\u05EA");
	private final JButton btnSignup = new JButton("SignUP");

	public ClientUI() {
		initialize();
		frmGhealth.setSize(656, 684);
		frmGhealth.setVisible(true);
		frmGhealth.setLocationRelativeTo(null);
		lblNewLabel_1.setBounds(331, 21, 279, 44);
		lblNewLabel_1.setText(Application.user.getFirstName() + " " + Application.user.getLastName() + " ("
				+ Application.user.getClass().getSimpleName() + ")");

		if (Application.client.isConnected())
			lblNewLabel_3.setText(
					"Connected to server: " + Config.getConfig().getHost() + ":" + Config.getConfig().getPort());
		else
			lblNewLabel_3.setText("Offline");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));

		lblNewLabel_3.setBounds(20, 634, 308, 14);

		frmGhealth.getContentPane().add(lblNewLabel_3);

		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(20, 80, 570, 4);
		frmGhealth.getContentPane().add(panel);
		lblMedical.setForeground(new Color(0, 191, 255));
		lblMedical.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMedical.setBounds(530, 111, 98, 35);

		frmGhealth.getContentPane().add(lblMedical);
		label.setForeground(new Color(0, 191, 255));
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(542, 228, 98, 35);
		
		frmGhealth.getContentPane().add(label);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setIcon(new ImageIcon(ClientUI.class.getResource("/img/info.png")));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setEnabled(false);
		button.setBorder(null);
		button.setBackground(Color.WHITE);
		button.setBounds(475, 274, 123, 53);
		
		frmGhealth.getContentPane().add(button);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSignup.setToolTipText("Users managment form");
		btnSignup.setHorizontalAlignment(SwingConstants.LEFT);
		btnSignup.setForeground(Color.BLACK);
		btnSignup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSignup.setEnabled(false);
		btnSignup.setBorder(null);
		btnSignup.setBackground(Color.WHITE);
		btnSignup.setBounds(210, 392, 184, 68);
		btnSignup.setIcon(Resources.getIcon("users.png"));
		frmGhealth.getContentPane().add(btnSignup);
		setPermissions();
	}

	private void disableAllButtons() {
		btnMonthlyReport.setEnabled(false);
		btnUsers.setEnabled(false);
		btnCreateInvoice.setEnabled(false);
		btnExaminations.setEnabled(false);
		btnResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new LabratoryArchive();
			}
		});
		btnResults.setEnabled(false);
	}

	private void setPermissions() {
		disableAllButtons();
		
		  if (Application.user.getClass() == Secretary.class) {
			btnCreateInvoice.setEnabled(true);
		} 

	}

	private void initialize() {

		Resources res = new Resources();
		frmGhealth = new JFrame();
		frmGhealth.setTitle("GHealth");
		frmGhealth.setResizable(false);
		frmGhealth.setIconImage(Resources.getImage("icon.png"));
		frmGhealth.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGhealth.setBackground(Color.WHITE);
		frmGhealth.getContentPane().setBackground(Color.WHITE);
		frmGhealth.getContentPane().setLayout(null);
		btnExaminations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new Labratory();
			}
		});

		btnExaminations.setHorizontalAlignment(SwingConstants.LEFT);
		btnExaminations.setForeground(Color.BLACK);
		btnExaminations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExaminations.setBorder(null);
		btnExaminations.setBackground(Color.WHITE);
		btnExaminations.setBounds(210, 157, 191, 53);
		btnExaminations.setIcon(Resources.getIcon("lab.png"));
		frmGhealth.getContentPane().add(btnExaminations);

		btnResults.setHorizontalAlignment(SwingConstants.LEFT);
		btnResults.setForeground(Color.BLACK);
		btnResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResults.setBorder(null);
		btnResults.setBackground(Color.WHITE);
		btnResults.setBounds(20, 157, 165, 53);
		btnResults.setIcon(Resources.getIcon("treatment.png"));
		frmGhealth.getContentPane().add(btnResults);
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UsersManagingUI();
			}
		});
		btnUsers.setToolTipText("Users managment form");

		btnUsers.setBounds(426, 392, 184, 68);
		btnUsers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUsers.setBackground(Color.WHITE);
		btnUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsers.setForeground(Color.BLACK);
		btnUsers.setBorder(null);
		btnUsers.setIcon(Resources.getIcon("users.png"));
		btnUsers.setBorder(null);

		frmGhealth.getContentPane().add(btnUsers);
		btnMonthlyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			//	MonthlyReport mReport = new MonthlyReport();

			//	mReport.getFrame().setVisible(true);
			}
		});

		btnMonthlyReport.setHorizontalAlignment(SwingConstants.LEFT);
		btnMonthlyReport.setForeground(Color.BLACK);
		btnMonthlyReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMonthlyReport.setBorder(null);
		btnMonthlyReport.setBackground(Color.WHITE);
		btnMonthlyReport.setBounds(429, 157, 181, 53);
		btnMonthlyReport.setIcon(Resources.getIcon("tests.png"));

		frmGhealth.getContentPane().add(btnMonthlyReport);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 284, 60);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setIcon(Resources.getIcon("pic.png"));
		frmGhealth.getContentPane().add(lblNewLabel);
		lblNewLabel_1.setIcon(Resources.getIcon("user.png"));
		frmGhealth.getContentPane().add(lblNewLabel_1);
		btnCreateInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnCreateInvoice.setIcon(new ImageIcon(ClientUI.class.getResource("/img/info.png")));
		btnCreateInvoice.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateInvoice.setForeground(Color.BLACK);
		btnCreateInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateInvoice.setBorder(null);
		btnCreateInvoice.setBackground(Color.WHITE);
		btnCreateInvoice.setBounds(259, 274, 181, 53);
		frmGhealth.getContentPane().add(btnCreateInvoice);

	}
}
