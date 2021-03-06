package ui.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;

import Client.Application;
import Client.Resources;
import Controllers.UsersController;
import models.User;
import ui.utils.Logo;
import ui.utils.UITests;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.SwingConstants;
import javax.swing.text.IconView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SignInUI {

	private JFrame SignInUI;
	private JPasswordField passwordField;
	private JTextField idtextField;
	private int attempts = 0;
	private JButton btnLogIn = new JButton("Sign in");
	private JLabel labelDetails;

	JButton btnConnected;

	public SignInUI() {
		initialize();
		SignInUI.setVisible(true);
		SignInUI.setLocationRelativeTo(null);
		updateStatus();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void updateStatus() {
		if (Application.client.isConnected()) {
			btnConnected.setText("Connected, ready to signIn");
			btnLogIn.setEnabled(true);
		} else {
			btnConnected.setText("Unable to connect, click to configure server");
			btnLogIn.setEnabled(false);
		}
	}

	private void initialize() {
		SignInUI self = this;
		SignInUI = new JFrame();
		SignInUI.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
		SignInUI.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		SignInUI.setTitle("Sign In");
		SignInUI.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		SignInUI.setIconImage(icon);
		SignInUI.setForeground(Color.BLACK);
		SignInUI.setFont(new Font("Dialog", Font.PLAIN, 16));
		SignInUI.setBackground(Color.WHITE);
		SignInUI.getContentPane().setBackground(Color.WHITE);
		SignInUI.getContentPane().setLayout(null);

		btnConnected = new JButton("Connecting......");

		btnConnected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Settings(self);
			}
		});
		btnConnected.setIcon(new ImageIcon(SignInUI.class.getResource("/img/settings.png")));
		btnConnected.setToolTipText("Doctors managment form");
		btnConnected.setHorizontalAlignment(SwingConstants.LEFT);
		btnConnected.setForeground(Color.BLACK);
		btnConnected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConnected.setBorder(null);
		btnConnected.setBackground(Color.WHITE);
		btnConnected.setBounds(0, 350, 668, 35);
		SignInUI.getContentPane().add(btnConnected);
		
		JLabel logo = new JLabel (Logo.getLogoText());
		logo.setVerticalAlignment(SwingConstants.BOTTOM);
		logo.setBounds(166, 11, 306, 76);
		logo.setForeground(new Color(0, 0, 0));
		logo.setFont(new Font(Logo.getLogofont(), Font.BOLD, Logo.getLogosize()));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon(Logo.getLogoUrl()));
		SignInUI.getContentPane().add(logo);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(166, 100, 46, 14);
		SignInUI.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(136, 132, 76, 14);
		SignInUI.getContentPane().add(lblNewLabel_1);

		passwordField = new JPasswordField();

		passwordField.setBounds(212, 127, 200, 25);
		SignInUI.getContentPane().add(passwordField);

		idtextField = new JTextField();
		idtextField.setBounds(212, 95, 200, 25);
		SignInUI.getContentPane().add(idtextField);
		idtextField.setColumns(10);

		labelDetails = new JLabel("Please enter user Id and Password");
		labelDetails.setForeground(Color.RED);
		labelDetails.setBounds(225, 155, 271, 14);
		SignInUI.getContentPane().add(labelDetails);
		labelDetails.setVisible(false);
		SignInUI.setBounds(100, 100, 708, 414);
		SignInUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnLogIn.setBounds(212, 177, 200, 23);
		SignInUI.getContentPane().add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				signInHandler();
			}
		});

		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				signInHandler();

			}
		});
	}

	private void signInHandler() {
		labelDetails.setVisible(true);

		String pass = new String(passwordField.getPassword());

		if (UITests.notEmpty(idtextField.getText()) == false && UITests.notEmpty(pass) == false) {
			labelDetails.setText("*Please enter ID and password");
			return;
		} else if (UITests.notEmpty(idtextField.getText()) == false) {
			labelDetails.setText("*Please enter an ID");
			return;
		} else if (UITests.notEmpty(pass) == false) {
			labelDetails.setText("*Please enter a password");
			return;
		} else if (UITests.correctId(idtextField.getText()) == false) {
			labelDetails.setText("*Please enter 9 digits ID");
			return;
		}

		User u = UsersController.getUser(idtextField.getText());
		if (u == null) {
			labelDetails.setText("*User does not exist");
			return;
		}
		if (!UsersController.authinticateUser(u, pass)) {
			labelDetails.setText("*Wrong password or username");
			attempts++;
			if (attempts > 3) {
				UsersController.setLocked(u, true);
				labelDetails.setText("*Your account has been locked, contact admin");
				btnLogIn.setEnabled(false);
			}
			return;
		}

		if (!UsersController.setOnline(u)) {
			labelDetails.setText("*Unable to connect multiple users");
			return;
		}
		Application.user = u;
		ClientUI cui = new ClientUI();
		// SecretaryUI sec = new SecretaryUI();
		SignInUI.hide();
	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return SignInUI;
	}
}
