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

import ui.utils.Logo;
/**
 * public class ClientUI ,the menu window of Ghealth 
 * @author Bolous Abo Jaber 
 *
 */
public class ClientUI {

	private JFrame frmGhealth;
	private final JButton btnUsers = new JButton("Users");
	private final JLabel logoLbl = new JLabel(Logo.getLogoText()); 
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JButton labsBtn = new JButton("\u05DE\u05E2\u05D1\u05D3\u05D5\u05EA ");
	private final JButton addDescBtn = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05D4\u05E2\u05E8\u05D5\u05EA ");
	private final JLabel lblNewLabel_3 = new JLabel("Connected to server : ");
	private final JButton btnExaminations = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05DE\u05E2\u05D1\u05D3\u05D4 ");
	private final JButton btnResults = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05EA\u05DB\u05E0\u05D0\u05D9");
	private final JButton button = new JButton("\u05D4\u05E2\u05E8\u05D5\u05EA");
	private final JButton btnSignup = new JButton("SignUP");

	public ClientUI() {
		initialize();
		frmGhealth.setSize(656, 624);
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

		lblNewLabel_3.setBounds(10, 574, 308, 14);

		frmGhealth.getContentPane().add(lblNewLabel_3);

		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(20, 80, 570, 4);
		frmGhealth.getContentPane().add(panel);
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
		button.setBounds(223, 131, 188, 53);
		
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
		btnSignup.setBounds(223, 257, 188, 53);
		btnSignup.setIcon(Resources.getIcon("users.png"));
		frmGhealth.getContentPane().add(btnSignup);
		
		JButton button_1 = new JButton("\u05EA\u05DB\u05E0\u05D0\u05D9\u05DD");
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setEnabled(false);
		button_1.setBorder(null);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(10, 131, 188, 53);
		button_1.setIcon(Resources.getIcon("treatment.png"));
		frmGhealth.getContentPane().add(button_1);
		setPermissions();
	}

	private void disableAllButtons() {
		labsBtn.setEnabled(false);
		btnUsers.setEnabled(false);
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
			addDescBtn.setEnabled(true);
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
		btnExaminations.setBounds(441, 193, 188, 53);
		btnExaminations.setIcon(Resources.getIcon("lab.png"));
		frmGhealth.getContentPane().add(btnExaminations);

		btnResults.setHorizontalAlignment(SwingConstants.LEFT);
		btnResults.setForeground(Color.BLACK);
		btnResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResults.setBorder(null);
		btnResults.setBackground(Color.WHITE);
		btnResults.setBounds(10, 193, 188, 53);
		btnResults.setIcon(Resources.getIcon("treatment.png"));
		frmGhealth.getContentPane().add(btnResults);
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UsersManagingUI();
			}
		});
		btnUsers.setToolTipText("Users managment form");

		btnUsers.setBounds(441, 257, 188, 53);
		btnUsers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUsers.setBackground(Color.WHITE);
		btnUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsers.setForeground(Color.BLACK);
		btnUsers.setBorder(null);
		btnUsers.setIcon(Resources.getIcon("users.png"));
		btnUsers.setBorder(null);

		frmGhealth.getContentPane().add(btnUsers);
		labsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			//	MonthlyReport mReport = new MonthlyReport();

			//	mReport.getFrame().setVisible(true);
			}
		});

		labsBtn.setHorizontalAlignment(SwingConstants.LEFT);
		labsBtn.setForeground(Color.BLACK);
		labsBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labsBtn.setBorder(null);
		labsBtn.setBackground(Color.WHITE);
		labsBtn.setBounds(441, 131, 188, 53);
		labsBtn.setIcon(Resources.getIcon("tests.png"));

		frmGhealth.getContentPane().add(labsBtn);
		logoLbl.setBackground(Color.WHITE);
		logoLbl.setBounds(10, 11, 284, 60);
		logoLbl.setFont(new Font(Logo.getLogofont(), Font.BOLD, Logo.getLogosize()));
		logoLbl.setIcon(Resources.getIcon(Logo.getLogoUrl()));
		frmGhealth.getContentPane().add(logoLbl);
		lblNewLabel_1.setIcon(Resources.getIcon("user.png"));
		frmGhealth.getContentPane().add(lblNewLabel_1);
		addDescBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddNote();
				
			}
		});
		addDescBtn.setIcon(new ImageIcon(ClientUI.class.getResource("/img/info.png")));
		addDescBtn.setHorizontalAlignment(SwingConstants.LEFT);
		addDescBtn.setForeground(Color.BLACK);
		addDescBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addDescBtn.setBorder(null);
		addDescBtn.setBackground(Color.WHITE);
		addDescBtn.setBounds(223, 193, 188, 53);
		frmGhealth.getContentPane().add(addDescBtn);

	}
}
